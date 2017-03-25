package cn.cbbhy.schoolshare.logic.service;

import cn.cbbhy.schoolshare.logic.model.ShareOrder;
import cn.cbbhy.schoolshare.logic.model.ShareOrderDetails;
import cn.cbbhy.schoolshare.logic.model.SharePool;
import cn.cbbhy.schoolshare.logic.model.vo.JsonModel;

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
     *
     * @param userId
     * @param shareOrderId
     * @return
     */
    ShareOrder findShareOrder(String userId, String shareOrderId);

    /**
     * 根据ID查找已经支付的订单联系方式
     *
     * @param userId
     * @param shareOrderId
     * @return
     */
    ShareOrder findPayShareOrder(String userId, String shareOrderId);


    /**
     * 新建一个订单
     * @param userId
     * @param orderDetailsList
     */
    JsonModel addOneOrder(String userId, List<ShareOrderDetails> orderDetailsList);

    /**
     * 支付订单
     * @param userId
     * @param shareOrderId
     */
    boolean addPayOneOrder(String userId, String shareOrderId);

    /**
     *查找用户的所有订单
     * @param userId
     * @return
     */
    List<ShareOrder> listShareOrderByUser(String userId);

}
