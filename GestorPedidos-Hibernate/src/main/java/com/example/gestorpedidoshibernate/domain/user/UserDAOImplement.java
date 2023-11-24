package com.example.gestorpedidoshibernate.domain.user;

import com.example.gestorpedidoshibernate.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.ArrayList;

public class UserDAOImplement implements UserDAO{


    /**
     * Metodo que devuelve una lista de usuarios
     *
     * @return
     */
    @Override
    public ArrayList<User> getAll() {
        var salida = new ArrayList<User>(0);

        try (Session s = HibernateUtil.getSessionFactory().openSession()) {

            Query<User> q = s.createQuery("from User", User.class);
            salida = (ArrayList<User>) q.getResultList();


        }

        return salida;
    }


    @Override
    public User get(Integer id){  //Con este metodo actualizamos el usuario para que se reflejen las actualizaciones en la tabla
        var salida = new User();

        try (Session s = HibernateUtil.getSessionFactory().openSession()) {

            salida = s.get(User.class,id);

        }
        return salida;
    }


    @Override
    public void update(User data) {

    }

    @Override
    public User validateUser(String username, String password) {
        User result = null;
        try( Session session = HibernateUtil.getSessionFactory().openSession()){
            Query<User> q = session.createQuery("from User where username=:u and password=:p",User.class);
            q.setParameter("u",username);
            q.setParameter("p",password);

            try {
                result = q.getSingleResult();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return result; 
    }
}
