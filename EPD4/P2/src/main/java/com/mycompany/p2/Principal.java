package com.mycompany.p2;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.event.ItemClickEvent;

import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Table;
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
public class Principal extends UI {
    
    private int movieId;
    
    @Override
    protected void init(VaadinRequest vaadinRequest) {
        
        movieId=1;
       //Pagina Principal
        final VerticalLayout layout = new VerticalLayout();
        
        String descPeliculas[] = {"Un joven llamado \"El Elegido\" pretende vengar la muerte de sus padres, quienes fueron asesinados por una leyenda del Kung Fu llamado \"El Maestro Dolor\". \n"
            + "Contiene escenas del film \"El tigre y los puños de grulla\" de 1976.", "La película sigue las aventuras de JB (Jack Black) y KG (Kyle Gass), componentes del grupo Tenacious D, mientras viajan por el tiempo y el espacio en busca de la llave de la puerta al estrellato.",
             "El juicio final está cada vez más cerca. Jesucristo ha vuelto al mundo para llevar a cabo una última misión, antes de juzgar a la Humanidad por sus pecados. Esta misión consiste en acabar de una vez por todas con un ejército de vampiros capaz de resistir la luz del día."};
        
        String infPeliculas[] = {"Genero: Comedia.", "Genero: Comedia, Musical.","Genero: Comedia. Acción. Terror. Musical. Vampiros. Artes marciales. Serie B. Película de culto"};
        
        Label titulo = new Label("<h1>Peliculas disponibles</h1>", ContentMode.HTML);
        Label explicacion = new Label("<h3>Por favor, pulse sobre una pelicula para obtener mas informacion</h3>", ContentMode.HTML);
        Label explicacion2 = new Label("<h3>Por favor, pulse sobre una pelicula para obtener mas infodsfmacion</h3>", ContentMode.HTML);
        
        Button botonPaginaReservas = new Button("Ir a la pagina de reservas");
        Button botonPaginaPrincipal = new Button("Ir a la pagina principal");
        
        Table tabla = new Table();
        
        tabla.addContainerProperty("ID", Integer.class, null);
        tabla.addContainerProperty("Nombre de la pelicula", String.class, null);
        tabla.addContainerProperty("Precio por persona(€)", String.class, null);
        tabla.addContainerProperty("Sala de Proyeccion", Integer.class, null);
        tabla.addContainerProperty("Sesiones", String.class, null);
        
        tabla.addItem(new Object[]{0,"Kung Pow!: Enter The Fist", "3", 1, "18:00-19:30"}, 1);
        tabla.addItem(new Object[]{1,"Tenacious D: The Pick Of Destiny", "4", 2, "22:00-23:30"}, 2);
        tabla.addItem(new Object[]{2,"Jesus Christ Vampire Hunter", "2", 3, "00:00-01:00"}, 3);
        
        
        tabla.addItemClickListener(new ItemClickEvent.ItemClickListener() {
            @Override
            public void itemClick(ItemClickEvent event) {
                String title = event.getItem().getItemProperty("Nombre de la pelicula").getValue().toString();
                String desc = "";
                Integer value = Integer.parseInt(event.getItem().getItemProperty("ID").getValue().toString());

                desc = descPeliculas[value];

                Notification.show(title + " - "+infPeliculas[value],desc,Notification.Type.HUMANIZED_MESSAGE);  
            }
        });
       
        //Pagina Reservas
       final VerticalLayout layout2 = new VerticalLayout();
       Button botonConfirmarReserva = new Button("Confirmar la reserva");
       Label titulo2 = new Label("<h1>Pagina de reservas</h1>", ContentMode.HTML);
       Label titulo3 = new Label("<h1>Formulario de reserva</h1>", ContentMode.HTML);
       Label resumenDatos = new Label("", ContentMode.HTML);
       Label explicacion3 = new Label("<h3>Por favor, introduce tus datos y selecciona una pelicula de la tabla</h3>", ContentMode.HTML);
       Label explicacion4 = new Label("<h3>Recuerda que las entradas tendran que ser abonadas a la entrada del cine</h3>", ContentMode.HTML);
       
       Table tabla2 = new Table();
        
       tabla2.addContainerProperty("ID", Integer.class, null);
       tabla2.addContainerProperty("Nombre de la pelicula", String.class, null);
       tabla2.addContainerProperty("Precio por persona(€)", String.class, null);
       tabla2.addContainerProperty("Sala de Proyeccion", Integer.class, null);
       tabla2.addContainerProperty("Sesiones", String.class, null);
        
       tabla2.addItem(new Object[]{0,"Kung Pow!: Enter The Fist", "3", 1, "18:00-19:30"}, 1);
       tabla2.addItem(new Object[]{1,"Tenacious D: The Pick Of Destiny", "4", 2, "22:00-23:30"}, 2);
       tabla2.addItem(new Object[]{2,"Jesus Christ Vampire Hunter", "2", 3, "00:00-01:00"}, 3);
       
       FormLayout fl = new FormLayout();
       fl.setSizeUndefined();
       
       
       TextField tf = new TextField("Nombre del comprador de las entradas");
       fl.addComponent(tf);
       
       TextField tf2 = new TextField("Numero de entradas a comprar");
       fl.addComponent(tf2);
       
       TextField tf3 = new TextField("Telefono de contacto");
       fl.addComponent(tf3);
       
       TextField tf4 = new TextField("Email");
       fl.addComponent(tf4);
       
       tabla2.addItemClickListener(new ItemClickEvent.ItemClickListener() {
            @Override
            public void itemClick(ItemClickEvent event) {
                
               movieId = Integer.parseInt(event.getItem().getItemProperty("ID").getValue().toString()) + 1;
                
               String peliculaTitle = event.getItem().getItemProperty("Nombre de la pelicula").getValue().toString();
               
               Notification.show("Pelicula - '"+peliculaTitle+"' seleccionada",Notification.Type.WARNING_MESSAGE);
               
            }
        });
       
       botonConfirmarReserva.addClickListener(new Button.ClickListener(){
            public void buttonClick(Button.ClickEvent event){
                
                String peliculaTitle = tabla2.getContainerProperty(movieId,"Nombre de la pelicula").getValue().toString();
                
                String precio = tabla2.getContainerProperty(movieId,"Precio por persona(€)").getValue().toString();
                
                Integer sala = Integer.parseInt(tabla2.getContainerProperty(movieId,"Sala de Proyeccion").getValue().toString());
                
                String sesion = tabla2.getContainerProperty(movieId,"Sesiones").getValue().toString();
                
               resumenDatos.setValue("<h2>Su reserva se ha realizado correctamente</h2><br><h3>Valores introducidos</h3><br>Pelicula reservada: "+peliculaTitle+"<br>Precio por persona(€): "+precio+"<br>Sala de Proyeccion: "+sala+"<br>Sesion: "+sesion+"<br>Nombre del comprador: "+tf.getValue()+"<br>Numero de entradas a comprar: "+tf2.getValue()+"<br>Telefono de contacto: "+tf3.getValue()+"<br>Email: "+tf4.getValue());
            }       
        });
       
        botonPaginaReservas.addClickListener(new Button.ClickListener(){
            public void buttonClick(Button.ClickEvent event){
               setContent(layout2);
            }       
        });
        
        botonPaginaPrincipal.addClickListener(new Button.ClickListener(){
            public void buttonClick(Button.ClickEvent event){
                
              resumenDatos.setValue(""); 
              
              tf.clear();
              
              tf2.clear();
              
              tf3.clear();
              
              tf4.clear();
                
               setContent(layout);
            }       
        });
        
       
        tabla.setPageLength(tabla.size());
        tabla.setImmediate(true);
        tabla.setSelectable(true);
        
        tabla2.setPageLength(tabla.size());
        tabla2.setImmediate(true);
        tabla2.setSelectable(true);
       
       layout2.addComponent(titulo2);
       layout2.addComponent(explicacion3);
       layout2.addComponent(explicacion4);
       layout2.addComponent(tabla2);
       layout2.addComponent(titulo3);
       layout2.addComponent(resumenDatos);
       layout2.addComponent(fl);
       layout2.addComponent(botonConfirmarReserva);
       layout2.addComponent(botonPaginaPrincipal);
        
        
        layout.addComponent(titulo);
        layout.addComponent(explicacion);
        layout.addComponent(explicacion2);
        layout.addComponent(tabla);
        layout.addComponent(botonPaginaReservas);
        
        setContent(layout);
    }
    
    @WebServlet(urlPatterns = "/*", name = "PrincipalServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = Principal.class, productionMode = false)
    public static class PrincipalServlet extends VaadinServlet {
    }
}
