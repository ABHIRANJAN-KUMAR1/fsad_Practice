package com.main;

import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.entity.Product;
import com.util.HibernateUtil;

public class ProductApp {

    static Scanner sc = new Scanner(System.in);

    static void addProduct() {

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        sc.nextLine();
        System.out.print("Enter Product Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Description: ");
        String desc = sc.nextLine();

        System.out.print("Enter Price: ");
        double price = sc.nextDouble();

        System.out.print("Enter Quantity: ");
        int qty = sc.nextInt();

        Product p = new Product(name, desc, price, qty);
        session.save(p);

        tx.commit();
        session.close();

        System.out.println("Product added successfully");
    }

    static void viewProduct() {

        Session session = HibernateUtil.getSessionFactory().openSession();

        System.out.print("Enter Product ID: ");
        int id = sc.nextInt();

        Product p = session.get(Product.class, id);

        if (p != null) {
            System.out.println("Name: " + p.getName());
            System.out.println("Description: " + p.getDescription());
            System.out.println("Price: " + p.getPrice());
            System.out.println("Quantity: " + p.getQuantity());
        } else {
            System.out.println("Product not found");
        }

        session.close();
    }

    static void updateProduct() {

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        System.out.print("Enter Product ID: ");
        int id = sc.nextInt();

        Product p = session.get(Product.class, id);

        if (p == null) {
            System.out.println("Product not found");
            session.close();
            return;
        }

        System.out.println("1 Update Price");
        System.out.println("2 Update Quantity");
        System.out.print("Choose option: ");
        int option = sc.nextInt();

        if (option == 1) {
            System.out.print("Enter New Price: ");
            p.setPrice(sc.nextDouble());
        } else if (option == 2) {
            System.out.print("Enter New Quantity: ");
            p.setQuantity(sc.nextInt());
        } else {
            System.out.println("Invalid option");
            session.close();
            return;
        }

        session.update(p);
        tx.commit();
        session.close();

        System.out.println("Product updated successfully");
    }

    static void deleteProduct() {

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        System.out.print("Enter Product ID: ");
        int id = sc.nextInt();

        Product p = session.get(Product.class, id);

        if (p != null) {
            session.delete(p);
            tx.commit();
            System.out.println("Product deleted successfully");
        } else {
            System.out.println("Product not found");
        }

        session.close();
    }

    public static void main(String[] args) {

        while (true) {

            System.out.println();
            System.out.println("1 Add Product");
            System.out.println("2 View Product");
            System.out.println("3 Update Product");
            System.out.println("4 Delete Product");
            System.out.println("5 Exit");
            System.out.print("Enter your choice: ");

            int choice = sc.nextInt();

            if (choice == 1)
                addProduct();
            else if (choice == 2)
                viewProduct();
            else if (choice == 3)
                updateProduct();
            else if (choice == 4)
                deleteProduct();
            else if (choice == 5) {
                sc.close();
                HibernateUtil.getSessionFactory().close();
                System.out.println("Application Closed");
                break;
            } else {
                System.out.println("Invalid choice");
            }
        }
    }
}
