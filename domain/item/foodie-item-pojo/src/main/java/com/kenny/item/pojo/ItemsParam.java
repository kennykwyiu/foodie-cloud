package com.kenny.item.pojo;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "items_param")
public class ItemsParam {
    /**
     * Item parameter id
     */
    @Id
    private String id;

    /**
     * Item foreign key id
     */
    @Column(name = "item_id")
    private String itemId;

    /**
     * Place of origin, e.g., Jiangsu, China
     */
    @Column(name = "produc_place")
    private String producPlace;

    /**
     * Shelf life, e.g., 180 days
     */
    @Column(name = "foot_period")
    private String footPeriod;

    /**
     * Brand name, e.g., Three Big Wolves
     */
    private String brand;

    /**
     * Factory name, e.g., Big Wolf Factory
     */
    @Column(name = "factory_name")
    private String factoryName;

    /**
     * Factory address, e.g., Big Wolf Production Base
     */
    @Column(name = "factory_address")
    private String factoryAddress;

    /**
     * Packaging method, e.g., bagged
     */
    @Column(name = "packaging_method")
    private String packagingMethod;

    /**
     * Specification weight, e.g., 35g
     */
    private String weight;

    /**
     * Storage method, e.g., room temperature 5~25°
     */
    @Column(name = "storage_method")
    private String storageMethod;

    /**
     * Consumption method, e.g., ready to eat
     */
    @Column(name = "eat_method")
    private String eatMethod;

    /**
     * Creation time
     */
    @Column(name = "created_time")
    private Date createdTime;

    /**
     * Update time
     */
    @Column(name = "updated_time")
    private Date updatedTime;

    /**
     * Get item parameter id
     *
     * @return id - Item parameter id
     */
    public String getId() {
        return id;
    }

    /**
     * Set item parameter id
     *
     * @param id Item parameter id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Get item foreign key id
     *
     * @return item_id - Item foreign key id
     */
    public String getItemId() {
        return itemId;
    }

    /**
     * Set item foreign key id
     *
     * @param itemId Item foreign key id
     */
    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    /**
     * Get place of origin
     *
     * @return produc_place - Place of origin, e.g., Jiangsu, China
     */
    public String getProducPlace() {
        return producPlace;
    }

    /**
     * Set place of origin
     *
     * @param producPlace Place of origin, e.g., Jiangsu, China
     */
    public void setProducPlace(String producPlace) {
        this.producPlace = producPlace;
    }

    /**
     * Get shelf life
     *
     * @return foot_period - Shelf life, e.g., 180 days
     */
    public String getFootPeriod() {
        return footPeriod;
    }

    /**
     * Set shelf life
     *
     * @param footPeriod Shelf life, e.g., 180 days
     */
    public void setFootPeriod(String footPeriod) {
        this.footPeriod = footPeriod;
    }

    /**
     * Get brand name
     *
     * @return brand - Brand name, e.g., Three Big Wolves
     */
    public String getBrand() {
        return brand;
    }

    /**
     * Set brand name
     *
     * @param brand Brand name, e.g., Three Big Wolves
     */
    public void setBrand(String brand) {
        this.brand = brand;
    }

    /**
     * Get factory name
     *
     * @return factory_name - Factory name, e.g., Big Wolf Factory
     */
    public String getFactoryName() {
        return factoryName;
    }

    /**
     * Set factory name
     *
     * @param factoryName Factory name, e.g., Big Wolf Factory
     */
    public void setFactoryName(String factoryName) {
        this.factoryName = factoryName;
    }

    /**
     * Get factory address
     *
     * @return factory_address - Factory address, e.g., Big Wolf Production Base
     */
    public String getFactoryAddress() {
        return factoryAddress;
    }

    /**
     * Set factory address
     *
     * @param factoryAddress Factory address, e.g., Big Wolf Production Base
     */
    public void setFactoryAddress(String factoryAddress) {
        this.factoryAddress = factoryAddress;
    }

    /**
     * Get packaging method
     *
     * @return packaging_method - Packaging method, e.g., bagged
     */
    public String getPackagingMethod() {
        return packagingMethod;
    }

    /**
     * Set packaging method
     *
     * @param packagingMethod Packaging method, e.g., bagged
     */
    public void setPackagingMethod(String packagingMethod) {
        this.packagingMethod = packagingMethod;
    }

    /**
     * Get specification weight
     *
     * @return weight - Specification weight, e.g., 35g
     */
    public String getWeight() {
        return weight;
    }

    /**
     * Set specification weight
     *
     * @param weight Specification weight, e.g., 35g
     */
    public void setWeight(String weight) {
        this.weight = weight;
    }

    /**
     * Get storage method
     *
     * @return storage_method - Storage method, e.g., room temperature 5~25°
     */
    public String getStorageMethod() {
        return storageMethod;
    }

    /**
     * Set storage method
     *
     * @param storageMethod Storage method, e.g., room temperature 5~25°
     */
    public void setStorageMethod(String storageMethod) {
        this.storageMethod = storageMethod;
    }

    /**
     * Get consumption method
     *
     * @return eat_method - Consumption method, e.g., ready to eat
     */
    public String getEatMethod() {
        return eatMethod;
    }

    /**
     * Set consumption method
     *
     * @param eatMethod Consumption method, e.g., ready to eat
     */
    public void setEatMethod(String eatMethod) {
        this.eatMethod = eatMethod;
    }

    /**
     * Get creation time
     *
     * @return created_time - Creation time
     */
    public Date getCreatedTime() {
        return createdTime;
    }

    /**
     * Set creation time
     *
     * @param createdTime Creation time
     */
    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    /**
     * Get update time
     *
     * @return updated_time - Update time
     */
    public Date getUpdatedTime() {
        return updatedTime;
    }

    /**
     * Set update time
     *
     * @param updatedTime Update time
     */
    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }
}