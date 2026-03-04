package Edu.Icet.Clothify_Store.Controller.AdminDashBoard;

import Edu.Icet.Clothify_Store.DB.dbConnection;
import Edu.Icet.Clothify_Store.Model.Order;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class StaffMenberReport {
    public JFXTextField txt_product_name;
    public TableView tblCollection;
    public TableColumn cal_id;
    public TableColumn cal_name;
    public TableColumn cal_qty;
    public TableColumn cal_price;
    public TableColumn cal_size;

    public void findProduct(ActionEvent actionEvent) {

        cal_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        cal_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        cal_qty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        cal_size.setCellValueFactory(new PropertyValueFactory<>("size"));
        cal_price.setCellValueFactory(new PropertyValueFactory<>("price"));


        try {
            Connection connection = dbConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT id,productname,size,qty,price FROM product WHERE productname = ?");
            preparedStatement.setString(1,txt_product_name.getText());

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
                tblCollection.setItems(FXCollections.observableArrayList(orderList));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
