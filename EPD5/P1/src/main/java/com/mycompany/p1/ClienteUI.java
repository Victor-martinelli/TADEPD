package com.mycompany.p1;

import com.vaadin.annotations.Push;
import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

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
@Push
public class ClienteUI extends UI implements Broadcaster.BroadcastListener {

    final VerticalLayout layout = new VerticalLayout();

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        Broadcaster.register(this);
        
        layout.addComponent(new Label("<h4>Mensajes Push:</h4>", ContentMode.HTML));
        
        layout.setMargin(true);
        layout.setSpacing(true);
        
        setContent(layout);

    }

    @Override
    public void receiveBroadcast(final String message) {
        access(new Runnable() {
            @Override
            public void run() {
                layout.addComponent(new Label(message));
            }
        });
    }

    @Override
    public void detach() {
        Broadcaster.unregister(this);
        super.detach();
    }

    @WebServlet(value = "/ClienteUI/*", name = "ClienteUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(productionMode = false, ui = ClienteUI.class)
    public static class ClienteUIServlet extends VaadinServlet {
    }

}
