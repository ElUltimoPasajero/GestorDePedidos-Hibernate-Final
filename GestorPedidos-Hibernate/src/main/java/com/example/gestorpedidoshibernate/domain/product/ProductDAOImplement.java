package com.example.gestorpedidoshibernate.domain.product;

import com.example.gestorpedidoshibernate.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class ProductDAOImplement implements ProductDAO{


    @Override
    public List<String> getNames() {
        ArrayList<String> results = new ArrayList<>(0);

        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            Query<String> q = s.createQuery("select distinct(p.productName) from Product p", String.class);
            results = (ArrayList<String>) q.getResultList();
        }
        return results;
    }

    @Override
    public List<Integer> getPrices() {
        return null;
    }

    @Override
    public List<Integer> getAmmounts() {
        return null;
    }

    public List<Product> getAllProducts() {

        var exit = new ArrayList<Product>(0);

        try (Session s = HibernateUtil.getSessionFactory().openSession()) {


            Query<Product> q = s.createQuery("from Product ", Product.class);
            exit = (ArrayList<Product>) q.getResultList();

        }

        return exit;
    }
}
