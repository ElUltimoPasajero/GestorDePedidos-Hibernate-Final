package com.example.gestorpedidoshibernate.domain.user;

import com.example.gestorpedidoshibernate.domain.order.Order;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Data
@Entity
@Table(name = "usuario")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre")
    private String username;

    @Column(name = "contraseña")
    private String password;

    @Column(name = "email")
    private String email;

    @Transient
    private Long ordersQuantity;


    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private List<Order> orders = new ArrayList<>(0);



    public Long getOrdersQuantity() { // nos da la cantidad de pedidos
        ordersQuantity = (long) orders.size();
        return this.ordersQuantity;

    }


}



