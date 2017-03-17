package cn.cbbhy.schoolshare.logic.mapping;

import cn.cbbhy.schoolshare.logic.model.StudentFileUpload;

public interface StudentFileUploadMapper {
    int deleteByPrimaryKey(String id);

    int insert(StudentFileUpload record);

    int insertSelective(StudentFileUpload record);

    StudentFileUpload selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(StudentFileUpload record);

    int updateByPrimaryKey(StudentFileUpload record);
}