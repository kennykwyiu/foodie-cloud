package com.kenny.item.pojo;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "items_comments")
public class ItemsComments {
    /**
     * Primary key id
     */
    @Id
    private String id;

    /**
     * User id (username must be desensitized)
     */
    @Column(name = "user_id")
    private String userId;

    /**
     * Item id
     */
    @Column(name = "item_id")
    private String itemId;

    /**
     * Item name
     */
    @Column(name = "item_name")
    private String itemName;

    /**
     * Item specification id (can be null)
     */
    @Column(name = "item_spec_id")
    private String itemSpecId;

    /**
     * Specification name (can be null)
     */
    @Column(name = "sepc_name")
    private String sepcName;

    /**
     * Rating level 1: positive 2: neutral 3: negative
     */
    @Column(name = "comment_level")
    private Integer commentLevel;

    /**
     * Comment content
     */
    private String content;

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
     * Get primary key id
     *
     * @return id - Primary key id
     */
    public String getId() {
        return id;
    }

    /**
     * Set primary key id
     *
     * @param id Primary key id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Get user id (username must be desensitized)
     *
     * @return user_id - User id (username must be desensitized)
     */
    public String getUserId() {
        return userId;
    }

    /**
     * Set user id (username must be desensitized)
     *
     * @param userId User id (username must be desensitized)
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * Get item id
     *
     * @return item_id - Item id
     */
    public String getItemId() {
        return itemId;
    }

    /**
     * Set item id
     *
     * @param itemId Item id
     */
    public void setItemId(String itemId) {
        this.itemId = itemId;
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
     * Get item specification id
     *
     * @return item_spec_id - Item specification id (can be null)
     */
    public String getItemSpecId() {
        return itemSpecId;
    }

    /**
     * Set item specification id
     *
     * @param itemSpecId Item specification id (can be null)
     */
    public void setItemSpecId(String itemSpecId) {
        this.itemSpecId = itemSpecId;
    }

    /**
     * Get specification name
     *
     * @return sepc_name - Specification name (can be null)
     */
    public String getSepcName() {
        return sepcName;
    }

    /**
     * Set specification name
     *
     * @param sepcName Specification name (can be null)
     */
    public void setSepcName(String sepcName) {
        this.sepcName = sepcName;
    }

    /**
     * Get rating level
     *
     * @return comment_level - Rating level 1: positive 2: neutral 3: negative
     */
    public Integer getCommentLevel() {
        return commentLevel;
    }

    /**
     * Set rating level
     *
     * @param commentLevel Rating level 1: positive 2: neutral 3: negative
     */
    public void setCommentLevel(Integer commentLevel) {
        this.commentLevel = commentLevel;
    }

    /**
     * Get comment content
     *
     * @return content - Comment content
     */
    public String getContent() {
        return content;
    }

    /**
     * Set comment content
     *
     * @param content Comment content
     */
    public void setContent(String content) {
        this.content = content;
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