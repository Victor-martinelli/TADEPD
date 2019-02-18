package Grupo1;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.VerticalSplitPanel;

/**
 * This UI is the application entry point. A UI may either represent a browser window 
 * (or tab) or some part of a html page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be 
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@Theme("mytheme")
public class MyUI extends UI {

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        /*
        final VerticalLayout layout = new VerticalLayout();
        
        final TextField name = new TextField();
        name.setCaption("Type your name here:");

        Button button = new Button("Click Me");
        button.addClickListener( e -> {
            layout.addComponent(new Label("Thanks " + name.getValue() 
                    + ", it works!"));
        });
        
        layout.addComponents(name, button);
        layout.setMargin(true);
        layout.setSpacing(true);
        */
        
        GridLayout grid = new GridLayout(4,1);
        
        HorizontalLayout layoutNumbers1 = new HorizontalLayout();
        
        //VerticalSplitPanel vsplit = new VerticalSplitPanel(); 
        
        createNumbers(layoutNumbers1);
        grid.addComponent(layoutNumbers1,0,0);
        
        VerticalLayout layoutNumbers2 = new VerticalLayout();
        createNumbers(layoutNumbers2);
        grid.addComponent(layoutNumbers2,1,0);
        
        HorizontalLayout operationLayout = new HorizontalLayout();
        operationLayout.addComponent(new Button("+"));
        operationLayout.addComponent(new Button("-"));
        operationLayout.addComponent(new Button("/"));
        operationLayout.addComponent(new Button("*"));
        
        grid.addComponent(operationLayout, 2, 0);
        
        this.setContent(grid);
    }
    
    protected void createNumbers(Layout layout)
    {
        for (int i = 0; i <=9; i++) {
            Button button = new Button(Integer.toString(i));
            button.
           layout.addComponent(button);
        }
    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
