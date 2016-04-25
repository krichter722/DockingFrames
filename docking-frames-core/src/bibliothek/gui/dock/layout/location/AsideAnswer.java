/*
 * Bibliothek - DockingFrames
 * Library built on Java/Swing, allows the user to "drag and drop"
 * panels containing any Swing-Component the developer likes to add.
 *
 * Copyright (C) 2012 Benjamin Sigg
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
package bibliothek.gui.dock.layout.location;

import bibliothek.gui.dock.layout.DockableProperty;
import bibliothek.gui.dock.station.support.PlaceholderMap;

/**
 * The answer to an {@link AsideRequest}.
 * @author Benjamin Sigg
 */
public interface AsideAnswer {
    /**
     * Tells whether the request was canceled.
     * @return whether the request was canceled
     */
    public boolean isCanceled();

    /**
     * Gets the location generated by the child station. This location <b>must not</b> be set as
     * {@link DockableProperty#setSuccessor(DockableProperty) successor}. A value of <code>null</code> indicates
     * the the child station did not produce a location.
     * @return the location generated by the child or <code>null</code>
     */
    public DockableProperty getLocation();

    /**
     * Gets the layout of a non-existent child station. A value of <code>null</code> either indicates that
     * the child station exists, or that it does not (no longer) have a layout worth saving.
     * @return the layour of the non-existent child, can be <code>null</code>
     */
    public PlaceholderMap getLayout();
}
