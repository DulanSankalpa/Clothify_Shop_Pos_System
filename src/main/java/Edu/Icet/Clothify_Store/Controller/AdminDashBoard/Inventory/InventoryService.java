package Edu.Icet.Clothify_Store.Controller.AdminDashBoard.Inventory;

import Edu.Icet.Clothify_Store.Model.Product;

import java.util.List;

public interface InventoryService {
   List<Product> CurrentStock();
   List<Product> LowStock();
   List<Product> outofStock();
}
