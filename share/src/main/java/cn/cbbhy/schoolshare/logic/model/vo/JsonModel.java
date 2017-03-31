package cn.cbbhy.schoolshare.logic.model.vo;

/**
 * Created by Administrator on 2016/11/26 0026.
 */
public class JsonModel {
    private Integer code;
    private String desc;
    private Object data;

    public JsonModel() {
        this(null, null, null);
    }

    public JsonModel(Integer code) {
        this(code, null, null);
    }

    public JsonModel(Integer code, String desc) {
        this(code, desc, null);
    }

    public JsonModel(Integer code, String desc, Object data) {
        this.code = code;
        this.desc = desc;
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
