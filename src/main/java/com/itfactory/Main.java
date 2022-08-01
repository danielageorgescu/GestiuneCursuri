package com.itfactory;

import com.itfactory.model.Course;
import com.itfactory.services.DataLoader;
import com.itfactory.services.DataSaver;
import com.itfactory.utils.EmptyStringException;
import java.io.IOException;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException, EmptyStringException{

        System.out.println("Puteti introduce urmatoarele optiuni:\n" +
                "0 – Ies din program.\n" +
                "1 – Afiseaza cursuri\n" +
                "2 – Introduceti un curs nou\n" +
                "3 – Introduceti un student nou si inrolati-l la curs\n" +
                "4 – Cautati un student dupa nume \n" +
                "5 – Afiseaza studentii si cursul la care participa.\n" +
                "Introduceti optiunea: ");
        while (true) {
            Scanner scanner = new Scanner(System.in);
            int option = Integer.parseInt(scanner.nextLine());
            switch (option) {
                case 0:
                    System.exit(0);
                    break;
                case 1:
                    System.out.println(DataSaver.notStartedCourses());
                    break;
                case 2:
                    System.out.println(DataSaver.saveCourses());
                    break;
                case 3:
                    Course key = DataSaver.checkIfCourseExist();
                    System.out.println(DataSaver.availabelCourse(key));
                    break;
                case 4:
                    System.out.println(DataLoader.searchedStudent());
                    break;
                case 5:
                    System.out.println(DataLoader.mapari());
                    break;
                default:
                    System.out.println("Introduceti o optiune valida.");
                    break;
            }
        }
    }
}







