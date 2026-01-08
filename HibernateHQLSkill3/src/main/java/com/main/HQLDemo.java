package com.main;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.entity.Product;
import com.util.HibernateUtil;

public class HQLDemo {

    public static void main(String[] args) {

        Session session = HibernateUtil.getSessionFactory().openSession();

        session.createQuery("FROM Product ORDER BY price ASC", Product.class).list();
        session.createQuery("FROM Product ORDER BY price DESC", Product.class).list();
        session.createQuery("FROM Product ORDER BY quantity DESC", Product.class).list();

        Query<Product> q1 = session.createQuery("FROM Product", Product.class);
        q1.setFirstResult(0);
        q1.setMaxResults(3);
        q1.list();

        Query<Product> q2 = session.createQuery("FROM Product", Product.class);
        q2.setFirstResult(3);
        q2.setMaxResults(3);
        q2.list();

        session.createQuery("SELECT COUNT(p) FROM Product p", Long.class).uniqueResult();
        session.createQuery("SELECT COUNT(p) FROM Product p WHERE quantity > 0", Long.class).uniqueResult();
        session.createQuery("SELECT MIN(price), MAX(price) FROM Product").uniqueResult();
        session.createQuery("SELECT description, COUNT(*) FROM Product GROUP BY description").list();
        session.createQuery("FROM Product WHERE price BETWEEN 500 AND 5000", Product.class).list();
        session.createQuery("FROM Product WHERE name LIKE 'L%'", Product.class).list();
        session.createQuery("FROM Product WHERE name LIKE '%r'", Product.class).list();
        session.createQuery("FROM Product WHERE name LIKE '%book%'", Product.class).list();
        session.createQuery("FROM Product WHERE length(name)=5", Product.class).list();

        session.close();
    }
}
