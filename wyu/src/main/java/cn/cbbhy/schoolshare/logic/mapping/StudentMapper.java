package cn.cbbhy.schoolshare.logic.mapping;

import cn.cbbhy.schoolshare.logic.model.Student;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StudentMapper {
    int deleteByPrimaryKey(String id);

    int insert(Student record);

    int insertSelective(Student record);

    Student selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Student record);

    int updateByPrimaryKey(Student record);

    /**
     * 导入学生学号信息
     *
     * @param students
     */
    void insertStudents(@Param("list") List<Student> students);

    /**
     * 查找班长上传的学生名单
     *
     * @param monitor
     * @return
     */
    List<Student> searchStudentsByMonitor(String monitor);

    /**
     * 删除班长上传的学生名单
     *
     * @param monitor
     */
    void deleteStudentsByMonitor(String monitor);

    /**
     * 检查学号和姓名是否对应
     *
     * @param monitor
     * @param studentId
     * @param studentName
     * @return
     */
    int countStudentByStudentId(@Param("monitor") String monitor, @Param("studentId") String studentId, @Param("studentName") String studentName);

    /**
     * 查找学生数量
     *
     * @param monitor
     * @return
     */
    int countStudentByMonitor(String monitor);

}