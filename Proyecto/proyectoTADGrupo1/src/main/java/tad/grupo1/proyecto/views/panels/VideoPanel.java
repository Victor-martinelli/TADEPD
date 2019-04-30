/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tad.grupo1.proyecto.views.panels;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.FileResource;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Resource;
import com.vaadin.server.Responsive;
import com.vaadin.server.Sizeable;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.shared.ui.PreloadMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.VerticalSplitPanel;
import com.vaadin.ui.Video;
import com.vaadin.ui.themes.ValoTheme;
import java.io.File;
import javax.swing.Icon;
import org.vaadin.gwtav.GwtVideo;
import tad.grupo1.proyecto.controllers.VideoController;
import tad.grupo1.proyecto.objects.UserVideo;

/**
 *
 * @author Portatil
 */
public class VideoPanel extends CssLayout implements View{

    
    VideoController vc = new VideoController();
    
    public VideoPanel(String username,String videoTitle)
    {

        VerticalLayout content = new VerticalLayout();       
        HorizontalLayout uploaderInfo = new HorizontalLayout();
        HorizontalLayout videoInfo = new HorizontalLayout();
        HorizontalLayout interactionsInfo = new HorizontalLayout();
        UserVideo video = vc.getVideo(username, videoTitle);
        
        
        Label videoTitleLabel = new Label("<h1>"+videoTitle+"</h1>",ContentMode.HTML);
        Label viewsLabel = new Label("<h2>"+video.getViews()+" views</h2>",ContentMode.HTML);
        Label uploaderUsernameLabel = new Label("<h3>"+username+"</h3>", ContentMode.HTML);
        Button likesButton = new Button(FontAwesome.THUMBS_UP);
        Button dislikesButton = new Button(FontAwesome.THUMBS_DOWN);
        
        Label likesLabel = new Label("<h3>"+video.getLikes()+"</h3>", ContentMode.HTML);
        Label dislikesLabel = new Label("<h3>"+video.getDislikes()+"</h3>", ContentMode.HTML);
        

        
        
        content.setSizeFull();
        videoInfo.setSizeFull();
        
        content.setMargin(false);
        content.setSpacing(false);

        videoTitleLabel.setWidth(null);
        likesButton.addStyleName(ValoTheme.BUTTON_BORDERLESS);
        dislikesButton.addStyleName(ValoTheme.BUTTON_BORDERLESS);
        
        GwtVideo sample = new GwtVideo();
        sample.setPreload(PreloadMode.NONE);
        
        final Resource mp4Resource = new FileResource(
                new File(video.getVideoPath()));
        
        Image profile = new Image("",new FileResource(
                new File(vc.getProfilePicture(username))));
        
        profile.setWidth("5em");
        
        sample.setSource(mp4Resource);
        sample.setResponsive(true);
        sample.setHtmlContentAllowed(true);
        sample.setShowControls(true);
        //sample.setWidth("100em");
        //sample.setWidth("100%");
        sample.setWidth("1080px");
        sample.setAltText("Can't play media");
        
        
        
        
        /*
        sample.ssetPoster(new FileResource(
                new File(vc.getVideoThumbnail(username, videoTitle)))); */
        
        interactionsInfo.addComponents(likesButton,likesLabel,dislikesButton,dislikesLabel);
        
        videoInfo.addComponents(viewsLabel,interactionsInfo);
        videoInfo.setComponentAlignment(interactionsInfo, Alignment.MIDDLE_RIGHT);
        uploaderInfo.addComponents(profile,uploaderUsernameLabel);
        content.addComponents(videoTitleLabel,sample,videoInfo,uploaderInfo);
        
   
        
        addComponent(content);
        
    }
    
    
    
    
    
    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        
    }
    
}
