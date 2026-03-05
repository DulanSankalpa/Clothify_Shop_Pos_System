package Edu.Icet.Clothify_Store.Controller.StaffDashBoard.PlaseOrder;

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
import java.util.List;
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

        Order order = new Order(name,size,qty,price,counter);

        double total = qty * price;

        if(new PlaseOrderimpl().addOrder(order)){
            new Alert(Alert.AlertType.INFORMATION,"Added Complete !").show();
            tabledata();
        }else{
            new Alert(Alert.AlertType.ERROR,"Fail Added !").show();
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

        PlaseOrderimpl plaseOrderimpl = new PlaseOrderimpl();
        List<Order> getAllData = plaseOrderimpl.getAll();

        ArrayList<Order> GetDataArray = new ArrayList<>();
        getAllData.forEach(data->{
            GetDataArray.add(new Order(
                    data.getId(),
                    data.getName(),
                    data.getSize(),
                    data.getQty(),
                    data.getPrice(),
                    data.getTotalPrice(),
                    data.getCounterid()
            ));
        });

        tblCollection.setItems(FXCollections.observableArrayList(GetDataArray));
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

        String name = txtx_p_ame.getText();
        String size = txt_size.getText();
        int qrt = Integer.parseInt(txt_qty.getText());
        double price = Double.parseDouble(txt_price.getText());
        int counter = Integer.parseInt(txt_staff.getText());
        int id = Integer.parseInt(txt_id.getText());

        double total = qrt * price;
        Order order = new Order(id,name,size,qrt,price,total,counter);

        if(new PlaseOrderimpl().updateOrder(order)){
            new Alert(Alert.AlertType.INFORMATION,"Update Completre !").show();
            tabledata();
        }else{
            new Alert(Alert.AlertType.ERROR,"Fail Update !").show();
        }
    }
    public void delete(ActionEvent actionEvent) {
       int id = Integer.parseInt(txt_id.getText());

       if(new PlaseOrderimpl().DeleteOrder(id)){
           new Alert(Alert.AlertType.INFORMATION,"Delete Recode !").show();
           tabledata();
       }
    }
}
