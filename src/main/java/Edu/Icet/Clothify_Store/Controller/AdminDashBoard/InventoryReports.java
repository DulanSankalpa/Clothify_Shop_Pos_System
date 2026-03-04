package Edu.Icet.Clothify_Store.Controller.AdminDashBoard;

import Edu.Icet.Clothify_Store.DB.dbConnection;
import Edu.Icet.Clothify_Store.Model.Product;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.*;
import java.util.ArrayList;

public class InventoryReports {
    public TableView tblCollection;
    public TableColumn cal_id;
    public TableColumn cal_name;
    public TableColumn cal_size;
    public TableColumn cal_category;
    public TableColumn cal_qty;
    public TableColumn cal_price;

    public void currentStock(ActionEvent actionEvent) {
       tableCollem();
        try {
            Connection connection = dbConnection.getInstance().getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT id,productname,size,category,qty,price FROM product where qty>0");

            ArrayList<Product> productList = new ArrayList<>();
            while (resultSet.next()){
                Product product = new Product(
                        resultSet.getInt("id"),
                        resultSet.getString("productname"),
                        resultSet.getString("size"),
                        resultSet.getString("category"),
                        resultSet.getInt("qty"),
                        resultSet.getInt("price")
                );
                productList.add(product);
            }
            tblCollection.setItems(FXCollections.observableArrayList(productList));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void lowStock(ActionEvent actionEvent) {
        tableCollem();
        try {
            Connection connection = dbConnection.getInstance().getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT id,productname,size,category,qty,price FROM product where qty < 5");

            ArrayList<Product> productList = new ArrayList<>();
            while (resultSet.next()){
                Product product = new Product(
                        resultSet.getInt("id"),
                        resultSet.getString("productname"),
                        resultSet.getString("size"),
                        resultSet.getString("category"),
                        resultSet.getInt("qty"),
                        resultSet.getInt("price")
                );
                productList.add(product);
            }
            tblCollection.setItems(FXCollections.observableArrayList(productList));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void outofstock(ActionEvent actionEvent) {
        tableCollem();
        try {
            Connection connection = dbConnection.getInstance().getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT id,productname,size,category,qty,price FROM product where qty = 0");

            ArrayList<Product> productList = new ArrayList<>();
            while (resultSet.next()){
                Product product = new Product(
                        resultSet.getInt("id"),
                        resultSet.getString("productname"),
                        resultSet.getString("size"),
                        resultSet.getString("category"),
                        resultSet.getInt("qty"),
                        resultSet.getInt("price")
                );
                productList.add(product);
            }
            tblCollection.setItems(FXCollections.observableArrayList(productList));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void tableCollem(){
        cal_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        cal_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        cal_size.setCellValueFactory(new PropertyValueFactory<>("size"));
        cal_category.setCellValueFactory(new PropertyValueFactory<>("cmb"));
        cal_qty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        cal_price.setCellValueFactory(new PropertyValueFactory<>("price"));
    }
}
