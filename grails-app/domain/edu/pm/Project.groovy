package edu.pm

import java.util.Date;

class Project {
	String name
	String code
	String techLeadName
	String projectManagerName
	Date deliveryDate
	ProjectPhase currentPhase
	int priority

	static constraints = { 
		name nullable: false, unique: true
		code nullable: false, unique: true
		techLeadName nullable: false
		projectManagerName nullable: false
		deliveryDate nullable: false
		currentPhase nullable: false
		priority unique: true				
	}   
}
