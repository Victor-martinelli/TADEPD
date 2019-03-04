package com.mycompany.p1;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.OptionGroup;
import com.vaadin.ui.TextArea;
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
public class P1 extends UI {

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        final VerticalLayout formLayout = new VerticalLayout();
        final VerticalLayout datos = new VerticalLayout();
        final HorizontalSplitPanel layout = new HorizontalSplitPanel();

        Label producto = new Label("<h3><b>PRODUCTO</b></h3>", ContentMode.HTML);

        OptionGroup p1 = new OptionGroup("1 - ¿Conoce el producto/servicio?");
        p1.addItems("Sí", "No");

        OptionGroup p2 = new OptionGroup("2 - ¿Utiliza actualmente el producto/servicio?");
        p2.addItems("Sí", "No");

        OptionGroup p3 = new OptionGroup("3 - ¿Ha utilizado anteriormente el producto/servicio?");
        p3.addItems("Sí", "No");

        TextArea p4 = new TextArea("4 - Enumere por favor, las marcas que miraría");

        Button finalizar = new Button("Finalizar");
        finalizar.addClickListener(e -> {
            datos.removeAllComponents();
            Label d1 = new Label("<h3><b>RESUMEN</b></h3>", ContentMode.HTML);
            Label d2 = new Label("<b>¿Conoce el producto/servicio?:</b>  " + p1.getValue().toString(), ContentMode.HTML);
            Label d3 = new Label("<b>¿Utiliza actualmente el producto/servicio?:</b>  " + p2.getValue().toString(), ContentMode.HTML);
            Label d4 = new Label("<b>¿Ha utilizado anteriormente el producto/servicio?:</b>  " + p3.getValue().toString(), ContentMode.HTML);
            datos.addComponents(d1, d2, d3, d4);
            if (!p4.getValue().toString().equals("")) {
                Label d5 = new Label("<b>Marcas que miraría:</b>  " + p4.getValue().toString(), ContentMode.HTML);
                datos.addComponent(d5);
            }
            Notification.show("Datos Actualizados", Notification.Type.TRAY_NOTIFICATION);
        });

        formLayout.addComponents(producto, p1, p2, p3, p4, finalizar);

        formLayout.setMargin(true);
        formLayout.setSpacing(true);
        datos.setMargin(true);
        datos.setSpacing(true);
        layout.addComponents(formLayout, datos);
        setContent(layout);
    }

    @WebServlet(urlPatterns = "/*", name = "P1Servlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = P1.class, productionMode = false)
    public static class P1Servlet extends VaadinServlet {
    }
}
