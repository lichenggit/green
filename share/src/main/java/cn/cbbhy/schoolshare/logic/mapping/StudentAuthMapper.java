package cn.cbbhy.schoolshare.logic.mapping;

import cn.cbbhy.schoolshare.logic.model.StudentAuth;

public interface StudentAuthMapper {
    int deleteByPrimaryKey(String id);

    int insert(StudentAuth record);

    int insertSelective(StudentAuth record);

    StudentAuth selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(StudentAuth record);

    int updateByPrimaryKey(StudentAuth record);

    int countAuthByUser(String userId);
}