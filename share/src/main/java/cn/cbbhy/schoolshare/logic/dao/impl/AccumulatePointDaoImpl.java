package cn.cbbhy.schoolshare.logic.dao.impl;

import cn.cbbhy.schoolshare.logic.dao.AccumulatePointDao;
import cn.cbbhy.schoolshare.logic.mapping.AccumulatePointMapper;
import cn.cbbhy.schoolshare.logic.model.AccumulatePoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Administrator on 2017/2/16 0016.
 */
@Repository
public class AccumulatePointDaoImpl implements AccumulatePointDao {
    @Autowired
    private AccumulatePointMapper accumulatePointMapper;

    @Override
    public int insertSelective(AccumulatePoint record) {
        return accumulatePointMapper.insertSelective(record);
    }

    @Override
    public int countUserPoints(String userID) {
        return accumulatePointMapper.countUserPoints(userID);
    }

    @Override
    public List<AccumulatePoint> selectByUserId(String userId) {
        return accumulatePointMapper.selectByUserId(userId);
    }
}
