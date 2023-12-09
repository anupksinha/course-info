package com.pluralsight.courseinfo.server;

import com.pluralsight.courseinfo.domain.Course;
import com.pluralsight.courseinfo.repository.CourseRepository;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Comparator;
import java.util.stream.Stream;

@Path("/courses")
public class CourseResourse {
    private static final Logger LOG = LoggerFactory.getLogger(CourseResourse.class);
    private final CourseRepository courseRepository;
    public CourseResourse(CourseRepository courseRepository)
    {
        this.courseRepository = courseRepository;
    }
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Stream<Course> getCourses(){
        return courseRepository
                .getAllCourses()
                .stream()
                .sorted(Comparator.comparing(Course::id));
    }
    @POST
    @Path("/{id}/notes")
    @Consumes(MediaType.TEXT_PLAIN)
    public void AddNotes(@PathParam("id") String id, String notes)
    {
        courseRepository.addNotes(id, notes);
    }
}

