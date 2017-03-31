package cn.cbbhy.schoolshare.logic.dao;

import cn.cbbhy.schoolshare.logic.model.Need;
import cn.cbbhy.schoolshare.logic.model.NeedHave;
import cn.cbbhy.schoolshare.logic.model.condition.NeedFilterCondition;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 2016/12/1 0001.
 */
public interface NeedDao {
    void insertNeed(Need need);

    List<Need> selectByConditions(NeedFilterCondition condition);

    void updateNeedBySelective(Need need);

    /**
     * 查找用户的需求消息
     * @param neederId
     * @return
     */
    List<NeedHave> selectByNeederId(String neederId);

    /**
     * 加入一条需求回应
     * @param needHave
     */
    void addNeedHave(NeedHave needHave);

    List<HashMap<String ,Object>> matchNeedArticle();
}
