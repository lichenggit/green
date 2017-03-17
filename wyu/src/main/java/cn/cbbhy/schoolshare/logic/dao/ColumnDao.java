package cn.cbbhy.schoolshare.logic.dao;

import cn.cbbhy.schoolshare.logic.model.ColumnInfo;

import java.util.List;

/**
 * Created by Administrator on 2017/2/21 0021.
 */
public interface ColumnDao {
    /**
     * 批量插入字段信息
     * @param columnInfoList
     * @return
     */
    int addColumns(ColumnInfo[] columnInfoList);

    /**
     * 查询表格的所有字段
     * @param tableId
     * @return
     */
    List<ColumnInfo> selectByTableId(String tableId);
}
