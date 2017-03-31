package cn.cbbhy.schoolshare.logic.model.vo;


/**
 * 前端
 */
public class SelectModel {
    //显示的内容
    private String label;

    private String value;

    public SelectModel() {

    }

    public SelectModel(String label, String value) {
        this.label=label;
        this.value=value;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
