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
 * Contains a set of different {@link bibliothek.gui.dock.action.DockAction}s
 * and supporting classes.<br>
 * The actions in this package will create views, which show {@link javax.swing.Icon}s,
 * text, tooltips and other gimmicks. They are designed to give a reasonable
 * subset of features a {@link javax.swing.JButton} or a similar
 * {@link javax.swing.JComponent} would offer.<br>
 * The actions in this package are divided in two groups:
 * <ul>
 * <li>The simple-actions have one set of properties which they use regardless
 * for which {@link bibliothek.gui.Dockable} they are shown.</li>
 * <li>The grouped-actions have several sets of properties. They decide for each
 * <code>Dockable</code> to which group it belongs, and then uses
 * the set of properties associated with that group. <code>Dockable</code>
 * can change its group at any time</li>
 * </ul>
 */
package bibliothek.gui.dock.action.actions;