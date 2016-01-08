package com.cemgokmen.optimexam;

/**
 * Created by funstein on 08/01/16.
 */
public class Course {
    private String name;
    private int id;
    private boolean hasExam;

    public Course(String name, int id, boolean hasExam) {
        this.name = name;
        this.id = id;
        this.hasExam = hasExam;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public boolean hasExam() {
        return hasExam;
    }

}
