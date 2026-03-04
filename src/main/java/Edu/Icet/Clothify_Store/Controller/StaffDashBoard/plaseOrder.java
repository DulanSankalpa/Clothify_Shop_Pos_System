package Edu.Icet.Clothify_Store.Controller.StaffDashBoard;

import Edu.Icet.Clothify_Store.DB.dbConnection;
import Edu.Icet.Clothify_Store.Model.Order;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class plaseOrder implements Initializable {
    public TextField txtx_p_ame;
    public TextField txt_size;
    public TableView tblCollection;
    public TableColumn call_id;
    public TableColumn call_p_name;
    public TableColumn cal_size;
    public TableColumn cal_price;
    public TableColumn call_qty;
    public TableColumn call_staff;
    public TextField txt_qty;
    public TextField txt_staff;
    public TextField txt_id;
    public TextField txt_price;
    public TableColumn call_total;
    public TableColumn call_counter;

    public void plaseOrder(ActionEvent actionEvent) {

        String name = txtx_p_ame.getText();
        String size = txt_size.getText();
        int qty = Integer.parseInt(txt_qty.getText());
        double price = Double.parseDouble(txt_price.getText());
        int counter = Integer.parseInt(txt_staff.getText());

        double total = qty * price;

        try {
            Connection connection = dbConnection.getInstance().getConnection();
            PreparedStatement preparedStatement =
                    connection.prepareStatement(
                            "INSERT INTO Orders (productname,size,qty,price,totalprice,counterStaffID) VALUES (?,?,?,?,?,?)"
                    );

            preparedStatement.setString(1,name);
            preparedStatement.setString(2,size);
            preparedStatement.setInt(3,qty);
            preparedStatement.setDouble(4,price);
            preparedStatement.setDouble(5,total);
            preparedStatement.setInt(6,counter);

            if (preparedStatement.executeUpdate()>0){
                new Alert(Alert.AlertType.INFORMATION,"Order Placement Complete Thank You").show();
                tabledata();
                System.out.println("Hello");
            }else{
                new Alert(Alert.AlertType.ERROR,"Fail Order Please Try Again").show();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private void tabledata(){

        call_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        call_p_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        cal_size.setCellValueFactory(new PropertyValueFactory<>("size"));
        cal_price.setCellValueFactory(new PropertyValueFactory<>("price"));
        call_qty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        call_total.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));
        call_counter.setCellValueFactory(new PropertyValueFactory<>("counterid"));

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

            tblCollection.setItems(FXCollections.observableArrayList(orderlist));

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        tblCollection.getSelectionModel().selectedItemProperty().addListener((observableValue, o, t1) -> {
            setfeld((Order)t1);
        });
        tabledata();

    }

    private void setfeld(Order t1) {
        txt_id.setText(String.valueOf(t1.getId()));
    }

    public void update(ActionEvent actionEvent) {

        try {
            Connection connection = dbConnection.getInstance().getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(
                    "UPDATE Orders SET productname=?,size=?,qty=?,price=?,totalprice=?,counterStaffID=? WHERE id=?"
            );

            String name = txtx_p_ame.getText();
            String size = txt_size.getText();
            int qty = Integer.parseInt(txt_qty.getText());
            double price = Double.parseDouble(txt_price.getText());
            int counter = Integer.parseInt(txt_staff.getText());
            int id = Integer.parseInt(txt_id.getText());

            double total = qty * price;

            preparedStatement.setString(1, name);
            preparedStatement.setString(2, size);
            preparedStatement.setInt(3, qty);
            preparedStatement.setDouble(4, price);
            preparedStatement.setDouble(5, total);
            preparedStatement.setInt(6, counter);
            preparedStatement.setInt(7, id);

            if (preparedStatement.executeUpdate() > 0) {
                new Alert(Alert.AlertType.INFORMATION,
                        "Update Successfully Completed Thank You").show();
                tabledata();
            } else {
                new Alert(Alert.AlertType.ERROR, "Fail Update").show();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void delete(ActionEvent actionEvent) {
        try {
            Connection connection = dbConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM Orders WHERE id=?");
            preparedStatement.setInt(1,Integer.parseInt(txt_id.getText()));

            if (preparedStatement.executeUpdate()>0){
                new Alert(Alert.AlertType.INFORMATION,"Delete Successfull").show();
                tabledata();
            }else{
                new Alert(Alert.AlertType.ERROR,"Fail Delete Please Try Again").show();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
