package Edu.Icet.Clothify_Store.Service.Customer;

import Edu.Icet.Clothify_Store.Model.Product;
import Edu.Icet.Clothify_Store.Service.SupperService;

import java.util.List;

public interface CustomerSsrvice extends SupperService {
    boolean addproduct(Product product);
    boolean updateProduct(Product product);
    boolean deleteproduct(int id);
    List<Product> getAllProduct();
}
