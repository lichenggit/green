package cn.cbbhy.schoolshare.logic.dao;

import cn.cbbhy.schoolshare.logic.model.Attention;

import java.util.List;

/**
 * Created by Administrator on 2016/12/16 0016.
 */
public interface AttentionDao {


    /**
     * 插入一条收藏记录
     *
     * @param attention
     */
    void insertAttention(Attention attention);

    /**
     * 删除一条收藏记录
     * @param userId
     * @param articleId
     */
    void deleteAttention(String userId, String articleId);

    /**
     * 查询收藏记录
     *
     * @param attention
     * @return
     */
    List<Attention> searchByConditions(Attention attention);

    /**
     * 条件查询数量
     *
     * @param record
     * @return
     */
    int countByConditions(Attention record);
}
