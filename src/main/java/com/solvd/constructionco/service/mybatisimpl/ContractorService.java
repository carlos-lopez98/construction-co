package com.solvd.constructionco.service.mybatisimpl;

import com.solvd.constructionco.Main;
import com.solvd.constructionco.dao.IContractorDAO;
import com.solvd.constructionco.dao.ITaskDAO;
import com.solvd.constructionco.models.Contractor;
import com.solvd.constructionco.models.Task;
import com.solvd.constructionco.service.interfaces.IContractorService;
import com.solvd.constructionco.util.SQLSessionUtil;
import org.apache.ibatis.session.SqlSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class ContractorService implements IContractorService<Contractor, Integer> {

    private static final Logger logger = LogManager.getLogger(Main.class);
    private static final SQLSessionUtil sessionUtil = new SQLSessionUtil();

    @Override
    public Contractor getById(Integer contractorID) {
        if (contractorID > 0) {
            Contractor contractor = null;

            SqlSession session = sessionUtil.retrieveSqlSession();
            IContractorDAO contractorDAO = session.getMapper(IContractorDAO.class);
            contractor = (Contractor) contractorDAO.getById(contractorID);

            //Commits and closes
            //Until session.commit is closed changes are not persisted to the database
            session.commit();
            session.close();
            return contractor;
        } else {
            throw new RuntimeException("Invalid ContractorID entered");
        }
    }

    @Override
    public void save(Contractor contractor) {
        if (contractor != null) {
            SqlSession session = sessionUtil.retrieveSqlSession();
            IContractorDAO contractorDAO = session.getMapper(IContractorDAO.class);

            contractorDAO.save(contractor);

            session.commit();
            session.close();

            logger.info("Succesfully saved contractor to database");
        } else {
            throw new NullPointerException("Contractor provided is null");
        }
    }

    @Override
    public void update(Contractor contractor) {
        if (contractor != null) {
            SqlSession session = sessionUtil.retrieveSqlSession();
            IContractorDAO contractorDAO = session.getMapper(IContractorDAO.class);

            contractorDAO.update(contractor);

            session.commit();
            session.close();

            logger.info("Succesfully updated contractor in database");
        } else {
            throw new NullPointerException("Contractor provided is null");
        }
    }

    @Override
    public void delete(Integer contractorID) {
        if (contractorID > 0) {
            SqlSession session = sessionUtil.retrieveSqlSession();
            IContractorDAO contractorDAO = session.getMapper(IContractorDAO.class);
            Contractor contractor = (Contractor) contractorDAO.getById(contractorID);
            if (contractor != null) {
                contractorDAO.delete(contractorID);
                session.commit();
                session.close();
                logger.info("Successfully deleted contractor from database");
            } else {
                throw new RuntimeException("ContractorID is not in database");
            }
        } else {
            throw new RuntimeException("ContractorID given is not a valid ID");
        }
    }

    @Override
    public List<Contractor> getAll() {
        SqlSession session = sessionUtil.retrieveSqlSession();
        IContractorDAO contractorDAO = session.getMapper(IContractorDAO.class);

        List<Contractor> contractors = contractorDAO.getAll();

        if (contractors.isEmpty()) {
            throw new RuntimeException("No Contractors in Database");
        } else {
            session.commit();
            session.close();
            return contractors;
        }
    }
}
