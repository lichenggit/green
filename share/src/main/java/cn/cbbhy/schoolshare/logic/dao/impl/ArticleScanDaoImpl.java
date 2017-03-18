package cn.cbbhy.schoolshare.logic.dao.impl;

import cn.cbbhy.schoolshare.logic.dao.ArticleScanDao;
import cn.cbbhy.schoolshare.logic.mapping.ArticleScanMapper;
import cn.cbbhy.schoolshare.logic.model.ArticleScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Administrator on 2017/3/14 0014.
 */
@Repository
public class ArticleScanDaoImpl implements ArticleScanDao {
    @Autowired
    private ArticleScanMapper articleScanMapper;

    @Override
    public void addArticleScan(ArticleScan articleScan) {
        articleScanMapper.insertSelective(articleScan);
    }

    @Override
    public List<ArticleScan> listArticleScanByUserId(String userId) {
        return articleScanMapper.listArticleScanByUserId(userId);
    }
}
