package cn.cbbhy.schoolshare.logic.service.impl;

import cn.cbbhy.schoolshare.base.util.IdGenerator;
import cn.cbbhy.schoolshare.logic.dao.AttentionDao;
import cn.cbbhy.schoolshare.logic.model.Attention;
import cn.cbbhy.schoolshare.logic.model.constant.Status;
import cn.cbbhy.schoolshare.logic.service.AttentionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2016/12/16 0016.
 */
@Service
public class AttentionServiceImpl implements AttentionService {
    @Autowired
    private AttentionDao attentionDao;

    @Override
    public void addAttention(String userId, String articleId) {
        Attention attention = new Attention();
        attention.setAttentionId(IdGenerator.generateId());
        attention.setUserId(userId);
        attention.setArticleId(articleId);
        attention.setStatus(Status.NORMAL);
        attention.setCreateTime(new Date());
        attentionDao.insertAttention(attention);
    }

    @Override
    public void deleteAttention(String userId, String articleId) {
        attentionDao.deleteAttention(userId,articleId);
    }

    @Override
    public List<Attention> searchAttentionsByUserId(String userId) {
        Attention attention = new Attention();
        attention.setUserId(userId);
        attention.setStatus(Status.NORMAL);
        return attentionDao.searchByConditions(attention);
    }

    @Override
    public boolean checkAttention(String userId, String articleId) {
        Attention attention = new Attention();
        attention.setUserId(userId);
        attention.setArticleId(articleId);
        attention.setStatus(Status.NORMAL);
        return attentionDao.countByConditions(attention) > 0;
    }
}
