package tad.grupo1.proyecto.views;

import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.FileResource;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Page;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Upload;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import tad.grupo1.proyecto.controllers.SuscripcionesController;
import tad.grupo1.proyecto.controllers.UsuarioController;
import tad.grupo1.proyecto.controllers.VideoController;
import static tad.grupo1.proyecto.views.MainUI.session;
import tad.grupo1.proyecto.views.panels.ResultadoBusquedaVideosPanel;
import tad.grupo1.proyecto.views.panels.SubirVideoPanel;
import tad.grupo1.proyecto.views.panels.SuscripcionesPanel;
import tad.grupo1.proyecto.views.panels.VideoPanel;

/**
 * Content of the UI when the user is logged in.
 * 
 * 
 */
public class MainScreen extends HorizontalLayout {
    
    private Menu menu = createMenu(session.getAttribute("user").toString());
    public static UsuarioController uc = new UsuarioController();
    public static SuscripcionesController sc = new SuscripcionesController();
    public static VideoController vc = new VideoController();
    public VideoPanel videopanel = new VideoPanel("mikehunt", "despacito2");
    TopMenu top = createTopMenu();
    HorizontalLayout page;
    VerticalLayout videoContainer;
    VerticalLayout content;

    public MainScreen() {
        
        content = new VerticalLayout(top,videopanel);

        page = new HorizontalLayout(menu,content); 
      
        page.setSizeFull();

        addComponent(page);
        
        
        
    }
    
    public Menu createMenu(String username)
    {
        Menu aux = new Menu();
        
        Button myChannel = new Button("Mi canal");
        Button uploadVideo = new Button("Subir video");
        Button suscripctions = new Button("Suscripciones");
        Button closeSession = new Button("Cerrar Sesion");
        Image profile = new Image("", new FileResource(
                new File(uc.getProfilePicture(username))));
        Label usernameLabel = new Label(username);
        
        profile.setWidth("5em");
        
        aux.getSidebar().addComponents(myChannel,uploadVideo,suscripctions,closeSession,profile,usernameLabel);
        
        
        
        myChannel.setStyleName(ValoTheme.BUTTON_BORDERLESS_COLORED);
        uploadVideo.setStyleName(ValoTheme.BUTTON_BORDERLESS_COLORED);
        suscripctions.setStyleName(ValoTheme.BUTTON_BORDERLESS_COLORED);
        closeSession.setStyleName(ValoTheme.BUTTON_BORDERLESS_COLORED);
        
        aux.getSidebar().setComponentAlignment(myChannel,Alignment.MIDDLE_CENTER);
        aux.getSidebar().setComponentAlignment(uploadVideo,Alignment.MIDDLE_CENTER);
        aux.getSidebar().setComponentAlignment(suscripctions,Alignment.MIDDLE_CENTER);
        aux.getSidebar().setComponentAlignment(closeSession,Alignment.MIDDLE_CENTER);
        aux.getSidebar().setComponentAlignment(profile,Alignment.MIDDLE_CENTER);
        aux.getSidebar().setComponentAlignment(usernameLabel,Alignment.MIDDLE_CENTER);
        
        //aux.getSidebar().setComponentAlignment(profile,Alignment.MIDDLE_CENTER);
        //aux.getSidebar().setComponentAlignment(usernameLabel,Alignment.MIDDLE_CENTER);
        
        
         uploadVideo.addClickListener(new Button.ClickListener() {
                    @Override
                    public void buttonClick(Button.ClickEvent event) {
                        createUploadVideoView(username);
                    }
                });
         
         
         suscripctions.addClickListener(new Button.ClickListener() {
                    @Override
                    public void buttonClick(Button.ClickEvent event) {
                        createSuscripcionesView(username);
                    }
                });
        
        
        return aux;
    }
    
    public TopMenu createTopMenu()
    {
        TopMenu aux = new TopMenu();
        
        TextField search = new TextField();
        
        Button searchButton = new Button(FontAwesome.SEARCH);
        
        searchButton.addClickListener(new Button.ClickListener() {
                    @Override
                    public void buttonClick(Button.ClickEvent event) {
                        createSearchResultVideoView(search.getValue());
                    }
                });
        
        aux.getTopBar().addComponents(search,searchButton);
        
        //aux.setMargin(false);
        //aux.setSpacing(false);
        
        aux.setSizeFull();
        search.setWidthUndefined();
        //search.setSizeFull();
        
       aux.getTopBar().setComponentAlignment(search,Alignment.MIDDLE_CENTER);
        
        //aux.setSizeFull();
        
        return aux;
    }
    
    public void createUploadVideoView(String username)
    {
        
        page.removeAllComponents();
        content.removeAllComponents();
        
        content.addComponents(top,new SubirVideoPanel(username));

        page.addComponents(menu,content);
        
        page.setSizeFull();
        
        addComponent(page);
    }
    
    
    public void createSearchResultVideoView(String word)
    {
        
        page.removeAllComponents();
        content.removeAllComponents();
        
        content.addComponents(top,new ResultadoBusquedaVideosPanel(this,word));
        
        
        
        page.addComponents(menu,content);
        
        page.setSizeFull();
        
        addComponent(page);
    }
    
    public void createSuscripcionesView(String username)
    {
        
        page.removeAllComponents();
        content.removeAllComponents();
        
        content.addComponents(top,new SuscripcionesPanel(username));

        page.addComponents(menu,content);
        
        page.setSizeFull();
        
        addComponent(page);
        
    }
    
    public void createVideoPanel(String username,String title)
    {
        
        page.removeAllComponents();
        content.removeAllComponents();
        
        content.addComponents(top,new VideoPanel(username,title));
        
        
        
        page.addComponents(menu,content);
        
        page.setSizeFull();
        
        addComponent(page);
    }
   
    
    // notify the view menu about view changes so that it can display which view
    // is currently active
    ViewChangeListener viewChangeListener = new ViewChangeListener() {

        @Override
        public boolean beforeViewChange(ViewChangeListener.ViewChangeEvent event) {
            return true;
        }

    };
}
