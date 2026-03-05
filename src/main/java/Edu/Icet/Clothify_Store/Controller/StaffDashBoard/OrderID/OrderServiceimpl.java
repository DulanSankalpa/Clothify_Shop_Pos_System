package Edu.Icet.Clothify_Store.Controller.StaffDashBoard.OrderID;

import Edu.Icet.Clothify_Store.DB.dbConnection;
import Edu.Icet.Clothify_Store.Model.Order;
import javafx.collections.FXCollections;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class OrderServiceimpl implements OrderService{
    @Override
    public List<Order> getOrderRecode(int id) {
        try {
            Connection connection = dbConnection.getInstance().getConnection();

            PreparedStatement preparedStatement =
                    connection.prepareStatement(
                            "SELECT id, productname, size, qty, counterStaffID, price, totalprice,orderDateTime FROM orders WHERE id=?"
                    );

            preparedStatement.setInt(1,id);

            ResultSet resultSet = preparedStatement.executeQuery();

            ArrayList<Order> orderlist = new ArrayList<>();

            while (resultSet.next()) {

                Order order = new Order(
                        resultSet.getInt("id"),
                        resultSet.getString("productname"),
                        resultSet.getString("size"),
                        resultSet.getInt("qty"),
                        resultSet.getDouble("price"),
                        resultSet.getDouble("totalprice"),
                        resultSet.getInt("counterStaffID"),
                        resultSet.getString("orderDateTime")
                );

                orderlist.add(order);
            }

            return orderlist;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return List.of();
    }
}
