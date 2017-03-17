package cn.cbbhy.schoolshare.logic.service;

import cn.cbbhy.schoolshare.logic.model.Student;

import java.util.List;

/**
 * Created by Administrator on 2017/2/25 0025.
 */
public interface StudentService {
    /**
     * 导入学生学号信息
     * @param monitor
     * @param students
     * @param uploadType
     */
    void addStudents(String monitor, List<Student> students, String uploadType);

    /**
     * 清除学生学号信息
     * @param monitor
     */
    void deleteStudents(String monitor);

    /**
     * 查找班长上传的学生名单
     * @param monitor
     * @return
     */
    List<Student> listStudentsByMonitor(String monitor);

    /**
     * 学生是否存在
     * @param monitor
     * @param studentId
     * @param studentName
     * @return
     */
    boolean findStudentIsExist(String monitor, String studentId, String studentName);
}
