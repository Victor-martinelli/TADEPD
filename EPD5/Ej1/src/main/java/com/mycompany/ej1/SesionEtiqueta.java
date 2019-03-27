package com.mycompany.ej1;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.server.WrappedSession;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

/**
 * This UI is the application entry point. A UI may either represent a browser window 
 * (or tab) or some part of a html page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be 
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@Theme("mytheme")
public class SesionEtiqueta extends UI {

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        final VerticalLayout layout = new VerticalLayout();
        
        WrappedSession session = getSession().getSession();
        
        Label titulo = new Label("EPD 5, EJ1 - SesionEtiqueta");
        titulo.addStyleName("h1");
        
        Label label = new Label("Nombre introducido: " + (String) session.getAttribute("valorUsuario"));
        
        layout.addComponents(titulo, label);
        layout.setMargin(true);
        layout.setSpacing(true);
        
        setContent(layout);
    }

    @WebServlet(urlPatterns = "/SesionEtiqueta/*", name = "SesionEtiquetaServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = SesionEtiqueta.class, productionMode = false, heartbeatInterval = 10)
    public static class MyUIServlet extends VaadinServlet {
    }
}
