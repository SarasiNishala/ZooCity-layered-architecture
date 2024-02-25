package lk.ijse.bo;

import lk.ijse.bo.custom.impl.*;

public class BOFactory {
    private static BOFactory boFactory;

    private BOFactory(){}

    public static BOFactory getBoFactory() {
        return (boFactory == null)? boFactory = new BOFactory() : boFactory;
    }

    public enum BOTypes{
        ANIMAL,CAGE,EMPLOYEE,FOOD,MEDICINE,SALARY,TICKET,WORKSCHEDULE,ANIMALSFOOD,ANIMALSMEDICINE
    }

    public SuperBO getBO(BOTypes boTypes){
        switch (boTypes){
            case ANIMAL:
                return new AnimalBOImpl();
            case CAGE:
                return new CageBOImpl();
            case EMPLOYEE:
                return new EmployeeBOImpl();
            case MEDICINE:
                return new MedicineBOImpl();
            case FOOD:
                return new FoodBOImpl();
            case SALARY:
                return new SalaryBOImpl();
            case TICKET:
                return new TicketBOImpl();
            case WORKSCHEDULE:
                return new WorkScheduleBOImpl();
            default:
                return null;
        }
    }
}
