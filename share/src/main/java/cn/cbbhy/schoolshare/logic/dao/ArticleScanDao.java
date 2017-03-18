package cn.cbbhy.schoolshare.logic.dao;

import cn.cbbhy.schoolshare.logic.model.ArticleScan;

import java.util.List;

/**
 * Created by Administrator on 2017/3/14 0014.
 */
public interface ArticleScanDao {
    void addArticleScan(ArticleScan articleScan);

    List<ArticleScan> listArticleScanByUserId(String userId);
}
