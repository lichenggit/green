package cn.cbbhy.schoolshare.logic.model.condition;

import cn.cbbhy.schoolshare.logic.model.constant.Status;

import java.util.Date;

/**
 * 需求申请物品过滤条件
 */
public class NeedFilterCondition {
    //用户ID
    private String userId;
    //物品分类
    private String categoryId;
    //物品状态
    private String status = Status.NORMAL;
    //物品开始发布时间
    private Date createTimeLow;
    //物品结束发布时间
    private Date createTimeHigh;

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
}