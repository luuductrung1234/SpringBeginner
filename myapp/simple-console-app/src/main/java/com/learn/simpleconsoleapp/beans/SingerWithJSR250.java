package com.learn.simpleconsoleapp.beans;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class SingerWithJSR250 {
    private static final String DEFAULT_NAME = "Eric Clapton";
    private static final String DEFAULT_LYRIC = "Down there below us, under the clouds";

    private String name;
    private int age = Integer.MIN_VALUE;
    private String lyric;
    private String lyricFilePath;
    private String createdBy;

    @PostConstruct
    public void init() throws Exception {
        System.out.println("[Initializing bean] " + super.toString() + " . . .\n");

        if (name == null) {
            System.out.println("Using default name");
            name = DEFAULT_NAME;
        }

        if (age == Integer.MIN_VALUE) {
            throw new IllegalArgumentException(
                    "You must set the age property of any beans of type " + Singer.class);
        }

        if (!Files.exists(Paths.get(lyricFilePath))) {
            lyric = DEFAULT_LYRIC;

            Files.write(Paths.get(lyricFilePath), new ArrayList<>() {{
                add(lyric);
            }}, StandardCharsets.UTF_8, StandardOpenOption.CREATE_NEW);
        } else {
            List<String> lines = Files.readAllLines(Paths.get(lyricFilePath), StandardCharsets.UTF_8);
            lyric = String.join("\n", lines);
        }

        System.out.println("File " +
                "\n\tPath:" + lyricFilePath +
                "\n\texists: " + Files.exists(Paths.get(lyricFilePath)) + "\n");
    }

    @PreDestroy
    public void destroy() throws Exception {
        System.out.println("[Destroying bean] " + super.toString() + " . . .\n");

        Files.deleteIfExists(Paths.get(lyricFilePath));

        System.out.println("File " +
                "\n\tPath:" + lyricFilePath +
                "\n\texists: " + Files.exists(Paths.get(lyricFilePath)) + "\n");
    }

    public void sing() {
        System.out.println(lyric);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setLyricFilePath(String lyricFilePath) {
        this.lyricFilePath = lyricFilePath;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    @Override
    public String toString() {
        return "\n\t\tName: " + name + "\n\t\tAge: " + age + "\n\t\tLyric to sing: " + lyric + "\n\t\tCreated by: " + createdBy;
    }
}
