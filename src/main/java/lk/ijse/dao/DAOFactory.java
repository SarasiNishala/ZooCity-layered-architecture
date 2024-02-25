package lk.ijse.dao;


import lk.ijse.dao.custom.impl.*;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory(){}

    public static DAOFactory getDaoFactory() {
        return (daoFactory == null)? daoFactory = new DAOFactory() : daoFactory;
    }

    public enum DAOTypes{
        ANIMAL,CAGE,EMPLOYEE,FOOD,MEDICINE,SALARY,TICKET,WORKSCHEDULE,ANIMALSFOOD,ANIMALSMEDICINE,CAGEMANAGEFORM
    }

   public SuperDAO getDAO(DAOTypes daoTypes){
        switch (daoTypes){
            case ANIMAL:
                return new AnimalDAOImpl();
            case CAGE:
                return new CageDAOImpl();
            case FOOD:
                return new FoodDAOImpl();
            case EMPLOYEE:
                return new EmployeeDAOImpl();
            case MEDICINE:
                return new MedicineDAOImpl();
            case WORKSCHEDULE:
                return new WorkScheduleDAOImpl();
            case SALARY:
                return new SalaryDAOImpl();
            case TICKET:
                return new TicketDAOImpl();
            default:
                return null;
        }
   }
}
