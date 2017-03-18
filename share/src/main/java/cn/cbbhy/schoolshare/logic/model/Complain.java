package cn.cbbhy.schoolshare.logic.model;

import java.util.Date;

public class Complain {
    private String id;

    private String userId;

    private String complainType;

    private String complainId;

    private String complainContent;

    private Date createTime;

    private String dealwithUserId;

    private String dealwithSituation;

    private Date dealwithTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getComplainType() {
        return complainType;
    }

    public void setComplainType(String complainType) {
        this.complainType = complainType;
    }

    public String getComplainId() {
        return complainId;
    }

    public void setComplainId(String complainId) {
        this.complainId = complainId;
    }

    public String getComplainContent() {
        return complainContent;
    }

    public void setComplainContent(String complainContent) {
        this.complainContent = complainContent;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getDealwithUserId() {
        return dealwithUserId;
    }

    public void setDealwithUserId(String dealwithUserId) {
        this.dealwithUserId = dealwithUserId;
    }

    public String getDealwithSituation() {
        return dealwithSituation;
    }

    public void setDealwithSituation(String dealwithSituation) {
        this.dealwithSituation = dealwithSituation;
    }

    public Date getDealwithTime() {
        return dealwithTime;
    }

    public void setDealwithTime(Date dealwithTime) {
        this.dealwithTime = dealwithTime;
    }
}