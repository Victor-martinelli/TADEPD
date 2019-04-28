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
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
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
        
        VerticalLayout content = new VerticalLayout();
        
        Label videoTitleLabel = new Label("<h1>"+videoTitle+"</h1>",ContentMode.HTML);

        videoTitleLabel.setWidth(null);
        
        
        Video sample = new Video();
        setSizeFull();
        
        final Resource mp4Resource = new FileResource(
                new File(vc.getVideo(username, videoTitle)));
        sample.setSource(mp4Resource);
        sample.setSizeFull();
        sample.setHtmlContentAllowed(true);
        sample.setShowControls(true);
        sample.setWidth("640px");
        sample.setHeight("320px");
        sample.setAltText("Can't play media");
        
        sample.setPoster(new FileResource(
                new File(vc.getVideoThumbnail(username, videoTitle))));
        
        
        content.addComponents(videoTitleLabel,sample);
        
        
        content.setComponentAlignment(videoTitleLabel, Alignment.MIDDLE_CENTER);
        content.setComponentAlignment(sample, Alignment.MIDDLE_CENTER);
        
        
        addComponent(content);
        
    }
    
    
    
    
    
    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        
    }
    
}
