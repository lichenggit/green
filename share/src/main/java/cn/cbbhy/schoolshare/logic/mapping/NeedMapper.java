package cn.cbbhy.schoolshare.logic.mapping;

import cn.cbbhy.schoolshare.logic.model.Need;
import cn.cbbhy.schoolshare.logic.model.condition.NeedFilterCondition;

import java.util.List;

public interface NeedMapper {
    int deleteByPrimaryKey(String needId);

    int insert(Need record);

    int insertSelective(Need record);

    Need selectByPrimaryKey(String needId);

    int updateByPrimaryKeySelective(Need record);

    int updateByPrimaryKey(Need record);

    /**
     *
     * @param condition
     * @return
     */
    List<Need> selectByConditions(NeedFilterCondition condition);
}