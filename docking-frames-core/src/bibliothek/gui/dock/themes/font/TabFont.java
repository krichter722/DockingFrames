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

package bibliothek.gui.dock.themes.font;

import bibliothek.gui.DockStation;
import bibliothek.gui.Dockable;
import bibliothek.gui.dock.StackDockStation;
import bibliothek.gui.dock.util.font.AbstractDockFont;
import bibliothek.gui.dock.util.font.FontManager;
import bibliothek.gui.dock.util.font.FontModifier;
import bibliothek.util.Path;

/**
 * A font used for a tab for example on a {@link StackDockStation}.
 * @author Benjamin Sigg
 */
public abstract class TabFont extends AbstractDockFont{
    /** the identifier of this kind of font */
    public final static Path KIND_TAB_FONT = KIND_DOCK_FONT.append( "tab" );

    /** the station for which this font is used */
    private DockStation station;
    /** the element with which this font is associated */
    private Dockable dockable;

    /**
     * Creates a new font
     * @param id the unique identifier of this font, should match some identifier
     * used in a {@link FontManager}.
     * @param station the station for which this font is used
     * @param dockable the element which is associated with this font
     * @param backup default value for this font
     */
    public TabFont(  String id, DockStation station, Dockable dockable, FontModifier backup ) {
        this( id, KIND_TAB_FONT, station, dockable, backup );
    }

    /**
     * Creates a new font
     * @param id the unique identifier of this font, should match some identifier
     * used in a {@link FontManager}.
     * @param kind the kind of font this represents, should be a child of
     * {@link #KIND_TAB_FONT}.
     * @param station the station for which this font is used
     * @param dockable the element which is associated with this font
     * @param backup default value for this font
     */
    public TabFont( String id, Path kind, DockStation station, Dockable dockable, FontModifier backup ) {
        super( id, kind, backup );
        this.station = station;
        this.dockable = dockable;
    }

    /**
     * Creates a new font
     * @param id the unique identifier of this font, should match some identifier
     * used in a {@link FontManager}.
     * @param kind the kind of font this represents, should be a child of
     * {@link #KIND_TAB_FONT}.
     * @param station the station for which this font is used
     * @param dockable the element which is associated with this font
     */
    public TabFont( String id, Path kind, DockStation station, Dockable dockable ) {
        this( id, kind, station, dockable, null );
    }

    /**
     * Creates a new font
     * @param id the unique identifier of this font, should match some identifier
     * used in a {@link FontManager}.
     * @param station the station for which this font is used
     * @param dockable the element which is associated with this font
     */
    public TabFont( String id, DockStation station, Dockable dockable ) {
        this( id, KIND_TAB_FONT, station, dockable, null );
    }

    /**
     * Gets the station for which this font is used.
     * @return the station
     */
    public DockStation getStation() {
        return station;
    }

    /**
     * Gets the element for which this font is used.
     * @return the element
     */
    public Dockable getDockable() {
        return dockable;
    }
}
