package com.pluralsight.courseinfo.cli;
import com.pluralsight.courseinfo.cli.service.CourseRetrieverService;
import com.pluralsight.courseinfo.cli.service.PluralsightCourse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static java.util.function.Predicate.not;

public class CourseRetriever {


    private static final Logger LOG = LoggerFactory.getLogger(CourseRetriever.class);
    public static void main(String[] args) {
        LOG.info("CourseRetriver Starting");
        if(args.length == 0)
        {
            LOG.warn("No Author ID Provided!!");
            return;
        }
        try {
            RetrieverCourses(args[0]);

        }
        catch(Exception e){
            LOG.error("Unexpected Error happened...", e);
            e.printStackTrace();

        }
    }

    private static void RetrieverCourses(String authorID) {
        LOG.info("The author is: {}",authorID);

        CourseRetrieverService courseRetrieverService = new CourseRetrieverService();

        List<PluralsightCourse> coursesToStore = courseRetrieverService.getCoursesFor(authorID)
                .stream()
                .filter(not(PluralsightCourse::isRetired))
                .toList();
        LOG.info("Retrieving the following {} courses {}", coursesToStore.size(), coursesToStore);

    }
}
