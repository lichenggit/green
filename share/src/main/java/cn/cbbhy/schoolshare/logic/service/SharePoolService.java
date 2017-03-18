package cn.cbbhy.schoolshare.logic.service;

import cn.cbbhy.schoolshare.logic.model.ShareOrder;
import cn.cbbhy.schoolshare.logic.model.ShareOrderDetails;
import cn.cbbhy.schoolshare.logic.model.SharePool;

import java.util.List;

/**
 * Created by duoyi on 17-3-18.
 */
public interface SharePoolService {
    /**
     *查找用户的共享池的所有物品
     * @param userId
     * @return
     */
    List<SharePool> listSharePoolByUser(String userId);

    /**
     * 加入共享池
     * @param sharePool
     */
    void addToSharePool(SharePool sharePool);

    /**
     *查找用户的共享池的数量
     * @param userId
     * @return
     */
    int countSharePoolByUser(String userId);


    /**
     * 查找订单
     * @param shareOrderId
     * @return
     */
    ShareOrder findShareOrder(String shareOrderId);

    /**
     * 新建一个订单
     * @param userId
     * @param orderDetailsList
     */
    String addOneOrder(String userId,List<ShareOrderDetails>orderDetailsList);

    /**
     *
     * @param userId
     * @return
     */
    List<ShareOrder> listShareOrderByUsere(String userId);

}
