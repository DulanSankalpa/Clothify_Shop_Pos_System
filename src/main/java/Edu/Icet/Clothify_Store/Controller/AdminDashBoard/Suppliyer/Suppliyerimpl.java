package Edu.Icet.Clothify_Store.Controller.AdminDashBoard.Suppliyer;

import Edu.Icet.Clothify_Store.DB.dbConnection;
import Edu.Icet.Clothify_Store.Model.Supplier;
import javafx.collections.FXCollections;
import javafx.scene.control.Alert;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Suppliyerimpl implements SuppliyerService{

    @Override
    public boolean addsuppliyer(Supplier supplier) {
        try {
            Connection connection = dbConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO suppliyer (suppliyername,suppliyernumber,suppliyeremail,suppliyeraddress) values (?,?,?,?)");

            preparedStatement.setString(1,supplier.getName());
            preparedStatement.setString(2,supplier.getNumber());
            preparedStatement.setString(3,supplier.getEmail());
            preparedStatement.setString(4,supplier.getAddress());

            return preparedStatement.executeUpdate()>0;


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public boolean updatesuppiyer(Supplier supplier) {
        try {
            Connection connection = dbConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE suppliyer SET suppliyername = ? , suppliyernumber = ? , suppliyeremail=?,suppliyeraddress=? WHERE id = ? ");

            preparedStatement.setString(1,supplier.getName());
            preparedStatement.setString(2,supplier.getNumber());
            preparedStatement.setString(3,supplier.getEmail());
            preparedStatement.setString(4,supplier.getAddress());
            preparedStatement.setInt(5,supplier.getId());

            return preparedStatement.executeUpdate()>0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean deletesuppiyer(int id) {
        try {
            Connection connection = dbConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM suppliyer WHERE id = ? ");
            preparedStatement.setInt(1,id);

            return preparedStatement.executeUpdate()>0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Supplier> getAll() {
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

            return suppliers;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
