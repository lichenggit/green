package cn.cbbhy.schoolshare.logic.model;

public class Permission {
    private String perId;

    private String perName;

    private String perRemark;

    public String getPerId() {
        return perId;
    }

    public void setPerId(String perId) {
        this.perId = perId;
    }

    public String getPerName() {
        return perName;
    }

    public void setPerName(String perName) {
        this.perName = perName;
    }

    public String getPerRemark() {
        return perRemark;
    }

    public void setPerRemark(String perRemark) {
        this.perRemark = perRemark;
    }
}