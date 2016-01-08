package com.cemgokmen.optimexam;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by funstein on 08/01/16.
 */
public class Schedule {
    private int days;
    private int maxExamsPerDay;
    private List<List<Set<Course>>> exams;

    public Schedule(int days, int maxExamsPerDay) {
        this.days = days;
        this.maxExamsPerDay = maxExamsPerDay;
        this.exams = new ArrayList<List<Set<Course>>>();
        for (int day = 0; day < days; day++) {
            List<Set<Course>> slots = new ArrayList<Set<Course>>();
            for (int slot = 0; slot < maxExamsPerDay; slot++) {
                Set<Course> set = new HashSet<Course>();
                slots.add(set);
            }
            this.exams.add(slots);
        }
    }

    public void addExam(int day, int slot, Course course) {
        exams.get(day).get(slot).add(course);
    }

    public int count() {
        int count = 0;
        for (int day = 0; day < days; day++) {
            List<Set<Course>> slots = exams.get(day);
            for (int slot = 0; slot < maxExamsPerDay; slot++) {
                Set<Course> set = slots.get(slot);
                count += set.size();
            }
        }

        return count;
    }

    public Set<Course> getExams(int day, int slot) {
        return exams.get(day).get(slot);
    }

    public int getDays() {
        return days;
    }

    public int getMaxExamsPerDay() {
        return maxExamsPerDay;
    }

    public Schedule clone() {
        Schedule newSchedule = new Schedule(days, maxExamsPerDay);
        for (int day = 0; day < days; day++) {
            List<Set<Course>> slots = exams.get(day);
            for (int slot = 0; slot < maxExamsPerDay; slot++) {
                Set<Course> set = slots.get(slot);
                for (Course course : set) {
                    newSchedule.addExam(day, slot, course);
                }
            }
        }

        return newSchedule;
    }

    //TODO: Add toString method.
}
