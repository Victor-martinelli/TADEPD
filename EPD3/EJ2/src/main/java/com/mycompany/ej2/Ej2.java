package com.mycompany.ej2;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.DateField;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.HorizontalSplitPanel;
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
public class Ej2 extends UI {

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        final FormLayout formLayout = new FormLayout();
        final VerticalLayout datos = new VerticalLayout();
        final HorizontalSplitPanel layout = new HorizontalSplitPanel();

        TextField nombre = new TextField("Nombre");
        TextField apellidos = new TextField("Apellidos");
        DateField fNac = new DateField("Fecha de Nacimiento");

        CheckBox trabaja = new CheckBox("¿Trabaja?");
        trabaja.setValue(false);

        Button enviar1 = new Button("Enviar");
        enviar1.addClickListener(e -> {
            datos.removeAllComponents();
            boolean trab = trabaja.getValue();
            if (!trab) {
                datos.addComponents(new Label("Nombre: " + nombre.getValue()), new Label("Apellidos: " + apellidos.getValue()), new Label("Fecha de Nacimiento: " + fNac.getValue()), new Label("No trabaja"));
            } else {
                TextField sueldo = new TextField("Sueldo bruto mensual");
                TextField pagas = new TextField("Número de pagas");
                Button enviar2 = new Button("Enviar");
                formLayout.removeComponent(enviar1);
                formLayout.addComponents(sueldo, pagas, enviar2);
                enviar2.addClickListener(d -> {
                    datos.removeAllComponents();
                    datos.addComponents(new Label("Nombre: " + nombre.getValue()), new Label("Apellidos: " + apellidos.getValue()), new Label("Fecha de Nacimiento: " + fNac.getValue()), new Label("Sí trabaja"), new Label("Sueldo bruto al mes: " + sueldo.getValue()), new Label("Número de pagas: " + pagas.getValue()));
                    formLayout.removeComponent(sueldo);
                    formLayout.removeComponent(pagas);
                    formLayout.removeComponent(enviar2);
                    formLayout.addComponent(enviar1);
                });
            }
        });

        formLayout.addComponent(nombre);
        formLayout.addComponent(apellidos);
        formLayout.addComponent(fNac);
        formLayout.addComponent(trabaja);
        formLayout.addComponent(enviar1);

        formLayout.setMargin(true);
        formLayout.setSpacing(true);
        datos.setMargin(true);
        datos.setSpacing(true);
        layout.addComponents(formLayout, datos);
        setContent(layout);
    }

    @WebServlet(urlPatterns = "/*", name = "Ej2Servlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = Ej2.class, productionMode = false)
    public static class Ej2Servlet extends VaadinServlet {
    }
}
