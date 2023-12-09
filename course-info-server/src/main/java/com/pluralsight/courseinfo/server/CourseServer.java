package com.pluralsight.courseinfo.server;

import com.pluralsight.courseinfo.repository.CourseRepository;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.bridge.SLF4JBridgeHandler;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.Properties;
import java.util.logging.LogManager;

public class CourseServer {
    private static final Properties properties = new Properties();
    private static final Logger LOG = LoggerFactory.getLogger(CourseServer.class);

    static {
        LogManager.getLogManager().reset();
        SLF4JBridgeHandler.install();
        try {
            InputStream propertiesStream = CourseServer.class.getResourceAsStream("/server.properties");

            properties.load(propertiesStream);
        } catch (IOException e) {
            throw new IllegalStateException("Could not Load BASE_URI.");
        }
    }


    private static final String BASE_URI = "http://localhost:8080/";

    public static void main(String[] args) {
        String databaseFileName = LoadDatabaseFilename();
       // String baseUri = LoadBaseUri();
        LOG.info("Starting Http server with database {}", databaseFileName);
        CourseRepository courseRepository = CourseRepository.openCourseRepository(databaseFileName);
        ResourceConfig config = new ResourceConfig().register(new CourseResourse(courseRepository));
        GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), config);
    }

    private static String LoadBaseUri() {
            return properties.getProperty("base_uri");
    }

    private static String LoadDatabaseFilename() {
            return properties.getProperty("course-info.database");
    }
}
