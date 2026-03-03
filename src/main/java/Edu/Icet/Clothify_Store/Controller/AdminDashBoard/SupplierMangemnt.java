package Edu.Icet.Clothify_Store.Controller.AdminDashBoard;

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
        try {
            Connection connection = dbConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO suppliyer (suppliyername,suppliyernumber,suppliyeremail,suppliyeraddress) values (?,?,?,?)");

            preparedStatement.setString(1,suppliyer.getName());
            preparedStatement.setString(2,suppliyer.getNumber());
            preparedStatement.setString(3,suppliyer.getEmail());
            preparedStatement.setString(4,suppliyer.getAddress());

            if(preparedStatement.executeUpdate()>0){
                new Alert(Alert.AlertType.INFORMATION,"Added Successfull!").show();
                tabledata();
                clear();
            }else {
                new Alert(Alert.AlertType.ERROR,"Added Fail").show();
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void update(ActionEvent actionEvent) {
        try {
            Connection connection = dbConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE suppliyer SET suppliyername = ? , suppliyernumber = ? , suppliyeremail=?,suppliyeraddress=? WHERE id = ? ");

            preparedStatement.setString(1,txt_s_name.getText());
            preparedStatement.setString(2,txt_s_number.getText());
            preparedStatement.setString(3,txt_s_email.getText());
            preparedStatement.setString(4,txt_s_address.getText());
            preparedStatement.setInt(5,Integer.parseInt(txt_s_id.getText()));

            if (preparedStatement.executeUpdate()>0){
                new Alert(Alert.AlertType.INFORMATION,"Update Successfull").show();
                tabledata();
                clear();
            }else {
                new Alert(Alert.AlertType.ERROR,"Update Fail").show();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(ActionEvent actionEvent) {
        try {
            Connection connection = dbConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM suppliyer WHERE id = ? ");
            preparedStatement.setInt(1,Integer.parseInt(txt_s_id.getText()));

            if(preparedStatement.executeUpdate()>0){
                new Alert(Alert.AlertType.INFORMATION,"Delete Successfull").show();
                clear();
                tabledata();
            }else{
                new Alert(Alert.AlertType.ERROR,"Error Delete Please Try Again").show();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void tabledata(){
        cal_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        call_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        call_number.setCellValueFactory(new PropertyValueFactory<>("number"));
        call_email.setCellValueFactory(new PropertyValueFactory<>("email"));
        call_address.setCellValueFactory(new PropertyValueFactory<>("address"));

        try {
            Connection connection = dbConnection.getInstance().getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM suppliyer");

            ArrayList<Supplier> suppliers = new ArrayList<>();
            while (resultSet.next()){
                Supplier suppiyer = new Supplier(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5)
                );
                suppliers.add(suppiyer);
            }
            tblCollection.setItems(FXCollections.observableArrayList(suppliers));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

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
