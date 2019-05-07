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
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import java.io.File;
import java.util.List;
import org.vaadin.gwtav.GwtVideo;
import tad.grupo1.proyecto.model.DAO;
import tad.grupo1.proyecto.controllers.SuscripcionesController;
import tad.grupo1.proyecto.controllers.VideoController;
import tad.grupo1.proyecto.views.MainUI;

/**
 *
 * @author Lydia
 */
public class SuscripcionesPanel extends CssLayout implements View{
    
    VideoController vc = new VideoController();
    SuscripcionesController sc = new SuscripcionesController();
    DAO dao = new DAO();

    public SuscripcionesPanel() {
        VerticalLayout suscripcionesLayout = new VerticalLayout();
        suscripcionesLayout.setSpacing(true);
        suscripcionesLayout.setMargin(true);

        String username = (String) MainUI.session.getAttribute("user");

        List<String> listSuscripciones = (List<String>) sc.getSuscripciones(username);

        if (listSuscripciones.size() < 0) {
            Label label = new Label("No se ha suscrito a ningun canal todavía.");
            suscripcionesLayout.addComponent(label);
        } else {
            for (String nombre : listSuscripciones) {
                BasicDBList listVideo = (BasicDBList) sc.getVideoSuscrito(nombre);

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

                    for (Object video : listVideo) {
                        BasicDBObject v = (BasicDBObject) video;

                        Panel videoPanel = new Panel();
                        videoPanel.setWidth("340px");
                        videoPanel.setHeight("240px");
                        
                        VerticalLayout layout = new VerticalLayout();

                        Label tituloLabel = new Label("<h3>" + v.get("title") + "</h3>", ContentMode.HTML);
                        tituloLabel.setWidth(80, Unit.PERCENTAGE);

                        Resource mp4Resource = new FileResource(new File(dao.getVideoPath(nombre, (String) v.get("title"))));
                        GwtVideo sample = new GwtVideo();
                        sample.setPreload(PreloadMode.NONE);
                        sample.setSource(mp4Resource);
                        sample.setResponsive(true);
                        sample.setHtmlContentAllowed(true);
                        sample.setShowControls(true);
                        sample.setWidth("310px");
                        sample.setHeight("120px");
//                        sample.setWidth("80%");
                        sample.setAltText("Can't play media");

                        layout.addComponents(tituloLabel, sample);
                        videoPanel.setContent(layout);
                        
                        canalLayout.addComponent(videoPanel);
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
