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

package bibliothek.gui.dock.control;

import java.awt.event.InputEvent;

import bibliothek.gui.DockController;
import bibliothek.gui.dock.accept.DockAcceptance;

/**
 * A {@link DockRelocatorMode} that adds itself as a {@link DockAcceptance}
 * to the {@link DockController} when activated.
 * @author Benjamin Sigg
 *
 */
public abstract class AcceptanceDockRelocatorMode implements DockRelocatorMode, DockAcceptance {
    /** whether this mode is active */
    private boolean active;

    /** which keys must be pressed in order to activate this mode */
    private ModifierMask mask;

    /**
     * Creates a new mode. The masks are created by using the
     * constants "xzy_DOWN_MASK" from {@link InputEvent}.
     * @param onmask the keys that must be pressed to activate this mode
     * @param offmask the keys that must not be pressed to activate this mode
     */
    public AcceptanceDockRelocatorMode( int onmask, int offmask ){
        mask = new ModifierMask( onmask, offmask );
    }

    /**
     * Creates a new mode.
     * @param mask the pattern of keys that must be pressed to activate this
     * mode
     */
    public AcceptanceDockRelocatorMode( ModifierMask mask ){
        if( mask == null ) {
            throw new IllegalArgumentException( "mask must not be null" );
        }

        this.mask = mask;
    }

    /**
     * Sets the keys that must be pressed in order to activate this mode.
     * @param mask the mask
     */
    public void setMask( ModifierMask mask ) {
        if( mask == null ) {
            throw new IllegalArgumentException( "mask must not be null" );
        }

        this.mask = mask;
    }

    /**
     * Gets the mask for this mode.
     * @return the mask
     */
    public ModifierMask getMask() {
        return mask;
    }

    public void setActive( DockController controller, boolean active ) {
        if( this.active != active ){
            if( active ) {
                controller.addAcceptance( this );
            } else {
                controller.removeAcceptance( this );
            }

            this.active = active;
        }
    }

    public boolean shouldBeActive( DockController controller, int modifiers ) {
        return mask.matches( modifiers );
    }
}
