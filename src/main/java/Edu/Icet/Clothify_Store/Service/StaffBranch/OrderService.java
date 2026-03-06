package Edu.Icet.Clothify_Store.Service.StaffBranch;

import Edu.Icet.Clothify_Store.Model.Order;
import Edu.Icet.Clothify_Store.Service.SupperService;

import java.util.List;

public interface OrderService extends SupperService {
    List<Order> getOrderRecode(int id);
}
