package com.klu.model;


import javax.persistence.*;

@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int eid;

    private String ename;
    private double salary;

    @ManyToOne
    @JoinColumn(name = "dept_id")
    private Department department;

    
    public int getEid() {
    	return eid; 
    	}
    public void setEid(int eid) {
    	this.eid = eid;
    	}

    public String getEname() { 
    	return ename;
    	}
    public void setEname(String ename) { 
    	this.ename = ename;
    	}

    public double getSalary() { 
    	return salary;
    	}
    public void setSalary(double salary) { 
    	this.salary = salary; 
    	}

    public Department getDepartment() { 
    	return department; 
    	}
    public void setDepartment(Department department) { 
    	this.department = department;
    	}
}