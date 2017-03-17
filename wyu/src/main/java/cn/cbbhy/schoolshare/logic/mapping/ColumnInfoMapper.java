package cn.cbbhy.schoolshare.logic.mapping;

import cn.cbbhy.schoolshare.logic.model.ColumnInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ColumnInfoMapper {
    int deleteByPrimaryKey(String id);

    int insert(ColumnInfo record);

    int insertSelective(ColumnInfo record);

    ColumnInfo selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ColumnInfo record);


    int updateByPrimaryKey(ColumnInfo record);

    /**
     * 批量插入
     * @param columnInfoList
     * @return
     */
    int insertByBatch(@Param("columnInfoList") ColumnInfo[] columnInfoList);

    /**
     * 查询表格的所有字段
     * @param tableId
     * @return
     */
    List<ColumnInfo> selectByTableId(String tableId);
}