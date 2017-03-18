package cn.cbbhy.schoolshare.logic.service.impl;

import cn.cbbhy.schoolshare.base.util.IdGenerator;
import cn.cbbhy.schoolshare.logic.dao.ArticleScanDao;
import cn.cbbhy.schoolshare.logic.model.ArticleScan;
import cn.cbbhy.schoolshare.logic.service.ArticleScanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/3/14 0014.
 */
@Service
public class ArticleScanServiceImpl implements ArticleScanService {
    @Autowired
    private ArticleScanDao articleScanDao;
    @Override
    public void addArticleScan(String userId,String articleId) {
        ArticleScan articleScan = new ArticleScan();
        articleScan.setUserId(userId);
        articleScan.setArticleId(articleId);
        articleScan.setId(IdGenerator.generateId());
        articleScan.setCreateTime(new Date());
        articleScanDao.addArticleScan(articleScan);
    }

    @Override
    public List<ArticleScan> listArticleScanByUserId(String userId) {
        return articleScanDao.listArticleScanByUserId(userId);
    }
}
