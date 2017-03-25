package cn.cbbhy.schoolshare.logic.service;

import cn.cbbhy.schoolshare.logic.model.Need;
import cn.cbbhy.schoolshare.logic.model.NeedHave;
import cn.cbbhy.schoolshare.logic.model.condition.NeedFilterCondition;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 2016/12/1 0001.
 */
public interface NeedService {
    void addNeed(Need need);

    List<Need> selectByConditions(NeedFilterCondition condition);

    List<Need> searchByUserId(String userId, String status);

    void updateNeedStatus(String needId, String status);

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
