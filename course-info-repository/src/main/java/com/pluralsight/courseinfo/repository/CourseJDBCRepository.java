package com.pluralsight.courseinfo.repository;

import com.pluralsight.courseinfo.domain.Course;
import org.h2.jdbcx.JdbcDataSource;


import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class CourseJDBCRepository implements CourseRepository{
    private static final String H2_DATABASE_URL =
            "jdbc:h2:file:%s;AUTO_SERVER=TRUE;INIT=RUNSCRIPT from './db_init.sql'";
    private static final String INSERT_COURSE = """
            MERGE INTO Courses ( Id, name, length, url)
            VALUES (?,?,?,?)
            """;
    private final DataSource dataSource;

    public CourseJDBCRepository(String databaseFile){
        JdbcDataSource jdbcDataSource = new JdbcDataSource();
        jdbcDataSource.setURL(H2_DATABASE_URL.formatted(databaseFile));
        this.dataSource = jdbcDataSource;
    }


    @Override
    public void saveCourse(Course course) {
         try(Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(INSERT_COURSE);
            statement.setString(1,course.id());
            statement.setString(2,course.name());
            statement.setLong(3,course.length());
            statement.setString(4,course.url());
            statement.execute();
        } catch (SQLException e) {
            throw new RepositoryException("Failed to Save" + course, e);
        }
    }

    @Override
    public List<Course> getAllCourses() {
        try(Connection connection = dataSource.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM COURSES");

            List<Course> courses = new ArrayList<>();
            while (resultSet.next())
            {
                Course course = new Course(resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getLong(3),
                        resultSet.getString(4));
                courses.add(course);
            }
            return (List<Course>) Collections.unmodifiableCollection(courses);
        } catch (SQLException e) {
            throw new RepositoryException("Failed to retrive courses", e);
        }
    }
}
