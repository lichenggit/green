package cn.cbbhy.schoolshare.logic.mapping;

import cn.cbbhy.schoolshare.logic.model.UserTableRelated;

public interface UserTableRelatedMapper {
    int deleteByPrimaryKey(String id);

    int insert(UserTableRelated record);

    int insertSelective(UserTableRelated record);

    UserTableRelated selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(UserTableRelated record);

    int updateByPrimaryKey(UserTableRelated record);
}