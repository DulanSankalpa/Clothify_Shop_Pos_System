package Edu.Icet.Clothify_Store.Controller.StaffDashBoard.SerachProduct;

import Edu.Icet.Clothify_Store.Model.Order;
import Edu.Icet.Clothify_Store.Model.Product;

import java.util.List;

public interface StaffService {
    List<Order> FindProduct(String name);
}
