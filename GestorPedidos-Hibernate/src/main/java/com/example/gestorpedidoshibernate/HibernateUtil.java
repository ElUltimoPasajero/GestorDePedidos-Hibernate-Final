package com.example.gestorpedidoshibernate;

import lombok.extern.java.Log;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

@Log
public class HibernateUtil {

    private static SessionFactory sf = null;

    static{
        //Hacer siempre esto antes de continuar con el proyecto y comprobar que funcione
        //es el Configuration de hibernate cuidado!!!
        try {
            Configuration cfg = new Configuration();
            cfg.configure();//Comprobamos que el archivo de configuracion no tiene ningun problema
            sf = cfg.buildSessionFactory();
            log.info("SessionFactory creada con exito!");
        } catch(Exception ex){
            log.severe("Error al crear SessionFactory()");
            System.out.println(ex.getMessage());
        }
    }

    public static SessionFactory getSessionFactory(){
        return sf;
    }
}
