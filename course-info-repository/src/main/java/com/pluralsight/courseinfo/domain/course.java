package com.pluralsight.courseinfo.domain;

public record course(String id, String name, long length, String url) {
    public course{
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
