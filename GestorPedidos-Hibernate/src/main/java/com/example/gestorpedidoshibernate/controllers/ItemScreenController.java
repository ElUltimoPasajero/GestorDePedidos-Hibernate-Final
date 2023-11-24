package com.example.gestorpedidoshibernate.controllers;

import com.example.gestorpedidoshibernate.App;
import com.example.gestorpedidoshibernate.HibernateUtil;
import com.example.gestorpedidoshibernate.Session;
import com.example.gestorpedidoshibernate.domain.item.Item;
import com.example.gestorpedidoshibernate.domain.item.ItemDAOImplement;
import com.example.gestorpedidoshibernate.domain.order.Order;
import com.example.gestorpedidoshibernate.domain.order.OrderDAOImplemen;
import com.example.gestorpedidoshibernate.domain.product.Product;
import com.example.gestorpedidoshibernate.domain.product.ProductDAO;
import com.example.gestorpedidoshibernate.domain.product.ProductDAOImplement;
import com.example.gestorpedidoshibernate.domain.user.User;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.css.converter.StringConverter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.FlowPane;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ItemScreenController implements Initializable {
    @javafx.fxml.FXML
    private TableView tableItem;
    @javafx.fxml.FXML
    private TableColumn<Item, String> columnProductName;
    @javafx.fxml.FXML
    private TableColumn<Item, Double> columnProductPrice;
    @javafx.fxml.FXML
    private FlowPane back;
    @javafx.fxml.FXML
    private Button btnBack;
    @javafx.fxml.FXML
    private Button btnLogOut;
    @javafx.fxml.FXML
    private ComboBox<Product> comboGame;
    @javafx.fxml.FXML
    private Button btnAddGame;
    @javafx.fxml.FXML
    private Button btnSaveOrder;
    @FXML
    private TextField txtTotal;

    LocalDate currentDate = LocalDate.now();


    Double totalPrice = 0.0;
    @FXML
    private Button btnDeleteOrder;
    @FXML
    private Button btnDeleteGame;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        columnProductName.setCellValueFactory((fila) -> {
            return new SimpleStringProperty(fila.getValue().getProducto().getProductName());
        });

        columnProductPrice.setCellValueFactory((fila) -> {
            return new SimpleObjectProperty<>(fila.getValue().getProducto().getProductPrice());
        });


        tableItem.getItems().addAll(Session.getCurrentOrder().getItems());  //Rallenamos la tabla con los pedidos de cada usuario


        //Rellenamos los combos
        var item = new ItemDAOImplement().getAllItems();
        var dao = new ProductDAOImplement();


        comboGame.setConverter(new javafx.util.StringConverter<Product>() {  //Asi se mapea los objetos Producst en los combos
            @Override
            public String toString(Product p) {

                if (p != null) {

                    return p.getProductName();
                } else {

                    return null;
                }
            }

            @Override
            public Product fromString(String s) {
                return null;
            }
        });


        comboGame.getItems().addAll(dao.getAllProducts());
     /*   List<Product> lista = new ArrayList<>();
        lista.addAll(dao.getAllProducts());

        for (Product i : lista
        ) {

            System.out.println(i);

        }*/


    }

    @javafx.fxml.FXML

    /**
     * Metodo para volver atras hacia Main-Screen
     */
    public void back(ActionEvent actionEvent) {

        try {
            Session.setCurrentOrder(null); //Ponemos el pedido elegido antes en null

            App.changeScene("main-screen.fxml", "Order View");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Metodo para hacer Logout
     *
     * @param actionEvent
     */
    @javafx.fxml.FXML
    public void logOut(ActionEvent actionEvent) {

        try {
            App.changeScene("login-screen.fxml", "Login");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * Metodo para añadir juegos al pedido
     * @param actionEvent
     */
    @javafx.fxml.FXML
    public void addGame(ActionEvent actionEvent) {


        Product selectedProduct = comboGame.getValue();

        if (selectedProduct != null && Session.getCurrentOrder() != null) {
            Item newItem = new Item();
            newItem.setProducto(selectedProduct);

            System.out.println(selectedProduct.toString());

            // Agregar el nuevo Item a la lista de la tabla
            tableItem.getItems().add(newItem);

            // Actualizar la columna columnProductName
            columnProductName.setCellValueFactory(fila -> new SimpleStringProperty(selectedProduct.getProductName()));

            totalPrice += selectedProduct.getProductPrice();

            // Mostrar el precio total en txtTotal
            txtTotal.setText(String.valueOf(totalPrice) + " ptas");

            System.out.println(totalPrice);

            newItem.setProducto(selectedProduct);
            newItem.setPedido(Session.getCurrentOrder());

            // Agregar el nuevo ítem a la lista de ítems de la orden
            Session.getCurrentOrder().getItems().add(newItem);
            Session.getCurrentOrder().setAmmount(totalPrice);
            Session.getCurrentOrder().setDate(String.valueOf(currentDate));


        }
    }


    @javafx.fxml.FXML
    public void saveOrder(ActionEvent actionEvent) {

        OrderDAOImplemen daoimpl = new OrderDAOImplemen();

        daoimpl.update(Session.getCurrentOrder());

        try {
            App.changeScene("main-screen.fxml", "Order View");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }


    @FXML
    public void deleteOrder(ActionEvent actionEvent) {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("¿Sure to remove " + Session.getCurrentOrder().getCode() + " from list?");

        var result = alert.showAndWait().get();

        if (result.getButtonData() == ButtonBar.ButtonData.OK_DONE) {

            HibernateUtil.getSessionFactory().inTransaction((session) -> {
                session.remove(Session.getCurrentOrder());
            });

            Alert alertInfoRemov = new Alert(Alert.AlertType.INFORMATION);
            alertInfoRemov.setContentText("Order Removed!!!!! ");
            alertInfoRemov.show();

            try {
                App.changeScene("main-screen.fxml", "Order View");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @FXML
    public void deleteGame(ActionEvent actionEvent) {


        Item selectedItem = (Item) tableItem.getSelectionModel().getSelectedItem();

        if (selectedItem != null) {

            Alert alertRemoveGame = new Alert(Alert.AlertType.CONFIRMATION);
            alertRemoveGame.setContentText("¿Sure to remove " +selectedItem.getProducto().getProductName() + " from list?");

            var result = alertRemoveGame.showAndWait().get();


            if (result.getButtonData() == ButtonBar.ButtonData.OK_DONE) {

                tableItem.getItems().remove(selectedItem);

                totalPrice -= selectedItem.getProducto().getProductPrice();
                txtTotal.setText(String.valueOf(totalPrice) + " ptas");

                Session.getCurrentOrder().getItems().remove(selectedItem);

            }
        }
    }
}

