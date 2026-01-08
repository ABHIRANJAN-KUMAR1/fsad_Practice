package com.klu.app;

import com.klu.mode.Department;
import com.klu.mode.Employee;
import com.klu.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Scanner;

public class MainApp {

    static SessionFactory factory = HibernateUtil.getSessionFactory();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        int choice;

        while (true) {
            System.out.println("\n------- Main Menu -------");
            System.out.println("1. Insert Department");
            System.out.println("2. Insert Employee");
            System.out.println("3. Display Employees");
            System.out.println("4. Update Employee Salary");
            System.out.println("5. Delete Employee");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            choice = sc.nextInt();

            switch (choice) {
                case 1: insertDept(); 
                		break;
                case 2: insertEmployee(); 
                		break;
                case 3: displayEmployees(); 
                		break;
                case 4: updateEmployee(); 
                		break;
                case 5: deleteEmployee(); 
                		break;
                case 6:
                    factory.close();
                    System.out.println("Application Closed.");
                    System.exit(0);
                default:
                    System.out.println("Invalid Choice!");
            }
        }
    }

    public static void insertDept() {
        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();

        System.out.print("Enter Department Name: ");
        String dname = sc.next();

        Department d = new Department();
        d.setDeptName(dname);

        session.save(d);
        tx.commit();
        session.close();

        System.out.println("Department Inserted Successfully!");
    }

    public static void insertEmployee() {
        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();

        System.out.print("Enter Employee Name: ");
        String name = sc.next();

        System.out.print("Enter Salary: ");
        double sal = sc.nextDouble();

        System.out.print("Enter Department ID: ");
        int did = sc.nextInt();

        Department dept = session.get(Department.class, did);

        if (dept == null) {
            System.out.println("Department Not Found!");
            session.close();
            return;
        }

        Employee e = new Employee();
        e.setEmpName(name);
        e.setEmpSalary(sal);
        e.setDepartment(dept);

        session.save(e);
        tx.commit();
        session.close();

        System.out.println("Employee Inserted Successfully!");
    }

    public static void displayEmployees() {
        Session session = factory.openSession();

        List<Employee> list = session.createQuery("from Employee", Employee.class).list();

        System.out.println("\nEmpID\tName\tSalary\tDepartment");
        for (Employee e : list) {
            System.out.println(
                    e.getEmpId() + "\t" +
                    e.getEmpName() + "\t" +
                    e.getEmpSalary() + "\t" +
                    e.getDepartment().getDeptName()
            );
        }

        session.close();
    }

    public static void updateEmployee() {
        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();

        System.out.print("Enter Employee ID: ");
        int id = sc.nextInt();

        Employee e = session.get(Employee.class, id);

        if (e == null) {
            System.out.println("Employee Not Found!");
            session.close();
            return;
        }

        System.out.print("Enter New Salary: ");
        double sal = sc.nextDouble();

        e.setEmpSalary(sal);
        session.update(e);

        tx.commit();
        session.close();

        System.out.println("Salary Updated Successfully!");
    }

    public static void deleteEmployee() {
        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();

        System.out.print("Enter Employee ID to Delete: ");
        int id = sc.nextInt();

        Employee e = session.get(Employee.class, id);

        if (e == null) {
            System.out.println("Employee Not Found!");
            session.close();
            return;
        }

        session.delete(e);
        tx.commit();
        session.close();

        System.out.println("Employee Deleted Successfully!");
    }
}
