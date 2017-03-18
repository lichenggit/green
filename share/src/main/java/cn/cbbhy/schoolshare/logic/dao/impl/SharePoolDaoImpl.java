package cn.cbbhy.schoolshare.logic.dao.impl;

import cn.cbbhy.schoolshare.logic.dao.SharePoolDao;
import cn.cbbhy.schoolshare.logic.mapping.ShareOrderDetailsMapper;
import cn.cbbhy.schoolshare.logic.mapping.ShareOrderMapper;
import cn.cbbhy.schoolshare.logic.mapping.SharePoolMapper;
import cn.cbbhy.schoolshare.logic.model.ShareOrder;
import cn.cbbhy.schoolshare.logic.model.ShareOrderDetails;
import cn.cbbhy.schoolshare.logic.model.SharePool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by duoyi on 17-3-18.
 */
@Repository
public class SharePoolDaoImpl implements SharePoolDao {
    @Autowired
    private SharePoolMapper sharePoolMapper;
    @Autowired
    private ShareOrderMapper shareOrderMapper;
    @Autowired
    private ShareOrderDetailsMapper shareOrderDetailsMapper;


    @Override
    public SharePool findByUserAndArticleId(String userId,String articleId) {
        return sharePoolMapper.findByUserAndArticleId(userId,articleId);
    }
    @Override
    public List<SharePool> listSharePoolByUser(String userId) {
        return sharePoolMapper.listSharePoolByUser(userId);
    }

    @Override
    public int addToSharePool(SharePool sharePool) {
        return sharePoolMapper.insertSelective(sharePool);
    }

    @Override
    public void updateSharePool(SharePool sharePool) {
        sharePoolMapper.updateByPrimaryKeySelective(sharePool);
    }

    @Override
    public int countSharePoolByUser(String userId) {
        return sharePoolMapper.countSharePoolByUser(userId);
    }

    @Override
    public ShareOrder findShareOrder(String shareOrderId) {
        return shareOrderMapper.selectShareOrder(shareOrderId);
    }

    @Override
    public void addOrderDetails(List<ShareOrderDetails> orderDetailsList) {
        shareOrderDetailsMapper.addOrderDetails(orderDetailsList);
    }

    @Override
    public List<ShareOrder> listShareOrderByUsere(String userId) {
        return shareOrderMapper.listShareOrderByUsere(userId);
    }

    @Override
    public void addOneOrder(ShareOrder shareOrder) {
        shareOrderMapper.insertSelective(shareOrder);
    }

}
