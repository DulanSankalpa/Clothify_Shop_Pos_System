package Edu.Icet.Clothify_Store.Controller.AdminDashBoard;

import Edu.Icet.Clothify_Store.DB.dbConnection;
import Edu.Icet.Clothify_Store.Model.Product;
import com.jfoenix.controls.JFXComboBox;
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
import java.util.Arrays;
import java.util.ResourceBundle;

public class ManageProduct implements Initializable {
    public TextField p_name;
    public TextField p_size;
    public JFXComboBox cmb_catagory;
    public TextField p_price;
    public TextField p_qty;
    public TableView tblCollection;
    public TableColumn cal_id;
    public TableColumn cal_p_name;
    public TableColumn call_p_size;
    public TableColumn cal_p_catogory;
    public TableColumn call_p_price;
    public TableColumn call_p_qty;
    public TextField txt_id;


    public void addproduct(ActionEvent actionEvent) {
        String Product_name = p_name.getText();
        String size = p_size.getText();
        String cmb = cmb_catagory.getValue().toString();
        int price = Integer.parseInt(p_price.getText());
        int qty = Integer.parseInt(p_qty.getText());

        Product product = new Product(Product_name,size,cmb,price,qty);

        try {
            Connection connection = dbConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO product (productname,size,category,qty,price) values (?,?,?,?,?)");

            preparedStatement.setString(1,product.getName());
            preparedStatement.setString(2,product.getSize());
            preparedStatement.setString(3,product.getCmb());
            preparedStatement.setInt(4,product.getQty());
            preparedStatement.setInt(5,product.getPrice());

            if (preparedStatement.executeUpdate()>0){
                new Alert(Alert.AlertType.INFORMATION,"Added Successfull Complete").show();
                tabledata();
                clean();
            }else{
                new Alert(Alert.AlertType.ERROR,"Fail Added Try Again").show();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    public void updateProduct(ActionEvent actionEvent) {
        try {
            Connection connection = dbConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE product set productname = ? ,size=?,category=?,qty=?,price = ? WHERE id=?");


            preparedStatement.setString(1,p_name.getText());
            preparedStatement.setString(2,p_size.getText());
            preparedStatement.setString(3,cmb_catagory.getValue().toString());
            preparedStatement.setInt(4,Integer.parseInt(p_price.getText()));
            preparedStatement.setInt(5,Integer.parseInt(p_qty.getText()));
            preparedStatement.setInt(6,Integer.parseInt(txt_id.getText()));

            if (preparedStatement.executeUpdate()>0){
                new Alert(Alert.AlertType.INFORMATION,"Update Complete").show();
                tabledata();
                clean();
            }else{
                new Alert(Alert.AlertType.ERROR,"Fail Update").show();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteproduct(ActionEvent actionEvent) {
        try {
            Connection connection = dbConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM product WHERE id = ?");
            preparedStatement.setInt(1,Integer.parseInt(txt_id.getText()));

            if (preparedStatement.executeUpdate()>0){
                new Alert(Alert.AlertType.INFORMATION,"Delete Successfull").show();
                clean();
                tabledata();
            }else {
                new Alert(Alert.AlertType.ERROR,"Fail Delete Please Try Again").show();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cmb_catagory.setItems(FXCollections.observableArrayList(
                Arrays.asList("Kits Clothin","Men Clothing","Women Clothin")
        ));
        tabledata();
        tblCollection.getSelectionModel().selectedItemProperty().addListener((observableValue, o, t1) -> {
            selectid((Product)t1);
        });
    }

    private void selectid(Product product) {
        txt_id.setText(String.valueOf(product.getId()));
    }

    public void tabledata(){
        cal_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        cal_p_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        call_p_size.setCellValueFactory(new PropertyValueFactory<>("size"));
        cal_p_catogory.setCellValueFactory(new PropertyValueFactory<>("cmb"));
        call_p_price.setCellValueFactory(new PropertyValueFactory<>("price"));
        call_p_qty.setCellValueFactory(new PropertyValueFactory<>("qty"));

        try {
            Connection connection = dbConnection.getInstance().getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM product");

            ArrayList<Product> getDetails = new ArrayList<>();
            while ((resultSet.next())){
                Product product = new Product(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getInt(5),
                        resultSet.getInt(6)
                );
                getDetails.add(product);
            }

            tblCollection.setItems(FXCollections.observableArrayList(getDetails));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void clean(){
        txt_id.setText("");
        p_name.setText("");
        p_size.setText("");
        p_price.setText("");
        p_qty.setText("");
    }
}
