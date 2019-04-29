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
import com.vaadin.server.Sizeable;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.shared.ui.PreloadMode;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.VerticalSplitPanel;
import com.vaadin.ui.Video;
import java.io.File;
import org.vaadin.gwtav.GwtVideo;
import tad.grupo1.proyecto.controllers.VideoController;

/**
 *
 * @author Portatil
 */
public class VideoPanel extends CssLayout implements View{

    
    VideoController vc = new VideoController();
    
    public VideoPanel(String username,String videoTitle)
    {
        
        
        //root.setSizeFull();
        
        VerticalLayout content = new VerticalLayout();

        //content.setSizeFull();
        
        
        Label videoTitleLabel = new Label("<h1>"+videoTitle+"</h1>",ContentMode.HTML);
        
        Label viewsLabel = new Label("<h2>"+vc.getVideoViews(videoTitle)+" views</h2>",ContentMode.HTML);
        
        content.setMargin(true);

        videoTitleLabel.setWidth(null);
        
        
        GwtVideo sample = new GwtVideo();
        sample.setPreload(PreloadMode.NONE);
        
        final Resource mp4Resource = new FileResource(
                new File(vc.getVideo(username, videoTitle)));
        sample.setSource(mp4Resource);
        //sample.setSizeUndefined();
        sample.setResponsive(true);
        sample.setHtmlContentAllowed(true);
        sample.setShowControls(true);
        sample.setWidth("100%");
        sample.setAltText("Can't play media");
        
        /*
        sample.ssetPoster(new FileResource(
                new File(vc.getVideoThumbnail(username, videoTitle)))); */
        
        
        content.addComponents(videoTitleLabel,sample,viewsLabel);
        
        
        
        //content.addComponents(videoTitleLabel,new Label("test"),new Label("test"),new Label("test"),new Label("test"),new Label("test"),new Label("test"),new Label("test"),new Label("test"),new Label("test"),new Label("test"),new Label("test"),new Label("test"),new Label("test"),new Label("test"),new Label("test"),new Label("test"),new Label("test"),new Label("test"),new Label("test"),new Label("test"),new Label("test"),new Label("test"),new Label("test"),new Label("test"),new Label("test"),new Label("test"),new Label("test"),new Label("test"),new Label("test"),new Label("test"),new Label("test"));
        
        
        
        //content.setComponentAlignment(videoTitleLabel, Alignment.MIDDLE_CENTER);
        //content.setComponentAlignment(sample, Alignment.MIDDLE_CENTER);
        
        //root.addComponent(content);
        
        addComponent(content);
        
    }
    
    
    
    
    
    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        
    }
    
}
