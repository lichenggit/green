package cn.cbbhy.schoolshare.logic.dao;

import cn.cbbhy.schoolshare.logic.model.Need;
import cn.cbbhy.schoolshare.logic.model.condition.NeedFilterCondition;

import java.util.List;

/**
 * Created by Administrator on 2016/12/1 0001.
 */
public interface NeedDao {
    void insertNeed(Need need);

    List<Need> selectByConditions(NeedFilterCondition condition);

    void updateNeedBySelective(Need need);
}
