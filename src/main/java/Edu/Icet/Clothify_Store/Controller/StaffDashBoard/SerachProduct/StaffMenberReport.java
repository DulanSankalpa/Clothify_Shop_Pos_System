package Edu.Icet.Clothify_Store.Controller.StaffDashBoard.SerachProduct;

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
import java.util.List;

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

        String name = txt_product_name.getText();

        StaffReportimpl staffReportimpl = new StaffReportimpl();
        List<Order> GetData = staffReportimpl.FindProduct(name);

        ArrayList<Order> getList = new ArrayList<>();
        GetData.forEach(orders->{
            getList.add(new Order(
                    orders.getId(),
                    orders.getName(),
                    orders.getSize(),
                    orders.getQty(),
                    orders.getPrice()
            ));
        });

        tblCollection.setItems(FXCollections.observableArrayList(getList));
    }
}
