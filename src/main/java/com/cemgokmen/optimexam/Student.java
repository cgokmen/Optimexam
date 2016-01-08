package com.cemgokmen.optimexam;

import com.google.common.collect.Sets;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by funstein on 08/01/16.
 */
public class Student {
    private String name;
    private int number;
    private Set<Course> courses;

    public Student(String name, int number) {
        this.name = name;
        this.number = number;
        this.courses = new HashSet<Course>();
    }

    public String getName() {
        return this.name;
    }

    public int getNumber() {
        return this.number;
    }

    public int getCourseCount() {
        return this.courses.size();
    }

    public void addCourse(Course course) {
        courses.add(course);
    }

    public boolean hasConflict(Schedule schedule) {
        if (schedule == null) throw new NullPointerException();
        for (int day = 0; day < schedule.getDays(); day++) {
            for (int slot = 0; slot < schedule.getSlots(); slot++) {
                Set<Course> exams = schedule.getExams(day, slot);
                int intersections = Sets.intersection(exams, courses).size();

                if (intersections > 1) return true;
            }
        }
        return false;
    }

    public int[] calculateDays(Schedule schedule) throws Exception {
        if (schedule == null) throw new NullPointerException();
        int[] numberOfDaysByExamCount = {0, 0, 0};
        for (int day = 0; day < schedule.getDays(); day++) {
            int examsForTheDay = 0;
            for (int slot = 0; slot < schedule.getSlots(); slot++) {
                Set<Course> exams = schedule.getExams(day, slot);
                int intersections = Sets.intersection(exams, courses).size();
                if (intersections == 1) {
                    examsForTheDay++;
                } else if (intersections > 1) {
                    throw new Exception(String.format("Student %s has conflicting classes in schedule", number + ""));
                }
            }
            numberOfDaysByExamCount[examsForTheDay]++;
        }

        return numberOfDaysByExamCount;
    }
}
