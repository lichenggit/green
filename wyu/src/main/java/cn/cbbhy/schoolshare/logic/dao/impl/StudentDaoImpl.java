package cn.cbbhy.schoolshare.logic.dao.impl;

import cn.cbbhy.schoolshare.logic.dao.StudentDao;
import cn.cbbhy.schoolshare.logic.mapping.StudentMapper;
import cn.cbbhy.schoolshare.logic.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Administrator on 2017/2/25 0025.
 */
@Repository
public class StudentDaoImpl implements StudentDao {
    @Autowired
    private StudentMapper studentMapper;

    @Override
    public void addStudents(List<Student> students) {
        studentMapper.insertStudents(students);
    }

    @Override
    public void deleteStudentsByMonitor(String monitor) {
        studentMapper.deleteStudentsByMonitor(monitor);
    }

    @Override
    public List<Student> searchStudentsByMonitor(String monitor) {
        return studentMapper.searchStudentsByMonitor(monitor);
    }

    @Override
    public int countStudentByStudentId(String monitor, String studentId, String studentName) {
        return studentMapper.countStudentByStudentId(monitor,studentId,studentName);
    }

    @Override
    public int countStudentByMonitor(String monitor) {
        return studentMapper.countStudentByMonitor(monitor);
    }
}
