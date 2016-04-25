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
 * Classes and interfaces used internally. In general clients are
 * discouraged to use the elements in this package directly. To be
 * more specific:
 * <ul>
 *     <li>Clients should not create objects of classes in this package</li>
 *     <li>Clients should not implements interfaces of this package</li>
 *  <li>Clients can use subclasses and subinterfaces which extend elements of this package
 *  but are not declared within this package</li>
 *  <li>Clients can use the methods and constants offered in this package</li>
 *  <li>These rules are not enforced in any way. Clients can use elements of this package,
 *  it may just not be the easiest solution</li>
 * </ul>
 */
package bibliothek.gui.dock.common.intern;