package cn.cbbhy.schoolshare.logic.dao;

import cn.cbbhy.schoolshare.logic.model.ColumnInfo;
import cn.cbbhy.schoolshare.logic.model.TableInfo;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/2/21 0021.
 */
public interface TableDao {
    TableInfo selectByPrimaryKey(String id);

    /**
     * 仅仅是自己创建的
     *
     * @param userId
     * @return
     */
    List<TableInfo> selectByUserId(String userId);

    /**
     * 查找用户所能操作的表
     *
     * @param userId
     * @param isPermit（isPermit为N表示自己建，为Y表示别人授权的）
     * @return
     */
    List<TableInfo> searchAccessTablesByUserId(String userId, String isPermit);

    int addTable(TableInfo tableInfo);


    /**
     * 创建一张动态表
     *
     * @param tableInfo
     * @param columnInfoList
     */
    void createNewTable(TableInfo tableInfo, ColumnInfo[] columnInfoList);

    /**
     * 在动态表插入一条记录
     *
     * @param tableNameEn
     * @param columnMap
     * @return
     */
    int insertIntoDynamicTable(String tableNameEn, Map<String, Object> columnMap);

    /**
     * 在动态表中找出所有记录
     *
     * @param tableNameEn
     * @return
     */
    List<Map<String, Object>> selectFromDynamicTable(String tableNameEn);
}
