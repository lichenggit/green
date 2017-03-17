package cn.cbbhy.schoolshare.logic.model.condition;

import cn.cbbhy.schoolshare.logic.model.constant.Status;

import java.util.Date;

/**
 * Created by Administrator on 2016/11/26 0026.
 * 物品过滤条件
 */
public class ArticleFilterCondition {
    //用户ID
    private String userId;
    //物品分类
    private String categoryId;
    //物品新旧程度
    private Byte articleDegree;
    //物品最低价格
    private Double priceLow;
    //物品最高价格
    private Double priceHigh;
    //物品状态
    private String status = Status.NORMAL;
    //物品开始发布时间
    private Date createTimeLow;
    //物品结束发布时间
    private Date createTimeHigh;
    //是否可送
    private String sendable;
    //是否可借
    private String loanable;
    //是否可租
    private String tenantable;
    //是否可卖
    private String markrtable;


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    public Double getPriceLow() {
        return priceLow;
    }

    public void setPriceLow(Double priceLow) {
        this.priceLow = priceLow;
    }

    public Double getPriceHigh() {
        return priceHigh;
    }

    public void setPriceHigh(Double priceHigh) {
        this.priceHigh = priceHigh;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreateTimeLow() {
        return createTimeLow;
    }

    public void setCreateTimeLow(Date createTimeLow) {
        this.createTimeLow = createTimeLow;
    }

    public Date getCreateTimeHigh() {
        return createTimeHigh;
    }

    public void setCreateTimeHigh(Date createTimeHigh) {
        this.createTimeHigh = createTimeHigh;
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
}
