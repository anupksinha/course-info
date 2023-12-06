package com.pluralsight.courseinfo.domain;

public record Course(String id, String name, long length, String url) {
    public Course{
        Filled(id);
        Filled(name);
        Filled(url);
    }

    public static void Filled(String s) {
        if(s == null || s.isBlank()){
            throw new IllegalArgumentException("No Value Present");
        }
    }
}
