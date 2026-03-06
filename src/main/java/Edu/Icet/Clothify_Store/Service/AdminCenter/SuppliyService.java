package Edu.Icet.Clothify_Store.Service.AdminCenter;

import Edu.Icet.Clothify_Store.Model.Supplier;
import Edu.Icet.Clothify_Store.Service.SupperService;

import java.util.List;

public interface SuppliyService extends SupperService {
    boolean addsuppliyer(Supplier supplier);
    boolean updatesuppiyer(Supplier supplier);
    boolean deletesuppiyer(int id);
    List<Supplier> getAll();
}
