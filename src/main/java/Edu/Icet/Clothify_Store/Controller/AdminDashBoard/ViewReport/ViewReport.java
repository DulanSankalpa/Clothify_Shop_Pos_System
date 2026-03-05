package Edu.Icet.Clothify_Store.Controller.AdminDashBoard.ViewReport;

import Edu.Icet.Clothify_Store.DB.dbConnection;
import Edu.Icet.Clothify_Store.Model.Order;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ViewReport {
    public TableView tblCollection;
    public TableColumn cal_id;
    public TableColumn cal_p_name;
    public TableColumn cal_size;
    public TableColumn cal_qty;
    public TableColumn cal_price;
    public TableColumn cal_date_time;

    public void daliyreport(ActionEvent actionEvent){
        cal_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        cal_p_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        cal_size.setCellValueFactory(new PropertyValueFactory<>("size"));
        cal_qty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        cal_price.setCellValueFactory(new PropertyValueFactory<>("price"));
        cal_date_time.setCellValueFactory(new PropertyValueFactory<>("datetime"));

        ViewReportimpl viewReportimpl = new ViewReportimpl();
        List<Order> getOrder = viewReportimpl.GetOrder();

        ArrayList<Order> OrderArray = new ArrayList<>();
        getOrder.forEach(order ->{
            OrderArray.add(new Order(
                    order.getId(),
                    order.getName(),
                    order.getSize(),
                    order.getQty(),
                    order.getPrice(),
                    order.getDatetime()
            ));
        });
        tblCollection.setItems(FXCollections.observableArrayList(OrderArray));


    }

    public void Monthlyreport(ActionEvent actionEvent) {
    }

    public void totalreport(ActionEvent actionEvent) {
    }
}
