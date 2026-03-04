package Edu.Icet.Clothify_Store.Controller.StaffDashBoard;

import Edu.Icet.Clothify_Store.DB.dbConnection;
import Edu.Icet.Clothify_Store.Model.Order;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class GenOrderID {
    public TextField txt_order_id;
    public TableView tblCollection;
    public TableColumn cal_id;
    public TableColumn cal_name;
    public TableColumn cal_qty;
    public TableColumn cal_price;
    public TableColumn cal_staff_id;
    public TableColumn cal_total;
    public TableColumn cal_date;

    public void loaddata(ActionEvent actionEvent) {

        cal_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        cal_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        cal_qty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        cal_price.setCellValueFactory(new PropertyValueFactory<>("price"));
        cal_total.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));
        cal_staff_id.setCellValueFactory(new PropertyValueFactory<>("counterid"));
        cal_date.setCellValueFactory(new PropertyValueFactory<>("datetime"));


        try {
            Connection connection = dbConnection.getInstance().getConnection();

            PreparedStatement preparedStatement =
                    connection.prepareStatement(
                            "SELECT id, productname, size, qty, counterStaffID, price, totalprice,orderDateTime FROM orders WHERE id=?"
                    );

            preparedStatement.setInt(1, Integer.parseInt(txt_order_id.getText()));

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

            tblCollection.setItems(FXCollections.observableArrayList(orderlist));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
