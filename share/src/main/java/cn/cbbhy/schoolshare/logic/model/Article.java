package cn.cbbhy.schoolshare.logic.model;

import java.util.Date;

public class Article {
    private String articleId;

    private String userId;

    private String articleName;

    private String categoryId;

    private Byte articleDegree;

    private Double price;

    private String description;

    private String pictureIds;

    private String status;

    private String remark;

    private String contactMan;

    private String contactWay;

    private String accessEnable;

    private String sendable = "N";

    private String loanable = "N";

    private String tenantable = "N";

    private String markrtable = "N";

    private Date createTime;


    /**/
    private String categoryName;

    private User user;

    public Article() {
    }

    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getArticleName() {
        return articleName;
    }

    public void setArticleName(String articleName) {
        this.articleName = articleName;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public Byte getArticleDegree() {
        return articleDegree;
    }

    public void setArticleDegree(Byte articleDegree) {
        this.articleDegree = articleDegree;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPictureIds() {
        return pictureIds;
    }

    public void setPictureIds(String pictureIds) {
        this.pictureIds = pictureIds;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getContactMan() {
        return contactMan;
    }

    public void setContactMan(String contactMan) {
        this.contactMan = contactMan;
    }

    public String getContactWay() {
        return contactWay;
    }

    public void setContactWay(String contactWay) {
        this.contactWay = contactWay;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getAccessEnable() {
        return accessEnable;
    }

    public void setAccessEnable(String accessEnable) {
        this.accessEnable = accessEnable;
    }

    public String getSendable() {
        return sendable;
    }

    public void setSendable(String sendable) {
        this.sendable = sendable;
    }

    public String getLoanable() {
        return loanable;
    }

    public void setLoanable(String loanable) {
        this.loanable = loanable;
    }

    public String getTenantable() {
        return tenantable;
    }

    public void setTenantable(String tenantable) {
        this.tenantable = tenantable;
    }

    public String getMarkrtable() {
        return markrtable;
    }

    public void setMarkrtable(String markrtable) {
        this.markrtable = markrtable;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}