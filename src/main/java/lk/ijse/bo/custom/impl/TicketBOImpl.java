package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.TicketBO;
import lk.ijse.dao.DAOFactory;

import lk.ijse.dao.custom.TicketDAO;
import lk.ijse.dto.TicketDto;
import lk.ijse.entity.Ticket;

import java.sql.SQLException;
import java.util.ArrayList;

public class TicketBOImpl implements TicketBO {
    TicketDAO ticketDAO  = (TicketDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.TICKET);

    @Override
    public ArrayList<TicketDto> getAllTickets() throws SQLException, ClassNotFoundException {
        ArrayList<Ticket> tickets = ticketDAO.getAll();
        ArrayList<TicketDto> ticketDtos = new ArrayList<>();

        for (Ticket ticket :tickets ){
            ticketDtos.add(new TicketDto(ticket.getTicketNo(),ticket.getTicketType(),ticket.getPrice(),ticket.getDate(),ticket.getAdminId()));
        }
        return ticketDtos;
    }

    @Override
    public boolean saveTicket(TicketDto dto) throws SQLException, ClassNotFoundException {
        return ticketDAO.save(new Ticket(dto.getTicketNo(),dto.getTicketType(),dto.getPrice(),dto.getDate(),dto.getAdminId()));
    }

    @Override
    public boolean updateTickt(TicketDto dto) throws SQLException, ClassNotFoundException {
        return ticketDAO.update(new Ticket(dto.getTicketNo(),dto.getTicketType(),dto.getPrice(),dto.getDate(),dto.getAdminId()));
    }

    @Override
    public boolean existTickt(String id) throws SQLException, ClassNotFoundException {
        return ticketDAO.exist(id);
    }

    @Override
    public String generateNewTicketId() throws SQLException, ClassNotFoundException {
        return ticketDAO.generateNewId();
    }

    @Override
    public TicketDto searchTicket(String newValue) throws SQLException, ClassNotFoundException {
        Ticket ticket = ticketDAO.search(newValue);
        TicketDto ticketDto = new TicketDto(ticket.getTicketNo(),ticket.getTicketType(),ticket.getPrice(),ticket.getDate(),ticket.getAdminId());
        return ticketDto;
    }
}
