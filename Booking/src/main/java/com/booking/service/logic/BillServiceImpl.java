package com.booking.service.logic;

import com.booking.Printable;
import com.booking.dao.BillDao;
import com.booking.dao.DaoException;
import com.booking.entity.Bill;
import com.booking.service.BillService;
import com.booking.service.exception.ServiceException;

import java.util.List;

public class BillServiceImpl extends BaseService implements BillService {
    private BillDao billDao;

    public void setBillDao(BillDao billDao) {
        this.billDao = billDao;
    }

    @Override
    public List<Bill> readAll() throws ServiceException {
        try {
            return billDao.readAll();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void save(Bill bill) throws ServiceException {
        try {
            getTransaction().start();
            if (bill.getId() == null) {
                billDao.create(bill);
            } else {
                billDao.update(bill);
            }
            getTransaction().commit();
        } catch (DaoException | ServiceException e) {
            try {
                getTransaction().rollback();
            } catch (ServiceException e1) {
                Printable.printError(e1.getLocalizedMessage(),e);
            }
            throw new ServiceException(e);
        }
    }

    @Override
    public void delete(Integer id) throws ServiceException {
        try {
            billDao.delete(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
