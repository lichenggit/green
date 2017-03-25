package cn.cbbhy.schoolshare.base.datasource;

import com.alibaba.fastjson.JSON;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionDefinition;

/**
 * Created by duoyi on 17-3-24.
 */
public class DynamicDataSourceTransactionManager extends DataSourceTransactionManager {

    /**
     * 只读事务到读库，读写事务到写库
     * @param transaction
     * @param definition
     */
    @Override
    protected void doBegin(Object transaction, TransactionDefinition definition) {
        boolean readOnly = definition.isReadOnly();
        if(readOnly){
            DynamicDataSourceHolder.putDataSource(DynamicDataSourceGlobal.READ);
        }else {
            DynamicDataSourceHolder.putDataSource(DynamicDataSourceGlobal.WRITE);
        }
        System.out.println("readOnly:"+readOnly+","+ definition.getName()+","+definition.getIsolationLevel()+","+definition.getPropagationBehavior());
        super.doBegin(transaction, definition);
    }

    /**
     * 清理本地线程的数据源
     * @param transaction
     */
    @Override
    protected void doCleanupAfterCompletion(Object transaction) {
        super.doCleanupAfterCompletion(transaction);
        DynamicDataSourceHolder.clearDataSource();
    }
}
