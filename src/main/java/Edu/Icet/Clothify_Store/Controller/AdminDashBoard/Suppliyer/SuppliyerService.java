package Edu.Icet.Clothify_Store.Controller.AdminDashBoard.Suppliyer;

import Edu.Icet.Clothify_Store.Model.Supplier;

import java.util.List;

public interface SuppliyerService {
    boolean addsuppliyer(Supplier supplier);
    boolean updatesuppiyer(Supplier supplier);
    boolean deletesuppiyer(int id);
    List<Supplier> getAll();


}
