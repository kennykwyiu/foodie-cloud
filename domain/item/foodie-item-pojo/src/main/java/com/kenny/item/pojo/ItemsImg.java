package com.kenny.item.pojo;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "items_img")
public class ItemsImg {
    /**
     * Image primary key
     */
    @Id
    private String id;

    /**
     * Item foreign key id
     */
    @Column(name = "item_id")
    private String itemId;

    /**
     * Image URL
     */
    private String url;

    /**
     * Sort order, from small to large
     */
    private Integer sort;

    /**
     * Whether it's the main image, 1: yes, 0: no
     */
    @Column(name = "is_main")
    private Integer isMain;

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
     * Get image primary key
     *
     * @return id - Image primary key
     */
    public String getId() {
        return id;
    }

    /**
     * Set image primary key
     *
     * @param id Image primary key
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
     * Get image URL
     *
     * @return url - Image URL
     */
    public String getUrl() {
        return url;
    }

    /**
     * Set image URL
     *
     * @param url Image URL
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * Get sort order
     *
     * @return sort - Sort order, from small to large
     */
    public Integer getSort() {
        return sort;
    }

    /**
     * Set sort order
     *
     * @param sort Sort order, from small to large
     */
    public void setSort(Integer sort) {
        this.sort = sort;
    }

    /**
     * Get whether it's the main image
     *
     * @return is_main - Whether it's the main image, 1: yes, 0: no
     */
    public Integer getIsMain() {
        return isMain;
    }

    /**
     * Set whether it's the main image
     *
     * @param isMain Whether it's the main image, 1: yes, 0: no
     */
    public void setIsMain(Integer isMain) {
        this.isMain = isMain;
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