package cn.cbbhy.schoolshare.logic.mapping;

import cn.cbbhy.schoolshare.logic.model.ShareOrder;
import cn.cbbhy.schoolshare.logic.model.ShareOrderDetails;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ShareOrderDetailsMapper {
    int deleteByPrimaryKey(String id);

    int insert(ShareOrderDetails record);

    int insertSelective(ShareOrderDetails record);

    ShareOrderDetails selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ShareOrderDetails record);

    int updateByPrimaryKey(ShareOrderDetails record);

    /**
     * 为一个订单添加订单明细
     * @param orderDetailsList
     */
    void addOrderDetails(@Param("orderDetailsList") List<ShareOrderDetails> orderDetailsList);
}