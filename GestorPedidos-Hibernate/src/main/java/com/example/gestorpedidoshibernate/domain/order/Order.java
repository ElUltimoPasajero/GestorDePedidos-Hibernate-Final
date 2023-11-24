package com.example.gestorpedidoshibernate.domain.order;

import com.example.gestorpedidoshibernate.domain.item.Item;
import com.example.gestorpedidoshibernate.domain.product.Product;
import com.example.gestorpedidoshibernate.domain.user.User;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Data
@Entity
@Table(name = "pedido")
public class Order implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "código")
    private String code;

    @Column(name = "fecha")
    private String date;

    @Column(name = "total")
    private Double ammount;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private User user;//La relacion de muchos a uno, muchos pedidos pertenecen a un usuario

    @OneToMany(mappedBy = "pedido", fetch = FetchType.EAGER, cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Item> items = new ArrayList<>(0);




    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", date=" + date +
                ", total=" + ammount +
                ", user=" + user.getUsername() +

                '}';

    }

    public static void merge(Order origin, Order destiny) { //Con este metodo escribimos los datos en origen
        destiny.setCode(origin.getCode());
        destiny.setDate(origin.getDate());
        destiny.setAmmount(origin.getAmmount());


    }

}

