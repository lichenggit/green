package cn.cbbhy.schoolshare.logic.service.impl;

import cn.cbbhy.schoolshare.base.util.IdGenerator;
import cn.cbbhy.schoolshare.logic.dao.ColumnDao;
import cn.cbbhy.schoolshare.logic.dao.TableDao;
import cn.cbbhy.schoolshare.logic.model.ColumnInfo;
import cn.cbbhy.schoolshare.logic.model.TableInfo;
import cn.cbbhy.schoolshare.logic.service.TableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/2/21 0021.
 */
@Service
public class TableServiceImpl implements TableService {
    @Autowired
    private TableDao tableDao;
    @Autowired
    private ColumnDao columnDao;

    @Override
    public void addTable(String userId, TableInfo tableInfo) {
        tableInfo.setId(IdGenerator.generateId());
        tableInfo.setTableNameEn(generateTableName(userId));
        tableInfo.setCreator(userId);
        tableInfo.setCreateTime(new Date());
        ColumnInfo[] columnInfoList = tableInfo.getColumnInfoList();
        for (int i = 0; i < columnInfoList.length; i++) {
            ColumnInfo columnInfo = columnInfoList[i];
            if (columnInfo == null) {
                continue;
            }
            columnInfo.setSequence(i);
            columnInfo.setTableId(tableInfo.getId());
            columnInfo.setId(IdGenerator.generateId());
            columnInfo.setColumnType(getColumnType(columnInfo.getColumnConstraint()));
            columnInfo.setColumnNameEn(getColumnNameEn(i, columnInfo.getColumnConstraint()));
        }
        tableDao.addTable(tableInfo);
        columnDao.addColumns(tableInfo.getColumnInfoList());
        tableDao.createNewTable(tableInfo, tableInfo.getColumnInfoList());
    }


    @Override
    public TableInfo findTableInfo(String tableId) {
        TableInfo tableInfo = tableDao.selectByPrimaryKey(tableId);
        List<ColumnInfo> columnInfoList = columnDao.selectByTableId(tableId);
        tableInfo.setColumnInfoList(columnInfoList.toArray(new ColumnInfo[columnInfoList.size()]));
        return tableInfo;
    }

    @Override
    public List<TableInfo> listTableByUserId(String userId) {
        return tableDao.selectByUserId(userId);
    }

    @Override
    public List<TableInfo> listPermitTablesByUserId(String userId) {
        return tableDao.searchAccessTablesByUserId(userId, "Y");
    }

    @Override
    public void addRecordIntoDynamicTable(String tableNameEn, Map<String, Object> columnMap) {
        columnMap.put("ID", IdGenerator.generateId());
        tableDao.insertIntoDynamicTable(tableNameEn, columnMap);
    }

    @Override
    public List<Map<String, Object>> listFromDynamicTable(String tableNameEn) {
        return tableDao.selectFromDynamicTable(tableNameEn);
    }

    /**
     * 生成表名
     *
     * @param userId
     * @return
     */
    private String generateTableName(String userId) {
        return "t_wyu_" + userId + "_" + System.currentTimeMillis();
    }

    /**
     * 生成列名
     *
     * @param columnNum
     * @return
     */
    private String generateColumnName(int columnNum) {
        return "COLUMN" + columnNum;
    }

    private String getColumnType(String columnConstraint) {
        String type = "";
        switch (columnConstraint) {
            case "STRING":
            case "IDCARD":
            case "MOBILE":
            case "EMAIL":
            case "STUDENT_ID":
            case "STUDENT_NAME":
                type = "VARCHAR(255)";
                break;
            case "TEXT":
                type = "TEXT";
                break;
            case "INTEGER":
                type = "INTEGER";
                break;
            case "DOUBLE":
                type = "DOUBLE";
                break;
            case "TIMESTAMP":
                type = "TIMESTAMP";
                break;
        }
        return type;
    }

    private String getColumnNameEn(int i, String columnConstraint) {
        switch (columnConstraint) {
            case "STUDENT_ID":
                return "studentId";
            case "STUDENT_NAME":
                return "name";
            default:
                return generateColumnName(i + 1);
        }
    }
}
