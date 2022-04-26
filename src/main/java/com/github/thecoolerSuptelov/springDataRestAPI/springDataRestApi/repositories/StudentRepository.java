package com.github.thecoolerSuptelov.springDataRestAPI.springDataRestApi.repositories;

import com.github.thecoolerSuptelov.springDataRestAPI.springDataRestApi.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface StudentRepository extends JpaRepository<Student, UUID> {

    public Optional<Student> findByNameEqualsIgnoreCase(String name);

    public long deleteByNameEqualsIgnoreCase(String name);

    @Query("from Student s where s.name = :name")
    public Student findByName(@Param("name") String studentName);

    @Modifying
    @Transactional
    @Query("update Student set name= :name where name=''")
    void updateFirstName(@Param("name") String name);
}