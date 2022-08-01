package com.itfactoryTest;

import com.itfactory.model.Course;
import com.itfactory.services.DataLoader;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import com.itfactory.utils.DataLoaderUtils;
import org.junit.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;


public class DataLoaderTest {

    static ArrayList<Course> expected;
    static Path path;
    static List<String> readCourseLine;


    @Before
    public void init() throws IOException, FileNotFoundException {
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
    public void testReadingCourses() throws IOException {
        List<Course> actual = DataLoader.readingCourses();
        List<Course> expectedCourse = new ArrayList<>();
        expectedCourse.add(new Course(123126,"Introducere in programare Python",600.0,LocalDate.parse("2022-08-23")));
        expectedCourse.add(new Course(123124,"Testare automata",600.0,LocalDate.parse("2022-09-29")));
        expectedCourse.add(new Course(123123,"Introducere in programare Java",600.0,LocalDate.parse("2022-05-20")));

        //assertThat(actual).usingRecursiveComparison().isEqualTo(expectedCourse);

    }




}









