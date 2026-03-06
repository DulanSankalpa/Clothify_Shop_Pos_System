package Edu.Icet.Clothify_Store.Service.StaffBranch;

import Edu.Icet.Clothify_Store.Model.Order;

import java.util.List;

public interface SearchProductService {
    List<Order> FindProduct(String name);
}
