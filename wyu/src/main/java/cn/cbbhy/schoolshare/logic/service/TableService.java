package cn.cbbhy.schoolshare.logic.service;

import cn.cbbhy.schoolshare.logic.model.TableInfo;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/2/21 0021.
 */
public interface TableService {
    /**
     * 新建一个表格
     *
     * @param userId
     * @param tableInfo
     */
    void addTable(String userId, TableInfo tableInfo);

    /**
     * 根据表的ID找到表的所有信息
     * @param tableId
     * @return
     */
    TableInfo findTableInfo(String tableId);

    /**
     * 仅仅是自己创建的
     *
     * @param userId
     * @return
     */
    List<TableInfo> listTableByUserId(String userId);

    /**
     * 仅仅是别人授权的
     *
     * @param userId
     * @return
     */
    List<TableInfo> listPermitTablesByUserId(String userId);

    void addRecordIntoDynamicTable(String tableNameEn, Map<String, Object> columnMap);

    /**
     * 在动态表中找出所有记录
     *
     * @param tableNameEn
     * @return
     */
    List<Map<String, Object>> listFromDynamicTable(String tableNameEn);
}
