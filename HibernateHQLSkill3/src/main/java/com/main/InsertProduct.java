package com.main;

import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.entity.Product;
import com.util.HibernateUtil;

public class InsertProduct {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        System.out.print("Enter number of products: ");
        int n = sc.nextInt();
        sc.nextLine();

        for (int i = 1; i <= n; i++) {

            System.out.println("\nEnter details for Product " + i);

            System.out.print("Name: ");
            String name = sc.nextLine();

            System.out.print("Description: ");
            String description = sc.nextLine();

            System.out.print("Price: ");
            double price = sc.nextDouble();

            System.out.print("Quantity: ");
            int quantity = sc.nextInt();
            sc.nextLine();

            Product p = new Product(name, description, price, quantity);
            session.save(p);
        }

        tx.commit();
        session.close();
        sc.close();

        System.out.println("\nProducts inserted successfully");
    }
}
