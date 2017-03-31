package cn.cbbhy.schoolshare.logic.service;

import cn.cbbhy.schoolshare.logic.model.AccumulatePoint;

/**
 * Created by Administrator on 2017/2/16 0016.
 */
public interface AccumulatePointService {
    /**
     * 插入一条积分记录
     *
     * @param record
     * @return
     */
    void addPointItem(AccumulatePoint record);

    /**
     * 统计用户的积分
     *
     * @param userId
     * @return
     */
    int countUserPoints(String userId);
}
