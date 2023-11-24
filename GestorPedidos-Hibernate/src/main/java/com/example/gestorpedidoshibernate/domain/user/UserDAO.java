package com.example.gestorpedidoshibernate.domain.user;

import java.util.ArrayList;

public interface UserDAO {

    public ArrayList<User> getAll();
    public User get(Integer id);
    public void update(User data);

    public User validateUser(String username, String password);

}
