package cn.cbbhy.schoolshare.logic.service.impl;

import cn.cbbhy.schoolshare.base.util.IdGenerator;
import cn.cbbhy.schoolshare.logic.dao.ArticleDao;
import cn.cbbhy.schoolshare.logic.dao.SharePoolDao;
import cn.cbbhy.schoolshare.logic.dao.UserDao;
import cn.cbbhy.schoolshare.logic.model.*;
import cn.cbbhy.schoolshare.logic.model.vo.JsonModel;
import cn.cbbhy.schoolshare.logic.service.AccumulatePointService;
import cn.cbbhy.schoolshare.logic.service.SharePoolService;
import com.alibaba.fastjson.JSON;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by duoyi on 17-3-18.
 */
@Service
public class SharePoolServiceImpl implements SharePoolService {
    @Autowired
    private SharePoolDao sharePoolDao;
    @Autowired
    private ArticleDao articleDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private AccumulatePointService accumulatePointService;

    @Override
    public List<SharePool> listSharePoolByUser(String userId) {
        return sharePoolDao.listSharePoolByUser(userId);
    }

    @Override
    public void addToSharePool(SharePool sharePool) {
        SharePool oldSharePool = sharePoolDao.findByUserAndArticleId(sharePool.getUserId(), sharePool.getArticleId());
        if (oldSharePool != null) {
            oldSharePool.setArticleCount(oldSharePool.getArticleCount() + 1);
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
        Integer count = sharePoolDao.countSharePoolByUser(userId);
        if(count==null){
            return 0;
        }
        return count.intValue();
    }

    @Override
    public ShareOrder findShareOrder(String userId, String shareOrderId) {
        return sharePoolDao.findShareOrder(userId,shareOrderId);
    }

    @Override
    public ShareOrder findPayShareOrder(String userId, String shareOrderId) {
        ShareOrder shareOrder =sharePoolDao.findPayShareOrder(userId,shareOrderId);
        List<ShareOrderDetails> list = shareOrder.getOrderDetailsList();
        for(ShareOrderDetails orderDetails:list){
         Article article = orderDetails.getArticle();
            User user =userDao.selectUserByUserId(article.getUserId());
            article.setUser(user);
        }
        return shareOrder;
    }

    @Override
    public JsonModel addOneOrder(String userId, List<ShareOrderDetails> orderDetailsList) {
        List<String> list = judgeOwnerDefineAccess(orderDetailsList, userId);
        if (list != null && list.size() > 0) {
            JsonModel jsonModel = new JsonModel(1, "该物品你无权获取",list);
            return jsonModel;
        }
        ShareOrder shareOrder = new ShareOrder();
        shareOrder.setUserId(userId);
        shareOrder.setId(IdGenerator.generateId());
        shareOrder.setCreateTime(new Date());
        shareOrder.setStatus("READY_PAY");
        for (ShareOrderDetails orderDetails : orderDetailsList) {
            orderDetails.setId(IdGenerator.generateId());
            orderDetails.setStatus("NORMAL");
            orderDetails.setShareOrderId(shareOrder.getId());
        }
        articleDao.updateArticleStatus(orderDetailsList);
        sharePoolDao.addOneOrder(shareOrder);
        sharePoolDao.addOrderDetails(orderDetailsList);
        sharePoolDao.updateSharePoolStatus(userId,orderDetailsList);
        return new JsonModel(0, shareOrder.getId());
    }

    @Override
    public boolean addPayOneOrder(String userId, String shareOrderId) {
        boolean result = sharePoolDao.payOneOrder(userId,shareOrderId)>0;
        if(result){
            //积分
            AccumulatePoint accumulatePoint = new AccumulatePoint();
            accumulatePoint.setUserId(userId);
            accumulatePoint.setPointType("GAIN");
            accumulatePoint.setPoints(-3);
            accumulatePoint.setRemark("获取");
            accumulatePointService.addPointItem(accumulatePoint);
        }
        return result;
    }


    /**
     * @param orderDetailsList
     * @param userId
     * @return
     */
    private List<String> judgeOwnerDefineAccess(List<ShareOrderDetails> orderDetailsList, String userId) {
        List<String> list = new ArrayList<>();
        for (ShareOrderDetails orderDetails : orderDetailsList) {
            Article article = articleDao.searchArticleById(orderDetails.getArticleId());
            switch (article.getAccessEnable()) {
                case "NONE":
                    break;
                case "AUTHC":
                    Subject subject = SecurityUtils.getSubject();
                    if (!subject.isAuthenticated()) {
                        list.add(article.getArticleId());
                    }
                    break;
                case "SHARER":
                    int total = articleDao.countArticleByUserId(userId);
                    if (total <= 0) {
                        list.add(article.getArticleId());
                    }
                    break;
                case "REQUEST":
                    list.add(article.getArticleId());
                    break;
                case "GREAT":
                    int points = accumulatePointService.countUserPoints(userId);
                    total = articleDao.countArticleByUserId(userId);
                    if(points<4||total<=0){
                        list.add(article.getArticleId());
                    }
                    break;
                default:
                    break;
            }
        }
        return list;
    }

    private String getOwnerDenyTips(String ownerDefineAccess) {
        switch (ownerDefineAccess) {
            case "NONE":
                return "";
            case "AUTHC":
                return "未登录用户不能获取";
            case "SHARER":
                return "未分享过的用户不能获取";
            case "REQUEST":
                return "未经同意不能获取";
            default:
                return "未知错误";
        }
    }


    @Override
    public List<ShareOrder> listShareOrderByUser(String userId) {
        return sharePoolDao.listShareOrderByUser(userId);
    }

}
