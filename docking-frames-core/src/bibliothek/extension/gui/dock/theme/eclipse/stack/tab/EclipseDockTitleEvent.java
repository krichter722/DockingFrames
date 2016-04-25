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

package bibliothek.extension.gui.dock.theme.eclipse.stack.tab;

import bibliothek.gui.DockStation;
import bibliothek.gui.Dockable;
import bibliothek.gui.dock.title.ActivityDockTitleEvent;
import bibliothek.gui.dock.title.DockTitle;

/**
 * These events are fired by a {@link DockTitleTab} to its
 * {@link DockTitle} to indicate that some properties, normally not
 * used by a <code>DockTitle</code>, have changed.
 * @author Benjamin Sigg
 *
 */
public class EclipseDockTitleEvent extends ActivityDockTitleEvent {
    /** whether the tab is focused */
    private boolean focused;
    /** whether icons should be painted even when a tab is not selected */
    private boolean paintIconWhenInactive;

    /**
     * Creates a new event
     * @param station the station on which the tab lies, might be <code>null</code>
     * @param dockable the element for which the tab is shown
     * @param selected whether the tab is selected
     * @param focused whether the tab is focused
     * @param paintIconWhenInactive whether to paint icons when the tab is
     * not selected
     */
    public EclipseDockTitleEvent( DockStation station, Dockable dockable,
            boolean selected, boolean focused, boolean paintIconWhenInactive ){

        super( station, dockable, focused );
        this.focused = focused;
        setPreferred( selected && !focused );
        this.paintIconWhenInactive = paintIconWhenInactive;
    }

    /**
     * Tells whether the tab which fired this event is selected.
     * @return the selection state
     */
    public boolean isSelected(){
        return isActive();
    }

    /**
     * Tells whether the tab which fired this event is focused.
     * @return whether the content of the tab receives input events
     */
    public boolean isFocused() {
        return focused;
    }

    /**
     * Tells whether icons should be painted when the tab is not selected.
     * @return <code>true</code> if icons should always be painted
     */
    public boolean isPaintIconWhenInactive() {
        return paintIconWhenInactive;
    }
}
