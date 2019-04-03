package com.mycompany.p1;

import com.vaadin.server.FontAwesome;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.themes.ValoTheme;

@SuppressWarnings({ "serial", "unchecked" })
public final class DashboardMenu extends CustomComponent {

    public static final String ID = "dashboard-menu";
    public static final String REPORTS_BADGE_ID = "dashboard-menu-reports-badge";
    public static final String NOTIFICATIONS_BADGE_ID = "dashboard-menu-notifications-badge";

    public DashboardMenu() {
        setPrimaryStyleName("valo-menu");
        setId(ID);
        setSizeUndefined();

        setCompositionRoot(buildContent());
    }

    private Component buildContent() {
        final CssLayout menuContent = new CssLayout();
        menuContent.addStyleName("sidebar");
        menuContent.addStyleName(ValoTheme.MENU_PART);
        menuContent.addStyleName("no-vertical-drag-hints");
        menuContent.addStyleName("no-horizontal-drag-hints");
        menuContent.setWidth(null);
        menuContent.setHeight("100%");

        menuContent.addComponent(buildTitle());
        menuContent.addComponent(buildMenuItems());

        return menuContent;
    }

    private Component buildTitle() {
        Label logo = new Label("QuickTickets <strong>Dashboard</strong>", ContentMode.HTML);
        logo.setSizeUndefined();
        HorizontalLayout logoWrapper = new HorizontalLayout(logo);
        logoWrapper.setComponentAlignment(logo, Alignment.MIDDLE_CENTER);
        logoWrapper.addStyleName("valo-menu-title");
        return logoWrapper;
    }

    private Component buildMenuItems() {
        CssLayout menuItemsLayout = new CssLayout();
        menuItemsLayout.addStyleName("valo-menuitems");
        Component menuItemComponent = new ValoMenuItemButton("dashboard", FontAwesome.HOME);
        menuItemsLayout.addComponent(menuItemComponent);
        menuItemComponent = new ValoMenuItemButton("sales", FontAwesome.LINE_CHART);
        menuItemsLayout.addComponent(menuItemComponent);
        menuItemComponent = new ValoMenuItemButton("transactions", FontAwesome.EXCHANGE);
        menuItemsLayout.addComponent(menuItemComponent);
        menuItemComponent = new ValoMenuItemButton("reports", FontAwesome.PIE_CHART);
        menuItemsLayout.addComponent(menuItemComponent);
        menuItemComponent = new ValoMenuItemButton("schedule", FontAwesome.CALENDAR_O);
        menuItemsLayout.addComponent(menuItemComponent);
        return menuItemsLayout;

    }

    public final class ValoMenuItemButton extends Button {

        public ValoMenuItemButton(String name, FontAwesome icon) {
            setPrimaryStyleName("valo-menu-item");
            setIcon(icon);
            setCaption(name.substring(0, 1).toUpperCase() + name.substring(1));
        }
    }
}
