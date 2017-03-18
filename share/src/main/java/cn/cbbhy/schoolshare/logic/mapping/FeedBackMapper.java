package cn.cbbhy.schoolshare.logic.mapping;

import cn.cbbhy.schoolshare.logic.model.FeedBack;

public interface FeedBackMapper {
    int deleteByPrimaryKey(String id);

    int insert(FeedBack record);

    int insertSelective(FeedBack record);

    FeedBack selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(FeedBack record);

    int updateByPrimaryKey(FeedBack record);
}