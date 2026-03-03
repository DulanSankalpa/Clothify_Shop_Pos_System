package Edu.Icet.Clothify_Store.Controller.AdminDashBoard;

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

        try {
            Connection connection = dbConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO staff (username,email,pw,rolltypr) values (?,?,?,?)");

            preparedStatement.setString(1,staff.getUsername());
            preparedStatement.setString(2,staff.getEmail());
            preparedStatement.setString(3,staff.getPw());
            preparedStatement.setString(4,staff.getCmd());

            if (preparedStatement.executeUpdate()>0){
                new Alert(Alert.AlertType.INFORMATION,"Added Successfully").show();
                tabledata();
                clean();
            }else {
                new Alert(Alert.AlertType.ERROR,"Fail Input Please Try Again").show();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    public void Update(ActionEvent actionEvent) {
        SObject selected = (SObject) tblCollection.getSelectionModel().getSelectedItem();

        if (selected == null) {
            new Alert(Alert.AlertType.WARNING, "Please select a user first!").show();
            return;
        }

        try {
            Connection connection = dbConnection.getInstance().getConnection();

            String sql = "UPDATE staff SET username=?, email=?, pw=?, rolltypr=? WHERE id=?";
            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setString(1, txtusername.getText());
            ps.setString(2, txtemail.getText());
            ps.setString(3, txtpassword.getText());
            ps.setString(4, cmdbox.getValue().toString());
            ps.setInt(5, selected.getId());

            if (ps.executeUpdate() > 0) {
                new Alert(Alert.AlertType.INFORMATION, "Updated Successfully!").show();
                tabledata();
                clean();
            } else {
                new Alert(Alert.AlertType.ERROR, "Update Failed!").show();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(ActionEvent actionEvent) {

        try {
            Connection connection = dbConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM staff WHERE id=?");
            preparedStatement.setInt(1,Integer.parseInt(txt_id.getText()));
            int i = preparedStatement.executeUpdate();
            if (i>0){
                new Alert(Alert.AlertType.INFORMATION,"Delete Complete").show();
                tabledata();
                clean();
            }else {
                new Alert(Alert.AlertType.ERROR,"Please Try Again").show();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
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
