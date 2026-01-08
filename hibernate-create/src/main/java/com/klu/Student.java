package com.klu;
import javax.persistence.*;

@Entity
@Table(name = "bachchha")
public class Student {
	@Id
	private int Id;
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String name;
	
	public Student() {}
	public Student(String name) {
		this.name = name;
	}

}