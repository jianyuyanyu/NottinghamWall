package cn.yiming1234.NottinghamWall.mapper;

import cn.yiming1234.NottinghamWall.dto.PageQueryDTO;
import cn.yiming1234.NottinghamWall.entity.Student;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface StudentMapper {

    /**
     * 插入学生信息
     * @param student 学生信息
     */
    void insert(Student student);

    /**
     * 根据用户名查询管学生
     * @param username 用户名
     */
    Student getByUsername(String username);

    /**
     * 根据id查询学生
     * @param id 学生id
     */
    Student getById(Integer id);

    /**
     * 根据id查询openid
     * @param id 学生id
     */
    String getOpenidById(Integer id);

    /**
     * 根据openid查询学生
     * @param openid openid
     */
    Student findByOpenid(String openid);

    /**
     * 根据学号查询学生
     * @param studentid 学号
     */
    Student getByStudentId(Integer studentid);

    /**
     * 分页查询学生
     * @param pageQueryDTO 分页查询条件
     */
    Page<Student> pageQuery(PageQueryDTO pageQueryDTO);

    /**
     * 根据邮箱查询学生
     * @param email 邮箱
     */
    Student getByEmail(String email);

    /**
     * 更新学生信息
     * @param student 学生信息
     */
    void updateById(Student student);

}