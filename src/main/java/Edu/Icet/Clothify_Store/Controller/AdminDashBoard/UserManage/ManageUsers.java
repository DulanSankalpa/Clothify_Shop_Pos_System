package Edu.Icet.Clothify_Store.Controller.AdminDashBoard.UserManage;

import Edu.Icet.Clothify_Store.Controller.AdminDashBoard.ProductManage.ManageProductimpl;
import Edu.Icet.Clothify_Store.DB.dbConnection;
import Edu.Icet.Clothify_Store.Model.SObject;
import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

public class ManageUsers implements Initializable {
    public TextField txtusername;
    public TextField txtemail;
    public PasswordField txtpassword;
    public JFXComboBox cmdbox;
    public TableView tblCollection;
    public TableColumn calid;
    public TableColumn calusername;
    public TableColumn calemail;
    public TableColumn calpassword;
    public TableColumn calrolltype;
    public TextField txt_id;

    public void addnewUser(ActionEvent actionEvent) {
        String username = txtusername.getText();
        String email = txtemail.getText();
        String pw = txtpassword.getText();
        String cmd = cmdbox.getValue().toString();

        SObject staff = new SObject(username,email,pw,cmd);

       if (new ManagerUsersilmp().addUser(staff)){
           new Alert(Alert.AlertType.INFORMATION,"Added Complete !").show();
           tabledata();
           clean();
       }else{
           new Alert(Alert.AlertType.ERROR,"Fail Added !").show();
       }


    }

    public void Update(ActionEvent actionEvent) {
        SObject selected = (SObject) tblCollection.getSelectionModel().getSelectedItem();

        if (selected == null) {
            new Alert(Alert.AlertType.WARNING, "Please select a user first!").show();
            return;
        }

        SObject staff = new SObject(
                txtusername.getText(),
                txtemail.getText(),
                txtpassword.getText(),
                cmdbox.getValue().toString(),
                txt_id.getText()
        );


        if(new ManagerUsersilmp().UpdateUser(staff)){
            new Alert(Alert.AlertType.INFORMATION,"Update Complete !").show();
            clean();
            tabledata();
        }else{
            new Alert(Alert.AlertType.ERROR,"Fail Update !").show();
        }


    }

    public void delete(ActionEvent actionEvent) {
        int id = Integer.parseInt(txt_id.getText());

       if(new ManagerUsersilmp().deleteUser(id)){
           new Alert(Alert.AlertType.INFORMATION,"Delete Complete !").show();
           tabledata();
           clean();
       }else{
           new Alert(Alert.AlertType.ERROR,"Fail Delete").show();
       }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cmdbox.setItems(FXCollections.observableArrayList(
                Arrays.asList("Store Manager","Sales Associate","Cashier","Inventory Staff","Store Assistant")
        ));
        tabledata();
        tblCollection.getSelectionModel().selectedItemProperty().addListener((observableValue, o, t1) -> {
            setIntValue((SObject)t1);
        });
    }

    public void tabledata() {

        calid.setCellValueFactory(new PropertyValueFactory<>("id"));
        calusername.setCellValueFactory(new PropertyValueFactory<>("username"));
        calemail.setCellValueFactory(new PropertyValueFactory<>("email"));
        calpassword.setCellValueFactory(new PropertyValueFactory<>("pw"));
        calrolltype.setCellValueFactory(new PropertyValueFactory<>("cmd"));

        try {
            Connection connection = dbConnection.getInstance().getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM staff");

            ArrayList<SObject> staffArray = new ArrayList<>();

            while (resultSet.next()){
                SObject staff = new SObject(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(6)
                );
                staffArray.add(staff);
            }

            tblCollection.setItems(FXCollections.observableArrayList(staffArray));

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setIntValue(SObject sObject){
        txt_id.setText(String.valueOf(sObject.getId()));
    }

    private void clean(){
        txt_id.setText("");
        txtemail.setText("");
        txtpassword.setText("");
        txtusername.setText("");
    }
}
