package cn.cbbhy.schoolshare.logic.controller;

import cn.cbbhy.schoolshare.base.util.ExcelExport;
import cn.cbbhy.schoolshare.base.util.MenuUtil;
import cn.cbbhy.schoolshare.logic.model.TableInfo;
import cn.cbbhy.schoolshare.logic.model.User;
import cn.cbbhy.schoolshare.logic.model.vo.MenuItem;
import cn.cbbhy.schoolshare.logic.service.TableService;
import com.alibaba.fastjson.JSON;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/12/1 0001.
 * <p>
 * 对外开放入口
 */
@Controller
@RequestMapping("/table")
public class MainController {


    @Autowired
    private TableService tableService;
    @RequestMapping(value = "/createTable.html", method = RequestMethod.GET)
    public String createTable() {
        return "createTable";
    }

    @RequestMapping(value = "/createTable.html", method = RequestMethod.POST)
    public String createTable(HttpSession session, TableInfo tableInfo) {
        User user = (User) session.getAttribute("user");
        System.out.println(JSON.toJSONString(tableInfo));
        tableService.addTable(user.getUserId(), tableInfo);
        return "redirect:table/myTables.html";
    }

    @RequestMapping(value = "/enterTableInfo/{tableId}", method = RequestMethod.GET)
    public String findTable(@PathVariable String tableId, Model model) {
        model.addAttribute("tableInfo", tableService.findTableInfo(tableId));
        return "enterTableInfo";
    }

    @RequestMapping(value = "/myTables.html", method = RequestMethod.GET)
    public String myTables(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        model.addAttribute("myTables", tableService.listTableByUserId(user.getUserId()));
        model.addAttribute("permitTables", tableService.listPermitTablesByUserId(user.getUserId()));
        return "myTables";
    }

    /**
     * 表格的填写情况
     *
     * @param tableId
     * @param model
     * @return
     */
    @RequestMapping(value = "/tableExecution/{tableId}", method = RequestMethod.GET)
    public String tablesExecution(@PathVariable String tableId, Model model) {
        TableInfo tableInfo = tableService.findTableInfo(tableId);
        model.addAttribute("tableInfo", tableInfo);
        model.addAttribute("rows", tableService.listFromDynamicTable(tableInfo.getTableNameEn()));
        return "tableExecution";
    }

    /**
     * 管理界面
     *
     * @return
     */
    @RequestMapping("/admin.html")
    public String admin(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        List<MenuItem> menuItems = MenuUtil.getMenus(user.getUserCategory().getCategoryLevel());
        model.addAttribute("chineseName", user.getChineseName());
        model.addAttribute("menuItems", menuItems);
        return "admin";
    }

    @RequestMapping("/submitTable.json")
    @ResponseBody
    public Map submitTable( @RequestParam HashMap paramMap) {
        Map map = new HashMap();
        map.put("code", 0);
        paramMap.remove("verifyCode");
        String tableNameEn = (String) paramMap.remove("tableNameEn");
        System.out.println(JSON.toJSONString(paramMap));
        tableService.addRecordIntoDynamicTable(tableNameEn, paramMap);
        return map;
    }

    /**
     * 导出报表
     *
     * @param response
     * @return
     */
    @RequestMapping("/export/{tableId}")
    public void exportExcel(@PathVariable String tableId, HttpServletResponse response) {
        TableInfo tableInfo = tableService.findTableInfo(tableId);
        List<Map<String, Object>> list = tableService.listFromDynamicTable(tableInfo.getTableNameEn());
        response.setContentType("application/binary;charset=ISO8859_1");
        try {
            ServletOutputStream outputStream = response.getOutputStream();
            String fileName = new String(tableInfo.getTableNameCn().getBytes(), "ISO8859_1");
            response.setHeader("Content-disposition", "attachment; filename=" + fileName + ".xlsx");// 组装附件名称和格式
            ExcelExport excelExport = new ExcelExport(tableInfo, list);
            XSSFWorkbook workbook = excelExport.fillWorkBook();
            excelExport.export(workbook, outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
