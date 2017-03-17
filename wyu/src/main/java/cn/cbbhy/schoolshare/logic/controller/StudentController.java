package cn.cbbhy.schoolshare.logic.controller;

import cn.cbbhy.schoolshare.base.util.ExcelImport;
import cn.cbbhy.schoolshare.logic.model.Student;
import cn.cbbhy.schoolshare.logic.model.User;
import cn.cbbhy.schoolshare.logic.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by Administrator on 2017/2/25 0025.
 */
@Controller
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @RequestMapping(value = "/classGrade.html", method = RequestMethod.GET)
    public String classGrade(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        model.addAttribute("students", studentService.listStudentsByMonitor(user.getUserId()));
        return "classGradeManage";
    }

    @RequestMapping(value = "/uploadStudentsFile.html", method = RequestMethod.POST)
    public String uploadStudentsFile(HttpSession session, @RequestParam("studentsFile") MultipartFile multipartFile, String uploadType) throws Exception {
        User user = (User) session.getAttribute("user");
        //记录上传过程起始时的时间，用来计算上传时间
        int preTime = (int) System.currentTimeMillis();
        if (multipartFile != null) {
            //取得当前上传文件的文件名称
            String myFileName = multipartFile.getOriginalFilename();
            //如果名称不为“”,说明该文件存在，否则说明该文件不存在
            if (myFileName.trim() != "") {
                System.out.println(myFileName);
                //重命名上传后的文件名
//                        String fileName = "demoUpload" + multipartFile.getOriginalFilename();
//                        //定义上传路径
//                        String path = "D:/" + fileName;
//                        File localFile = new File(path);
//                        multipartFile.transferTo(localFile);

                //解释Excel表
                ExcelImport excelImport = new ExcelImport();
                List<Student> students = excelImport.getBankListByExcel(multipartFile.getInputStream(), multipartFile.getOriginalFilename());
                studentService.addStudents(user.getUserId(), students, uploadType);
            }
        }
        //记录上传该文件后的时间
        int finalTime = (int) System.currentTimeMillis();
        System.out.println(finalTime - preTime);

        return "redirect:/table/admin.html";
    }

    @RequestMapping(value = "/clearAllStudents.html")
    public String clearAllStudents(HttpSession session) {
        User user = (User) session.getAttribute("user");
        studentService.deleteStudents(user.getUserId());
        return "redirect:/student/classGrade.html";
    }

    @RequestMapping("/checkStudentId.json")
    @ResponseBody
    public boolean checkStudentId(String creator, String studentId, String name) {
        System.out.println(creator + "-" + studentId + "-" + name);
        if (creator == null || studentId == null || name == null) {
            return false;
        }
        return studentService.findStudentIsExist(creator, studentId, name);
    }
}
