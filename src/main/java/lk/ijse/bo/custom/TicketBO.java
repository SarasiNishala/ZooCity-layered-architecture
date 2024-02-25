package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBO;
import lk.ijse.dao.custom.TicketDAO;
import lk.ijse.dto.TicketDto;
import lk.ijse.entity.Ticket;

import java.sql.SQLException;
import java.util.ArrayList;

public interface TicketBO extends SuperBO {
    ArrayList<TicketDto> getAllTickets() throws SQLException, ClassNotFoundException;
    boolean saveTicket(TicketDto dto) throws SQLException, ClassNotFoundException;
    boolean updateTickt(TicketDto dto) throws SQLException, ClassNotFoundException;
    boolean existTickt(String id) throws SQLException, ClassNotFoundException;
    String generateNewTicketId() throws SQLException, ClassNotFoundException;
    TicketDto searchTicket(String newValue) throws SQLException, ClassNotFoundException;
}
