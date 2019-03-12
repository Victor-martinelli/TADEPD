package com.mycompany.p2;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
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

    @Override
    protected void init(VaadinRequest vaadinRequest) {

        HorizontalSplitPanel hsplit = new HorizontalSplitPanel();
        

        hsplit.setSplitPosition(45, Unit.PERCENTAGE);

        final VerticalLayout layout = new VerticalLayout();
        final VerticalLayout layout2 = new VerticalLayout();

        String descPeliculas[] = {"Un joven llamado \"El Elegido\" pretende vengar la muerte de sus padres, quienes fueron asesinados por una leyenda del Kung Fu llamado \"El Maestro Dolor\". \n"
            + "Contiene escenas del film \"El tigre y los puños de grulla\" de 1976.", "La película sigue las aventuras de JB (Jack Black) y KG (Kyle Gass), componentes del grupo Tenacious D, mientras viajan por el tiempo y el espacio en busca de la llave de la puerta al estrellato.",
            "El juicio final está cada vez más cerca. Jesucristo ha vuelto al mundo para llevar a cabo una última misión, antes de juzgar a la Humanidad por sus pecados. Esta misión consiste en acabar de una vez por todas con un ejército de vampiros capaz de resistir la luz del día."};

        String infPeliculas[] = {"Genero -  Comedia.", "Genero - Comedia, Musical.", "Genero - Comedia. Acción. Terror. Musical. Vampiros. Artes marciales. Serie B. Película de culto"};

        Label titulo = new Label("<h1>Peliculas disponibles</h1>", ContentMode.HTML);
        Label buscador = new Label("<h2>Buscador de Peliculas</h2>", ContentMode.HTML);
        Label buscadorExp = new Label("<h3>Introduzca el titulo de la pelicula para obtener mas informacion.</h3>", ContentMode.HTML);
        Label tituloPelicula = new Label("<h1><b>Titulo</b></h1>", ContentMode.HTML);
        Label masInf = new Label("<h3><b>Mas Informacion</b>: </h3>", ContentMode.HTML);
        Label sinopsis = new Label("<h2><b>Sinopsis</b></h2>", ContentMode.HTML);
        Label precioPersona = new Label("<h3><b>Precio por persona(€)</b></h3>", ContentMode.HTML);
        Label salaProyeccion = new Label("<h3><b>Sala de Proyeccion</b></h3>", ContentMode.HTML);
        
        
        
        Label sesiones = new Label("<h3><b>Sesiones</b></h3>", ContentMode.HTML);
        Label explicacion = new Label("<h3>Por favor, pulse sobre una pelicula para obtener mas informacion</h3>", ContentMode.HTML);

        Table tabla = new Table();

        tabla.addContainerProperty("ID", Integer.class, null);
        tabla.addContainerProperty("Nombre de la pelicula", String.class, null);
        tabla.addContainerProperty("Precio por persona(€)", String.class, null);
        tabla.addContainerProperty("Sala de Proyeccion", Integer.class, null);
        tabla.addContainerProperty("Sesiones", String.class, null);

        tabla.addItem(new Object[]{0, "Kung Pow Enter The Fist", "3", 1, "18:00-19:30"}, 0);
        tabla.addItem(new Object[]{1, "Tenacious D: The Pick Of Destiny", "4", 2, "22:00-23:30"}, 1);
        tabla.addItem(new Object[]{2, "Jesus Christ Vampire Hunter", "2", 3, "00:00-01:00"}, 2);

        tabla.addItemClickListener(new ItemClickEvent.ItemClickListener() {
            @Override
            public void itemClick(ItemClickEvent event) {
                
                int movieId = Integer.parseInt(event.getItem().getItemProperty("ID").getValue().toString());
                
                String title = event.getItem().getItemProperty("Nombre de la pelicula").getValue().toString();
                String desc = "";
                String inf = "";
                String precio = tabla.getContainerProperty(movieId,"Precio por persona(€)").getValue().toString();
                String sesion = tabla.getContainerProperty(movieId,"Sesiones").getValue().toString();
                String sala = tabla.getContainerProperty(movieId,"Sala de Proyeccion").getValue().toString();
                Integer value = Integer.parseInt(event.getItem().getItemProperty("ID").getValue().toString());

                desc = descPeliculas[value];
                inf = infPeliculas[value];

                tituloPelicula.setValue("<h1><b>Titulo</b>: "+title+"</h1>");
                precioPersona.setValue("<h3><b>Precio por persona(€)</b>: "+precio+"</h3>");
                salaProyeccion.setValue("<h3><b>Sala de Proyeccion</b>: "+sala+"</h3>");
                sesiones.setValue("<h3><b>Sesion</b>: "+sesion+"</h3>");
                masInf.setValue("<h3><b>Mas Informacion</b>: "+inf+"</h3>");
                sinopsis.setValue("<h3><b>Sinopsis</b>: "+desc+"</h3>");
            }
        });
        
        FormLayout formularioBuscador= new FormLayout();
        
        formularioBuscador.setSizeUndefined();
        
        TextField tf = new TextField("Nombre de la pelicula");
        formularioBuscador.addComponent(tf);
        
        Button botonBuscar = new Button("Buscar Pelicula");
        
        formularioBuscador.addComponent(botonBuscar);
        
        botonBuscar.addClickListener(new Button.ClickListener(){
            public void buttonClick(Button.ClickEvent event){
                
                String title = tf.getValue();
                
                
                int movieId = getMovieIDByTitle(title,tabla);
                
                //We found the movie
                if(movieId!=-1)
                {
                    tf.clear();
                    String desc = "";
                    String inf = "";
                    String precio = tabla.getContainerProperty(movieId,"Precio por persona(€)").getValue().toString();
                    String sesion = tabla.getContainerProperty(movieId,"Sesiones").getValue().toString();
                    String sala = tabla.getContainerProperty(movieId,"Sala de Proyeccion").getValue().toString();

                    desc = descPeliculas[movieId];
                    inf = infPeliculas[movieId];

                    tituloPelicula.setValue("<h1><b>Titulo</b>: "+title+"</h1>");
                    precioPersona.setValue("<h3><b>Precio por persona(€)</b>: "+precio+"</h3>");
                    salaProyeccion.setValue("<h3><b>Sala de Proyeccion</b>: "+sala+"</h3>");
                    sesiones.setValue("<h3><b>Sesion</b>: "+sesion+"</h3>");
                    masInf.setValue("<h3><b>Mas Informacion</b>: "+inf+"</h3>");
                    sinopsis.setValue("<h3><b>Sinopsis</b>: "+desc+"</h3>");
                }
                else
                {
                    Notification.show("ERROR","No se ha encontrado una pelicula con ese titulo, por favor compruebe si hay errores en el titulo proporcionado.", Notification.Type.ERROR_MESSAGE);
                }
            }       
        });
        
        Button botonCerrarDerecho = new Button("Pulsar para ocultar parte derecha de la pagina");
        
        botonCerrarDerecho.addClickListener(new Button.ClickListener(){
            public void buttonClick(Button.ClickEvent event){
                hsplit.setSplitPosition(100, Unit.PERCENTAGE);
            }       
        });
        
        Button botonAbrirDerecho = new Button("Pulsar para mostrar parte derecha de la pagina");
        
        botonAbrirDerecho.addClickListener(new Button.ClickListener(){
            public void buttonClick(Button.ClickEvent event){
                hsplit.setSplitPosition(45, Unit.PERCENTAGE);
            }       
        });
        

        tabla.setPageLength(tabla.size());
        tabla.setImmediate(true);
        tabla.setSelectable(true);
        
        layout.addComponent(titulo);
        layout.addComponent(explicacion);
        layout.addComponent(tabla);
        layout.addComponent(buscador);
        layout.addComponent(buscadorExp);
        layout.addComponent(formularioBuscador);
        layout.addComponent(botonAbrirDerecho);
        
        layout2.addComponent(tituloPelicula);
        layout2.addComponent(precioPersona);
        layout2.addComponent(salaProyeccion);
        layout2.addComponent(sesiones);
        layout2.addComponent(masInf);
        layout2.addComponent(sinopsis);
        layout2.addComponent(botonCerrarDerecho);
        
        hsplit.setFirstComponent(layout);
        hsplit.setSecondComponent(layout2);

        setContent(hsplit);
    }
    
    public int getMovieIDByTitle(String title, Table tabla)
    {
        int result = -1;
        
        for(int i=0;i<tabla.size();i++)
        {
            if(tabla.getContainerProperty(i,"Nombre de la pelicula").getValue().equals(title))
            {
                result=i;
            }
            
        }
        
        
        return result;
    }

    @WebServlet(urlPatterns = "/*", name = "PrincipalServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = Principal.class, productionMode = false)
    public static class PrincipalServlet extends VaadinServlet {
    }
}
