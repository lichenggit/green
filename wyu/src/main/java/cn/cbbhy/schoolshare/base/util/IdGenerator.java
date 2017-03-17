package cn.cbbhy.schoolshare.base.util;

import redis.clients.jedis.Jedis;

import java.util.UUID;

/**
 * Created by Administrator on 2016/11/25 0025.
 * 随机数生成器
 */
public class IdGenerator {
    /**
     * 生成ID
     *
     * @return
     */
    public static String generateId() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
