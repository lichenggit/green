package cn.cbbhy.schoolshare.base.util;

import cn.cbbhy.schoolshare.logic.model.vo.MenuItem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Administrator on 2017/2/27 0027.
 */
public class MenuUtil {

    private static MenuItem subAccountMenu = new MenuItem();
    private static MenuItem classGradeMenu = new MenuItem();
    private static MenuItem createTableMenu = new MenuItem();
    private static MenuItem myTablesMenu = new MenuItem();

    private static List<MenuItem> normalMenus = new ArrayList<MenuItem>();
    private static List<MenuItem> seniorMenus = new ArrayList<MenuItem>();

    static {
        subAccountMenu.setName("子账号管理");
        subAccountMenu.setUrl("/user/listAllUser.html");
        subAccountMenu.setIcon("glyphicon-user");

        classGradeMenu.setName("我的班级");
        classGradeMenu.setUrl("/student/classGrade.html");
        classGradeMenu.setIcon("glyphicon-heart");

        createTableMenu.setName("创建表格");
        createTableMenu.setUrl("/table/createTable.html");
        createTableMenu.setIcon("glyphicon-plus");

        myTablesMenu.setName("我的表格");
        myTablesMenu.setUrl("/table/myTables.html");
        myTablesMenu.setIcon("glyphicon-th-list");

        normalMenus.add(classGradeMenu);
        normalMenus.add(createTableMenu);
        normalMenus.add(myTablesMenu);

        seniorMenus.add(subAccountMenu);
        seniorMenus.add(createTableMenu);
        seniorMenus.add(myTablesMenu);

    }


    public static List<MenuItem> getMenus(String categoryLevel) {
        try {
            int level = Integer.parseInt(categoryLevel);
            if (1 == level) {
                return normalMenus;
            } else if (level > 1) {
                return seniorMenus;
            } else {
                return Collections.emptyList();
            }
        } catch (Exception e) {
            return Collections.emptyList();
        }
    }

}
