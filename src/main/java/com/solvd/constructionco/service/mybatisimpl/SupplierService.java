package com.solvd.constructionco.service.mybatisimpl;

import com.solvd.constructionco.Main;
import com.solvd.constructionco.dao.ICustomerDAO;
import com.solvd.constructionco.dao.ISupplierDAO;
import com.solvd.constructionco.models.Customer;
import com.solvd.constructionco.models.Supplier;
import com.solvd.constructionco.service.interfaces.ISupplierService;
import com.solvd.constructionco.util.SQLSessionUtil;
import org.apache.ibatis.session.SqlSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class SupplierService implements ISupplierService {

    private static final Logger logger = LogManager.getLogger(Main.class);
    private static final SQLSessionUtil sessionUtil = new SQLSessionUtil();

    @Override
    public Supplier getById(Integer supplierID) {
        if (supplierID > 0) {
            Supplier supplier = null;

            SqlSession session = sessionUtil.retrieveSqlSession();
            ISupplierDAO supplierDAO = session.getMapper(ISupplierDAO.class);
            supplier = (Supplier) supplierDAO.getById(supplierID);

            //Commits and closes
            //Until session.commit is closed changes are not persisted to the database
            session.commit();
            session.close();
            return supplier;
        } else {
            throw new RuntimeException("Invalid SupplierID Entered");
        }
    }

    @Override
    public void save(Supplier supplier) {

    }

    @Override
    public void update(Supplier supplier) {

    }

    @Override
    public void delete(Integer supplierID) {

    }

    @Override
    public List<Supplier> getAll() {
        return null;
    }
}
