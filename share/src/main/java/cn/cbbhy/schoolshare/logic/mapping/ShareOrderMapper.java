package cn.cbbhy.schoolshare.logic.mapping;

import cn.cbbhy.schoolshare.logic.model.ShareOrder;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ShareOrderMapper {
    int deleteByPrimaryKey(String id);

    int insert(ShareOrder record);

    int insertSelective(ShareOrder record);

    ShareOrder selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ShareOrder record);

    int updateByPrimaryKey(ShareOrder record);

    /**
     * 根据ID查找订单
     *
     * @param userId
     * @param id
     * @return
     */
    ShareOrder selectShareOrder(@Param("userId") String userId, @Param("id") String id);

    /**
     * 根据ID查找已经支付的订单联系方式
     *
     * @param userId
     * @param id
     * @return
     */
    ShareOrder selectPayShareOrder(@Param("userId") String userId, @Param("id") String id);


    /**
     *
     * @param userId
     * @return
     */
    List<ShareOrder> listShareOrderByUsere(String userId);

    /**
     * 支付订单
     * @param userId
     * @param shareOrderId
     * @return
     */
    int payOneOrder(@Param("userId") String userId, @Param("shareOrderId") String shareOrderId);
}