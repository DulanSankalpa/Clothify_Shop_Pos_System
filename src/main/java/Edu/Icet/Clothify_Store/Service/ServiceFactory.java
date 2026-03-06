package Edu.Icet.Clothify_Store.Service;

import Edu.Icet.Clothify_Store.Service.AdminCenter.impl.*;
import Edu.Icet.Clothify_Store.Service.StaffBranch.impl.OrderServiceimpl;
import Edu.Icet.Clothify_Store.Service.StaffBranch.impl.PlaseOrderimpl;
import Edu.Icet.Clothify_Store.Util.ServiceType;

public class ServiceFactory {
    private static ServiceFactory Instance;
    private ServiceFactory(){};

    public static ServiceFactory getInstance() {
        return Instance==null?Instance = new ServiceFactory():Instance;
    }

    public <T extends SupperService>T getServiceTyrpe(ServiceType serviceType){
        switch (serviceType){
            case ManageProduct:return (T) new ManageProsuctimpl();
            case Inventory: return (T) new InventoryServiceimpl();
            case StaffReport: return (T) new StaffReportimpl();
            case SuppliyerService: return (T) new SuppliyerServiceimpl();
            case UserManagement: return (T) new UserManemnetimpl();
            case ViewRepoer: return (T) new ViewReportimpl();
            case Order: return (T) new OrderServiceimpl();
            case PlaseOrder: return (T) new PlaseOrderimpl();

        }
        return null;
    }
}
