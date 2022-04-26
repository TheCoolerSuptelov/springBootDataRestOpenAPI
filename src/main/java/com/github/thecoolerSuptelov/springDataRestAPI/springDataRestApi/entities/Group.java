package com.github.thecoolerSuptelov.springDataRestAPI.springDataRestApi.entities;

import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "group")
public class Group {
    @Column(name = "uuid", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private UUID uuid;

    @Column(name = "name")
    private String name;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
    @JoinTable(name = "group_students",
            joinColumns = @JoinColumn(name = "group_uuid"),
            inverseJoinColumns = @JoinColumn(name = "students_uuid"))
    private Set<Student> students = new LinkedHashSet<>();

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }
}