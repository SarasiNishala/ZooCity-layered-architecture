package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.MedicineBO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.MedicineDAO;
import lk.ijse.dto.MedicineDto;
import lk.ijse.entity.Medicine;

import java.sql.SQLException;
import java.util.ArrayList;

public class MedicineBOImpl implements MedicineBO {
    MedicineDAO medicineDAO = (MedicineDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.MEDICINE);
    @Override
    public ArrayList<MedicineDto> getAllAnimalMedicine() throws SQLException, ClassNotFoundException {
        ArrayList<Medicine> medicines = medicineDAO.getAll();
        ArrayList<MedicineDto> medicineDtos = new ArrayList<>();

        for (Medicine medicine :medicines ){
            medicineDtos.add(new MedicineDto(medicine.getMediId(),medicine.getName(),medicine.getPrice(),medicine.getQty()));
        }
        return medicineDtos;
    }

    @Override
    public boolean saveMedicine(MedicineDto dto) throws SQLException, ClassNotFoundException {
        return medicineDAO.save(new Medicine(dto.getMediId(),dto.getName(),dto.getPrice(),dto.getQty()));
    }

    @Override
    public boolean updateMedicine(MedicineDto dto) throws SQLException, ClassNotFoundException {
        return medicineDAO.update(new Medicine(dto.getMediId(),dto.getName(),dto.getPrice(),dto.getQty()));
    }

    @Override
    public boolean deleteMedicine(String id) throws SQLException, ClassNotFoundException {
        return medicineDAO.delete(id);
    }

    @Override
    public boolean existMedicine(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String generateNewMedicineId() throws SQLException, ClassNotFoundException {
        return medicineDAO.generateNewId();
    }

    @Override
    public MedicineDto searchMedicine(String newValue) throws SQLException, ClassNotFoundException {
        Medicine medicine = medicineDAO.search(newValue);
        MedicineDto medicineDto = new MedicineDto(medicine.getMediId(),medicine.getName(),medicine.getPrice(),medicine.getQty());
        return medicineDto;
    }

    @Override
    public boolean updateStock(String foodId, int qty) throws SQLException {
        return false;
    }
}
