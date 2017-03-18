package cn.cbbhy.schoolshare.logic.service;

import cn.cbbhy.schoolshare.logic.model.ArticleScan;

import java.util.List;

/**
 * Created by Administrator on 2017/3/14 0014.
 */
public interface ArticleScanService {
    void addArticleScan(String userId, String articleId);

    List<ArticleScan> listArticleScanByUserId(String userId);
}
