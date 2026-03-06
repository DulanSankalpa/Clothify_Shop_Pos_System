package Edu.Icet.Clothify_Store.Service;

import Edu.Icet.Clothify_Store.Service.Customer.impl.CustomerServiceimpl;
import Edu.Icet.Clothify_Store.Service.Customer.impl.ItemServiceimpl;
import Edu.Icet.Clothify_Store.Service.Customer.impl.OrderServiceimpl;
import Edu.Icet.Clothify_Store.Util.ServiceType;

public class ServiceFactory {
    private static ServiceFactory Instance;
    private ServiceFactory(){};

    public static ServiceFactory getInstance() {
        return Instance==null?Instance = new ServiceFactory():Instance;
    }

    public <T extends SupperService>T getServiceTyrpe(ServiceType serviceType){
        switch (serviceType){
            case Customer:return (T) new CustomerServiceimpl();
            case Item: return (T) new ItemServiceimpl();
            case Orders: return (T) new OrderServiceimpl();
        }
        return null;
    }
}
