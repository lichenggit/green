package cn.cbbhy.schoolshare.logic.mapping;

import cn.cbbhy.schoolshare.logic.model.Article;
import cn.cbbhy.schoolshare.logic.model.ShareOrderDetails;
import cn.cbbhy.schoolshare.logic.model.condition.ArticleFilterCondition;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ArticleMapper {
    int deleteByPrimaryKey(String articleId);

    int insert(Article record);

    int insertSelective(Article record);

    Article selectByPrimaryKey(String articleId);

    int updateByPrimaryKeySelective(Article record);

    int updateByPrimaryKey(Article record);

    /**
     * 条件查询
     * @param condition
     * @return
     */
    List<Article> selectByConditions(ArticleFilterCondition condition);

    /**
     * 查询用户所发布的共享物品数量
     * @param userId
     * @return
     */
    int countArticleByUserId(String userId);

    int updateArticleStatus(@Param("shareOrderDetailsList") List<ShareOrderDetails> shareOrderDetailsList);
}