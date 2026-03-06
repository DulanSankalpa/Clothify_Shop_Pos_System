package Edu.Icet.Clothify_Store.Controller.AdminDashBoard.Inventory;

import Edu.Icet.Clothify_Store.DB.dbConnection;
import Edu.Icet.Clothify_Store.Model.Product;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class InventoryServiceimpl implements Edu.Icet.Clothify_Store.Controller.AdminDashBoard.Inventory.InventoryService {
    @Override
    public List<Product> CurrentStock() {
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
            return productList;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Product> LowStock() {
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

            return productList;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Product> outofStock() {
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

            return productList;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
