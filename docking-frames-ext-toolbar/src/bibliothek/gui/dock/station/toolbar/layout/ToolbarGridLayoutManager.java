package bibliothek.gui.dock.station.toolbar.layout;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.LayoutManager2;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.SwingUtilities;

import bibliothek.gui.DockController;
import bibliothek.gui.Dockable;
import bibliothek.gui.Orientation;
import bibliothek.gui.dock.ToolbarGroupDockStation;
import bibliothek.gui.dock.station.span.SpanFactory;
import bibliothek.gui.dock.station.support.PlaceholderListItem;
import bibliothek.gui.dock.station.toolbar.group.ToolbarGroupSpanStrategy;

/**
 * This {@link LayoutManager2} orders a set of {@link Component}s in columns. To
 * decide which component belongs to which column, the contents of a
 * {@link DockablePlaceholderToolbarGrid} are used.
 * 
 * @author Benjamin Sigg
 */
public abstract class ToolbarGridLayoutManager<P extends PlaceholderListItem<Dockable>> implements LayoutManager2 {
	/** the list defining the layout of the components */
	private final DockablePlaceholderToolbarGrid<P> grid;

	/** how to align the components */
	private final Orientation orientation;

	/** the {@link Container} which is using this {@link LayoutManager} */
	private Container parent;
	
	/** tells the size of gaps between columns and lines */
	private ToolbarGroupSpanStrategy<P> spans;

	private enum Size {
		MAXIMUM, MINIMUM, PREFERRED;

		public Dimension get( Component component ){
			switch( this ){
				case MAXIMUM:
					return component.getMaximumSize();
				case MINIMUM:
					return component.getMinimumSize();
				case PREFERRED:
					return component.getPreferredSize();
				default:
					throw new IllegalStateException();
			}
		}
	}

	/**
	 * Creates a new layout manager.
	 * @param parent the {@link Container} which is going to use this {@link LayoutManager}
	 * @param orientation the orientation, must not be <code>null</code>
	 * @param grid the list of items to lay out, must not be <code>null</code>
	 * @param station the station using this layout manager
	 */
	public ToolbarGridLayoutManager( Container parent, Orientation orientation, DockablePlaceholderToolbarGrid<P> grid, ToolbarGroupDockStation station ){
		if( orientation == null ) {
			throw new IllegalArgumentException( "orientation must not be null" );
		}
		if( grid == null ) {
			throw new IllegalArgumentException( "grid must not be null" );
		}
		if( parent == null ) {
			throw new IllegalArgumentException( "parent must not be null" );
		}

		this.orientation = orientation;
		this.grid = grid;
		this.parent = parent;
		this.spans = new ToolbarGroupSpanStrategy<P>( grid, station ){
			@Override
			protected void handleResized(){
				Container p = ToolbarGridLayoutManager.this.parent;
				if( p instanceof JComponent ){
					((JComponent)p).revalidate();
				}
			}
		};
	}
	
	/**
	 * Sets the {@link DockController} in whose realm this manager works. Allows this manager access to properties
	 * like the {@link SpanFactory}.
	 * @param controller the controller or <code>null</code>
	 */
	public void setController( DockController controller ){
		spans.setController( controller );
	}

	/**
	 * Closes all gaps for inserting items.
	 */
	public void mutate(){
		mutate( -1 );
	}
	
	/**
	 * Opens a gap for inserting an item into <code>column</code>.
	 * @param column the column where an item is inserted
	 */
	public void mutate( int column ){
		spans.mutate( column );
	}
	
	/**
	 * Opens a gap for inserting an item into <code>column</code> at <code>line</code>.
	 * @param column the column into which an item is inserted
	 * @param line the location of the new item
	 */
	public void mutate( int column, int line ){
		spans.mutate( column, line );
	}
	
	/**
	 * Converts <code>item</code> into a {@link Component}, this
	 * {@link LayoutManager} will then set the location and size of the
	 * resulting component.
	 * 
	 * @param item
	 *            the item to convert
	 * @return the {@link Component} whose position and size will be set
	 */
	protected abstract Component toComponent( P item );

	/**
	 * Gets an array of columns, where each column is an array of
	 * {@link Component}s.
	 * 
	 * @return all children sorted into the columns
	 */
	@SuppressWarnings("unchecked")
	protected Wrapper[][] layout(){
		final Wrapper[][] components = new ToolbarGridLayoutManager.Wrapper[grid.getColumnCount()][];
		for( int i = 0; i < components.length; i++ ) {
			final List<Wrapper> list = new ArrayList<Wrapper>();
			final Iterator<P> iter = grid.getColumnContent( i );
			while( iter.hasNext() ) {
				list.add( new Wrapper( toComponent( iter.next() ) ) );
			}
			components[i] = list.toArray( new ToolbarGridLayoutManager.Wrapper[list.size()] );
		}
		return components;
	}

