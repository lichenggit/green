package cn.cbbhy.schoolshare.logic.dao;

import cn.cbbhy.schoolshare.logic.model.ShareOrder;
import cn.cbbhy.schoolshare.logic.model.ShareOrderDetails;
import cn.cbbhy.schoolshare.logic.model.SharePool;

import java.util.List;

/**
 * Created by duoyi on 17-3-18.
 */
public interface SharePoolDao {
    //共享池

    SharePool findByUserAndArticleId(String userId,String articleId);
    /**
     *查找用户的共享池的所有物品
     * @param userId
     * @return
     */
    List<SharePool> listSharePoolByUser(String userId);

    int addToSharePool(SharePool sharePool);

    void updateSharePool(SharePool sharePool);
    /**
     *查找用户的共享池的数量
     * @param userId
     * @return
     */
    int countSharePoolByUser(String userId);


    /**
     * 根据id查找订单
     * @param shareOrderId
     * @return
     */
   ShareOrder findShareOrder(String shareOrderId);

    /**
     * 新建一个订单
     * @param shareOrder
     */
    void addOneOrder(ShareOrder shareOrder);

    /**
     * 为一个订单添加订单明细
     * @param orderDetailsList
     */
    void addOrderDetails(List<ShareOrderDetails> orderDetailsList);

    /**
     *
     * @param userId
     * @return
     */
    List<ShareOrder> listShareOrderByUsere(String userId);


}
