package com.cemgokmen.optimexam;

import java.util.Scanner;
import java.util.ArrayList;

/**
 * Hello world!
 *
 */
public class Optimexam
{
    private static Schedule winningSchedule;
    private static int winningScore;

    public static void main( String[] args ) throws Exception {
        Scanner console = new Scanner(System.in);
        System.out.println("\nWelcome to Optimexam!");

        System.out.print("\nPlease enter the name of the file containing your student data in a CSV format (number, name, course1, course2, ...): ");
        String file = console.nextLine();
        System.out.println("Thank you. Your data is now loading...");

        //TODO: Load data from file!
        ArrayList<Course> courses = new ArrayList<Course>();
        ArrayList<Student> students = new ArrayList<Student>();

        //TODO: Load schedule size
        System.out.print("\nPlease enter the number of days to have in your schedule: ");
        int days = console.nextInt();
        System.out.println("Thank you.");

        System.out.print("\nPlease enter the number of exam slots per day to have in your schedule: ");
        int slots = console.nextInt();
        System.out.println("Thank you.");

        Schedule schedule = new Schedule(days, slots);

        System.out.println("\nStarting solution...");
        Student[] studentArray = new Student[students.size()];
        solve(students.toArray(studentArray), schedule, 0, 0);
        // TODO: Print some progress!
        System.out.println("Solution complete.");

        System.out.println("\nHere is the winning schedule:");
        System.out.println((winningSchedule != null) ? winningSchedule.toString() : "NULL");

        System.out.println("\nThe winning score was " + winningScore + ".\n");

        System.out.println("Here is a list of students with their number of days by # of exams per day:");
        if (students.size() > 0) {
            System.out.println("\n        NAME             ID     FR    1E    2E");
            for (Student student : students) {
                int[] nODBEC = student.calculateDays(schedule); // Number of days by exam count
                System.out.printf("%s    %s    %s    %s    %s%n", pad(student.getName(), 20), pad(student.getNumber() + "", 4), pad(nODBEC[0] + "", 3), pad(nODBEC[1] + "", 3), pad(nODBEC[2] + "", 3));
            }
        } else {
            System.out.println("No students found!");
        }

        System.out.println("\nThanks for using Optimexam!\n");
    }

    public static String pad(String str, int chars) {
        if (str.length() > chars) {
            return str.substring(0, chars);
        } else {
            String newstr = str;
            while (newstr.length() < chars) {
                newstr = " " + newstr;
            }
            return newstr;
        }
    }

    public static String repeat(int count, String with) {
        return new String(new char[count]).replace("\0", with);
    }

    public static void solve(Student[] students, Schedule schedule, int day, int slot) {
        /* Constraints:
            Score must be greater than 1.
         */
        // TODO: Write the solving code.
    }

    public int score(Student[] students, Schedule schedule) {
        int score = 0;

        for (Student student : students) {
            try {
                int[] numberOfDaysByExamCount = student.calculateDays(schedule);

                // We add two points per empty day and one point for a half-full day
                score += numberOfDaysByExamCount[0] * 2 + numberOfDaysByExamCount[1];
            } catch (Exception e) {
                return -1;
            }
        }

        return score;
    }
}
