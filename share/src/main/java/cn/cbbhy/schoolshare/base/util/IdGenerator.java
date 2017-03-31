package cn.cbbhy.schoolshare.base.util;

import java.util.UUID;

/**
 * Created by Administrator on 2016/11/25 0025.
 * ID生成器
 */
public class IdGenerator {
    /**
     * 生成ID
     *
     * @return
     */
    public static String generateId() {
        return UUID.randomUUID().toString().replace("-","");
    }

    public static void main(String[] args){
        System.out.println(generateId());
        System.out.println(generateId());
    }
}
