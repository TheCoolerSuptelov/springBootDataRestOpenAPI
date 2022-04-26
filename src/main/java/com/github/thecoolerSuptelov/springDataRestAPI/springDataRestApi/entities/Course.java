package com.github.thecoolerSuptelov.springDataRestAPI.springDataRestApi.entities;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "course")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "title")
    private String title;

    @Column(name = "amount_of_hours")
    private Integer amountOfHours;

    public Integer getAmountOfHours() {
        return amountOfHours;
    }

    public void setAmountOfHours(Integer amountOfHours) {
        this.amountOfHours = amountOfHours;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

}