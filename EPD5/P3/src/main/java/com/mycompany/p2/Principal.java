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

    private int movieId;

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
        Label masInf = new Label("<h2><b>Mas Informacion</b>: </h2>", ContentMode.HTML);
        Label sinopsis = new Label("<h2><b>Sinopsis</b></h2>", ContentMode.HTML);
        Label precioPersona = new Label("<h2><b>Precio por persona(€)</b></h2>", ContentMode.HTML);
        Label salaProyeccion = new Label("<h2><b>Sala de Proyeccion</b></h2>", ContentMode.HTML);
        Label sesiones = new Label("<h2><b>Sesiones</b></h2>", ContentMode.HTML);
        Label explicacion = new Label("<h3>Por favor, pulse sobre una pelicula para obtener mas informacion</h3>", ContentMode.HTML);

        Table tabla = new Table();

        tabla.addContainerProperty("ID", Integer.class, null);
        tabla.addContainerProperty("Nombre de la pelicula", String.class, null);
        tabla.addContainerProperty("Precio por persona(€)", String.class, null);
        tabla.addContainerProperty("Sala de Proyeccion", Integer.class, null);
        tabla.addContainerProperty("Sesiones", String.class, null);

        tabla.addItem(new Object[]{0, "Kung Pow! -  Enter The Fist", "3", 1, "18:00-19:30"}, 1);
        tabla.addItem(new Object[]{1, "Tenacious D: The Pick Of Destiny", "4", 2, "22:00-23:30"}, 2);
        tabla.addItem(new Object[]{2, "Jesus Christ Vampire Hunter", "2", 3, "00:00-01:00"}, 3);

        tabla.addItemClickListener(new ItemClickEvent.ItemClickListener() {
            @Override
            public void itemClick(ItemClickEvent event) {
                String title = event.getItem().getItemProperty("Nombre de la pelicula").getValue().toString();
                String desc = "";
                String inf = "";
                String precio = "";
                String sesion = "";
                String sala = "";
                Integer value = Integer.parseInt(event.getItem().getItemProperty("ID").getValue().toString());

                desc = descPeliculas[value];
                inf = infPeliculas[value];

                tituloPelicula.setValue("<h1><b>Titulo</b>: "+title+"</h1>");
                masInf.setValue("<h2><b>Mas Informacion</b>: "+inf+"</h2>");
                sinopsis.setValue("<h2><b>Sinopsis</b>: "+desc+"</h2>");
            }
        });

        tabla.setPageLength(tabla.size());
        tabla.setImmediate(true);
        tabla.setSelectable(true);

        layout.addComponent(titulo);
        layout.addComponent(explicacion);
        layout.addComponent(tabla);
        
        layout2.addComponent(tituloPelicula);
        layout2.addComponent(precioPersona);
        layout2.addComponent(salaProyeccion);
        layout2.addComponent(sesiones);
        layout2.addComponent(masInf);
        layout2.addComponent(sinopsis);
        
        hsplit.setFirstComponent(layout);
        hsplit.setSecondComponent(layout2);

        setContent(hsplit);
    }

    @WebServlet(urlPatterns = "/*", name = "PrincipalServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = Principal.class, productionMode = false)
    public static class PrincipalServlet extends VaadinServlet {
    }
}
