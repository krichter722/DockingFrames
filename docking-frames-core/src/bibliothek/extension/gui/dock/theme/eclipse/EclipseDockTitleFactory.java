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

package bibliothek.extension.gui.dock.theme.eclipse;

import bibliothek.extension.gui.dock.theme.EclipseTheme;
import bibliothek.extension.gui.dock.theme.eclipse.EclipseThemeConnector.TitleBar;
import bibliothek.gui.Dockable;
import bibliothek.gui.dock.title.DockTitleFactory;
import bibliothek.gui.dock.title.DockTitleRequest;

/**
 * A {@link DockTitleFactory} that calls another factory but only
 * if the method {@link EclipseThemeConnector#getTitleBarKind(bibliothek.gui.DockStation, Dockable)} returns
 * {@link TitleBar#BASIC} for the {@link Dockable} whose title should be
 * created.
 * @author Benjamin Sigg
 *
 */
public class EclipseDockTitleFactory implements DockTitleFactory{
    /** the theme for which titles are created */
    private EclipseTheme theme;
    /** the factory that really does create the titles */
    private DockTitleFactory factory;

    /**
     * Creates a new factory
     * @param theme the theme whose {@link EclipseThemeConnector} is used
     * to determine whether to create a title for a {@link Dockable} or not.
     * @param factory the factory which creates title when necessary
     */
    public EclipseDockTitleFactory( EclipseTheme theme, DockTitleFactory factory ){
        if( theme == null ) {
            throw new IllegalArgumentException( "theme must not be null" );
        }

        if( factory == null ) {
            throw new IllegalArgumentException( "factory must not be null" );
        }

        this.theme = theme;
        this.factory = factory;
    }

    public void install( DockTitleRequest request ){
        factory.install( request );
    }

    public void uninstall( DockTitleRequest request ){
        factory.uninstall( request );
    }

    public void request( DockTitleRequest request ){
        TitleBar bar = theme.getThemeConnector( request.getVersion().getController() ).getTitleBarKind( request.getParent(), request.getTarget() );
        if( bar == TitleBar.BASIC || bar == TitleBar.BASIC_BORDERED ){
            factory.request( request );
        }
        else{
            request.answer( null );
        }
    }
}
