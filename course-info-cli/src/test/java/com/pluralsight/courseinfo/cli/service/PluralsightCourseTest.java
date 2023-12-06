package com.pluralsight.courseinfo.cli.service;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class PluralsightCourseTest {

    @ParameterizedTest
    @CsvSource(textBlock = """
            00:05:30, 5
            01:05:30.453621, 65
            00:00:00, 0
            """)
    void durationInMinutes(String duration, long expectedResult) {
        PluralsightCourse course =
                new PluralsightCourse("id", "Test Course", duration, "url", false);
        assertEquals(expectedResult, course.durationInMinutes());

    }
}