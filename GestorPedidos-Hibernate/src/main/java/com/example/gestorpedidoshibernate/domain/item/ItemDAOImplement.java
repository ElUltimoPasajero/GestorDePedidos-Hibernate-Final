package com.example.gestorpedidoshibernate.domain.item;

import com.example.gestorpedidoshibernate.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class ItemDAOImplement implements ItemDAO{
    @Override
    public List<Item> getAllItems() {

        var exit = new ArrayList<Item>(0);

        try (Session s = HibernateUtil.getSessionFactory().openSession()) {


            Query<Item> q = s.createQuery("from Item", Item.class);
            exit = (ArrayList<Item>) q.getResultList();

        }

        return exit;
    }
}
