package cn.cbbhy.schoolshare.logic.service.impl;

import cn.cbbhy.schoolshare.base.util.IdGenerator;
import cn.cbbhy.schoolshare.logic.dao.SharePoolDao;
import cn.cbbhy.schoolshare.logic.model.ShareOrder;
import cn.cbbhy.schoolshare.logic.model.ShareOrderDetails;
import cn.cbbhy.schoolshare.logic.model.SharePool;
import cn.cbbhy.schoolshare.logic.service.SharePoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by duoyi on 17-3-18.
 */
@Service
public class SharePoolServiceImpl implements SharePoolService {
    @Autowired
    private SharePoolDao sharePoolDao;

    @Override
    public List<SharePool> listSharePoolByUser(String userId) {
        return sharePoolDao.listSharePoolByUser(userId);
    }

    @Override
    public void addToSharePool(SharePool sharePool) {
        SharePool oldSharePool= sharePoolDao.findByUserAndArticleId(sharePool.getUserId(),sharePool.getArticleId());
        if(oldSharePool!=null){
            oldSharePool.setArticleCount(oldSharePool.getArticleCount()+1);
            sharePoolDao.updateSharePool(oldSharePool);
            return;
        }
        sharePool.setId(IdGenerator.generateId());
        sharePool.setArticleCount(1);
        sharePool.setCreateTime(new Date());
        sharePool.setStatus("NORMAL");
        sharePoolDao.addToSharePool(sharePool);
    }

    @Override
    public int countSharePoolByUser(String userId) {
        return sharePoolDao.countSharePoolByUser(userId);
    }

    @Override
    public ShareOrder findShareOrder(String shareOrderId) {
        return sharePoolDao.findShareOrder(shareOrderId);
    }

    @Override
    public String addOneOrder(String userId,List<ShareOrderDetails>orderDetailsList){
        ShareOrder shareOrder = new ShareOrder();
        shareOrder.setUserId(userId);
        shareOrder.setId(IdGenerator.generateId());
        shareOrder.setCreateTime(new Date());
        shareOrder.setStatus("NORMAL");
        for(ShareOrderDetails orderDetails:orderDetailsList){
            orderDetails.setId(IdGenerator.generateId());
            orderDetails.setStatus("NORMAL");
            orderDetails.setShareOrderId(shareOrder.getId());
        }
        sharePoolDao.addOneOrder(shareOrder);
        sharePoolDao.addOrderDetails(orderDetailsList);

        return shareOrder.getId();
    }

    @Override
    public List<ShareOrder> listShareOrderByUsere(String userId) {
        return sharePoolDao.listShareOrderByUsere(userId);
    }

}
