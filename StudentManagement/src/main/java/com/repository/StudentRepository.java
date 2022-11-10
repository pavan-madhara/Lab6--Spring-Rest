package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Integer> {

}
