package cn.cbbhy.schoolshare.logic.shiro;

import cn.cbbhy.schoolshare.logic.service.AccumulatePointService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Administrator on 2017/2/17 0017.
 */
public class AccessEnableJudge {
    @Autowired
    private AccumulatePointService accumulatePointService;

    private int point;

    public AccessEnableJudge(String userId) {
        point = accumulatePointService.countUserPoints(userId);
    }

    public void judge(String accessEnable) {
        switch (accessEnable){
            case "NONE":

                break;
            case "AUTHC":

                break;
            case "SHARER":

                break;
            case "REQUEST":

                break;
        }
    }

    private boolean judgeAuthc(){
        Subject subject = SecurityUtils.getSubject();
        return subject.isAuthenticated();
    }
}
