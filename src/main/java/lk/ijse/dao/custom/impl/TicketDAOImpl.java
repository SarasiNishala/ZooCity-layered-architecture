package lk.ijse.dao.custom.impl;

import lk.ijse.dao.custom.TicketDAO;
import lk.ijse.entity.Ticket;
import lk.ijse.util.SQLUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TicketDAOImpl implements TicketDAO {
    @Override
    public ArrayList<Ticket> getAll() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute( "SELECT * FROM Ticket");

        ArrayList<Ticket> allTicket = new ArrayList<>();
        while (resultSet.next()) {
            Ticket entity = new Ticket(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getDouble(3),
                    resultSet.getDate(4).toLocalDate(),
                    resultSet.getString(5)
            );
            allTicket.add(entity);
        }
        return allTicket;
    }

    @Override
    public boolean save(Ticket entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO Ticket VALUES(?, ?, ?, ?, ?)",
            entity.getTicketNo(),
            entity.getTicketType(),
            entity.getPrice(),
            entity.getDate(),
            entity.getAdminId());
    }

    @Override
    public boolean update(Ticket entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE Ticket SET TicketType = ?, Price = ?, Date = ?, AdminId = ? WHERE TicketNo = ?",
                entity.getTicketNo(),
                entity.getTicketType(),
                entity.getPrice(),
                entity.getDate(),
                entity.getAdminId());
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("SELECT * FROM Ticket WHERE TicketNo" , id);
    }

    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT TicketNo FROM Ticket ORDER BY TicketNo DESC LIMIT 1;");
        if (rst.next()) {
            String id = rst.getString("Ticket");
            int newTicketId = Integer.parseInt(id.replace("T00", "")) + 1;
            return String.format("T00", newTicketId);
        } else {
            return "T001";
        }
    }

    @Override
    public Ticket search(String newValue) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM Ticket WHERE TicketNo=?", newValue);

        Ticket entity = null;
        if(rst.next()) {
            entity = new Ticket(
                    newValue + "",
                    rst.getString(1),
                    rst.getDouble(2),
                    rst.getDate(3).toLocalDate(),
                    rst.getString(4));
        }
        return entity;
    }
}
