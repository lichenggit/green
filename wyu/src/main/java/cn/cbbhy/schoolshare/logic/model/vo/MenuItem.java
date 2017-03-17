package cn.cbbhy.schoolshare.logic.model.vo;

/**
 * Created by Administrator on 2017/2/27 0027.
 * 菜单项
 */
public class MenuItem {
    //菜单名字
    private String name;
    //菜单点击的链接
    private String url;
    //菜单的图标
    private String icon;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
