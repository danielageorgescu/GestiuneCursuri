package com.itfactory.services;

import com.itfactory.utils.EmptyStringException;
import com.itfactory.model.Course;
import com.itfactory.model.Student;
import com.itfactory.utils.DataLoaderUtils;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.*;

public class DataLoader {

    public static List<Course> readingCourses() throws IOException,NumberFormatException {
        Path pathCourse = Paths.get(DataLoaderUtils.COURSE_FILE_PATH);
        List<String> readCoursesline = Files.readAllLines(pathCourse);
        //citirea din fisier si verificarea ca nu este empty fisierul
        if (readCoursesline.isEmpty()) {
            System.out.println("Fisierul este gol.");
        }
        List<Course> courseDetails = new ArrayList<>();
        for (String line : readCoursesline) {
            String[] courseValues = line.split(",");

            courseDetails.add(new
                    Course(Integer.parseInt(courseValues[0]),
                    courseValues[1],
                    Double.parseDouble(courseValues[2]),
                    LocalDate.parse(courseValues[3])));
        }
        return courseDetails;
    }// optiunea 1 din consola

    private static List<Student> readingStudents() throws IOException {
        Path pathStudent = Paths.get(DataLoaderUtils.STUDENT_FILE_PATH);
        List<String> readStudentsline = Files.readAllLines(pathStudent);

        List<Student> studentDetails = new ArrayList<>();
        for (String line : readStudentsline) {
            String[] studentValues = line.split(",");
            studentDetails.add(new
                    Student(Integer.parseInt(studentValues[0]), studentValues[1],
                    Double.parseDouble(studentValues[2])));
        }
        return studentDetails;
    }

    public static Map<Course, List<Student>> mapData() throws EmptyStringException, IOException, NumberFormatException {
        Map<Course, List<Student>> map = new HashMap<>();
        //Cursurile sunt unice. Deci preluam lista de cursuri si introducem in map cate un curs.
        for (Course course : readingCourses()) {
            map.put(course, new ArrayList<>()); //La inceput avem o lista goala initializata (sa nu fie null)
        }

        //Imi pregatesc lista de studenti
        List<Student> students = readingStudents();

        //Citim fisierul de mapari si obtinem cate 2 integers care sunt id-urile.
        //O sa facem un for peste toate maparile din fisier
        Path pathCourse = Paths.get(DataLoaderUtils.MAPPING_FILE_PATH);
        List<String> mapari = Files.readAllLines(pathCourse);
        // List<String> mapari = new ArrayList<>(); // Aici citesti fisierul de mapari
        for (String mapare : mapari) {
            String[] mapping = mapare.split(",");

            int idStudent = Integer.parseInt(mapping[0]);
            int idCourse = Integer.parseInt(mapping[1]);

            Student studentCautat = null; //Studentul cautat
            //Am cele 2 id-uri. Prima data caut studentul eventual ca dupa cand ma duc in map sa caut cursul il si adaug
            for (Student student : students) {
                if (student.getStudentId() == idStudent) {
                    studentCautat = student;
                    break;
                }
            }

            //Prima data verific daca exista cursul cu acel Id.
            Set<Course> setCourse = map.keySet();
            for (Course course : setCourse) {
                if (course.getCourseID() == idCourse) {
                    map.get(course).add(studentCautat);
                }
            }
        }
        return map;
    }

    public static List<String> searchedStudent() throws IOException {
        List<String> searchedStudent = new ArrayList<>();
        System.out.println("Introduceti prenumele si numele studentului cautat: ");
        Scanner scanner = new Scanner(System.in);
        String nameStudent = scanner.nextLine();

        while (true) {
            for (int i = 0; i < DataLoader.readingStudents().size(); i++) {

                if (DataLoader.readingStudents().get(i).getStudentName().equalsIgnoreCase(nameStudent)) {
                    searchedStudent.addAll(Collections.singleton(String.valueOf(DataLoader.readingStudents().get(i))));
                }
            }
            if (searchedStudent.isEmpty()) {
                System.out.println("Studentul nu se afla in baza de date.");
            }
            return searchedStudent;
        }
    }// optiunea 4 din consola

    public static Map<Student, List<Course>> mapari() throws
            EmptyStringException, IOException, NumberFormatException {
        Map<Student, List<Course>> mapari = new HashMap<>();

        for (Student student : readingStudents()) {
            mapari.put(student, new ArrayList<>());
        }

        List<Course> courses = readingCourses();

        Path pathCourse = Paths.get(DataLoaderUtils.MAPPING_FILE_PATH);
        List<String> mapariBasic = Files.readAllLines(pathCourse);

        for (String mapare : mapariBasic) {
            String[] mapping = mapare.split(",");

            int idStudent = Integer.parseInt(mapping[0]);
            int idCourse = Integer.parseInt(mapping[1]);

            Course searchedCourse = null;
            for (Course course : courses) {
                if (course.getCourseID() == idCourse) {
                    searchedCourse = course;
                    break;
                }
            }

            Set<Student> setStudent = mapari.keySet();
            for (Student student : setStudent) {
                if (student.getStudentId() == idStudent) {
                    mapari.get(student).add(searchedCourse);
                }
            }
        }
        return mapari;
    }// optiunea 5 din consola
}












