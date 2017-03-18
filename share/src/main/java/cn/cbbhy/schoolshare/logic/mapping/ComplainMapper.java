package cn.cbbhy.schoolshare.logic.mapping;

import cn.cbbhy.schoolshare.logic.model.Complain;

public interface ComplainMapper {
    int deleteByPrimaryKey(String id);

    int insert(Complain record);

    int insertSelective(Complain record);

    Complain selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Complain record);

    int updateByPrimaryKey(Complain record);
}