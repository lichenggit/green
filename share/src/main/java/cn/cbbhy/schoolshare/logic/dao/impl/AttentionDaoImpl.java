package cn.cbbhy.schoolshare.logic.dao.impl;

import cn.cbbhy.schoolshare.logic.dao.AttentionDao;
import cn.cbbhy.schoolshare.logic.mapping.AttentionMapper;
import cn.cbbhy.schoolshare.logic.model.Attention;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Administrator on 2016/12/16 0016.
 */
@Repository
public class AttentionDaoImpl implements AttentionDao {
    @Autowired
    private AttentionMapper attentionMapper;

    @Override
    public void insertAttention(Attention attention) {
        attentionMapper.insertSelective(attention);
    }

    @Override
    public void deleteAttention(String userId, String articleId) {
        attentionMapper.deleteByUserAndArticle(userId, articleId);
    }

    @Override
    public List<Attention> searchByConditions(Attention attention) {
        return attentionMapper.selectByConditions(attention);
    }

    @Override
    public int countByConditions(Attention record) {
        return attentionMapper.countByConditions(record);
    }
}
