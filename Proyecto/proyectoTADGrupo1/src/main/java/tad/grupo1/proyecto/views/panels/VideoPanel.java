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
import com.vaadin.server.Page;
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
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.VerticalSplitPanel;
import com.vaadin.ui.themes.ValoTheme;
import java.io.File;
import org.vaadin.gwtav.GwtVideo;
import tad.grupo1.proyecto.controllers.VideoController;
import tad.grupo1.proyecto.objects.UserVideo;

/**
 *
 * @author Portatil
 */
public class VideoPanel extends CssLayout implements View {

    VideoController vc = new VideoController();

    boolean clickLikeButton = false;
    boolean clickDislikeButton = false;

    public VideoPanel(String username, String videoTitle) {

        VerticalLayout content = new VerticalLayout();
        VerticalLayout usernameAndDate = new VerticalLayout();
        HorizontalLayout uploaderInfo = new HorizontalLayout();
        HorizontalLayout videoInfo = new HorizontalLayout();
        HorizontalLayout interactionsInfo = new HorizontalLayout();
        UserVideo video = vc.playVideo(username, videoTitle);

        Label videoTitleLabel = new Label("<h1>" + videoTitle + "</h1>", ContentMode.HTML);
        Label videoDateLabel = new Label("<p>Publicado el: " + video.getDate() + "</p>", ContentMode.HTML);
        Label viewsLabel = new Label("<h2>" + video.getViews() + " visitas</h2>", ContentMode.HTML);
        Label uploaderUsernameLabel = new Label("<h3>" + username + "</h3>", ContentMode.HTML);
        Label likesLabel = new Label("<p>" + video.getLikesCount() + "</p>", ContentMode.HTML);
        Label dislikesLabel = new Label("<p>" + video.getDislikesCount() + "</p>", ContentMode.HTML);
        Button subscribeButton = new Button("Suscribirse");
        Button likesButton = createInteractionButton(1,video,username);
        Button dislikesButton = new Button(FontAwesome.THUMBS_DOWN);

        content.setSizeFull();
        videoInfo.setSizeFull();
        videoInfo.setMargin(true);

        content.setMargin(false);
        content.setSpacing(false);

        videoTitleLabel.setWidth(null);
        likesButton.addStyleName(ValoTheme.BUTTON_BORDERLESS);
        dislikesButton.addStyleName(ValoTheme.BUTTON_BORDERLESS);
        subscribeButton.addStyleName(ValoTheme.BUTTON_PRIMARY);

        GwtVideo sample = new GwtVideo();
        sample.setPreload(PreloadMode.NONE);

        final Resource mp4Resource = new FileResource(
                new File(video.getVideoPath()));

        Image profile = new Image("", new FileResource(
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
        usernameAndDate.addComponents(uploaderUsernameLabel, videoDateLabel);
        interactionsInfo.addComponents(likesButton, likesLabel, dislikesButton, dislikesLabel);
        videoInfo.addComponents(viewsLabel, interactionsInfo);
        uploaderInfo.addComponents(profile, usernameAndDate, subscribeButton);
        content.addComponents(videoTitleLabel, sample, videoInfo, uploaderInfo);

        videoInfo.setComponentAlignment(interactionsInfo, Alignment.MIDDLE_RIGHT);
        uploaderInfo.setComponentAlignment(subscribeButton, Alignment.MIDDLE_RIGHT);

        addComponent(content);

    }

    private Button createInteractionButton(int buttonType, UserVideo video, String username) {
        /*
        0 --> Like
        1 --> Dislike
         */

        Button interactionButton = null;

        if (buttonType == 1) {
            interactionButton = new Button(FontAwesome.THUMBS_UP);
            if (video.hasUserLikedVideo(username)) {
                clickLikeButton = true;
            }

            interactionButton.addClickListener(new Button.ClickListener() {
                @Override
                public void buttonClick(Button.ClickEvent event) {
                    
                    //Video ya tenia like
                    if(clickLikeButton)
                    {
                        vc.unlikeVideo(video.getTitle(),username);
                        new Notification("AVISO","Ya no te gusta el vídeo",Notification.TYPE_TRAY_NOTIFICATION).show(Page.getCurrent());
                    }
                    else //No tenia like
                    {
                        
                    }
                    
                }
            });
        } else {
            interactionButton = new Button(FontAwesome.THUMBS_DOWN);
        }

        return interactionButton;
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {

    }

}
