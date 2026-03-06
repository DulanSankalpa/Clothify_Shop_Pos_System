package Edu.Icet.Clothify_Store.Controller.StaffDashBoard.OrderID;

import Edu.Icet.Clothify_Store.DB.dbConnection;
import Edu.Icet.Clothify_Store.Model.Order;
import Edu.Icet.Clothify_Store.Service.StaffBranch.impl.OrderServiceimpl;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

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

        int id = Integer.parseInt(txt_order_id.getText());

        OrderServiceimpl orderServiceimpl = new OrderServiceimpl();
        List<Order> getdata =orderServiceimpl.getOrderRecode(id);

        ArrayList<Order> data = new ArrayList<>();
        getdata.forEach(datarecode->{
            data.add(new Order(
                    datarecode.getId(),
                    datarecode.getName(),
                    datarecode.getSize(),
                    datarecode.getQty(),
                    datarecode.getPrice(),
                    datarecode.getTotalPrice(),
                    datarecode.getCounterid(),
                    datarecode.getDatetime()
            ));
        });

        tblCollection.setItems(FXCollections.observableArrayList(data));
    }
}
