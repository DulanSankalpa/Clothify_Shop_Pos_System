package Edu.Icet.Clothify_Store.Controller.AdminDashBoard.UserManage;

import Edu.Icet.Clothify_Store.Model.SObject;

import java.util.List;

public interface ManageService {
    boolean addUser(SObject staff);
    boolean UpdateUser(SObject staff);
    boolean deleteUser(int id);
    List<SObject> getAll();
}
