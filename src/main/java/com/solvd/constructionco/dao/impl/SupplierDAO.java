package com.solvd.constructionco.dao.impl;

import com.solvd.constructionco.dao.ISupplierDAO;
import com.solvd.constructionco.models.Supplier;

import java.util.ArrayList;
import java.util.List;

public class SupplierDAO implements ISupplierDAO<Supplier, Integer> {
    private List<Supplier> suppliers;

    private static final String getAllQuery = "SELECT supplier_id, supplier_name, email FROM suppliers";
    private static final String getByIdQuery = "SELECT supplier_id, supplier_name, email FROM suppliers WHERE supplier_id = ?";
    private static final String saveQuery = "INSERT INTO suppliers VALUES(?,?,?)";
    private static final String deleteQuery = "DELETE FROM suppliers WHERE supplier_id = ?";
    private static final String updateQuery = "UPDATE suppliers SET supplier_id = ?, supplier_name = ?, email = ? WHERE supplier_id = ?";


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
