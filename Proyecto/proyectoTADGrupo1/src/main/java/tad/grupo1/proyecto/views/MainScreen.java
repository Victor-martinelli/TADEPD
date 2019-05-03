package tad.grupo1.proyecto.views;

import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.FileResource;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;
import java.io.File;
import tad.grupo1.proyecto.controllers.UsuarioController;
import tad.grupo1.proyecto.controllers.VideoController;
import static tad.grupo1.proyecto.views.MainUI.session;
import tad.grupo1.proyecto.views.panels.VideoPanel;

/**
 * Content of the UI when the user is logged in.
 * 
 * 
 */
public class MainScreen extends HorizontalLayout {
    
    private Menu menu;
    public static UsuarioController uc = new UsuarioController();
    public static VideoController vc = new VideoController();

    public MainScreen() {

        String username = session.getAttribute("user").toString();
        
        /*
        
        CAMBIAR ESTO A LA PAGINA DE INICIO, POR AHORA PARA TESTEAR
        CUANDO INICIES SESION TE MOSTRARÁ UN VIDEO POR DEFECTO
        
        
        menu.addView(new SampleCrudView(), SampleCrudView.VIEW_NAME,
                SampleCrudView.VIEW_NAME, FontAwesome.EDIT);
        menu.addView(new AboutView(), AboutView.VIEW_NAME, AboutView.VIEW_NAME,
                FontAwesome.INFO_CIRCLE);

        */

        VideoPanel videoContainer = new VideoPanel("mikehunt","despacito2");
        
        Menu menu = createMenu(username);
        
        HorizontalLayout top = createTopMenu();
        
        
        VerticalLayout content = new VerticalLayout(top,videoContainer);
        
        top.setSizeFull();
        
        HorizontalLayout page = new HorizontalLayout(menu,content); 

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
        
        aux.getSidebar().setMargin(false);
        aux.getSidebar().setSpacing(false);
        aux.getSidebar().setSizeFull();
        
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
        
        return aux;
    }
    
    public HorizontalLayout createTopMenu()
    {
        HorizontalLayout aux = new HorizontalLayout();
        
        TextField search = new TextField();
        
        Button searchButton = new Button(FontAwesome.SEARCH);
        
        
        
        aux.addComponents(search,searchButton);
        
        aux.setMargin(false);
        aux.setSpacing(false);
        aux.setSizeFull();
        
        //aux.setSizeFull();
        //search.setSizeFull();
        
        aux.setComponentAlignment(search,Alignment.MIDDLE_CENTER);
        
        
        return aux;
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
