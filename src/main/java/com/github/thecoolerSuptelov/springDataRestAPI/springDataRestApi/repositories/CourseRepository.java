package com.github.thecoolerSuptelov.springDataRestAPI.springDataRestApi.repositories;

import com.github.thecoolerSuptelov.springDataRestAPI.springDataRestApi.entities.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface CourseRepository extends JpaRepository<Course, UUID> {
  Course findByTitleEqualsIgnoreCase(String title);
}