package com.itfactory.services;

import com.itfactory.model.Course;
import com.itfactory.model.Student;
import com.itfactory.utils.DataLoaderUtils;
import com.itfactory.utils.EmptyStringException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.*;

import static com.itfactory.services.DataLoader.mapData;

public class DataSaver {

    private static Integer newIDCourse() {
        Scanner scanner = new Scanner(System.in);
        String newID = scanner.nextLine();
        Integer newIDCourse1 = null;

        for (int i = 0; newID.length() != 6; i++) {
            System.out.println("Introduceti un numar de minim 6 numere.");
            while (true) {
                String newID1 = scanner.nextLine();
                newID = newID1;
                try {
                    newIDCourse1 = Integer.parseInt(newID);
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("S-a introdus un numar invalid. Introduceti un numar valid.");
                }
            }
        }
        Integer newIDCourse = Integer.parseInt(newID);
        return newIDCourse;
    }

    private static Double newCoursePrice() {
        Scanner scanner = new Scanner(System.in);
        Double newCoursePrice1 = null;
        while (true) {
            String price = scanner.nextLine();
            try {
                newCoursePrice1 = Double.parseDouble(price);
                break;
            } catch (NumberFormatException e) {
                System.out.println("S-a introdus un pret invalid. Introduceti un pret valid.");
            }
        }
        Double newCoursePrice = newCoursePrice1;
        return newCoursePrice;
    }

