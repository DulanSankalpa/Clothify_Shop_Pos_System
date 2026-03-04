package Edu.Icet.Clothify_Store.Controller.AdminDashBoard;

import Edu.Icet.Clothify_Store.DB.dbConnection;
import Edu.Icet.Clothify_Store.Model.Order;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.*;
import java.util.ArrayList;

public class StaffReport {
    public JFXTextField txt_staff_id;
    public TableView tblCollection;
    public TableColumn call_id;
    public TableColumn cal_name;
    public TableColumn cal_size;
    public TableColumn cal_staff_id;
    public TableColumn cal_total;
    public TableColumn cal_data;

    public void findStaff(ActionEvent actionEvent) {
        call_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        cal_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        cal_size.setCellValueFactory(new PropertyValueFactory<>("size"));
        cal_total.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));
        cal_staff_id.setCellValueFactory(new PropertyValueFactory<>("counterid"));
        cal_data.setCellValueFactory(new PropertyValueFactory<>("datetime"));
        try {
            Connection connection = dbConnection.getInstance().getConnection();
            Statement statement = connection.createStatement();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT id,productname,size,counterStaffID,totalprice,orderDateTime FROM Orders WHERE counterStaffID = ?");
            preparedStatement.setInt(1,Integer.parseInt(txt_staff_id.getText()));

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
            tblCollection.setItems(FXCollections.observableArrayList(OrderList));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
