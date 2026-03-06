package Edu.Icet.Clothify_Store.Service.AdminCenter.impl;

import Edu.Icet.Clothify_Store.DB.dbConnection;
import Edu.Icet.Clothify_Store.Model.Order;
import Edu.Icet.Clothify_Store.Service.AdminCenter.ViewReportService;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ViewReportimpl implements ViewReportService {
    @Override
    public List<Order> GetOrder() {
        try {
            Connection connection = dbConnection.getInstance().getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT id,productname,size,qty,price,orderDateTime FROM Orders where DATE(orderDateTime) = CURRENT_DATE");

            ArrayList<Order> orderList = new ArrayList<>();
            while (resultSet.next()){
                Order order = new Order(
                        resultSet.getInt("id"),
                        resultSet.getString("productname"),
                        resultSet.getString("size"),
                        resultSet.getInt("qty"),
                        resultSet.getDouble("price"),
                        resultSet.getString("orderDateTime")
                );
                orderList.add(order);
            }
            return orderList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
