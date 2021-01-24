package com.learning.conference.conferencefirstbootapp.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.*;

@Entity(name = "sessions")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Session {
    public Session() {
    }

    public Session(String name, String description, Integer length) {
        this.name = name;
        this.description = description;
        this.length = length;
    }

    public static Session from(String name, String description, Integer length) {
        return new Session(name, description, length);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "session_id")
    private Long id;

    @Column(name = "session_name")
    private String name;

    @Column(name = "session_description")
    private String description;

    @Column(name = "session_length")
    private Integer length;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "session_speakers",
            joinColumns = @JoinColumn(name = "session_id"),
            inverseJoinColumns = @JoinColumn(name = "speaker_id"))
    private Set<Speaker> speakers = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public Set<Speaker> getSpeakers() { return speakers; }

    public void setSpeakers(Set<Speaker> speakers) { this.speakers = speakers; }
}
