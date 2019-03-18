package com.mycompany.p2;

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
import com.vaadin.ui.Notification;


import org.apache.commons.lang3.StringUtils;
import com.vaadin.ui.UI;
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
@Theme("tests-valo-facebook")
public class Principal extends UI implements Button.ClickListener{
    
    public String table[][] = {{"7","8","9","/"},
                                {"4","5","6","*"},
                                {"1","2","3","-"},
                                {"0","=","C","+"}};
    public GridLayout grid;
    /*
    0 --> Esperando primer numero
    1 --> Esperando operacion
    2 --> Esperando segundo numero
    3 --> Esperando =
    4 --> Resultado
    */
    public Integer fase;
    public Integer resultado;
    public Label primeroEtiq;
    public Label esperandoEtiq;
    public Label segundoEtiq;
    public Label operacionEtiq;
    public Label resultadoEtiq;

    @Override
    protected void init(VaadinRequest vaadinRequest) {

        fase=0;
        esperandoEtiq = new Label("Esperando Primer Numero...");
        primeroEtiq = new Label("");
        segundoEtiq = new Label("");
        operacionEtiq = new Label("");
        resultadoEtiq = new Label("");

        grid = new GridLayout(4,4);
        
        createButtons();
        
        VerticalLayout vLayout = new VerticalLayout();
        
        HorizontalLayout hLayout = new HorizontalLayout();
        
        hLayout.addComponent(primeroEtiq);
        hLayout.addComponent(operacionEtiq);
        hLayout.addComponent(segundoEtiq);
        hLayout.addComponent(resultadoEtiq);
        
        vLayout.addComponent(esperandoEtiq);
        
        VerticalLayout vLayoutGrid = new VerticalLayout();

        vLayoutGrid.addComponent(grid);
        
        vLayout.addComponent(hLayout);
        vLayout.addComponent(vLayoutGrid);

        this.setContent(vLayout);
    }
    
    
    public void createButtons()
    {
        for(int i=0;i<table.length;i++)
        {
            for(int j=0;j<table[i].length;j++)
            {
                Button button = new Button(table[i][j]);
                
                button.addClickListener(this);
                
                grid.addComponent(button,j,i);
                
            }
        }
    }

    public void buttonClick(ClickEvent event) {

        Button button = event.getButton();

        String valorBotonPulsado = Character.toString(button.getCaption().charAt(0));

        realizarOperacionYMostrar(valorBotonPulsado, button);

    }

    public void realizarOperacionYMostrar(String valorBotonPulsado, Button boton) {
        //Si no pulsamos el boton de borrar
        if (!valorBotonPulsado.equals("C")) {
            switch (fase) {
                case 0:
                    if(StringUtils.isNumeric(valorBotonPulsado))
                    {
                        primeroEtiq.setValue(valorBotonPulsado);
                        esperandoEtiq.setValue("Esperando Operacion...");
                        fase++;
                    }
                    else
                    {
                        Notification.show("ERROR","Debes pulsar un numero",Notification.Type.ERROR_MESSAGE);
                    }   break;
                case 1:
                    if(!StringUtils.isNumeric(valorBotonPulsado))
                    {
                        operacionEtiq.setValue(valorBotonPulsado);
                        esperandoEtiq.setValue("Esperando Segundo Numero...");
                        fase++;
                    }
                    else
                    {
                        Notification.show("ERROR","Debes pulsar una operacion",Notification.Type.ERROR_MESSAGE);
                    }   break;
                case 2:
                    if(StringUtils.isNumeric(valorBotonPulsado))
                    {
                        segundoEtiq.setValue(valorBotonPulsado);
                        esperandoEtiq.setValue("Esperando =...");
                        fase++;
                    }
                    else
                    {
                        Notification.show("ERROR","Debes pulsar un numero",Notification.Type.ERROR_MESSAGE);
                    }   break;
                case 3:
                    if(valorBotonPulsado.equals("="))
                    {
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
                        {
                            resultadoEtiq.setValue("=" + Double.toString(result));
                        }
                        fase++;
                        esperandoEtiq.setValue("Para realizar otra operacion, pulsa C...");
                    }
                    else
                    {
                        Notification.show("ERROR","Debes pulsar =",Notification.Type.ERROR_MESSAGE);
                    }   break;
                case 4:
                    Notification.show("Atencion","Para realizar otra operacion, pulsa C",Notification.Type.HUMANIZED_MESSAGE);
                    break;
                default:
                    break;
            }

        } else {
            fase=0;
            esperandoEtiq.setValue("Esperando Primer Numero...");
            primeroEtiq.setValue("");
            segundoEtiq.setValue("");
            operacionEtiq.setValue("");
            resultadoEtiq.setValue("");
        }
    }
    
    @WebServlet(urlPatterns = "/*", name = "PrincipalServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = Principal.class, productionMode = false)
    public static class PrincipalServlet extends VaadinServlet {
    }
}
