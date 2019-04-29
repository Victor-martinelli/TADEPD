package tad.grupo1.proyecto.views;

import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import tad.grupo1.proyecto.views.panels.VideoPanel;

/**
 * Content of the UI when the user is logged in.
 * 
 * 
 */
public class MainScreen extends HorizontalLayout {
    
    private Menu menu;

    public MainScreen(MainUI ui) {

        
        /*
        
        CAMBIAR ESTO A LA PAGINA DE INICIO, POR AHORA PARA TESTEAR
        CUANDO INICIES SESION TE MOSTRARÁ UN VIDEO POR DEFECTO
        
        
        menu.addView(new SampleCrudView(), SampleCrudView.VIEW_NAME,
                SampleCrudView.VIEW_NAME, FontAwesome.EDIT);
        menu.addView(new AboutView(), AboutView.VIEW_NAME, AboutView.VIEW_NAME,
                FontAwesome.INFO_CIRCLE);

        */

        VideoPanel videoContainer = new VideoPanel("mikehunt","despacito2");
        
        HorizontalLayout page = new HorizontalLayout(new Menu(),videoContainer); 
        

        addComponent(page);
        //addComponent(videoContainer);
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
