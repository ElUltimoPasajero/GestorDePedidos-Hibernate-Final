package com.example.gestorpedidoshibernate.domain.product;

import java.util.List;

public interface ProductDAO {

    public List<String> getNames();
    public List<Integer> getPrices();
    public List<Integer> getAmmounts();



}
