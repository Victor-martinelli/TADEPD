package com.mycompany.p2;

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
    
    public List<Trabajador> registroTrabajadores = new ArrayList<>();

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
        
        layoutRegistroTrabajadores.addComponents(nombreField, apellidoField, dniField, horaEntradaField, horaSalidaField, fecha);
          
        //Layout horizotan para incluir los botones necesarios para realizar el 
        //registo y listado de los trabajadores.
        HorizontalLayout layoutBotones = new HorizontalLayout();
        layoutBotones.setSpacing(true);
        
        Button buttonRegistrar = new Button("Registrar");
        Button buttonListado = new Button("Listado trabajadores");
        
        layoutBotones.addComponents(buttonRegistrar, buttonListado);  
        
        Label tituloListado = new Label("<h2>Trabajadores registrados</h2>", ContentMode.HTML); 
        
        //Layout vertical para mostrar el listado de los trabajadores previamente registrados.
        VerticalLayout layoutListado = new VerticalLayout();
        layoutListado.setSpacing(true);        
        
        layout.addComponents(layoutRegistroTrabajadores, layoutBotones, tituloListado, layoutListado);
        
        buttonRegistrar.addClickListener(new Button.ClickListener(){
            public void buttonClick(ClickEvent event){
                Trabajador trabajador = new Trabajador(nombreField.getValue(), apellidoField.getValue(), dniField.getValue(), horaEntradaField.getValue(), horaSalidaField.getValue(), fecha.getValue());
                registroTrabajadores.add(trabajador);    
                
                nombreField.clear();
                apellidoField.clear();
                dniField.clear();
                horaEntradaField.clear();
                horaSalidaField.clear();
                fecha.clear();                
            }       
        });
        
        buttonListado.addClickListener(new Button.ClickListener(){
            public void buttonClick(ClickEvent event){
                layoutListado.removeAllComponents();
                Label label;
                
                for(Trabajador trabajador : registroTrabajadores){
                    label = new Label(trabajador.toString());
                    layoutListado.addComponent(label);
                }                               
            }       
        });      
        
        setContent(layout);
    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
