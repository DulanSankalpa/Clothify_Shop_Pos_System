package Edu.Icet.Clothify_Store.Service.AdminCenter;

import Edu.Icet.Clothify_Store.Model.SObject;
import Edu.Icet.Clothify_Store.Service.SupperService;

public interface ManamgementUser extends SupperService {
    boolean addUser(SObject sObject);
    boolean UpdateUser(SObject sObject);
    boolean deleteUser(int id);
}





