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

package bibliothek.gui.dock.station.stack.tab;

import bibliothek.gui.DockController;
import bibliothek.gui.Dockable;

/**
 * An observer added to a {@link TabPane}. This listener is informed when
 * elements are added and removed from the {@link TabPane}, and also
 * when the selection changes.
 * @author Benjamin Sigg
 */
public interface TabPaneListener {
    /**
     * Called after <code>dockable</code> has been added to <code>pane</code>.
     * @param pane the parent
     * @param dockable the new child
     */
    public void added( TabPane pane, Dockable dockable );

    /**
     * Called after <code>dockable</code> has been removed from <code>pane</code>.
     * @param pane the parent
     * @param dockable the removed child
     */
    public void removed( TabPane pane, Dockable dockable );

    /**
     * Called when the selection on <code>pane</code> has changed.
     * @param pane the {@link TabPane} whose selection changed
     */
    public void selectionChanged( TabPane pane );

    /**
     * Called if the {@link TabPane#getInfoComponent()} was replaced.
     * @param pane the source of this event
     * @param oldInfo the old info component, may be <code>null</code>
     * @param newInfo the new info component, may be <code>null</code>
     */
    public void infoComponentChanged( TabPane pane, LonelyTabPaneComponent oldInfo, LonelyTabPaneComponent newInfo );

    /**
     * Called if the {@link DockController} of <code>pane</code> has changed.
     * @param controller the new controller, can be <code>null</code>
     */
    public void controllerChanged( TabPane pane, DockController controller );
}
