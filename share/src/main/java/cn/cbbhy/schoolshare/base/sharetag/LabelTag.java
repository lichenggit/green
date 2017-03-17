package cn.cbbhy.schoolshare.base.sharetag;


import cn.cbbhy.schoolshare.logic.model.vo.SelectModel;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.Tag;
import java.io.IOException;
import java.util.List;

/**
 * Created by acer on 2016/6/30.
 */
public class LabelTag implements Tag{

    private PageContext page;
    //集合
    private List<SelectModel> items;
    //对应select的value值
    private String key;

    public void setPageContext(PageContext pageContext) {
        this.page=pageContext;
    }

    public void setParent(Tag tag) {

    }

    public Tag getParent() {
        return null;
    }

    public int doStartTag() throws JspException {
        return Tag.SKIP_BODY;
    }

    public int doEndTag() throws JspException {
        try {
            String label = "";
            for (SelectModel item : items) {
                if (key.equals(item.getValue())&&item.getLabel()!=null) {
                    page.getOut().write(item.getLabel());
                }
            }
        }catch(IOException e){
            e.printStackTrace();
        }

        return Tag.EVAL_PAGE;
    }



    public void release() {

    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public List<SelectModel> getItems() {
        return items;
    }

    public void setItems(List<SelectModel> items) {
        this.items = items;
    }
}
