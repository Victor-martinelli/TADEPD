package com.mycompany.ej1;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.server.WrappedSession;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
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
public class SesionBoton extends UI {

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        final VerticalLayout layout = new VerticalLayout();
        
        Label titulo = new Label("EPD 5, EJ1 - SesionBoton");
        titulo.addStyleName("h1");
        
        TextField name = new TextField();
        name.setCaption("Introduzca su nombre:");
        name.setImmediate(true);
        Button button = new Button("Click Me");
        
        button.addClickListener( e -> {
           WrappedSession session = getSession().getSession();
           session.setAttribute("valorUsuario", name.getValue());
        });
                
        layout.addComponents(titulo, name, button);
        layout.setMargin(true);
        layout.setSpacing(true);
        
        setContent(layout);
    }

    @WebServlet(urlPatterns = "/*", name = "SesionBotonServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = SesionBoton.class, productionMode = false, heartbeatInterval = 10)
    public static class MyUIServlet extends VaadinServlet {
    }
}
