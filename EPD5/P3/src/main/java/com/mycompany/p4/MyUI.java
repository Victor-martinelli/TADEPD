package com.mycompany.p4;

import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.model.ChartType;
import com.vaadin.addon.charts.model.Configuration;
import com.vaadin.addon.charts.model.DataSeries;
import com.vaadin.addon.charts.model.DataSeriesItem;
import com.vaadin.addon.charts.model.PlotOptionsPie;
import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
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
@Theme("mytheme")
public class MyUI extends UI {

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        final VerticalLayout layout = new VerticalLayout();

        Chart chart = new Chart(ChartType.PIE);
        Configuration conf = chart.getConfiguration();

        conf.setTitle("Diputados por Partido");
        conf.setSubTitle("Elecciones generales 2016");

        PlotOptionsPie options = new PlotOptionsPie();
        options.setInnerSize("0");
        options.setSize("75%");  // Default
        options.setCenter("50%", "50%"); // Default
        conf.setPlotOptions(options);

        DataSeries series = new DataSeries("Diputados");
        DataSeriesItem pp = new DataSeriesItem("PP", 137);
        pp.setSliced(true);
        series.add(pp);
        series.add(new DataSeriesItem("PSOE", 85));
        series.add(new DataSeriesItem("UNIDOS PODEMOS", 71));
        series.add(new DataSeriesItem("CIUDADANOS", 32));
        series.add(new DataSeriesItem("ERC", 9));
        series.add(new DataSeriesItem("CDC", 8));
        series.add(new DataSeriesItem("PNV", 5));
        series.add(new DataSeriesItem("EH BILDU", 2));
        series.add(new DataSeriesItem("CCA-PNC", 1));
        conf.addSeries(series);

        layout.addComponent(chart);
        layout.setMargin(true);
        layout.setSpacing(true);

        setContent(layout);
    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
