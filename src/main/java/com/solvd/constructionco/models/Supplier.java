package com.solvd.constructionco.models;

public class Supplier {
    private int supplierId;
    private String supplierName;
    private String email;

    public Supplier(){

    };

    public Supplier(String supplierName, String email){
      this.supplierName = supplierName;
      this.email = email;
    };

    public int getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(int supplierId) {
        this.supplierId = supplierId;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
