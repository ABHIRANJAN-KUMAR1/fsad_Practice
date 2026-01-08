package com.klu.app;


import com.klu.model.*;
import com.klu.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Scanner;

public class MainApp {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int choice;
        do {
            System.out.println("\n===== EMPLOYEE MENU =====");
            System.out.println("1. Insert Employee");
            System.out.println("2. Display Employee");
            System.out.println("3. Update Employee");
            System.out.println("4. Delete Employee");
            System.out.println("5. Exit");
            System.out.print("Select your choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1: insertEmployee(); break;
                case 2: displayEmployee(); break;
                case 3: updateEmployee(); break;
                case 4: deleteEmployee(); break;
                case 5: System.out.println("Thank You"); break;
                default: System.out.println("Wrong choice!");
            }

        } while (choice != 5);

        HibernateUtil.getSessionFactory().close();
        sc.close();
    }

    static void insertEmployee() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        System.out.print("Employee Name: "); String name = sc.next();
        System.out.print("Salary: "); double salary = sc.nextDouble();
        System.out.print("Department Name: "); String dname = sc.next();

        Department dept = new Department();
        dept.setDeptName(dname);

        Employee emp = new Employee();
        emp.setEname(name);
        emp.setSalary(salary);
        emp.setDepartment(dept);

        session.save(dept);
        session.save(emp);
        tx.commit();
        session.close();

        System.out.println("Employee Inserted!");
    }

    static void displayEmployee() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Employee> employees = session.createQuery("from Employee", Employee.class).list();
        for(Employee e : employees) {
            System.out.println("ID: "+e.getEid()+", Name: "+e.getEname()+", Salary: "+e.getSalary()+", Dept: "+e.getDepartment().getDeptName());
        }
        session.close();
    }

    static void updateEmployee() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        System.out.print("Enter Employee ID to update: "); int id = sc.nextInt();
        Employee e = session.get(Employee.class, id);
        if(e != null) {
            System.out.print("New Name: "); e.setEname(sc.next());
            System.out.print("New Salary: "); e.setSalary(sc.nextDouble());
            session.update(e);
            tx.commit();
            System.out.println("Employee updated!");
        } 
        else {
            System.out.println("Employee not found!");
        }
        session.close();
    }

    static void deleteEmployee() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        System.out.print("Enter Employee ID to delete: "); int id = sc.nextInt();
        Employee e = session.get(Employee.class, id);
        if(e != null) {
            session.delete(e);
            tx.commit();
            System.out.println("Employee deleted!");
        } 
        else {
            System.out.println("Employee not found!");
        }

        session.close();
    }
}