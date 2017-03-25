package cn.cbbhy.schoolshare.logic.model;

import java.util.Date;

public class StudentAuth {
    private String id;

    private String userId;

    private String schoolName;

    private String studentName;

    private String studentNumber;

    private String status;

    private Date creataTime;

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

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreataTime() {
        return creataTime;
    }

    public void setCreataTime(Date creataTime) {
        this.creataTime = creataTime;
    }
}