	@Override
	public void addLayoutComponent( String name, Component comp ){
		// nothing to do
	}

	@Override
	public void removeLayoutComponent( Component comp ){
		// nothing to do
	}

	@Override
	public void addLayoutComponent( Component comp, Object constraints ){
		// nothing to do
	}

	/**
	 * Tells which column covers the coordinate <code>location</code>. If the location is between columns, then the
	 * result can be either of these columns.
	 * @param location a point on the axis that is orthogonal to the orientation of the columns
	 * @return the column covering <code>location</code> or -1 if out of bounds
	 * @see #isColumnAt(int)
	 */
	public int getColumnAt( int location ){
		int index = 0;
		
		for( int i = 0, n = grid.getColumnCount(); i<n; i++ ){
			index = i;
			Rectangle bounds = getBounds( i );
			
			if( orientation == Orientation.VERTICAL ){
				if( bounds.x + bounds.width > location ){
					break;
				}
			}
			else{
				if( bounds.y + bounds.height > location ){
					break;
				}
			}
		}
		
		return index;
	}
	
	/**
	 * Tells whether there is a column covering <code>location</code>.
	 * @param location a point on the axis that is orthogonal to the orientation of the columns
	 * @return whether there is a column directly covering <code>location</code>
	 * @see #getColumnAt(int)
	 */
	public boolean isColumnAt( int location ){
		for( int i = 0, n = grid.getColumnCount(); i<n; i++ ){
			Rectangle bounds = getBounds( i );
			
			if( orientation == Orientation.VERTICAL ){
				if( bounds.x <= location && bounds.x + bounds.width > location ){
					return true;
				}
			}
			else{
				if( bounds.y <= location && bounds.y + bounds.height > location ){
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * Tells where to insert an item during a drag and drop operation, if the mouse is hovering over 
	 * the column <code>column</code>.
	 * @param column the column over which the mouse is hovering, must be an existing column
	 * @param location a point on the axis that is parallel to the orientation of the columns
	 * @return the best line to insert a new item or -1
	 */
	public int getInsertionLineAt( int column, int location ){
		int count = grid.getLineCount( column );
		for( int i = 0; i<count; i++ ){
			Rectangle bounds = getBounds( column, i );
			if( orientation == Orientation.HORIZONTAL ){
				if( bounds.x + bounds.width/2 >= location ){
					return i;
				}
			}
			else{
				if( bounds.y + bounds.height/2 >= location ){
					return i;
				}
			}
		}
		return count;
	}
	
	@Override
	public Dimension maximumLayoutSize( Container parent ){
		return layoutSize( parent, layout(), Size.MAXIMUM );
	}

	@Override
	public Dimension preferredLayoutSize( Container parent ){
		return layoutSize( parent, layout(), Size.PREFERRED );
	}

	@Override
	public Dimension minimumLayoutSize( Container parent ){
		return layoutSize( parent, layout(), Size.MINIMUM );
	}

	private Dimension layoutSize( Container parent, Wrapper[][] content, Size size ){
		spans.reset( false );
		
		int width = 0;
		int height = 0;

		if( orientation == Orientation.HORIZONTAL ) {
			int index = 0;
			for( final Wrapper[] column : content ) {
				final Dimension dim = layoutSize( index++, column, size );
				width = Math.max( width, dim.width );
				height += dim.height;
			}
			for( int i = 0; i <= content.length; i++ ){
				height += spans.getColumn( i );
			}
		}
		else {
			int index = 0;
			for( final Wrapper[] column : content ) {
				final Dimension dim = layoutSize( index++, column, size );
				height = Math.max( height, dim.height );
				width += dim.width;
			}
			for( int i = 0; i <= content.length; i++ ){
				width += spans.getColumn( i );
			}
		}

		Insets insets = parent.getInsets();
		Dimension result = new Dimension( width, height );
		if( insets != null ) {
			result.width += insets.left + insets.right;
			result.height += insets.top + insets.bottom;
		}
		return result;
	}

	private Dimension layoutSize( int columnIndex, Wrapper[] column, Size size ){
		int width = 0;
		int height = 0;

		if( orientation == Orientation.HORIZONTAL ) {
			for( final Wrapper item : column ) {
				item.reset( size );
				final Dimension dim = item.required;
				width += dim.width;
				height = Math.max( dim.height, height );
			}
			for( int i = 0; i <= column.length; i++ ){
				width += spans.getLine( columnIndex, i );
			}
		}
		else {
			for( final Wrapper item : column ) {
				item.reset( size );
				final Dimension dim = item.required;
				height += dim.height;
				width = Math.max( dim.width, width );
			}
			for( int i = 0; i <= column.length; i++ ){
				height += spans.getLine( columnIndex, i );
			}
		}

		return new Dimension( width, height );
	}

	@Override
	public void layoutContainer( Container parent ){
		final Wrapper[][] components = layout();
		final Dimension available = parent.getSize();
		final Dimension preferred = layoutSize( parent, components, Size.PREFERRED );
		if( (preferred.width <= available.width) && (preferred.height <= available.height) ) {
			layout( parent, components, preferred, available, Size.PREFERRED );
		}
		else {
			layout( parent, components, layoutSize( parent, components, Size.MINIMUM ), available, Size.MINIMUM );
		}
	}

	/**
	 * Layouts <code>components</code> such that they fit into
	 * <code>available</code>.
	 * 
	 * @param parent
	 *            the {@link Container} whose layout is upated
	 * @param components
	 *            the components to layout
	 * @param required
	 *            the size required for the optimal layout
	 * @param available
	 *            the size that is actually available
	 * @param size
	 *            which {@link Dimension} to get for layouting the components
	 */
	protected void layout( Container parent, Wrapper[][] components, Dimension required, Dimension available, Size size ){
		if( components.length == 0 || available.width < 1 || available.height < 1 ) {
			return;
		}

		final Dimension[] columns = new Dimension[components.length];
		for( int i = 0; i < columns.length; i++ ) {
			columns[i] = layoutSize( i, components[i], size );
		}

		Insets insets = parent.getInsets();

		if( orientation == Orientation.HORIZONTAL ) {
			double factor = available.height / (double) required.height;
			if( factor < 1 ){
				int sum = 0;
				for( int i = 0, n = columns.length - 1; i < n; i++ ) {
					columns[i].height = (int) (factor * columns[i].height);
					sum += columns[i].height;
				}
				columns[columns.length - 1].height = available.height - sum;
			}
			else{
				factor = 1;
			}
			int y = 0;
			if( insets != null ) {
				y = insets.top;
			}
			for( int i = 0; i < columns.length; i++ ) {
				y += spans.getColumn( i ) * factor;
				layout( components[i], i, columns[i], available, y, size );
				y += columns[i].height;
			}
		}
		else {
			double factor = available.width / (double) required.width;
			if( factor < 1 ) {
				int sum = 0;
				for( int i = 0, n = columns.length - 1; i < n; i++ ) {
					columns[i].width = (int) (factor * columns[i].width);
					sum += columns[i].width;
				}
				columns[columns.length - 1].width = available.width - sum;
			}
			else{
				factor = 1;
			}

			int x = 0;
			if( insets != null ) {
				x = insets.left;
			}
			for( int i = 0; i < columns.length; i++ ) {
				x += spans.getColumn( i ) * factor;
				layout( components[i], i, columns[i], available, x, size );
				x += columns[i].width;
			}
		}
	}

	private void layout( Wrapper[] column, int columnIndex, Dimension required, Dimension available, int startPoint, Size size ){
		if( orientation == Orientation.HORIZONTAL ) {
			double factor = available.width / (double) required.width;
			if( factor < 1 ){
				int sum = 0;
				for( int i = 0, n = column.length - 1; i < n; i++ ) {
					final Dimension dim = column[i].required;
					dim.width = (int) (dim.width * factor);
					sum += dim.width;
				}
				column[column.length - 1].required.width = available.width - sum;
			}
			else{
				factor = 1;
			}
			
			int x = 0;
			final int y = startPoint;

			for( int i = 0; i < column.length; i++ ) {
				x += spans.getLine( columnIndex, i ) * factor;
				column[i].component.setBounds( x, y, column[i].required.width, required.height );
				x += column[i].required.width;
			}
		}
		else {
			double factor = available.height / (double) required.height;
			if( factor < 1 ){
				int sum = 0;
				for( int i = 0, n = column.length - 1; i < n; i++ ) {
					final Dimension dim = column[i].required;
					dim.height = (int) (dim.height * factor);
					sum += dim.height;
				}
				column[column.length - 1].required.height = available.height - sum;
			}
			else{
				factor = 1;
			}

			final int x = startPoint;
			int y = 0;

			for( int i = 0; i < column.length; i++ ) {
				y += spans.getLine( columnIndex, i ) * factor;
				column[i].component.setBounds( x, y, required.width, column[i].required.height );
				y += column[i].required.height;
			}
		}
	}

	@Override
	public float getLayoutAlignmentX( Container target ){
		return 0.5f;
	}

	@Override
	public float getLayoutAlignmentY( Container target ){
		return 0.5f;
	}

	@Override
	public void invalidateLayout( Container target ){
		// nothing to do
	}

	/**
	 * Calculates the current boundaries of the cell at <code>column/line</code>
	 * .
	 * 
	 * @param column
	 *            the column in which to search the cell
	 * @param line
	 *            the row in <code>column</code>
	 * @return the boundaries, does not include any borders or offsets
	 * @throws IllegalArgumentException
	 *             if there is no cell at <code>column/line</code>
	 */
	public Rectangle getBounds( int column, int line ){
		Wrapper[][] cells = layout();
		if( column < 0 || column >= cells.length ) {
			throw new IllegalArgumentException( "column out of bounds: " + column );
		}
		if( line < 0 || line >= cells[column].length ) {
			throw new IllegalArgumentException( "line out of bounds: " + line );
		}
		return cells[column][line].getBounds();
	}

	public Rectangle getScreenBounds( int column, int line ){
		Wrapper[][] cells = layout();
		if( column < 0 || column >= cells.length ) {
			throw new IllegalArgumentException( "column out of bounds: " + column );
		}
		if( line < 0 || line >= cells[column].length ) {
			throw new IllegalArgumentException( "line out of bounds: " + line );
		}
		return cells[column][line].getScreenBounds();
	}

	/**
	 * Gets the boundaries of the column at index <code>column</code>. This
	 * method returns the union of the boundaries of all cells, meaning if there
	 * are gaps between the cells they are included in the boundaries. But
	 * excess space around the cells (e.g. because other columns are bigger) is
	 * ignored by this method.
	 * 
	 * @param column
	 *            the column whose boundaries are searched
	 * @return the boundaries or <code>null</code> if the column
	 *         <code>column</code> does not have any cells. This however is a
	 *         special case that should not appear in a live application and
	 *         indicates a bigger issue.
	 * @throws IllegalArgumentException
	 *             if <code>column</code> is out of bounds
	 */
	public Rectangle getBounds( int column ){
		Wrapper[][] cells = layout();
		return getBounds( column, cells );
	}

	public Rectangle getScreenBounds( int column ){
		Wrapper[][] cells = layout();
		return getScreenBounds( column, cells );
	}

	private Rectangle getBounds( int column, Wrapper[][] cells ){
		if( column < 0 || column >= cells.length ) {
			throw new IllegalArgumentException( "column out of bounds: " + column );
		}

		Rectangle result = null;
		for( Wrapper cell : cells[column] ) {
			if( result == null ) {
				result = cell.getBounds();
			}
			else {
				result = result.union( cell.getBounds() );
			}
		}
		
		int left = spans.getLine( column, 0 );
		if( orientation == Orientation.HORIZONTAL ){
			result.x -= left;
			result.width += left;
		}
		else{
			result.y -= left;
			result.height += left;
		}
		
		int lines = cells[column].length;
		if( lines > 0 ){
			int right = spans.getLine( column, lines );
			if( orientation == Orientation.HORIZONTAL ){
				result.width += right;
			}
			else{
				result.height += right;
			}	
		}
		
		return result;
	}

	private Rectangle getScreenBounds( int column, Wrapper[][] cells ){
		if( column < 0 || column >= cells.length ) {
			throw new IllegalArgumentException( "column out of bounds: " + column );
		}

		Rectangle result = null;
		for( Wrapper cell : cells[column] ) {
			if( result == null ) {
				result = cell.getScreenBounds();
			}
			else {
				result = result.union( cell.getScreenBounds() );
			}
		}
		return result;
	}

	/**
	 * Gets the boundaries of the gab between the column <code>column-1</code>
	 * and <code>column</code>. This is the gap of <code>column</code> to its
	 * left or top neighbor. The gap may have a width or height of
	 * <code>0</code>, meaning the gap is not visible to the user. If
	 * <code>column</code> is <code>0</code>, then the gap to the left or top
	 * end of the {@link Container} using this {@link LayoutManager} is
	 * returned. If <code>column</code> is equal to the total number of columns,
	 * the gap to the right or bottom end of the {@link Container} is returned.<br>
	 * If both columns do not use up all available space, then the result of
	 * this method does have a width and a height smaller then the width and
	 * height of the {@link Container}.
	 * 
	 * @param column
	 *            the column whose left gap is searched
	 * @return the gap, <code>null</code> if one columns has no cells. This
	 *         however is a case that should not appear in a live client and
	 *         that indicates a bigger issue.
	 * @throws IllegalArgumentException
	 *             if <code>column</code> is out of bounds
	 */
	public Rectangle getGapBounds( int column ){
		Wrapper[][] cells = layout();
		Rectangle left;
		Insets insets = parent.getInsets();

		if( column == 0 ) {
			left = new Rectangle( insets.left, insets.right, 0, 0 );
		}
		else {
			left = getBounds( column - 1, cells );
		}

		Rectangle right = null;
		if( column == cells.length ) {
			right = new Rectangle( insets.left, insets.right, 0, 0 );
			if( orientation == Orientation.HORIZONTAL ) {
				right.y = parent.getHeight() - insets.bottom;
			}
			else {
				right.x = parent.getWidth() - insets.right;
			}
		}
		else {
			right = getBounds( column, cells );
		}

		if( left == null || right == null ) {
			return null;
		}

		if( orientation == Orientation.HORIZONTAL ) {
			int x = Math.min( left.x, right.x );
			int y = left.y + left.height;
			int width = Math.max( left.x + left.width, right.x + right.width ) - x;
			int height = right.y - y;
			return new Rectangle( x, y, width, height );
		}
		else {
			int x = left.x + left.width;
			int y = Math.min( left.y, right.y );
			int width = right.x - x;
			int height = Math.max( left.y + left.height, right.y + right.height ) - y;
			return new Rectangle( x, y, width, height );
		}
	}

	/**
	 * Gets the size of a gap between two rows.
	 * @param column the column in which the rows are
	 * @param line the row into which an item is inserted
	 * @return the gap between <code>line-1</code> and <code>line</code>
	 */
	public Rectangle getGapBounds( int column, int line ){
		Wrapper[][] cells = layout();
		int gap = spans.getLine( column, line );
		if( orientation == Orientation.HORIZONTAL ){
			int y = Integer.MAX_VALUE;
			int height = 0;
			for( Wrapper cell : cells[column] ){
				Rectangle bounds = cell.getBounds();
				y = Math.min( y, bounds.y );
				height = Math.max( height, y - bounds.y + bounds.height );
			}
			if( line == 0 ){
				return new Rectangle( cells[column][line].getBounds().x-gap, y, gap, height );
			}
			else{
				Rectangle bounds = cells[column][line-1].getBounds();
				return new Rectangle( bounds.x + bounds.width, y, gap, height );
			}
		}
		else{
			int x = Integer.MAX_VALUE;
			int width = 0;
			for( Wrapper cell : cells[column] ){
				Rectangle bounds = cell.getBounds();
				x = Math.min( x, bounds.x );
				width = Math.max( width, x - bounds.x + bounds.width );
			}
			if( line == 0 ){
				return new Rectangle( x, cells[column][line].getBounds().y-gap, width, gap );
			}
			else{
				Rectangle bounds = cells[column][line-1].getBounds();
				return new Rectangle( x, bounds.y + bounds.height, width, gap );
			}
		}
	}
	
	/**
	 * A wrapper around one {@link Component}, caches minimal, maximal or
	 * preferred size.
	 * 
	 * @author Benjamin Sigg
	 */
	protected class Wrapper {
		public Component component;
		public Dimension required;
		private Size size;

		public Wrapper( Component component ){
			this.component = component;
		}

		/**
		 * Resets the size constraints of this item.
		 * 
		 * @param size
		 *            the kind of size that should be used as constraint
		 */
		public void reset( Size size ){
			if( this.size != size ) {
				this.size = size;
				required = new Dimension( size.get( component ) );
			}
		}

		/**
		 * Gets the current boundaries of this cell.
		 * 
		 * @return the current boundaries, never <code>null</code>
		 */
		public Rectangle getBounds(){
			return component.getBounds();
		}

		/**
		 * Gets the current boundaries of this cell in the screen coordinates
		 * 
		 * @return the current boundaries, never <code>null</code>
		 */
		public Rectangle getScreenBounds(){
			Rectangle bounds = component.getBounds();
			Point origin = bounds.getLocation();
			SwingUtilities.convertPointToScreen( origin, component );
			Rectangle screenBounds = new Rectangle( origin, new Dimension( bounds.width, bounds.height ) );
			return screenBounds;
		}
	}
}
