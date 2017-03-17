package cn.cbbhy.schoolshare.logic.mapping;

import cn.cbbhy.schoolshare.logic.model.AccumulatePoint;

import java.util.List;

public interface AccumulatePointMapper {
    int deleteByPrimaryKey(String id);

    int insert(AccumulatePoint record);

    int insertSelective(AccumulatePoint record);

    AccumulatePoint selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(AccumulatePoint record);

    int updateByPrimaryKey(AccumulatePoint record);

    /**
     * 统计用户的积分
     *
     * @param userID
     * @return
     */
    int countUserPoints(String userID);

    /**
     * 查找用户所有的积分项目
     *
     * @param userId
     * @return
     */
    List<AccumulatePoint> selectByUserId(String userId);
}