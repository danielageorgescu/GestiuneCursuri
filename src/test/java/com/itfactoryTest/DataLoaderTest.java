package com.itfactoryTest;

import com.itfactory.model.Course;
import com.itfactory.model.Student;
import com.itfactory.services.DataLoader;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.itfactory.utils.DataLoaderUtils;
import org.junit.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class DataLoaderTest {

    static ArrayList<Course> expected;
    static ArrayList<Student> expectedStudents;
    static Map<Integer, Integer> expectedMap;
    static Path path;
    static List<String> readCourseLine;
    static List<String> readStudentsLine;
    static List<String> readMapLine;


    @Before
    public void init() throws IOException {
        expected = new ArrayList<>();
        path = Paths.get(DataLoaderUtils.COURSE_FILE_PATH);
        readCourseLine = Files.readAllLines(path);

        for (String line : readCourseLine) {
            String[] courseValues = line.split(",");

            expected.add(new
                    Course(Integer.parseInt(courseValues[0]),
                    courseValues[1],
                    Double.parseDouble(courseValues[2]),
                    LocalDate.parse(courseValues[3])));
        }
    }

    @Test
    public void containingInfo() {
        assertNotNull(readCourseLine);

    }

    @Test
    public void splitTheLine() {
        Course actual = new Course(123124, "Testare automata", 600.0, LocalDate.parse("2022-09-29"));
        assertEquals(expected.get(0), actual);
    }

    @Test
    public void testReadingCourses() throws IOException {
        List<Course> actual = DataLoader.readingCourses();
        List<Course> expectedCourse = new ArrayList<>();
        expectedCourse.add(new Course(123124, "Testare automata", 600.0, LocalDate.parse("2022-09-29")));
        expectedCourse.add(new Course(123123, "Introducere in programare Java", 600.0, LocalDate.parse("2022-05-20")));

        assertNotEquals(actual, expectedCourse);
    }


    @Before
    public void initStudents() throws IOException {
        expectedStudents = new ArrayList<>();
        path = Paths.get(DataLoaderUtils.STUDENT_FILE_PATH);
        readStudentsLine = Files.readAllLines(path);

        for (String line : readStudentsLine) {
            String[] studentValues = line.split(",");

            expectedStudents.add(new
                    Student(Integer.parseInt(studentValues[0]),
                    studentValues[1],
                    Double.parseDouble(studentValues[2])));
        }
    }

    @Test
    public void containingInfoStudent() {
        assertNotNull(readStudentsLine);
    }

    @Test
    public void splitTheLineStudents() {
        Student actual = new Student(5123, "Vasile Ionn", 1000.0);
        assertEquals(expectedStudents.get(0), actual);
    }


    @Before
    public void initMap() throws IOException {
        expectedMap = new HashMap<Integer, Integer>();
        path = Paths.get(DataLoaderUtils.MAPPING_FILE_PATH);
        readMapLine = Files.readAllLines(path);

        for (String line : readMapLine) {
            String[] mapValues = line.split(",");
            int idStudent = Integer.parseInt(mapValues[0]);
            int idCourse = Integer.parseInt(mapValues[1]);

            expectedMap.put(idStudent, idCourse);
        }
    }

    @Test
    public void containingInfoMap() {
        assertNotNull(readMapLine);
    }

    @Test
    public void splitTheLineMap() {
       Map<Integer,Integer> actual = new HashMap<>();
       actual.put(5123,123123);
        assertEquals(expectedMap, actual);
    }
}









