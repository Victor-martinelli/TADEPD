package com.mycompany.p1;

import java.util.ArrayList;
import java.util.List;

import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.model.ChartType;
import com.vaadin.addon.charts.model.Credits;
import com.vaadin.addon.charts.model.DataSeries;
import com.vaadin.addon.charts.model.DataSeriesItem;
import com.vaadin.addon.charts.model.PlotOptionsPie;

@SuppressWarnings("serial")
public class TopSixTheatersChart extends Chart {
    
    Datos d = new Datos();

    public TopSixTheatersChart() {
        super(ChartType.PIE);

        setCaption("Popular Movies");
        getConfiguration().setTitle("");
        getConfiguration().getChart().setType(ChartType.PIE);
        getConfiguration().getChart().setAnimation(false);
        setWidth("100%");
        setHeight("90%");

        DataSeries series = new DataSeries();

        List<Movie> movies = new ArrayList<Movie>(d.getMovies());
        for (int i = 0; i < 6; i++) {
            Movie movie = movies.get(i);
            DataSeriesItem item = new DataSeriesItem(movie.getTitle(),
                    movie.getScore());
            series.add(item);
        }
        getConfiguration().setSeries(series);

        PlotOptionsPie opts = new PlotOptionsPie();
        opts.setBorderWidth(0);
        opts.setShadow(false);
        opts.setAnimation(false);
        getConfiguration().setPlotOptions(opts);

        Credits c = new Credits("");
        getConfiguration().setCredits(c);
    }

}
