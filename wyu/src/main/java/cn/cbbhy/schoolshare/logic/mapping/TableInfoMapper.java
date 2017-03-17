package cn.cbbhy.schoolshare.logic.mapping;

import cn.cbbhy.schoolshare.logic.model.ColumnInfo;
import cn.cbbhy.schoolshare.logic.model.TableInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface TableInfoMapper {
    int deleteByPrimaryKey(String id);

    int insert(TableInfo record);

    int insertSelective(TableInfo record);

    TableInfo selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(TableInfo record);

    int updateByPrimaryKey(TableInfo record);

    /**
     * 查找用户所建的表
     *
     * @param userId
     * @return
     */
    List<TableInfo> searchTablesByUserId(String userId);

    /**
     * 查找用户所能操作的表
     *
     * @param userId
     * @param isPermit（isPermit为N表示自己建，为Y表示别人授权的）
     * @return
     */
    List<TableInfo> searchAccessTablesByUserId(@Param("userId") String userId, @Param("isPermit") String isPermit);

    /**
     * 创建表格
     *
     * @param tableInfo
     * @param columnInfoList
     */
    void createNewTable(@Param("tableInfo") TableInfo tableInfo, @Param("columnInfoList") ColumnInfo[] columnInfoList);

    /**
     * 插入动态表
     *
     * @param tableNameEn
     * @param columnMap
     * @return
     */
    int insertIntoDynamicTable(@Param("tableNameEn") String tableNameEn, @Param("columnMap") Map<String, Object> columnMap);

    /**
     * 在动态表中找出所有记录
     *
     * @param tableNameEn
     * @return
     */
    List<Map<String, Object>> selectFromDynamicTable(@Param("tableNameEn") String tableNameEn);

}