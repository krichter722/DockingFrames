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
 * Listeners, adapters and events used in the whole framework.<br>
 * <ul>
 *     <li>A <b>Listener</b> is added to some object that needs to be observed, the
 * Listener will be informed whenever the observed object changes.</li>
 *     <li>An <b>Adapter</b> is an implementation of a Listener, but the
 * methods of an Adapter are empty.</li>
 *     <li>An <b>Event</b> is an object carrying information from an observed
 * object to a Listener.</li>
 * </ul>
 */
package bibliothek.gui.dock.event;