package cn.cbbhy.schoolshare.logic.mapping;

import cn.cbbhy.schoolshare.logic.model.NeedHave;

import java.util.HashMap;
import java.util.List;

public interface NeedHaveMapper {
    int deleteByPrimaryKey(String id);

    int insert(NeedHave record);

    int insertSelective(NeedHave record);

    NeedHave selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(NeedHave record);

    int updateByPrimaryKey(NeedHave record);

    /**
     * 查找用户的需求消息
     * @param neederId
     * @return
     */
    List<NeedHave> selectByNeederId(String neederId);

    List<HashMap<String ,Object>> matchNeedArticle();
}