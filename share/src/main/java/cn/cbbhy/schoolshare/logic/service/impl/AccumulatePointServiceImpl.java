package cn.cbbhy.schoolshare.logic.service.impl;

import cn.cbbhy.schoolshare.base.util.IdGenerator;
import cn.cbbhy.schoolshare.logic.dao.AccumulatePointDao;
import cn.cbbhy.schoolshare.logic.model.AccumulatePoint;
import cn.cbbhy.schoolshare.logic.service.AccumulatePointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by Administrator on 2017/2/16 0016.
 */
@Service
public class AccumulatePointServiceImpl implements AccumulatePointService {
    @Autowired
    private AccumulatePointDao accumulatePointDao;

    @Override
    public void addPointItem(AccumulatePoint record) {
        record.setId(IdGenerator.generateId());
        record.setCreateTime(new Date());
        accumulatePointDao.insertSelective(record);
    }

    @Override
    public int countUserPoints(String userId) {
        return accumulatePointDao.countUserPoints(userId);
    }
}
