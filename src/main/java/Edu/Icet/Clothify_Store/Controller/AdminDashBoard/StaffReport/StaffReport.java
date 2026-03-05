package Edu.Icet.Clothify_Store.Controller.AdminDashBoard.StaffReport;

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
import java.util.List;

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

        int id = Integer.parseInt(txt_staff_id.getText());

        StaffReportimpl staffReportimpl = new StaffReportimpl();
        List<Order> getAllData = staffReportimpl.getdata(id);

        ArrayList<Order> DataArray = new ArrayList<>();
        getAllData.forEach(order->{
           DataArray.add(new Order(
                   order.getId(),
                   order.getName(),
                   order.getSize(),
                   order.getQty(),
                   order.getCounterid(),
                   order.getDatetime()
           ));
        });

        tblCollection.setItems(FXCollections.observableArrayList(DataArray));

    }
}
