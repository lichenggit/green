package cn.cbbhy.schoolshare.logic.dao;

import cn.cbbhy.schoolshare.logic.model.Article;
import cn.cbbhy.schoolshare.logic.model.ShareOrderDetails;
import cn.cbbhy.schoolshare.logic.model.condition.ArticleFilterCondition;

import java.util.List;

/**
 * Created by Administrator on 2016/11/25 0025.
 */
public interface ArticleDao {
    /**
     * 新增一条物品
     *
     * @param article
     */
    void insertArticle(Article article);

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
     * 查询用户所分享的物品数量
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

    int updateArticleStatus(List<ShareOrderDetails> shareOrderDetailsList);

}
