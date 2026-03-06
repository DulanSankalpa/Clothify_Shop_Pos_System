package Edu.Icet.Clothify_Store.Service.AdminCenter.impl;

import Edu.Icet.Clothify_Store.DB.dbConnection;
import Edu.Icet.Clothify_Store.Model.Product;
import Edu.Icet.Clothify_Store.Service.AdminCenter.ManageProsuctService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ManageProsuctimpl implements ManageProsuctService {
    @Override
    public boolean addproduct(Product product) {
        try {
            Connection connection = dbConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO product (productname,size,category,qty,price) values (?,?,?,?,?)");

            preparedStatement.setString(1,product.getName());
            preparedStatement.setString(2,product.getSize());
            preparedStatement.setString(3,product.getCmb());
            preparedStatement.setInt(4,product.getQty());
            preparedStatement.setInt(5,product.getPrice());

            return preparedStatement.executeUpdate()>0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean updateProduct(Product product) {

        try {
            Connection connection = dbConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE product set productname = ? ,size=?,category=?,qty=?,price = ? WHERE id=?");


            preparedStatement.setString(1,product.getName());
            preparedStatement.setString(2,product.getSize());
            preparedStatement.setString(3,product.getCmb());
            preparedStatement.setInt(4,product.getPrice());
            preparedStatement.setInt(5,product.getQty());
            preparedStatement.setInt(6,product.getId());

            return preparedStatement.executeUpdate()>0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean deleteproduct(int id) {
        try {
            Connection connection = dbConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM product WHERE id = ?");
            preparedStatement.setInt(1,id);

            return preparedStatement.executeUpdate()>0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Product> getAllProduct() {
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

            return getDetails;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
