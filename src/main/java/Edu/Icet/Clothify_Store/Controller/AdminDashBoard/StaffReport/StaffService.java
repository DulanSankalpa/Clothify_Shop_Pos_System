package Edu.Icet.Clothify_Store.Controller.AdminDashBoard.StaffReport;

import Edu.Icet.Clothify_Store.Model.Order;
import Edu.Icet.Clothify_Store.Model.SObject;

import java.util.List;

public interface StaffService {
    List<Order> getdata(int id);
}
