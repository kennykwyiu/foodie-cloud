package com.kenny.item.pojo;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

@Table(name = "items_spec")
public class ItemsSpec {
    /**
     * Item specification id
     */
    @Id
    private String id;

    /**
     * Item foreign key id
     */
    @Column(name = "item_id")
    private String itemId;

    /**
     * Specification name
     */
    private String name;

    /**
     * Stock
     */
    private Integer stock;

    /**
     * Discount rate
     */
    private BigDecimal discounts;

    /**
     * Discounted price
     */
    @Column(name = "price_discount")
    private Integer priceDiscount;

    /**
     * Original price
     */
    @Column(name = "price_normal")
    private Integer priceNormal;

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
     * Get item specification id
     *
     * @return id - Item specification id
     */
    public String getId() {
        return id;
    }

    /**
     * Set item specification id
     *
     * @param id Item specification id
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
     * Get specification name
     *
     * @return name - Specification name
     */
    public String getName() {
        return name;
    }

    /**
     * Set specification name
     *
     * @param name Specification name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get stock
     *
     * @return stock - Stock
     */
    public Integer getStock() {
        return stock;
    }

    /**
     * Set stock
     *
     * @param stock Stock
     */
    public void setStock(Integer stock) {
        this.stock = stock;
    }

    /**
     * Get discount rate
     *
     * @return discounts - Discount rate
     */
    public BigDecimal getDiscounts() {
        return discounts;
    }

    /**
     * Set discount rate
     *
     * @param discounts Discount rate
     */
    public void setDiscounts(BigDecimal discounts) {
        this.discounts = discounts;
    }

    /**
     * Get discounted price
     *
     * @return price_discount - Discounted price
     */
    public Integer getPriceDiscount() {
        return priceDiscount;
    }

    /**
     * Set discounted price
     *
     * @param priceDiscount Discounted price
     */
    public void setPriceDiscount(Integer priceDiscount) {
        this.priceDiscount = priceDiscount;
    }

    /**
     * Get original price
     *
     * @return price_normal - Original price
     */
    public Integer getPriceNormal() {
        return priceNormal;
    }

    /**
     * Set original price
     *
     * @param priceNormal Original price
     */
    public void setPriceNormal(Integer priceNormal) {
        this.priceNormal = priceNormal;
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