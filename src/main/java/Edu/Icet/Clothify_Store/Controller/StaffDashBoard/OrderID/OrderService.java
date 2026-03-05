package Edu.Icet.Clothify_Store.Controller.StaffDashBoard.OrderID;

import Edu.Icet.Clothify_Store.Model.Order;

import java.util.List;

public interface OrderService {
    List<Order> getOrderRecode(int id);
}
