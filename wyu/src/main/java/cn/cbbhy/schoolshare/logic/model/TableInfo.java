package cn.cbbhy.schoolshare.logic.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TableInfo {
    private String id;

    private String tableNameEn;

    private String tableNameCn;

    private String creator;

    private String remark;

    private Date startTime;

    private Date endTime;

    private Date updateTime;

    private String status;

    private Date createTime;

    //
    private ColumnInfo[] columnInfoList;

    //
    private String startTimeStr;

    private String endTimeStr;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTableNameEn() {
        return tableNameEn;
    }

    public void setTableNameEn(String tableNameEn) {
        this.tableNameEn = tableNameEn;
    }

    public String getTableNameCn() {
        return tableNameCn;
    }

    public void setTableNameCn(String tableNameCn) {
        this.tableNameCn = tableNameCn;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }


    public ColumnInfo[] getColumnInfoList() {
        return columnInfoList;
    }

    public void setColumnInfoList(ColumnInfo[] columnInfoList) {
        this.columnInfoList = columnInfoList;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getEndTimeStr() {
        return endTimeStr;
    }

    public void setEndTimeStr(String endTimeStr) throws ParseException {
        setEndTime(new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(endTimeStr));
        this.endTimeStr = endTimeStr;
    }

    public String getStartTimeStr() {
        return startTimeStr;
    }

    public void setStartTimeStr(String startTimeStr) throws ParseException {
        setStartTime(new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(startTimeStr));
        this.startTimeStr = startTimeStr;
    }
}