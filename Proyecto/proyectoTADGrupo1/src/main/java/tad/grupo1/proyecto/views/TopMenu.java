package tad.grupo1.proyecto.views;

import java.util.HashMap;
import java.util.Map;

import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.server.FileResource;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Page;
import com.vaadin.server.Resource;
import com.vaadin.server.ThemeResource;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.MenuBar.Command;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;
import java.io.File;
import tad.grupo1.proyecto.controllers.GeneralController;

/**
 * Responsive navigation menu presenting a list of available views to the user.
 */
public class TopMenu extends CssLayout {

    private Navigator navigator;
    private GeneralController gc = new GeneralController();
    private HorizontalLayout searchBar = new HorizontalLayout();

    public TopMenu() {
        searchBar.setMargin(false);
        searchBar.setSpacing(false);
        addComponent(searchBar);
    }

    public HorizontalLayout getTopBar() {
        return searchBar;
    }
    

    
}
