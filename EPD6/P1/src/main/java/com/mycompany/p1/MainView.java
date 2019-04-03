package com.mycompany.p1;

import com.vaadin.ui.HorizontalLayout;

@SuppressWarnings("serial")
public class MainView extends HorizontalLayout {

    public MainView(boolean isDebug) {
        setSizeFull();
        addStyleName("mainview");

        addComponent(new DashboardMenu());

        DashboardView content = new DashboardView();
        content.addStyleName("view-content");
        content.setSizeFull();
        addComponent(content);
        setExpandRatio(content, 1.0f);
    }
    
    private boolean equals(Object a, Object b) {
        return (a == b) || (a != null && a.equals(b));
    }
}
