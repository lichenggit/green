package cn.cbbhy.schoolshare.logic.service;

import cn.cbbhy.schoolshare.logic.model.Attention;

import java.util.List;

/**
 * Created by Administrator on 2016/12/16 0016.
 */
public interface AttentionService {
    /**
     * 用户userId收藏物品
     *
     * @param userId
     * @param articleId
     */
    void addAttention(String userId, String articleId);

    /**
     * 用户userId取消收藏物品
     *
     * @param userId
     * @param articleId
     */
    void deleteAttention(String userId, String articleId);

    /**
     * 查询用户的所有收藏记录
     *
     * @param userId
     * @return
     */
    List<Attention> searchAttentionsByUserId(String userId);

    /**
     * 查询用户userId是否已经关注了物品articleId
     *
     * @param userId
     * @param articleId
     * @return
     */
    boolean checkAttention(String userId, String articleId);
}
