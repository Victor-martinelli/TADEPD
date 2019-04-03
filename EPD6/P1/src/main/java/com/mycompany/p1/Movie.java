package com.mycompany.p1;

public final class Movie {

    private long id;
    private String title;
    private int score;

    public void setId(final long id) {
        this.id = id;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public int getScore() {
        return score;
    }

    public void setScore(final int score) {
        this.score = score;
    }

    public String getTitle() {
        return title;
    }

    public long getId() {
        return id;
    }

}
