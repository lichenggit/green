package cn.cbbhy.schoolshare.logic.dao.impl;

import cn.cbbhy.schoolshare.logic.dao.ArticleDao;
import cn.cbbhy.schoolshare.logic.mapping.ArticleMapper;
import cn.cbbhy.schoolshare.logic.model.Article;
import cn.cbbhy.schoolshare.logic.model.ShareOrderDetails;
import cn.cbbhy.schoolshare.logic.model.condition.ArticleFilterCondition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Administrator on 2016/11/25 0025.
 */
@Repository
public class ArticleDaoImpl implements ArticleDao {
    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public void insertArticle(Article article) {
        articleMapper.insertSelective(article);
    }

    @Override
    public Article searchArticleById(String articleId) {
        return articleMapper.selectByPrimaryKey(articleId);
    }

    @Override
    public List<Article> selectByConditions(ArticleFilterCondition condition) {
        return articleMapper.selectByConditions(condition);
    }

    @Override
    public int countArticleByUserId(String userId) {
        return articleMapper.countArticleByUserId(userId);
    }

    @Override
    public void updateArticle(Article article) {
        articleMapper.updateByPrimaryKeySelective(article);
    }

    @Override
    public int updateArticleStatus(List<ShareOrderDetails> shareOrderDetailsList) {
        return articleMapper.updateArticleStatus(shareOrderDetailsList);
    }
}
