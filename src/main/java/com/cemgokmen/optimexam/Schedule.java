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
    private int slots;
    private List<List<Set<Course>>> exams;

    public Schedule(int days, int maxExamsPerDay) {
        this.days = days;
        this.slots = maxExamsPerDay;
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
            for (int slot = 0; slot < this.slots; slot++) {
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

    public int getSlots() {
        return slots;
    }

    public Schedule clone() {
        Schedule newSchedule = new Schedule(days, slots);
        for (int day = 0; day < days; day++) {
            List<Set<Course>> slots = exams.get(day);
            for (int slot = 0; slot < this.slots; slot++) {
                Set<Course> set = slots.get(slot);
                for (Course course : set) {
                    newSchedule.addExam(day, slot, course);
                }
            }
        }

        return newSchedule;
    }

    public String toString() {
        String output = "";
        for (int day = 0; day < days; day++) {
            output += "Day " + (day + 1) + "\n";
            output += "\n";
            output += "|" + Optimexam.repeat(slots, "----------------------|") + "\n";

            output += "|";
            for (int i = 0; i < slots; i++) {
                output += "       PERIOD " + (i + 1) + "       |";
            }
            output += "\n";
            output += "|" + Optimexam.repeat(slots, "----------------------|") + "\n";

            int moreEntries = -1;
            for (int j = 0; j < slots; j++) {
                int size = exams.get(day).get(j).size();
                if (size > moreEntries) moreEntries = size;
            }

            if (moreEntries > 0) {
                // Unfortunately, since sets don't have indices, we need to convert to lists first :(
                List<List<Course>> slotLists = new ArrayList<List<Course>>();
                for (int slot = 0; slot < slots; slot++) {
                    slotLists.add(new ArrayList<Course>(exams.get(day).get(slot)));
                }

                for (int entry = 0; entry < moreEntries; entry++) {
                    output += "| ";
                    for (int slotI = 0; slotI < slots; slotI++) {
                        List<Course> slot = slotLists.get(slotI);
                        String add = "";
                        if (entry < slot.size()) {
                            add = slot.get(entry).getName().toUpperCase();
                        }

                        output += Optimexam.pad(add, 20) + " |";
                    }
                    output += "\n";
                }
            }

            output += "|" + Optimexam.repeat(slots, "----------------------|") + "\n";
            output += "\n\n";

        }

        return output;
    }
}
