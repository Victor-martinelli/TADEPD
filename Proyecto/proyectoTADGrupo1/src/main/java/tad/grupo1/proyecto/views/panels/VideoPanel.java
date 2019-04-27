/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tad.grupo1.proyecto.views.panels;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.FileResource;
import com.vaadin.server.Resource;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.Label;
import com.vaadin.ui.Video;
import java.io.File;
import tad.grupo1.proyecto.controllers.VideoController;

/**
 *
 * @author Portatil
 */
public class VideoPanel extends CssLayout implements View{

    
    VideoController vc = new VideoController();
    
    public VideoPanel(String username,String videoTitle)
    {
        Video sample = new Video();
        setSizeFull();
        
        final Resource mp4Resource = new FileResource(
                new File(vc.getVideo(username, videoTitle)));
        sample.setSource(mp4Resource);
        sample.setSizeFull();
        sample.setHtmlContentAllowed(true);
        sample.setWidth("640px");
        sample.setHeight("320px");
        sample.setAltText("Can't play media");
        
        
        
        addComponent(sample);
        
    }
    
    
    
    
    
    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        
    }
    
}
