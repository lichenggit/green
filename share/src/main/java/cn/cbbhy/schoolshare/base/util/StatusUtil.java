package cn.cbbhy.schoolshare.base.util;

import cn.cbbhy.schoolshare.logic.model.constant.Status;
import cn.cbbhy.schoolshare.logic.model.vo.SelectModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by acer on 2016/6/24.
 */
public class StatusUtil {

    private static Map<String[], Map<String, SelectModel>> maps = new HashMap<String[], Map<String, SelectModel>>();

    //共享物品
    public static final String[] GXWP = {Status.NORMAL, Status.FORBID, Status.DELETE, Status.EXPIRED, Status.FINISHED};
    //需求物品
    public static final String[] XQWP = {Status.NORMAL, Status.FORBID, Status.DELETE, Status.EXPIRED, Status.FINISHED};

    static {
        Map<String, SelectModel> mapGXWP = new HashMap<String, SelectModel>();
        mapGXWP.put(Status.NORMAL, new SelectModel("正在出售", Status.NORMAL));
        mapGXWP.put(Status.FORBID, new SelectModel("已禁止", Status.FORBID));
        mapGXWP.put(Status.DELETE, new SelectModel("已删除", Status.DELETE));
        mapGXWP.put(Status.EXPIRED, new SelectModel("已下架", Status.EXPIRED));
        mapGXWP.put(Status.FINISHED, new SelectModel("已售出", Status.FINISHED));

        Map<String, SelectModel> mapXQWP = new HashMap<String, SelectModel>();
        mapXQWP.put(Status.NORMAL, new SelectModel("待解决", Status.NORMAL));
        mapXQWP.put(Status.FORBID, new SelectModel("已禁止", Status.FORBID));
        mapXQWP.put(Status.DELETE, new SelectModel("已删除", Status.DELETE));
        mapXQWP.put(Status.EXPIRED, new SelectModel("已失效", Status.EXPIRED));
        mapXQWP.put(Status.FINISHED, new SelectModel("已解决", Status.FINISHED));

        maps.put(GXWP, mapGXWP);
        maps.put(XQWP, mapXQWP);
    }

    /**
     * 通过字母数组获取 状态集合
     *
     * @param arrys
     * @return
     */
    public static List<SelectModel> getStatusList(String[] arrys) {
        Map<String, SelectModel> map = maps.get(arrys);
        List<SelectModel> status = new ArrayList<SelectModel>();
        status.add(new SelectModel("全部", ""));
        for (String key : arrys) {
            status.add(map.get(key));
        }
        return status;
    }

    public static List<SelectModel> getStatusListForPart(String[] arrys) {
        Map<String, SelectModel> map = maps.get(arrys);
        List<SelectModel> status = new ArrayList<SelectModel>();
        for (String key : arrys) {
            status.add(map.get(key));
        }
        return status;
    }

}
