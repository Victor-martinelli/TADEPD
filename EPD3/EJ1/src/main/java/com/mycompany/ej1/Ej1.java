package com.mycompany.ej1;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

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
public class Ej1 extends UI {

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        final VerticalLayout layout = new VerticalLayout();
        Calendar cal = Calendar.getInstance(Locale.ITALY);
        String[] strMonths = new String[]{
            "Enero",
            "Febrero",
            "Marzo",
            "Abril",
            "Mayo",
            "Junio",
            "Julio",
            "Agosto",
            "Septiembre",
            "Octubre",
            "Noviembre",
            "Diciembre"};
        Button button = new Button("Pulse Aquí");
        button.addClickListener(e -> {
            String saludo = "Buenas";
            if (cal.get(Calendar.HOUR_OF_DAY) >= 5 && cal.get(Calendar.HOUR_OF_DAY) < 12) {
                saludo = "Buenos Días";
            }
            if (cal.get(Calendar.HOUR_OF_DAY) >= 12 && cal.get(Calendar.HOUR_OF_DAY) < 21) {
                saludo = "Buenas Tardes";
            }
            if (cal.get(Calendar.HOUR_OF_DAY) >= 21 && cal.get(Calendar.HOUR_OF_DAY) < 24) {
                saludo = "Buenas Noches";
            }
            String s = saludo + ", hoy es " + cal.get(Calendar.DAY_OF_MONTH) + " de " + strMonths[cal.get(Calendar.MONTH)] + " de " + cal.get(Calendar.YEAR) + ".";
            layout.addComponent(new Label(s));
        });

        layout.addComponents(button);
        layout.setMargin(true);
        layout.setSpacing(true);

        setContent(layout);
    }

    @WebServlet(urlPatterns = "/*", name = "Ej1Servlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = Ej1.class, productionMode = false)
    public static class Ej1Servlet extends VaadinServlet {
    }
}
