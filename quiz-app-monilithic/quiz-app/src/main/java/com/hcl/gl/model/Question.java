package com.hcl.gl.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Question {
	
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Integer id;
	    private String questionTitle;
	    private String option1;
	    private String option2;
	    private String option3;
	    private String option4;
	    private String rightAnswer;
	    private String difficultylevel;
	    private String category;

}
