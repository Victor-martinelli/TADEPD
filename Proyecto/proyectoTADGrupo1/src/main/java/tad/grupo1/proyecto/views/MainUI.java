package tad.grupo1.proyecto.views;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.annotations.Viewport;
import com.vaadin.server.Responsive;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.server.WrappedSession;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;
import tad.grupo1.proyecto.views.LoginScreen.LoginListener;

/**
 * This UI is the application entry point. A UI may either represent a browser window 
 * (or tab) or some part of a html page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be 
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@Theme("mytheme")
@Viewport("user-scalable=no,initial-scale=1.0")
public class MainUI extends UI {

    
    public static WrappedSession session;
    
    @Override
    protected void init(VaadinRequest vaadinRequest) {
        session = getSession().getSession();
        Responsive.makeResponsive(this);
        setLocale(vaadinRequest.getLocale());
        getPage().setTitle("My");
        //Si el usuario no estaba logeado
        if (session.getAttribute("user")==null) {
            setContent(new LoginScreen(new LoginListener() {
                @Override
                //Cuando el login sea correcto
                public void loginSuccessful() {
                    showMainView();
                }
            }));
        } else {
            //Si el usuario ya estaba logeado
            showMainView();
        }
    }

    protected void showMainView() {
        addStyleName(ValoTheme.UI_WITH_MENU);
        setContent(new MainScreen(MainUI.this));
        //Tras setear el navigator dentro del constructor de Main Screen, vamos a esa vista
        getNavigator().navigateTo(getNavigator().getState());
    }
    
    
    @WebServlet(urlPatterns = "/*", name = "MainUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MainUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
