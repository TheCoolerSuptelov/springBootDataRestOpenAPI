package com.github.thecoolerSuptelov.springDataRestAPI.springDataRestApi.entities;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.sql.Date;
import java.time.Period;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "student")
public class Student {
    @Column(name = "name")
    private String name;

    @Column(name = "date_of_birthday")
    private Date dateOfBirthday;

    @Column(name = "uuid", nullable = false, unique = true)
    @Id
    @JsonIgnore
    private UUID uuid = UUID.randomUUID();

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    public Student() {
    }

    public Student(String name) {
        this.name = name;
        this.setUuid(UUID.randomUUID());
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Date getDateOfBirthday() {
        return dateOfBirthday;
    }

    public void setDateOfBirthday(Date dateOfBirthday) {
        this.dateOfBirthday = dateOfBirthday;
    }

    @JsonGetter("Age")
    public String getAge() {
        if (dateOfBirthday == null) {
            return "";
        }
        return (Period.between(java.time.LocalDate.now(), dateOfBirthday.toLocalDate())).toString();
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o){ return true;}

        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)){ return false;}
        Student student = (Student) o;
        return getUuid() != null && Objects.equals(getUuid(), student.getUuid());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
