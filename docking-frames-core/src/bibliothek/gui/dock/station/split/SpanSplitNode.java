/*
 * Bibliothek - DockingFrames
 * Library built on Java/Swing, allows the user to "drag and drop"
 * panels containing any Swing-Component the developer likes to add.
 *
 * Copyright (C) 2007 Benjamin Sigg
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301  USA
 *
 * Benjamin Sigg
 * benjamin_sigg@gmx.ch
 * CH - Switzerland
 */

package bibliothek.gui.dock.station.split;

import bibliothek.gui.dock.SplitDockStation;
import bibliothek.gui.dock.station.span.Span;
import bibliothek.util.FrameworkOnly;

/**
 * A {@link SplitNode} that also stores some {@link Span}s.
 * @author Benjamin Sigg
 */
public abstract class SpanSplitNode extends VisibleSplitNode {
    /** the spans at the four sides of this root */
    private Span[] spans;

    /**
     * Creates a new node.
     * @param access access ot the {@link SplitDockStation}
     * @param id a unique identifier for this node
     */
    protected SpanSplitNode( SplitDockAccess access, long id ){
        super( access, id );
        createSpans();
    }

    /**
     * Creates or re-creates the {@link Span}s used by this {@link Leaf}. This method should not be called
     * by clients.
     */
    @FrameworkOnly
    public void createSpans(){
        spans = getAccess().getSpanStrategy().createSpans( this );
    }

    /**
     * Gets the {@link Span}s that are currently used by this {@link Leaf}. This method should not be called
     * by clients.
     * @return the spans, can be <code>null</code>
     */
    @FrameworkOnly
    public Span[] getSpans(){
        return spans;
    }

    /**
     * Called if a {@link Span} of this node changed its size
     */
    @FrameworkOnly
    public abstract void onSpanResize();
}
