package Edu.Icet.Clothify_Store.Service.StaffBranch.impl;

import Edu.Icet.Clothify_Store.DB.dbConnection;
import Edu.Icet.Clothify_Store.Model.Order;
import Edu.Icet.Clothify_Store.Service.StaffBranch.SearchProductService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SearchProductimpl implements SearchProductService {
    @Override
    public List<Order> FindProduct(String name) {
        try {
            Connection connection = dbConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT id,productname,size,qty,price FROM product WHERE productname = ?");
            preparedStatement.setString(1,name);

            ResultSet resultSet = preparedStatement.executeQuery();
            ArrayList<Order> orderList = new ArrayList<>();
            while (resultSet.next()){
                Order order = new Order(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getInt(4),
                        resultSet.getDouble(5)
                );
                orderList.add(order);
            }
            return orderList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
