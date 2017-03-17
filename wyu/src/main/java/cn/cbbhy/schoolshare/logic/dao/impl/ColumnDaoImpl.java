package cn.cbbhy.schoolshare.logic.dao.impl;

import cn.cbbhy.schoolshare.logic.dao.ColumnDao;
import cn.cbbhy.schoolshare.logic.mapping.ColumnInfoMapper;
import cn.cbbhy.schoolshare.logic.model.ColumnInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Administrator on 2017/2/21 0021.
 */
@Repository
public class ColumnDaoImpl implements ColumnDao {
    @Autowired
    private ColumnInfoMapper columnInfoMapper;

    @Override
    public int addColumns(ColumnInfo[] columnInfoList) {
        return columnInfoMapper.insertByBatch(columnInfoList);
    }

    @Override
    public List<ColumnInfo> selectByTableId(String tableId) {
        return columnInfoMapper.selectByTableId(tableId);
    }


}
