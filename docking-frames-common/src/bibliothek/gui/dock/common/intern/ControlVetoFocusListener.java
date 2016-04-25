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

package bibliothek.gui.dock.common.intern;

import bibliothek.gui.DockController;
import bibliothek.gui.Dockable;
import bibliothek.gui.dock.common.CControl;
import bibliothek.gui.dock.common.event.CVetoFocusListener;
import bibliothek.gui.dock.control.focus.FocusController;
import bibliothek.gui.dock.event.FocusVetoListener;
import bibliothek.gui.dock.title.DockTitle;
import bibliothek.util.FrameworkOnly;

/**
 * This listener observes a {@link DockController} and forwards
 * all calls to a {@link CVetoFocusListener}.
 * @author Benjamin Sigg
 */
@FrameworkOnly
public class ControlVetoFocusListener implements FocusVetoListener{
    private CControl control;
    private CVetoFocusListener callback;

    /**
     * Creates a new veto focus listener.
     * @param control the control in whose realm this listener operates
     * @param callback the callback to be called if an event is triggered
     */
    public ControlVetoFocusListener( CControl control, CVetoFocusListener callback ){
        this.control = control;
        this.callback = callback;
    }

    private FocusVeto veto( Dockable dockable ){
        Dockable current = control.intern().getController().getFocusedDockable();

        if( current != dockable ){
            if( current instanceof CommonDockable ){
                if( !callback.willLoseFocus( ((CommonDockable)current).getDockable() )) {
                    return FocusVeto.VETO;
                }
            }

            if( dockable instanceof CommonDockable ){
                if( !callback.willGainFocus( ((CommonDockable)dockable).getDockable() )) {
                    return FocusVeto.VETO;
                }
            }
        }
        return FocusVeto.NONE;
    }

    public FocusVeto vetoFocus( FocusController controller, DockTitle title ){
        return veto( title.getDockable() );
    }

    public FocusVeto vetoFocus( FocusController controller, Dockable dockable ){
        return veto( dockable );
    }
}
