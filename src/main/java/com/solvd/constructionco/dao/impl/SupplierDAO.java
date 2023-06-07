package com.solvd.constructionco.dao.impl;

import com.solvd.constructionco.dao.ConstructionDAO;
import com.solvd.constructionco.dao.iSupplierDAO;
import com.solvd.constructionco.models.Supplier;

import java.util.ArrayList;
import java.util.List;

public class SupplierDAO implements iSupplierDAO<Supplier, Integer> {
    private List<Supplier> suppliers;

    public SupplierDAO() {
        suppliers = new ArrayList<>();
    }

    @Override
    public Supplier getById(Integer id) {
        for (Supplier supplier : suppliers) {
            if (supplier.getSupplierId() == id) {
                return supplier;
            }
        }
        return null;
    }

    @Override
    public void save(Supplier supplier) {
        suppliers.add(supplier);
    }

    @Override
    public void update(Supplier supplier) {
        int supplierId = supplier.getSupplierId();
        for (int i = 0; i < suppliers.size(); i++) {
            Supplier existingSupplier = suppliers.get(i);
            if (existingSupplier.getSupplierId() == supplierId) {
                suppliers.set(i, supplier);
                return;
            }
        }
    }

    @Override
    public void delete(Integer id) {
        suppliers.removeIf(supplier -> supplier.getSupplierId() == id);
    }

    @Override
    public List<Supplier> getAll() {
        return new ArrayList<>(suppliers);
    }
}
