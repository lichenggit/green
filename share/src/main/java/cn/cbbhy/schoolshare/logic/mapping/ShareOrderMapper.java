package cn.cbbhy.schoolshare.logic.mapping;

import cn.cbbhy.schoolshare.logic.model.ShareOrder;

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
     * @param id
     * @return
     */
    ShareOrder selectShareOrder(String id);

    /**
     *
     * @param userId
     * @return
     */
    List<ShareOrder> listShareOrderByUsere(String userId);
}