package Edu.Icet.Clothify_Store.Controller.StaffDashBoard.PlaseOrder;

import Edu.Icet.Clothify_Store.Model.Order;

import java.util.List;

public interface PlaseOrderService {
    boolean addOrder(Order order);
    boolean updateOrder (Order order);
    boolean DeleteOrder (int id);
    List<Order> getAll();
}
