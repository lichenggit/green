package cn.cbbhy.schoolshare.logic.dao.impl;

import cn.cbbhy.schoolshare.logic.dao.TableDao;
import cn.cbbhy.schoolshare.logic.mapping.TableInfoMapper;
import cn.cbbhy.schoolshare.logic.model.ColumnInfo;
import cn.cbbhy.schoolshare.logic.model.TableInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/2/21 0021.
 */
@Repository
public class TableDaoImpl implements TableDao {
    @Autowired
    private TableInfoMapper tableInfoMapper;

    @Override
    public TableInfo selectByPrimaryKey(String id) {
        return tableInfoMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<TableInfo> selectByUserId(String userId) {
        return tableInfoMapper.searchTablesByUserId(userId);
    }

    @Override
    public List<TableInfo> searchAccessTablesByUserId(String userId, String isPermit) {
        return tableInfoMapper.searchAccessTablesByUserId(userId, isPermit);
    }

    @Override
    public int addTable(TableInfo tableInfo) {
        return tableInfoMapper.insertSelective(tableInfo);
    }

    @Override
    public void createNewTable(TableInfo tableInfo, ColumnInfo[] columnInfoList) {
        tableInfoMapper.createNewTable(tableInfo, columnInfoList);
    }

    @Override
    public int insertIntoDynamicTable(String tableNameEn, Map<String, Object> columnMap) {
        return tableInfoMapper.insertIntoDynamicTable(tableNameEn, columnMap);
    }

    @Override
    public List<Map<String, Object>> selectFromDynamicTable(String tableNameEn) {
        return tableInfoMapper.selectFromDynamicTable(tableNameEn);
    }
}
