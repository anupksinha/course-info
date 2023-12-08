package com.pluralsight.courseinfo.repository;

import com.pluralsight.courseinfo.domain.Course;

import java.util.Collection;

public interface CourseRepository {
    void saveCourse(Course course);
    Collection<Course> getAllCourses();

    void addNotes(String id, String notes);

    static CourseRepository openCourseRepository(String databaseFile){
        return  new CourseJDBCRepository(databaseFile);
    }


}
