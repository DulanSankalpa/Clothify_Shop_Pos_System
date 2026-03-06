package Edu.Icet.Clothify_Store.Controller.AdminDashBoard.ProductManage;

import Edu.Icet.Clothify_Store.Model.Product;
import Edu.Icet.Clothify_Store.Service.AdminCenter.ManageProsuctService;
import Edu.Icet.Clothify_Store.Service.AdminCenter.impl.ManageProsuctimpl;
import Edu.Icet.Clothify_Store.Service.ServiceFactory;
import Edu.Icet.Clothify_Store.Util.ServiceType;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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


     ManageProsuctService servicetype =  ServiceFactory.getInstance().getServiceTyrpe(ServiceType.ManageProduct);

    public void addproduct(ActionEvent actionEvent) {
        String Product_name = p_name.getText();
        String size = p_size.getText();
        String cmb = cmb_catagory.getValue().toString();
        int price = Integer.parseInt(p_price.getText());
        int qty = Integer.parseInt(p_qty.getText());

        Product product = new Product(Product_name,size,cmb,price,qty);

        if(servicetype.addproduct(product)){
            new Alert(Alert.AlertType.INFORMATION,"Product Added !").show();
            tabledata();
        }else{
            new Alert(Alert.AlertType.ERROR,"Product Not Added !").show();
        }

    }

    public void updateProduct(ActionEvent actionEvent) {

        String id = txt_id.getText();
        Product product = new Product(
                Integer.parseInt(txt_id.getText()),
                p_name.getText(),
                p_size.getText(),
                cmb_catagory.getValue().toString(),
                Integer.parseInt(p_price.getText()),
                Integer.parseInt(p_qty.getText()));

        if(servicetype.updateProduct(product)){
            new Alert(Alert.AlertType.INFORMATION,"Product Updated !").show();
            tabledata();
            clean();
        }else {
            new Alert(Alert.AlertType.ERROR,"Update Fail !").show();
        }


    }

    public void deleteproduct(ActionEvent actionEvent) {
        int id = Integer.parseInt(txt_id.getText());

        if(servicetype.deleteproduct(id)){
            new Alert(Alert.AlertType.INFORMATION,"Delete Complete").show();
        }else {
            new Alert(Alert.AlertType.ERROR,"Fail Delete").show();;
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

        ManageProsuctimpl manageProsuctimpl = new ManageProsuctimpl();
        List<Product> all = manageProsuctimpl.getAllProduct();

        ArrayList<Product> getdata = new ArrayList<>();
        all.forEach(product ->{
            getdata.add(new Product(
                    product.getId(),
                    product.getName(),
                    product.getSize(),
                    product.getCmb(),
                    product.getPrice(),
                    product.getQty()

            ));
        });

        tblCollection.setItems(FXCollections.observableArrayList(getdata));
    }

    private void clean(){
        txt_id.setText("");
        p_name.setText("");
        p_size.setText("");
        p_price.setText("");
        p_qty.setText("");
    }
}
