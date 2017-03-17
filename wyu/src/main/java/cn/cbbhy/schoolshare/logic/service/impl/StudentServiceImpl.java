package cn.cbbhy.schoolshare.logic.service.impl;

import cn.cbbhy.schoolshare.base.util.IdGenerator;
import cn.cbbhy.schoolshare.logic.dao.StudentDao;
import cn.cbbhy.schoolshare.logic.model.Student;
import cn.cbbhy.schoolshare.logic.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2017/2/25 0025.
 */
@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    protected StudentDao studentDao;

    @Override
    public void addStudents(String monitor, List<Student> students, String uploadType) {
        if ("Y".equals(uploadType)) {
            studentDao.deleteStudentsByMonitor(monitor);
        }
        for (Student student : students) {
            student.setId(IdGenerator.generateId());
            student.setMonitor(monitor);
        }
        studentDao.addStudents(students);
    }

    @Override
    public void deleteStudents(String monitor) {
        studentDao.deleteStudentsByMonitor(monitor);
    }

    @Override
    public List<Student> listStudentsByMonitor(String monitor) {
        return studentDao.searchStudentsByMonitor(monitor);
    }

    @Override
    public boolean findStudentIsExist(String monitor, String studentId, String studentName) {
        int total = studentDao.countStudentByMonitor(monitor);
        //总数为0，说明不要校验真假，默认为真
        if (total == 0) {
            return true;
        } else {
            int count = studentDao.countStudentByStudentId(monitor, studentId, studentName);
            return count > 0;
        }
    }

}
