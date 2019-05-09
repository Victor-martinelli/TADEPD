/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tad.grupo1.proyecto.views.panels;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.FileResource;
import com.vaadin.server.Resource;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.shared.ui.PreloadMode;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import java.io.File;
import java.util.List;
import org.vaadin.gwtav.GwtVideo;
import tad.grupo1.proyecto.model.DAO;
import tad.grupo1.proyecto.controllers.SuscripcionesController;
import tad.grupo1.proyecto.controllers.VideoController;
import tad.grupo1.proyecto.objects.UserVideo;
import static tad.grupo1.proyecto.views.MainScreen.sc;
import tad.grupo1.proyecto.views.MainUI;

/**
 *
 * @author Lydia
 */
public class SuscripcionesPanel extends CssLayout implements View{

    public SuscripcionesPanel(String username) {
        VerticalLayout suscripcionesLayout = new VerticalLayout();
        suscripcionesLayout.setSpacing(true);
        suscripcionesLayout.setMargin(true);


        List<String> listSuscripciones = (List<String>) sc.getSuscripciones(username);

        if (listSuscripciones.size() < 0) {
            Label label = new Label("No se ha suscrito a ningun canal todavía.");
            suscripcionesLayout.addComponent(label);
        } else {
            for (String nombre : listSuscripciones) {
                List<UserVideo> listVideo = (List<UserVideo>) sc.getVideoSuscrito(nombre);

                Label nombreCanalLabel = new Label("<h1><b>" + nombre + "</b></h1>", ContentMode.HTML);
                suscripcionesLayout.addComponent(nombreCanalLabel);

                if (listVideo.size() < 0) {
                    Label label = new Label("Este canal no tiene videos");
                    suscripcionesLayout.addComponent(label);
                } else {
                    HorizontalLayout canalLayout = new HorizontalLayout();
                    canalLayout.setSpacing(true);
                    canalLayout.setMargin(true);
                    suscripcionesLayout.addComponent(canalLayout);

                    for (UserVideo video : listVideo) {
                        VerticalLayout layout = new VerticalLayout();

                        
                        
                        Image img = new Image("",new FileResource(
                        new File(video.getThumbPath())));
                        Label tituloLabel = new Label("<h3>" + video.getTitle() + "</h3>", ContentMode.HTML);
                        tituloLabel.setWidth(80, Unit.PERCENTAGE);

                        

                        layout.addComponents(tituloLabel, img);
                        
                        canalLayout.addComponent(layout);
                    }
                }

            }
        }

        
        
        addComponent(suscripcionesLayout);
    }   
    

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        
    }
    
}
