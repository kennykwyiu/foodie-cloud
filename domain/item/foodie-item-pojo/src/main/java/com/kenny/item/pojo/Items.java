package com.kenny.item.pojo;

import javax.persistence.Column;
import javax.persistence.Id;
import java.util.Date;

public class Items {
    /**
     * Item primary key id
     */
    @Id
    private String id;

    /**
     * Item name
     */
    @Column(name = "item_name")
    private String itemName;

    /**
     * Category foreign key id
     */
    @Column(name = "cat_id")
    private Integer catId;

    /**
     * Root category foreign key id
     */
    @Column(name = "root_cat_id")
    private Integer rootCatId;

    /**
     * Total sales
     */
    @Column(name = "sell_counts")
    private Integer sellCounts;

    /**
     * On/off status, 1: on shelf, 2: off shelf
     */
    @Column(name = "on_off_status")
    private Integer onOffStatus;

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
     * Item content
     */
    private String content;

    /**
     * Get item primary key id
     *
     * @return id - Item primary key id
     */
    public String getId() {
        return id;
    }

    /**
     * Set item primary key id
     *
     * @param id Item primary key id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Get item name
     *
     * @return item_name - Item name
     */
    public String getItemName() {
        return itemName;
    }

    /**
     * Set item name
     *
     * @param itemName Item name
     */
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    /**
     * Get category foreign key id
     *
     * @return cat_id - Category foreign key id
     */
    public Integer getCatId() {
        return catId;
    }

    /**
     * Set category foreign key id
     *
     * @param catId Category foreign key id
     */
    public void setCatId(Integer catId) {
        this.catId = catId;
    }

    /**
     * Get root category foreign key id
     *
     * @return root_cat_id - Root category foreign key id
     */
    public Integer getRootCatId() {
        return rootCatId;
    }

    /**
     * Set root category foreign key id
     *
     * @param rootCatId Root category foreign key id
     */
    public void setRootCatId(Integer rootCatId) {
        this.rootCatId = rootCatId;
    }

    /**
     * Get total sales
     *
     * @return sell_counts - Total sales
     */
    public Integer getSellCounts() {
        return sellCounts;
    }

    /**
     * Set total sales
     *
     * @param sellCounts Total sales
     */
    public void setSellCounts(Integer sellCounts) {
        this.sellCounts = sellCounts;
    }

    /**
     * Get on/off status, 1: on shelf, 2: off shelf
     *
     * @return on_off_status - On/off status, 1: on shelf, 2: off shelf
     */
    public Integer getOnOffStatus() {
        return onOffStatus;
    }

    /**
     * Set on/off status, 1: on shelf, 2: off shelf
     *
     * @param onOffStatus On/off status, 1: on shelf, 2: off shelf
     */
    public void setOnOffStatus(Integer onOffStatus) {
        this.onOffStatus = onOffStatus;
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

    /**
     * Get item content
     *
     * @return content - Item content
     */
    public String getContent() {
        return content;
    }

    /**
     * Set item content
     *
     * @param content Item content
     */
    public void setContent(String content) {
        this.content = content;
    }
}