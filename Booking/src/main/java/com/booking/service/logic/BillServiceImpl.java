package com.booking.service.logic;

import com.booking.dao.BillDao;
import com.booking.dao.DaoException;
import com.booking.entity.Bill;
import com.booking.service.BillService;
import com.booking.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class BillServiceImpl extends BaseService implements BillService {
    public static final Logger LOGGER = LogManager.getLogger(BillServiceImpl.class);

    private BillDao billDao;

    public void setBillDao(BillDao billDao) {
        this.billDao = billDao;
    }

    @Override
    public Bill readById(Integer id) throws ServiceException {
        try {
            return billDao.read(id);
        } catch (DaoException e){
            throw new ServiceException(e);
        }
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
                LOGGER.error(e1.getLocalizedMessage());
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
