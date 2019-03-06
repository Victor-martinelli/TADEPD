package com.mycompany.ej3;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.DateField;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
        layout.setMargin(true);
        layout.setSpacing(true);

        //Layout horizotan para incluir los campos de registro de trabajadores.
        HorizontalLayout layoutRegistroTrabajadores = new HorizontalLayout();
        layoutRegistroTrabajadores.setSpacing(true);

        Label titulo = new Label("<h1>Gestión de entrada/salida al puesto de trabajo</h1>", ContentMode.HTML);
        layout.addComponent(titulo);

        TextField nombreField = new TextField();
        nombreField.setCaption("Nombre: ");

        TextField apellidoField = new TextField();
        apellidoField.setCaption("Apellidos: ");

        TextField dniField = new TextField();
        dniField.setCaption("DNI: ");

        TextField horaEntradaField = new TextField();
        horaEntradaField.setCaption("Hora entrada (hh:mm): ");

        TextField horaSalidaField = new TextField();
        horaSalidaField.setCaption("Hora salida (hh:mm): ");

        DateField fecha = new DateField();
        fecha.setCaption("Fecha: ");
        fecha.setDateFormat ("dd-MM-aaaa");


        layoutRegistroTrabajadores.addComponents(nombreField, apellidoField, dniField, horaEntradaField, horaSalidaField, fecha);
 
        Label tituloListado = new Label("<h2>Trabajadores registrados</h2>", ContentMode.HTML); 

        Button buttonRegistrar = new Button("Registrar");

        Table listadoTrabajadores = new Table();
        listadoTrabajadores.setWidth(100, Unit.PERCENTAGE);
        listadoTrabajadores.setPageLength(listadoTrabajadores.size());
        listadoTrabajadores.addContainerProperty("Nombre", String.class, null);
        listadoTrabajadores.addContainerProperty("Apellidos", String.class, null);
        listadoTrabajadores.addContainerProperty("DNI", String.class, null);
        listadoTrabajadores.addContainerProperty("Hora entrada", String.class, null);
        listadoTrabajadores.addContainerProperty("Hora salida", String.class, null);
        listadoTrabajadores.addContainerProperty("Fecha", Date.class, null);
        
        layout.addComponents(layoutRegistroTrabajadores, buttonRegistrar, tituloListado, listadoTrabajadores);

        buttonRegistrar.addClickListener(new Button.ClickListener() {
            public void buttonClick(ClickEvent event) {
                listadoTrabajadores.addItem(new Object[]{nombreField.getValue(), apellidoField.getValue(), dniField.getValue(), horaEntradaField.getValue(), horaSalidaField.getValue(), fecha.getValue()}, null);

                nombreField.clear();
                apellidoField.clear();
                dniField.clear();
                horaEntradaField.clear();
                horaSalidaField.clear();
                fecha.clear();
            }
        });       

        setContent(layout);
    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
