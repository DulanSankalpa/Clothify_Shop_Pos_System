package Edu.Icet.Clothify_Store.Controller.AdminDashBoard.Suppliyer;

import Edu.Icet.Clothify_Store.DB.dbConnection;
import Edu.Icet.Clothify_Store.Model.Supplier;
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

public class SupplierMangemnt implements Initializable {
    public TextField txt_s_id;
    public TextField txt_s_address;
    public TextField txt_s_email;
    public TextField txt_s_name;
    public TextField txt_s_number;
    public TableView tblCollection;
    public TableColumn cal_id;
    public TableColumn call_name;
    public TableColumn call_number;
    public TableColumn call_email;
    public TableColumn call_address;

    public void addnewSuppliyers(ActionEvent actionEvent) {

        String s_name = txt_s_name.getText();
        String s_number = txt_s_number.getText();
        String s_email = txt_s_email.getText();
        String s_address = txt_s_address.getText();

        Supplier suppliyer = new Supplier(s_name,s_number,s_email,s_address);

        if(new Suppliyerimpl().addsuppliyer(suppliyer)){
            new Alert(Alert.AlertType.INFORMATION,"Added Complete ! ").show();
            clear();
            tabledata();
        }else {
            new Alert(Alert.AlertType.ERROR,"Fail Added ! ").show();
        }
    }

    public void update(ActionEvent actionEvent) {
        Supplier supplier = new Supplier(
                Integer.parseInt(txt_s_id.getText()),
                txt_s_name.getText(),
                txt_s_number.getText(),
                txt_s_email.getText(),
                txt_s_address.getText()
        );
       if(new Suppliyerimpl().updatesuppiyer(supplier)){
           new Alert(Alert.AlertType.INFORMATION,"Update Complete !").show();
           tabledata();
           clear();
       }else {
           new Alert(Alert.AlertType.ERROR,"Update Fails !").show();
        }
    }

    public void delete(ActionEvent actionEvent) {
        int id = Integer.parseInt(txt_s_id.getText());
       if(new Suppliyerimpl().deletesuppiyer(id)){
           new Alert(Alert.AlertType.INFORMATION,"Delete Complete !").show();
           tabledata();
           clear();
       }else{
           new Alert(Alert.AlertType.ERROR,"Delete Fail !").show();
       }
    }

    public void tabledata(){
        cal_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        call_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        call_number.setCellValueFactory(new PropertyValueFactory<>("number"));
        call_email.setCellValueFactory(new PropertyValueFactory<>("email"));
        call_address.setCellValueFactory(new PropertyValueFactory<>("address"));

        Suppliyerimpl suppliyerimpl = new Suppliyerimpl();
        List<Supplier> getAll = suppliyerimpl.getAll();

        ArrayList<Supplier> dataArray = new ArrayList<>();
        getAll.forEach(list ->{
            dataArray.add(new Supplier(
                    list.getId(),
                    list.getName(),
                    list.getNumber(),
                    list.getEmail(),
                    list.getAddress()
            ));
        });

        tblCollection.setItems(FXCollections.observableArrayList(dataArray));

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tblCollection.getSelectionModel().selectedItemProperty().addListener((observableValue, o, t1) -> {
            setfaild((Supplier)t1);
        });
        tabledata();
    }

    private void setfaild(Supplier t1) {
        txt_s_id.setText(String.valueOf(t1.getId()));
    }
    private void clear(){
        txt_s_id.setText("");
        txt_s_name.setText("");
        txt_s_email.setText("");
        txt_s_address.setText("");
        txt_s_number.setText("");
    }
}
