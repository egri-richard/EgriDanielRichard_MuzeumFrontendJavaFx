package com.example.museumrest;

public class Painting {
    int id;
    String title;
    int year;
    boolean on_display;

    public Painting(int id, String title, int year, boolean on_display) {
        this.id = id;
        this.title = title;
        this.year = year;
        this.on_display = on_display;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public boolean getOn_display() {
        return on_display;
    }

    public void setOn_display(boolean on_display) {
        this.on_display = on_display;
    }

    @Override
    public String toString() {
        return "id: " +id+ " title: " +title+ " year: " +year+ " On display: " +on_display+ "\n";
    }
}
