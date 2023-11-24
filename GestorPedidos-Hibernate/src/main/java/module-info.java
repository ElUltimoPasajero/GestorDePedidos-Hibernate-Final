module com.example.gestorpedidoshibernate {
    requires javafx.controls;


    requires lombok;

    requires jakarta.persistence;
    requires org.hibernate.orm.core;
    requires java.naming;
    requires java.sql;
    requires javafx.fxml;


    opens com.example.gestorpedidoshibernate to javafx.fxml;
    opens com.example.gestorpedidoshibernate.controllers to javafx.fxml;
    opens com.example.gestorpedidoshibernate.domain.order;
    opens com.example.gestorpedidoshibernate.domain.user;
    opens com.example.gestorpedidoshibernate.domain.product;
    opens com.example.gestorpedidoshibernate.domain.item;




    exports com.example.gestorpedidoshibernate;
    exports com.example.gestorpedidoshibernate.controllers;
    exports com.example.gestorpedidoshibernate.domain.user;
    exports com.example.gestorpedidoshibernate.domain.order;


}