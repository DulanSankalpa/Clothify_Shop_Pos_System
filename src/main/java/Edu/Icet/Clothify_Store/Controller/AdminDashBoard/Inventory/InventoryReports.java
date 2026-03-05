package Edu.Icet.Clothify_Store.Controller.AdminDashBoard.Inventory;

import Edu.Icet.Clothify_Store.DB.dbConnection;
import Edu.Icet.Clothify_Store.Model.Product;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InventoryReports {
    public TableView tblCollection;
    public TableColumn cal_id;
    public TableColumn cal_name;
    public TableColumn cal_size;
    public TableColumn cal_category;
    public TableColumn cal_qty;
    public TableColumn cal_price;

    public void currentStock(ActionEvent actionEvent) {
       tableCollem();

        InventoryReportimpl inventoryReportimpl = new InventoryReportimpl();
        List<Product> getcurrentStock = inventoryReportimpl.CurrentStock();

        ArrayList<Product> ProductList= new ArrayList<>();
        getcurrentStock.forEach(Product ->{
            ProductList.add(new Product(
                    Product.getId(),
                    Product.getName(),
                    Product.getSize(),
                    Product.getCmb(),
                    Product.getPrice(),
                    Product.getQty()
            ));
        });

        tblCollection.setItems(FXCollections.observableArrayList(ProductList));
    }

    public void lowStock(ActionEvent actionEvent) {
        tableCollem();

        InventoryReportimpl inventoryReportimpl = new InventoryReportimpl();
        List<Product> lowStock = inventoryReportimpl.LowStock();

        ArrayList<Product> productslisted = new ArrayList<>();
        lowStock.forEach(product ->{
            productslisted.add(new Product(
                    product.getId(),
                    product.getName(),
                    product.getSize(),
                    product.getCmb(),
                    product.getPrice(),
                    product.getQty()
            ));
        });

        tblCollection.setItems(FXCollections.observableArrayList(productslisted));
    }

    public void outofstock(ActionEvent actionEvent) {
        tableCollem();
        InventoryReportimpl inventoryReportimpl = new InventoryReportimpl();
        List<Product> getoutofstock = inventoryReportimpl.outofStock();

        ArrayList<Product> outofstockArray =new ArrayList<>();
        getoutofstock.forEach(product->{
            outofstockArray.add(new Product(
                    product.getId(),
                    product.getName(),
                    product.getSize(),
                    product.getCmb(),
                    product.getPrice(),
                    product.getQty()
            ));
        });

        tblCollection.setItems(FXCollections.observableArrayList(outofstockArray));

    }

    private void tableCollem(){
        cal_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        cal_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        cal_size.setCellValueFactory(new PropertyValueFactory<>("size"));
        cal_category.setCellValueFactory(new PropertyValueFactory<>("cmb"));
        cal_qty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        cal_price.setCellValueFactory(new PropertyValueFactory<>("price"));
    }
}
