package cn.cbbhy.schoolshare.logic.service;

import cn.cbbhy.schoolshare.logic.model.Article;
import cn.cbbhy.schoolshare.logic.model.User;
import cn.cbbhy.schoolshare.logic.model.condition.ArticleFilterCondition;

import java.util.List;

/**
 * Created by Administrator on 2016/11/26 0026.
 */
public interface ArticleService {
    /**
     * 新增一条物品
     *
     * @param article
     */
    void addArticle(Article article);


    /**
     * 根据主键查询
     *
     * @param articleId
     * @return
     */
    Article searchArticleById(String articleId);

    /**
     * 条件查询
     *
     * @param condition
     * @return
     */
    List<Article> selectByConditions(ArticleFilterCondition condition);


    /**
     * 获取物品的联系方式
     *
     * @param articleId
     * @param userId
     * @return
     */
    User searchContacts(String articleId, String userId);


    /**
     * 查询用户的物品
     *
     * @param userId
     * @param status
     * @return
     */
    List<Article> searchArticleByUserId(String userId, String status);

    /**
     * 查询用户所分享的物品数量
     *
     * @param userId
     * @return
     */
    int countArticleByUserId(String userId);

    /**
     * 变更物品信息
     *
     * @param article
     */
    void updateArticle(Article article);
}
