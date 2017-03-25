package cn.cbbhy.schoolshare.logic.service.impl;

import cn.cbbhy.schoolshare.base.util.IdGenerator;
import cn.cbbhy.schoolshare.logic.dao.NeedDao;
import cn.cbbhy.schoolshare.logic.model.Need;
import cn.cbbhy.schoolshare.logic.model.NeedHave;
import cn.cbbhy.schoolshare.logic.model.condition.NeedFilterCondition;
import cn.cbbhy.schoolshare.logic.model.constant.Status;
import cn.cbbhy.schoolshare.logic.service.NeedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 2016/12/1 0001.
 */
@Service
public class NeedServiceImpl implements NeedService {
    @Autowired
    private NeedDao needDao;

    @Override
    public void addNeed(Need need) {
        need.setNeedId(IdGenerator.generateId());
        need.setStatus(Status.NORMAL);
        need.setCreateTime(new Date());
        needDao.insertNeed(need);
    }

    @Override
    public List<Need> selectByConditions(NeedFilterCondition condition) {
        return needDao.selectByConditions(condition);
    }

    @Override
    public List<Need> searchByUserId(String userId, String status) {
        NeedFilterCondition condition = new NeedFilterCondition();
        condition.setUserId(userId);
        condition.setStatus(status);
        return needDao.selectByConditions(condition);
    }

    @Override
    public void updateNeedStatus(String needId, String status) {
        Need need = new Need();
        need.setNeedId(needId);
        need.setStatus(status);
        needDao.updateNeedBySelective(need);
    }

    @Override
    public List<NeedHave> selectByNeederId(String neederId) {
        return needDao.selectByNeederId(neederId);
    }

    @Override
    public void addNeedHave(NeedHave needHave) {
        needHave.setId(IdGenerator.generateId());
        needHave.setStatus("NORMAL");
        needHave.setCreateTime(new Date());
        needDao.addNeedHave(needHave);
    }

    @Override
    public  List<HashMap<String ,Object>> matchNeedArticle() {
        return needDao.matchNeedArticle();
    }

}
