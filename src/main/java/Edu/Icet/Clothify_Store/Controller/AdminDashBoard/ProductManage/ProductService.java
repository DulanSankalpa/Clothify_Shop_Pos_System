package Edu.Icet.Clothify_Store.Controller.AdminDashBoard.ProductManage;

import Edu.Icet.Clothify_Store.Model.Product;

import java.util.List;

public interface ProductService {
    boolean addproduct(Product product);
    boolean updateProduct(Product product);
    boolean deleteproduct(int id);
    List<Product> getAllProduct();
}
