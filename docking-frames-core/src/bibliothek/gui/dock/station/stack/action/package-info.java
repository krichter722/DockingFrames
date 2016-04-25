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

/**
 * Contains the {@link bibliothek.gui.dock.station.stack.action.DockActionDistributor} interface
 * which allows clients to tell where a {@link bibliothek.gui.dock.action.DockAction} appears, either
 * as part of a tab, of the info panel, or on the {@link bibliothek.gui.dock.title.DockTitle}.<br>
 * Clients can make use of the annotations {@link bibliothek.gui.dock.station.stack.action.InfoDockAction},
 * {@link bibliothek.gui.dock.station.stack.action.TabDockAction} and {@link bibliothek.gui.dock.station.stack.action.TitleDockAction}
 * to easily mark the location of their actions.
 */
package bibliothek.gui.dock.station.stack.action;