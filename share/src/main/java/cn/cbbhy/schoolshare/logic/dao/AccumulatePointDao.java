package cn.cbbhy.schoolshare.logic.dao;

import cn.cbbhy.schoolshare.logic.model.AccumulatePoint;

import java.util.List;

/**
 * Created by Administrator on 2017/2/16 0016.
 * 积分
 */
public interface AccumulatePointDao {

    /**
     * 插入一条积分记录
     *
     * @param record
     * @return
     */
    int insertSelective(AccumulatePoint record);

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
