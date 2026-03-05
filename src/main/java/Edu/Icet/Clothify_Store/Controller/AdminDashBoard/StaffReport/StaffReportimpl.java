package Edu.Icet.Clothify_Store.Controller.AdminDashBoard.StaffReport;

import Edu.Icet.Clothify_Store.DB.dbConnection;
import Edu.Icet.Clothify_Store.Model.Order;
import javafx.collections.FXCollections;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StaffReportimpl implements StaffService{
    @Override
    public List<Order> getdata(int id) {
        try {
            Connection connection = dbConnection.getInstance().getConnection();
            Statement statement = connection.createStatement();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT id,productname,size,counterStaffID,totalprice,orderDateTime FROM Orders WHERE counterStaffID = ?");
            preparedStatement.setInt(1,id);

            ResultSet resultSet = preparedStatement.executeQuery();

            ArrayList<Order> OrderList = new ArrayList<>();
            while(resultSet.next()){
                Order ordere = new Order(
                        resultSet.getInt("id"),
                        resultSet.getString("productname"),
                        resultSet.getString("size"),
                        resultSet.getDouble("totalprice"),
                        resultSet.getInt("counterStaffID"),
                        resultSet.getString("orderDateTime")
                );
                OrderList.add(ordere);
            }
            return OrderList;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
