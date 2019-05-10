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
import tad.grupo1.proyecto.views.panels.CommentAdminPanel;
import tad.grupo1.proyecto.views.panels.ResultadoBusquedaVideosPanel;
import tad.grupo1.proyecto.views.panels.SubirVideoPanel;
import tad.grupo1.proyecto.views.panels.SuscripcionesPanel;
import tad.grupo1.proyecto.views.panels.UserAdminPanel;
import tad.grupo1.proyecto.views.panels.VideoAdminPanel;
import tad.grupo1.proyecto.views.panels.VideoPanel;

/**
 * Content of the UI when the user is logged in.
 * 
 * 
 */
public class MainScreenAdmin extends HorizontalLayout {
    
    public static UsuarioController uc = new UsuarioController();
    public static VideoController vc = new VideoController();
    HorizontalLayout page;
    VerticalLayout videoContainer;
    VerticalLayout content;

    public MainScreenAdmin() {
        content = new VerticalLayout();
        
        
       
        content.setSizeFull();
        
        Button closeSession = new Button("Cerrar Sesion");
        
         closeSession.addClickListener(new Button.ClickListener() {
                    @Override
                    public void buttonClick(Button.ClickEvent event) {
                        
                        Notification.show("Exito",
                        "Se ha cerrado la sesion correctamente, ya puede cerrar esta ventana.",
                        Notification.Type.HUMANIZED_MESSAGE);
                        session.invalidate();
                    }
                });
         
      content.addComponents(new UserAdminPanel(),new VideoAdminPanel(),new CommentAdminPanel(),closeSession);
        
        addComponents(content);
    }
    

    
}
