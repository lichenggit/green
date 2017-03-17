package cn.cbbhy.schoolshare.logic.mapping;

import cn.cbbhy.schoolshare.logic.model.Attention;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AttentionMapper {
    int deleteByPrimaryKey(String collectionId);

    int insert(Attention record);

    int insertSelective(Attention record);

    Attention selectByPrimaryKey(String collectionId);

    int updateByPrimaryKeySelective(Attention record);

    int updateByPrimaryKey(Attention record);

    /**
     * 条件查询结果集
     *
     * @param record
     * @return
     */
    List<Attention> selectByConditions(Attention record);

    /**
     * 条件查询数量
     *
     * @param record
     * @return
     */
    int countByConditions(Attention record);

    /**
     * 取消关注
     * @param userId
     * @param articleId
     * @return
     */
    int deleteByUserAndArticle(@Param("userId") String userId, @Param("articleId") String articleId);
}