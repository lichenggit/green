package cn.cbbhy.schoolshare.logic.service.impl;

import cn.cbbhy.schoolshare.base.util.IdGenerator;
import cn.cbbhy.schoolshare.logic.dao.ArticleDao;
import cn.cbbhy.schoolshare.logic.dao.UserDao;
import cn.cbbhy.schoolshare.logic.model.Article;
import cn.cbbhy.schoolshare.logic.model.User;
import cn.cbbhy.schoolshare.logic.model.condition.ArticleFilterCondition;
import cn.cbbhy.schoolshare.logic.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2016/11/26 0026.
 */
@Service
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private ArticleDao articleDao;
    @Autowired
    private UserDao userDao;

    @Override
    public void addArticle(Article article) {
        article.setArticleId(IdGenerator.generateId());
        article.setStatus("NORMAL");
        article.setCreateTime(new Date());
        articleDao.insertArticle(article);
    }

    @Override
    public Article searchArticleById(String articleId) {
        return articleDao.searchArticleById(articleId);
    }

    @Override
    public List<Article> selectByConditions(ArticleFilterCondition condition) {
        return articleDao.selectByConditions(condition);
    }

    @Override
    public User searchContacts(String articleId, String userId) {
        return userDao.selectUserByUserId(userId);
    }

    @Override
    public List<Article> searchArticleByUserId(String userId, String status) {
        ArticleFilterCondition condition = new ArticleFilterCondition();
        condition.setUserId(userId);
        condition.setStatus(status);
        return articleDao.selectByConditions(condition);
    }

    @Override
    public int countArticleByUserId(String userId) {
        return articleDao.countArticleByUserId(userId);
    }

    @Override
    public void updateArticle(Article article) {
        articleDao.updateArticle(article);
    }
}
