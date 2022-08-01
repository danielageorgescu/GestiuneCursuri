package com.itfactory.model;

import java.time.LocalDate;
import java.util.Objects;

public class Course {
    private int courseID;
    private String courseName;
    private double price;
    private LocalDate startDate;

    public Course(int courseID, String courseName, double price, LocalDate startDate) {
        this.courseID = courseID;
        this.courseName = courseName;
        this.price = price;
        this.startDate = startDate;
    }

    public Course(Course fromFile) {
        courseID = fromFile.courseID;
        courseName = fromFile.courseName;
        price = fromFile.price;
        startDate = fromFile.startDate;
    }

    public int getCourseID() {
        return courseID;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Course)) return false;
        Course course = (Course) o;
        return getCourseID() == course.getCourseID() && Double.compare(course.getPrice(), getPrice()) == 0 && Objects.equals(getCourseName(), course.getCourseName()) && Objects.equals(getStartDate(), course.getStartDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCourseID(), getCourseName(), getPrice(), getStartDate());
    }

    @Override
    public String toString() {
        return courseID + "," + courseName + "," + price + "," + startDate;
    }

}