    private static LocalDate newCourseDate() {
        LocalDate newCourseDate1;
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Introduceti o data de forma \"an-luna-zi\".");
            try {
                newCourseDate1 = LocalDate.parse(scanner.nextLine());
                while (newCourseDate1.isBefore(LocalDate.now())) {
                    System.out.println("S-a introdus o data depasita. Introduceti o data valida.");
                    LocalDate newCourseDate2 = LocalDate.parse(scanner.nextLine());
                    newCourseDate1 = newCourseDate2;
                }
                break;
            } catch (DateTimeParseException e) {
                System.out.println("S-a introdus un format de data invalid. Introduceti o data valida.");
            }
        }
        LocalDate newCourseDate = newCourseDate1;
        return newCourseDate;
    }

    private static Course putNewCourse() throws EmptyStringException, IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Introduceti datele pentru noul curs:\n" +
                "ID-ul cursului de forma a 6 numere: ");

        Integer newCourseID = newIDCourse();

        System.out.println("Numele cursului: ");
        String newCourseName = scanner.nextLine();

        System.out.println("Pretul cursului: ");
        double newCoursePrice = newCoursePrice();

        System.out.println("Data de start a cursului in urmatorul format \"an-luna-zi\": ");
        LocalDate newCourseDate = newCourseDate();

        Course newCourse = new Course(newCourseID, newCourseName, newCoursePrice, newCourseDate);

        return newCourse;
    }

    public static String saveCourses() throws EmptyStringException, IOException {

        Course datesCourse = putNewCourse();

        if (!(mapData().containsKey(datesCourse))) {

            String dateCourse = String.valueOf(datesCourse);
            dateCourse = dateCourse.concat("\n");

            Path path = Paths.get(DataLoaderUtils.COURSE_FILE_PATH);
            Files.write(path, dateCourse.getBytes(), StandardOpenOption.APPEND);

            return "Cursul " + dateCourse + " a fost inregistrat cu succes.";
        } else {
            return "Cursul exista deja inregistrat in sistem.";
        }
    }// optiunea 2 din consola, de introducere a unui nou curs

    private static Integer newIDStudent() {
        Scanner scanner = new Scanner(System.in);
        String newID = scanner.nextLine();
        Integer newIDStudent1 = null;

        for (int i = 0; newID.length() != 4 && newID.length() != 5 && newID.length() != 6; i++) {
            System.out.println("Introduceti un numar de minim 4-6 numere.");
            while (true) {
                String newID1 = scanner.nextLine();
                newID = newID1;
                try {
                    newIDStudent1 = Integer.parseInt(newID);
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("S-a introdus un numar invalid. Introduceti un numar valid.");
                }
            }
        }
        Integer newIDStudent = Integer.parseInt(newID);
        return newIDStudent;
    }

    private static Double newStudentBudget() {
        Scanner scanner = new Scanner(System.in);
        Double newStudentBudget1 = null;
        while (true) {
            String price = scanner.nextLine();
            try {
                newStudentBudget1 = Double.parseDouble(price);
                break;
            } catch (NumberFormatException e) {
                System.out.println("S-a introdus un pret invalid. Introduceti un pret valid.");
            }
        }
        Double newStudentBudget = newStudentBudget1;
        return newStudentBudget;
    }

    private static Student newStudent() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Introduceti datele pentru noul student:\n" +
                "ID-ul studentului de 4-6 numere: ");
        Integer newStudentID = newIDStudent();

        System.out.println("Numele studentului: ");
        String newStudentName = scanner.nextLine();

        System.out.println("Bugetul studentului: ");
        double newStudentBudget = newStudentBudget();

        Student newStudent = new Student(newStudentID, newStudentName, newStudentBudget);

        return newStudent;
    }

    public static Course checkIfCourseExist() throws EmptyStringException, IOException {
        Course checkIfCourseExist1 = null;

        while (checkIfCourseExist1 == null) {
            System.out.println("Introduceti numele cursului la care doriti sa inregistrati un nou student.");
            Scanner scanner = new Scanner(System.in);
            String lookingForCourseName = scanner.nextLine();

            for (int i = 0; i < DataLoader.readingCourses().size(); i++) {
                if (DataLoader.readingCourses().get(i).getCourseName().equalsIgnoreCase(lookingForCourseName)) {
                    checkIfCourseExist1 = DataLoader.readingCourses().get(i);
                    break;
                }
            }
        }
        Course checkIfCourseExist = checkIfCourseExist1;
        return checkIfCourseExist;
    }

    public static String availabelCourse(Course key) throws EmptyStringException, IOException {
        String mesaj;
        LocalDate courseDate = key.getStartDate();
        if (courseDate.isAfter(LocalDate.now())) {
            mesaj = acceptStudentToCourse(key);

        } else {
            mesaj = "Cursul cautat a inceput in data de: " + courseDate + "\n" +
                    "Incercati un alt curs:\n" + notStartedCourses();
        }
        return mesaj;
    }//optiunea 3 din consola

    public static List<Course> notStartedCourses() throws EmptyStringException, IOException {
        List<Course> notStartedCourses = new ArrayList<>();

        for (int i = 0; i < DataLoader.readingCourses().size(); i++) {
            if (DataLoader.readingCourses().get(i).getStartDate().isAfter(LocalDate.now())) {
                notStartedCourses.add(DataLoader.readingCourses().get(i));
            }
        }
        return notStartedCourses;
    }

    private static String acceptStudentToCourse(Course key) throws EmptyStringException, IOException {

        String mesaj = null;
        List<Student> countStudents = mapData().get(key);

        System.out.println("Studenti inscrisi sunt:\n" + countStudents);
        int y = 9;

        System.out.println("Sunt inscrisi: " + countStudents.size());
        System.out.println("Mai pot fi inscrisi: " + (y - countStudents.size()));

        if (countStudents.size() < 9) {
            mesaj = enrollNewStudent(key);
        } else {
            if (!(coursesNotFull().size() == 0)) {
                mesaj = "S-a depasita limita de studenti admisa la curs.\n" +
                        "cursuri disponibile mai sunt:\n" + coursesNotFull();
            } else {
                mesaj = "Nu sunt cursuri disponibile.";
            }
        }
        return mesaj;
    }

    private static List<Course> coursesNotFull() throws EmptyStringException, IOException {
        List<Course> coursesNotFull = new ArrayList<>();

        for (int i = 0; i < DataSaver.notStartedCourses().size(); i++) {
            if (mapData().get(DataSaver.notStartedCourses().get(i)).size() < 9) {
                coursesNotFull.add(DataSaver.notStartedCourses().get(i));
            }
        }
        return coursesNotFull;
    }

    private static String enrollNewStudent(Course key) throws IOException, EmptyStringException {
        Student newEnrolledStudent = DataSaver.newStudent();
        Double payFromBudget = newEnrolledStudent.getBudget();
        Double toBeDeductFromBudget = key.getPrice();
        Double differenceBetwenBudgetAndPrice = payFromBudget - toBeDeductFromBudget;

        newEnrolledStudent.setBudget(differenceBetwenBudgetAndPrice);

        String mesaj;

        if (!(mapData().get(key).contains(newEnrolledStudent))) {

            if (differenceBetwenBudgetAndPrice > 0 || differenceBetwenBudgetAndPrice == 0) {

                Path path = Paths.get(DataLoaderUtils.STUDENT_FILE_PATH);
                Files.write(path, (String.valueOf(newEnrolledStudent).concat("\n")).getBytes(), StandardOpenOption.APPEND);
                mesaj = "Studentul " + newEnrolledStudent + " a fost inscris la curs.";

                mapToAdd(key, newEnrolledStudent);
            } else {
                mesaj = "Studentul nu are buget pentru cursul ales.";

            }
        } else {
            mesaj = "Studentul este deja inscris la acest curs.";
        }
        return mesaj;
    }

    private static String mapToAdd(Course key, Student newEnrolledStudent) throws IOException, EmptyStringException {

        String mapareToAdd = String.valueOf(newEnrolledStudent.getStudentId() + "," + key.getCourseID()).concat("\n");

        Path path1 = Paths.get(DataLoaderUtils.MAPPING_FILE_PATH);
        Files.write(path1, mapareToAdd.getBytes(), StandardOpenOption.APPEND);

        return mapareToAdd;
    }
}

