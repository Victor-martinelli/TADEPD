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
 * This UI is the application entry point. A UI may either represent a browser
 * window (or tab) or some part of a html page where a Vaadin application is
 * embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is
 * intended to be overridden to add component to the user interface and
 * initialize non-component functionality.
 */
@Theme("mytheme")
public class MyUI extends UI implements Button.ClickListener {

    public GridLayout grid;
    public Integer resultado;
    public Label primeroEtiq;
    public Label segundoEtiq;
    public Label operacionEtiq;
    public Label resultadoEtiq;

    @Override
    protected void init(VaadinRequest vaadinRequest) {

        primeroEtiq = new Label("");
        segundoEtiq = new Label("");
        operacionEtiq = new Label("");
        resultadoEtiq = new Label("");

        grid = new GridLayout(7, 1);

        HorizontalLayout layoutNumbers1 = new HorizontalLayout();

        //VerticalSplitPanel vsplit = new VerticalSplitPanel(); 
        createNumbers(layoutNumbers1, "0");
        grid.addComponent(layoutNumbers1, 0, 0);

        VerticalLayout layoutNumbers2 = new VerticalLayout();

        createNumbers(layoutNumbers2, "1");
        grid.addComponent(layoutNumbers2, 1, 0);

        HorizontalLayout operationLayout = new HorizontalLayout();

        operationLayout.addComponent(createOperationButton("+"));
        operationLayout.addComponent(createOperationButton("-"));
        operationLayout.addComponent(createOperationButton("*"));
        operationLayout.addComponent(createOperationButton("/"));

        grid.addComponent(operationLayout, 2, 0);

        grid.addComponent(primeroEtiq, 3, 0);
        grid.addComponent(operacionEtiq, 4, 0);
        grid.addComponent(segundoEtiq, 5, 0);
        grid.addComponent(resultadoEtiq, 6, 0);

        this.setContent(grid);
    }

    public void createNumbers(Layout layout, String phase) {
        for (int i = 0; i <= 9; i++) {
            Button button = new Button(Integer.toString(i));
            //This is so that we know if its first or second
            button.setId(phase);
            button.addClickListener(this);
            layout.addComponent(button);
        }
    }

    public Button createOperationButton(String value) {
        Button operation = new Button(value);

        operation.addClickListener(this);

        return operation;
    }

    public void buttonClick(ClickEvent event) {

        Button button = event.getButton();

        String valorBotonPulsado = Character.toString(button.getCaption().charAt(0));

        realizarOperacionYMostrar(valorBotonPulsado, button);

    }

    public void realizarOperacionYMostrar(String valorBotonPulsado, Button boton) {
        //Si pulsamos un boton de numero
        if (StringUtils.isNumeric(valorBotonPulsado)) {

            //Si pulsamos uno de los segundos botones y hemos pulsado anteriormente
            // los primeros botones, realizamos el calculo
            if (boton.getId().equals("0")) {
                primeroEtiq.setValue(valorBotonPulsado);
            } else {
                segundoEtiq.setValue(valorBotonPulsado);

                if (StringUtils.isNumeric(primeroEtiq.getValue())) {
                    Double primero = Double.parseDouble(primeroEtiq.getValue());
                    Double segundo = Double.parseDouble(segundoEtiq.getValue());
                    Double result = 0.0;
                    boolean error = false;
                    String operacion = operacionEtiq.getValue();

                        switch (operacion) {
                            case "+": {
                                result = primero + segundo;
                                break;
                            }

                            case "-": {
                                result = primero - segundo;
                                break;
                            }
                            case "*": {
                                result = primero * segundo;
                                break;
                            }
                            case "/": {
                                if(segundo==0.0)
                                {
                                   resultadoEtiq.setValue("= ERROR: Operacion no permitida");
                                   error=true;
                                }
                                else
                                result = primero/segundo;
                                break;
                            }

                        }          
                        if(!error)
                        resultadoEtiq.setValue("=" + Double.toString(result));
                }

            }

        } else {
            operacionEtiq.setValue(valorBotonPulsado);
        }

    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
