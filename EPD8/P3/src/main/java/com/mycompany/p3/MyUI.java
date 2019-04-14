package com.mycompany.p3;

import com.mongodb.BasicDBList;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
import com.vaadin.ui.UI;
import java.util.Iterator;

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

        DBCursor cursor = null;
        try {

            Label titulo = new Label("<h1>EPD8 - Problema 3</h1>",ContentMode.HTML);
            
            final HorizontalLayout layout = new HorizontalLayout();
            
            Table tablaEquipos = new Table("Tabla Equipos");
            
            tablaEquipos.addContainerProperty("Nombre",String.class,null);
            tablaEquipos.addContainerProperty("Nombre del Estadio",String.class,null);
            
            
            Table tablaJugadores = new Table("Tabla Jugadores");
            tablaJugadores.addContainerProperty("Nombre",String.class,null);
            tablaJugadores.addContainerProperty("Dorsal",String.class,null);
            tablaJugadores.addContainerProperty("Fecha Nacimiento",String.class,null);
            tablaJugadores.addContainerProperty("Nombre Equipo",String.class,null);
            
           


            // To connect to mongodb server
            MongoClient mongoClient = new MongoClient("localhost", 27017);
            // Now connect to your databases
            DB db = mongoClient.getDB("P1");
            System.out.println("Connect to database successfully");

            //Obtención colección "usuario"
            DBCollection collection = db.getCollection("Equipos");
            cursor = collection.find();
            int i = 0;
            int j = 0;
            while (cursor.hasNext()) {

                DBObject current = cursor.next();

                tablaEquipos.addItem(new Object[]{current.get("nombre").toString(),current.get("nombre estadio").toString()},i);

                
                //Cogemos a los jugadores
                BasicDBList currentJugadores = (BasicDBList) current.get("jugadores");
                
                //Iteramos sobre ellos
                Iterator it = currentJugadores.iterator();
                while(it.hasNext())
                {
                    DBObject currentJugador = (DBObject) it.next();

                    tablaJugadores.addItem(new Object[]{currentJugador.get("nombre").toString(),currentJugador.get("dorsal").toString(),currentJugador.get("fecha nacimiento").toString(),current.get("nombre").toString()},j);
                    j++;
                }
                
                
                //System.out.println("Documento leído: " + i);
                //System.out.println(cursor.next());
                i++;
            }

            tablaEquipos.setPageLength(tablaEquipos.size());
             tablaJugadores.setPageLength(tablaJugadores.size());
            layout.addComponents(titulo, tablaEquipos,tablaJugadores);
            layout.setMargin(true);
            layout.setSpacing(true);

            setContent(layout);

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        } finally {
            cursor.close();
        }

    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
