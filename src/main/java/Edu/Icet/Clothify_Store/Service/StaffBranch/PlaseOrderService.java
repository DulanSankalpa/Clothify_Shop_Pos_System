package Edu.Icet.Clothify_Store.Service.StaffBranch;

import Edu.Icet.Clothify_Store.Model.Order;
import Edu.Icet.Clothify_Store.Service.SupperService;

import java.util.List;

public interface PlaseOrderService extends SupperService {
    boolean addOrder(Order order);
    boolean updateOrder (Order order);
    boolean DeleteOrder (int id);
    List<Order> getAll();
}
