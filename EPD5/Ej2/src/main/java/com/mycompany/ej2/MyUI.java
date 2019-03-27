package com.mycompany.ej2;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.Label;
import com.vaadin.ui.Tree;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.VerticalSplitPanel;

/**
 * This UI is the application entry point. A UI may either represent a browser
 * window (or tab) or some part of a html page where a Vaadin application is
 * embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is
 * intended to be overridden to add component to the user interface and
 * initialize non-component functionality.
 */
@Theme("mytheme")
public class MyUI extends UI {

    @Override
    protected void init(VaadinRequest vaadinRequest) {

        final VerticalSplitPanel splitPanelVertical = new VerticalSplitPanel();
        final VerticalSplitPanel subPanelHorizontal = new VerticalSplitPanel();
        final HorizontalSplitPanel splitPanelHorizontal = new HorizontalSplitPanel();
        final VerticalLayout layoutIzquierda = new VerticalLayout();
        final HorizontalLayout subLayoutArriba = new HorizontalLayout();
        final HorizontalLayout subLayoutAbajo = new HorizontalLayout();
        final HorizontalLayout layoutArriba = new HorizontalLayout();

        final Object[][] planets = new Object[][]{
            new Object[]{"Mercury"},
            new Object[]{"Venus"},
            new Object[]{"Earth", "The Moon"},
            new Object[]{"Mars", "Phobos", "Deimos"},
            new Object[]{"Jupiter", "Europa", "Ganimedes", "Io", "Calisto"},
            new Object[]{"Saturn", "Titán", "Dione"},
            new Object[]{"Urano", "Titania", "Umbriel"},
            new Object[]{"Neptune", "Triton", "Galatea"},};

        Tree tree = new Tree("The Planets and Major Moons");

        for (int i = 0; i < planets.length; i++) {
            String planet = (String) (planets[i][0]);
            tree.addItem(planet);

            if (planets[i].length == 1) {
                tree.setChildrenAllowed(planet, false);
            } else {
                for (int j = 1; j < planets[i].length; j++) {
                    String moon = (String) planets[i][j];
                    tree.addItem(moon);
                    tree.setParent(moon, planet);
                    tree.setChildrenAllowed(moon, false);
                }
                tree.expandItemsRecursively(planet);
            }
        }
        
        setSizeFull();
        setContent(splitPanelVertical);
        layoutArriba.setSizeFull();
        Label h1 = new Label("The Ultimate Cat Finder");
        h1.addStyleName("h1");
        layoutArriba.addComponent(h1);
        
        layoutIzquierda.addComponent(tree);
        
        Label detail = new Label("<b>Details</b>",ContentMode.HTML);
        subLayoutArriba.addComponent(detail);
        
        Label label1 = new Label("<h1>Where is the cat?</h1>",ContentMode.HTML);
        subLayoutAbajo.addComponent(label1);
        
        Label label2 = new Label("<h1>I don't know!</h1>",ContentMode.HTML);
        subLayoutAbajo.addComponent(label2);
        
        subPanelHorizontal.setFirstComponent(subLayoutArriba);
        subPanelHorizontal.setSecondComponent(subLayoutAbajo);
        subPanelHorizontal.setSplitPosition(10, Unit.PERCENTAGE);
        
        splitPanelHorizontal.setFirstComponent(layoutIzquierda);
        splitPanelHorizontal.setSecondComponent(subPanelHorizontal);
        splitPanelHorizontal.setSplitPosition(17, Unit.PERCENTAGE);
        
        
        splitPanelVertical.setFirstComponent(layoutArriba);
        splitPanelVertical.setSecondComponent(splitPanelHorizontal);
        splitPanelVertical.setSplitPosition(25, Unit.PERCENTAGE);

    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
