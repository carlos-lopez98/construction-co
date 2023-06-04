package com.solvd.constructionco.models;

public class Material {

    private int materialId;
    private int supplierId;
    private String materialName;
    private String description;
    private int pricePerUnit;
    private int unit;


    public Material(){

    };

    public Material(String materialName, String description, int pricePerUnit, int unit) {
        this.materialName = materialName;
        this.description = description;
        this.pricePerUnit = pricePerUnit;
        this.unit = unit;
    }

    public int getMaterialId() {
        return materialId;
    }

    public void setMaterialId(int materialId) {
        this.materialId = materialId;
    }

    public int getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(int supplierId) {
        this.supplierId = supplierId;
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPricePerUnit() {
        return pricePerUnit;
    }

    public void setPricePerUnit(int pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
    }

    public int getUnit() {
        return unit;
    }

    public void setUnit(int unit) {
        this.unit = unit;
    }
}
