package com.pluralsight.courseinfo.domain;

import java.util.Optional;

public record Course(String id, String name, long length, String url, Optional<String> notes) {
    public Course{
        Filled(id);
        Filled(name);
        Filled(url);
        notes.ifPresent(Course::Filled);
    }

    public static void Filled(String s) {
        if(s == null || s.isBlank()){
            throw new IllegalArgumentException("No Value Present");
        }
    }
}
