package com.mycompany.p1;

import com.vaadin.server.VaadinRequest;
import com.vaadin.util.CurrentInstance;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class Datos {

    private static Collection<Movie> movies;
    private static Collection<MovieRevenue> revenue;

    public Datos() {
        movies = loadMoviesData();
        revenue = getRevenues();
    }

    public Collection<Movie> getMovies() {
        return Collections.unmodifiableCollection(movies);
    }
    
    public Collection<MovieRevenue> getRevenues() {
        Collection <MovieRevenue> c = new ArrayList();
        c.add(new MovieRevenue("The Boxtrolss", 383.98));
        c.add(new MovieRevenue("When The Game Stands Tall", 351.16));
        c.add(new MovieRevenue("The Skeleton Twins", 348.43));
        c.add(new MovieRevenue("The Equalizer", 322.52));
        c.add(new MovieRevenue("My Old Lady", 286.13));
        c.add(new MovieRevenue("This Is Where I Leave You", 280.49));
        c.add(new MovieRevenue("Let´s Be Cops", 272.43));
        c.add(new MovieRevenue("My Little Pony: Equestria Girls - Rainbow Rocks", 239.78));
        c.add(new MovieRevenue("Guardians of the Galaxy", 230.6));
        c.add(new MovieRevenue("Gone Girl", 225.39));
        return c;
    }

    private static Collection<Movie> loadMoviesData() {

        JsonObject json = null;
        File cache;
        VaadinRequest vaadinRequest = CurrentInstance.get(VaadinRequest.class);

        File baseDirectory = vaadinRequest.getService().getBaseDirectory();
        cache = new File(baseDirectory + "/movies.txt");

        try {
            if (cache.exists()
                    && System.currentTimeMillis() < cache.lastModified()
                    + (1000 * 60 * 60 * 24)) {
                json = readJsonFromFile(cache);
            } else {

                json = readJsonFromFile(new File(baseDirectory + "/movies-fallback.txt"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        Collection<Movie> result = new ArrayList<Movie>();
        if (json != null) {
            JsonArray moviesJson;

            moviesJson = json.getAsJsonArray("movies");
            for (int i = 0; i < moviesJson.size(); i++) {
                JsonObject movieJson = moviesJson.get(i).getAsJsonObject();
                JsonObject posters = movieJson.get("posters").getAsJsonObject();
                if (!posters.get("profile").getAsString()
                        .contains("poster_default")) {
                    Movie movie = new Movie();
                    movie.setId(i);
                    movie.setTitle(movieJson.get("title").getAsString());

                    try {
                        movie.setScore(movieJson.get("ratings")
                                .getAsJsonObject().get("critics_score")
                                .getAsInt());
                    } catch (Exception e) {
                    }

                    result.add(movie);

                }
            }
        }
        return result;
    }

    private static JsonObject readJsonFromFile(File path) throws IOException {
        BufferedReader rd = new BufferedReader(new FileReader(path));
        String jsonText = readAll(rd);
        JsonElement jelement = new JsonParser().parse(jsonText);
        JsonObject jobject = jelement.getAsJsonObject();
        return jobject;
    }

    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }
}
