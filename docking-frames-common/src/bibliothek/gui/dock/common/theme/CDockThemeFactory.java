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

package bibliothek.gui.dock.common.theme;

import bibliothek.gui.DockController;
import bibliothek.gui.DockTheme;
import bibliothek.gui.dock.common.CControl;
import bibliothek.gui.dock.themes.ThemeFactory;
import bibliothek.gui.dock.themes.ThemeMeta;
import bibliothek.gui.dock.themes.ThemePropertyFactory;

/**
 * A factory that envelops another factory in order to build a
 * CX-theme instead of a X-theme.
 * @author Benjamin Sigg
 *
 * @param <D> the kind of theme that gets wrapped up
 */
public  abstract class CDockThemeFactory<D extends DockTheme> implements ThemeFactory{
    private ThemePropertyFactory<D> delegate;
    private CControl control;

    /**
     * Creates a new factory.
     * @param delegate the factory that should be used as delegate to create
     * the initial {@link DockTheme}.
     * @param control the control for which this factory will work
     */
    public CDockThemeFactory( ThemePropertyFactory<D> delegate, CControl control ){
        this.delegate = delegate;
        this.control = control;
    }

    /**
     * Gets the control for which this factory works.
     * @return the control
     */
    public CControl getControl() {
        return control;
    }

    public DockTheme create( DockController controller ) {
        if( control.getController() != controller ){
            throw new IllegalArgumentException( "the supplied controller does not match the CControl" );
        }
        return create( control );
    }

    /**
     * Creates a new theme.
     * @param control the control in whose realm the theme will be used
     * @return the new theme
     */
    public abstract DockTheme create( CControl control );

    public ThemeMeta createMeta( DockController controller ){
        ThemeMeta meta = delegate.createMeta( controller );
        meta.setFactory( this );
        return meta;
    }
}