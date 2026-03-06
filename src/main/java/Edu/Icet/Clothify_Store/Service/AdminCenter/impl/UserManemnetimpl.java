package Edu.Icet.Clothify_Store.Service.AdminCenter.impl;

import Edu.Icet.Clothify_Store.DB.dbConnection;
import Edu.Icet.Clothify_Store.Model.SObject;
import Edu.Icet.Clothify_Store.Service.AdminCenter.ManamgementUser;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserManemnetimpl implements ManamgementUser {

    public boolean addUser(SObject staff) {
        try {
            Connection connection = dbConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO staff (username,email,pw,rolltypr) values (?,?,?,?)");

            preparedStatement.setString(1,staff.getUsername());
            preparedStatement.setString(2,staff.getEmail());
            preparedStatement.setString(3,staff.getPw());
            preparedStatement.setString(4,staff.getCmd());

            return preparedStatement.executeUpdate()>0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean UpdateUser(SObject staff) {
        try {
            Connection connection = dbConnection.getInstance().getConnection();

            String sql = "UPDATE staff SET username=?, email=?, pw=?, rolltypr=? WHERE id=?";
            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setString(1, staff.getUsername());
            ps.setString(2, staff.getEmail());
            ps.setString(3, staff.getPw());
            ps.setString(4, staff.getCmd());
            ps.setInt(5, staff.getId());

            return ps.executeUpdate()>0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteUser(int id) {
        try {
            Connection connection = dbConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM staff WHERE id=?");
            preparedStatement.setInt(1,id);
            int i = preparedStatement.executeUpdate();
            return i>0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
