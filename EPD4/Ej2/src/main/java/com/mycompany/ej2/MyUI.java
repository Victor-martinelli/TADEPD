package com.mycompany.ej2;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.data.Property;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
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
public class MyUI extends UI {

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        final VerticalLayout layout = new VerticalLayout();

        //Mensaje de advertencia
        CheckBox checkboxWaening = new CheckBox("Warning message");
        checkboxWaening.addValueChangeListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                String value = event.getProperty().getValue().toString();
                Notification.show("WARNING message", "Value is: " + value, Notification.Type.WARNING_MESSAGE);
            }
        });
        
        //Mensaje por defecto
        CheckBox checkboxHumanized = new CheckBox("Humanized message");
        checkboxHumanized.addValueChangeListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                String value = event.getProperty().getValue().toString();
                Notification.show("HUMANIZED message", "Value is: " + value, Notification.Type.HUMANIZED_MESSAGE);
            }
        });
        
        //Mensaje de error
        CheckBox checkboxError = new CheckBox("Error message");
        checkboxError.addValueChangeListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                String value = event.getProperty().getValue().toString();
                Notification.show("ERROR message", "Value is: " + value, Notification.Type.ERROR_MESSAGE);
            }
        });
        
        //TRAY NOTIFICATION 
        CheckBox checkboxTray = new CheckBox("Tray notification");
        checkboxTray.addValueChangeListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                String value = event.getProperty().getValue().toString();
                Notification.show("TRAY NOTIFICATION", "Value is: " + value, Notification.Type.TRAY_NOTIFICATION);
            }
        });      

        layout.addComponents(checkboxWaening, checkboxHumanized, checkboxError, checkboxTray);
        layout.setMargin(true);
        layout.setSpacing(true);

        setContent(layout);
    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
