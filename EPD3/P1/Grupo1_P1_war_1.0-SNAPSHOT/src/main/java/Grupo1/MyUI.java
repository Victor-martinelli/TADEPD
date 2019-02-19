package Grupo1;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.UI;
import org.apache.commons.lang3.StringUtils;
import com.vaadin.ui.VerticalLayout;

/**
 * This UI is the application entry point. A UI may either represent a browser window 
 * (or tab) or some part of a html page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be 
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@Theme("mytheme")
public class MyUI extends UI implements Button.ClickListener{
    
    public GridLayout grid;
    public Integer resultado;
    public Label resultadoEtiq;
    
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
        grid = new GridLayout(4,1);
        
        HorizontalLayout layoutNumbers1 = new HorizontalLayout();
        
        //VerticalSplitPanel vsplit = new VerticalSplitPanel(); 
        
        createNumbers(layoutNumbers1);
        grid.addComponent(layoutNumbers1,0,0);
        
        VerticalLayout layoutNumbers2 = new VerticalLayout();

        createNumbers(layoutNumbers2);
        grid.addComponent(layoutNumbers2,1,0);
        
        HorizontalLayout operationLayout = new HorizontalLayout();
        
        operationLayout.addComponent(createOperationButton("+"));
        operationLayout.addComponent(createOperationButton("-"));
        operationLayout.addComponent(createOperationButton("*"));
        operationLayout.addComponent(createOperationButton("/"));
        
        grid.addComponent(operationLayout, 2, 0);
        
        this.setContent(grid);
    }
    
    protected void createNumbers(Layout layout)
    {
        for (int i = 0; i <=9; i++) {
            Button button = new Button(Integer.toString(i));
            button.addListener(this);
           layout.addComponent(button);
        }
    }
    

    // application.
    public void buttonClick(ClickEvent event) {

        Button button = event.getButton();

        String valorBotonPulsado = Character.toString(button.getCaption().charAt(0));

        // Calculate the new value
        int newValue = realizarOperacion(valorBotonPulsado);

        // Update the result label with the new value
        display.setValue(newValue);

    }
    
    protected int realizarOperacion(String valorBotonPulsado)
    {
        
        //Si pulsamos un boton de numero
        if(StringUtils.isNumeric(valorBotonPulsado))
        {
            
        }
        
    }
    
    
    
    protected Button createOperationButton(String value)
    {
        Button operation = new Button(value);
        
        operation.addClickListener(new Button.ClickListener() { 
                public void buttonClick(ClickEvent event) {
                operacion=value;
                }
            }
            );
        
        return operation;
    }
    
    

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
