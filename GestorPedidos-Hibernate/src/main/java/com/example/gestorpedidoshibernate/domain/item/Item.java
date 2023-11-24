package com.example.gestorpedidoshibernate.domain.item;

import com.example.gestorpedidoshibernate.domain.order.Order;
import com.example.gestorpedidoshibernate.domain.product.Product;
import com.example.gestorpedidoshibernate.domain.user.User;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

    @Data
    @Entity
    @Table(name="item")
    public class Item implements Serializable {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer id;

        @Column(name="cantidad")
        private Integer cantidad;


    @OneToOne
    @JoinColumn(name="producto_id")

    private Product producto;

    @ManyToOne
    @JoinColumn(name="codigo_pedido",referencedColumnName = "código")


        private Order pedido;

        @Override
        public String toString() {
            return "Item{" +
                    "id=" + id +
                    ", cantidad=" + cantidad +
                    ", producto=" + producto.getProductName() +
                    ", pedido=" + pedido +
                    '}';
        }


    }

