package Edu.Icet.Clothify_Store.Service.StaffBranch.impl;

import Edu.Icet.Clothify_Store.DB.dbConnection;
import Edu.Icet.Clothify_Store.Model.Order;
import Edu.Icet.Clothify_Store.Service.StaffBranch.PlaseOrderService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlaseOrderimpl implements PlaseOrderService {
    @Override
    public boolean addOrder(Order order) {
        try {
            Connection connection = dbConnection.getInstance().getConnection();
            PreparedStatement preparedStatement =
                    connection.prepareStatement(
                            "INSERT INTO Orders (productname,size,qty,price,totalprice,counterStaffID) VALUES (?,?,?,?,?,?)"
                    );

            int qrt = order.getQty();
            double price = order.getPrice();

            double total= qrt * price;

            preparedStatement.setString(1,order.getName());
            preparedStatement.setString(2,order.getSize());
            preparedStatement.setInt(3,order.getQty());
            preparedStatement.setDouble(4,order.getPrice());
            preparedStatement.setDouble(5,total);
            preparedStatement.setInt(6,order.getCounterid());

            return preparedStatement.executeUpdate()>0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateOrder(Order order) {
        try {
            Connection connection = dbConnection.getInstance().getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(
                    "UPDATE Orders SET productname=?,size=?,qty=?,price=?,totalprice=?,counterStaffID=? WHERE id=?"
            );



            int qrt = order.getQty();
            double price = order.getPrice();
            double total = qrt * price;

            preparedStatement.setString(1, order.getName());
            preparedStatement.setString(2, order.getSize());
            preparedStatement.setInt(3, order.getQty());
            preparedStatement.setDouble(4, order.getPrice());
            preparedStatement.setDouble(5, total);
            preparedStatement.setInt(6, order.getCounterid());
            preparedStatement.setInt(7, order.getId());

            return preparedStatement.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean DeleteOrder(int id) {
        try {
            Connection connection = dbConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM Orders WHERE id=?");
            preparedStatement.setInt(1,id);

            return preparedStatement.executeUpdate()>0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Order> getAll() {
        try {
            Connection connection = dbConnection.getInstance().getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Orders");

            ArrayList<Order> orderlist = new ArrayList<>();

            while(resultSet.next()){
                orderlist.add(new Order(
                        resultSet.getInt("id"),
                        resultSet.getString("productname"),
                        resultSet.getString("size"),
                        resultSet.getInt("qty"),
                        resultSet.getDouble("price"),
                        resultSet.getDouble("totalprice"),
                        resultSet.getInt("counterStaffID")
                ));
            }

            return orderlist;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return List.of();
    }
}
