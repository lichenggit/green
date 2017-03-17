package cn.cbbhy.schoolshare.logic.dao;

import cn.cbbhy.schoolshare.logic.model.Student;

import java.util.List;

/**
 * Created by Administrator on 2017/2/25 0025.
 */
public interface StudentDao {
    /**
     * 导入学生学号信息
     *
     * @param students
     */
    void addStudents(List<Student> students);

    /**
     * 删除班长上传的学生名单
     *
     * @param monitor
     */
    void deleteStudentsByMonitor(String monitor);

    /**
     * 查找班长上传的学生名单
     *
     * @param monitor
     * @return
     */
    List<Student> searchStudentsByMonitor(String monitor);

    /**
     * 检查学号和姓名是否对应
     *
     * @param monitor
     * @param studentId
     * @param studentName
     * @return
     */
    int countStudentByStudentId(String monitor, String studentId, String studentName);

    /**
     * 查找学生数量
     *
     * @param monitor
     * @return
     */
    int countStudentByMonitor(String monitor);
}
