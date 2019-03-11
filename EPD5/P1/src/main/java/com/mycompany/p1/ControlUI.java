package com.mycompany.p1;

import com.vaadin.annotations.Push;
import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.shared.communication.PushMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
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
@Push(PushMode.AUTOMATIC)
public class ControlUI extends UI implements Broadcaster.BroadcastListener {

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        Broadcaster.register(this);

        final VerticalLayout layout = new VerticalLayout();
        final TextField input = new TextField();
        layout.addComponent(input);
        Button send = new Button("Enviar");
        layout.addComponent(send);

        send.addClickListener(new ClickListener() {
            @Override
            public void buttonClick(ClickEvent event) {
                Broadcaster.broadcast(input.getValue()); // Broadcast the message
                input.setValue("");
            }
        });

        layout.setMargin(true);
        layout.setSpacing(true);
        
        setContent(layout);
    }

    @Override
    public void receiveBroadcast(final String message) {
        access(new Runnable() {
            @Override
            public void run() {
            }
        });
    }

    @WebServlet(urlPatterns = "/*", name = "ControlUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = ControlUI.class, productionMode = false)
    public static class ControlUIServlet extends VaadinServlet {
    }
}
