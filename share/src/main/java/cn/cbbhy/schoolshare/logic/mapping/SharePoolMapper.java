package cn.cbbhy.schoolshare.logic.mapping;

import cn.cbbhy.schoolshare.logic.model.ShareOrderDetails;
import cn.cbbhy.schoolshare.logic.model.SharePool;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SharePoolMapper {
    int deleteByPrimaryKey(String id);

    int insert(SharePool record);

    int insertSelective(SharePool record);

    SharePool selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SharePool record);

    int updateByPrimaryKey(SharePool record);

    /**
     *在共享池中查找用户是否已有物品ArticleId
     * @param userId
     * @param articleId
     * @return
     */
    SharePool findByUserAndArticleId(@Param("userId") String userId, @Param("articleId") String articleId);

    /**
     *查找用户的共享池的所有物品
     * @param userId
     * @return
     */
    List<SharePool> listSharePoolByUser(String userId);

    /**
     *查找用户的共享池的数量
     * @param userId
     * @return
     */
    Integer countSharePoolByUser(String userId);

    /**
     * 更新共享池的状态
     * @param userId
     * @param shareOrderDetailsList
     */
    void updateSharePoolStatus(@Param("userId") String userId,@Param("shareOrderDetailsList")List<ShareOrderDetails> shareOrderDetailsList);

}