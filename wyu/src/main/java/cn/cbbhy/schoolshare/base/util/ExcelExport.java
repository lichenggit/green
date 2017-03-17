package cn.cbbhy.schoolshare.base.util;

import cn.cbbhy.schoolshare.logic.model.ColumnInfo;
import cn.cbbhy.schoolshare.logic.model.TableInfo;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.*;

import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/2/23 0023.
 */
public class ExcelExport {
    private TableInfo tableInfo;
    private List<Map<String, Object>> list;

    public ExcelExport(TableInfo tableInfo, List<Map<String, Object>> list) {
        this.tableInfo = tableInfo;
        this.list = list;
    }

    public XSSFWorkbook fillWorkBook() {
        //创建一个XSSFWorkbook（即一个Excel文件）
        XSSFWorkbook workBook = new XSSFWorkbook();
        // 在workbook中添加一个sheet,对应Excel文件中的sheet
        XSSFSheet sheet = workBook.createSheet(tableInfo.getTableNameCn());
        ExcelStyleUtil styleUtil = new ExcelStyleUtil(workBook, sheet);
        XSSFCellStyle headStyle = styleUtil.getHeadStyle();
        XSSFCellStyle bodyStyle = styleUtil.getBodyStyle();

        ColumnInfo[] columnInfoList = tableInfo.getColumnInfoList();
        XSSFCell cell = null;
        //合并第一和第二行来做标题
        sheet.addMergedRegion(new CellRangeAddress(0, 1, 0, columnInfoList.length - 1));
        XSSFRow titleRow = sheet.createRow(0);
        cell = titleRow.createCell(0);
        cell.setCellStyle(headStyle);
        cell.setCellValue(tableInfo.getTableNameCn() + "(" + tableInfo.getRemark() + ")");
        //构建表头
        XSSFRow headRow = sheet.createRow(2);
        for (int i = 0; i < columnInfoList.length; i++) {
            ColumnInfo columnInfo = columnInfoList[i];
            cell = headRow.createCell(i);
            cell.setCellStyle(headStyle);
            cell.setCellValue(columnInfo.getColumnNameCn());
        }
        //构建表体
        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                XSSFRow bodyRow = sheet.createRow(i + 3);
                Map<String, Object> map = list.get(i);
                map.remove("ID");//(去除第0列的ID)
                Iterator<Map.Entry<String, Object>> iterator = map.entrySet().iterator();
                int j = 0;
                while (iterator.hasNext()) {
                    Map.Entry entry = iterator.next();
                    cell = bodyRow.createCell(j);
                    cell.setCellStyle(bodyStyle);
                    Object value = entry.getValue();
                    System.out.println(columnInfoList[j].getColumnType() + "--" + value.toString());
                    try {
                        switch (columnInfoList[j].getColumnType()) {
                            case "INTEGER":
                            case "DOUBLE":
                                cell.setCellValue((Double) value);
                                break;
                            case "VARCHAR(255)":
                            case "TEXT":
                                cell.setCellValue((String) value);
                                break;
                            case "TIMESTAMP":
                                cell.setCellValue(new SimpleDateFormat("yyyy-MM-dd HH:mm").format(value));
                                break;
                            default:
                                cell.setCellValue("");
                                break;
                        }
                    } catch (Exception e) {
                        cell.setCellValue("");
                    }
                    j++;
                }

            }
        }
        return workBook;
    }

    public void export(XSSFWorkbook workBook, OutputStream outputStream) {
        try {
            workBook.write(outputStream);
            outputStream.flush();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
