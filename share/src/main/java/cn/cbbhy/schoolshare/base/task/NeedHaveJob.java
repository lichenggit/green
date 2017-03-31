package cn.cbbhy.schoolshare.base.task;

import cn.cbbhy.schoolshare.logic.service.NeedService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Administrator on 2017/3/22 0022.
 */
public class NeedHaveJob {

    public void matchNeed(){
        //List<HashMap<String ,Object>> list = needService.matchNeedArticle();
        System.out.println("定时任务----> NeedHaveJob matchNeed");
    }
}
