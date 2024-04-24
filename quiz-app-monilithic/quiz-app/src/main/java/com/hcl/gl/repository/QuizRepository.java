package com.hcl.gl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hcl.gl.model.Quiz;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, Integer> {

}
