package cn.cbbhy.schoolshare.logic.dao.impl;

import cn.cbbhy.schoolshare.logic.dao.NeedDao;
import cn.cbbhy.schoolshare.logic.mapping.NeedMapper;
import cn.cbbhy.schoolshare.logic.model.Need;
import cn.cbbhy.schoolshare.logic.model.condition.NeedFilterCondition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Administrator on 2016/12/1 0001.
 */
@Repository
public class NeedDaoImpl implements NeedDao {
    @Autowired
    private NeedMapper needMapper;

    @Override
    public void insertNeed(Need need) {
        needMapper.insertSelective(need);
    }

    @Override
    public List<Need> selectByConditions(NeedFilterCondition condition) {
        return needMapper.selectByConditions(condition);
    }

    @Override
    public void updateNeedBySelective(Need need) {
        needMapper.updateByPrimaryKeySelective(need);
    }
}
