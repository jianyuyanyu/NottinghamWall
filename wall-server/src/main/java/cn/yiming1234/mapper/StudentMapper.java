package cn.yiming1234.mapper;

import cn.yiming1234.dto.StudentPageQueryDTO;
import cn.yiming1234.entity.Student;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface StudentMapper {
    /**
     * 插入学生信息
     *
     * @param student
     */
    void insert(Student student);
    /**
     * 根据用户名查询管理员
     * @param username
     * @return
     */
    @Select("select * from student where username = #{username}")
    Student getByUsername(String username);
    /**
     * 根据id查询管理员
     * @param id
     * @return
     */
    @Select("select * from student where id = #{id}")
    Student getById(Long id);
    /**
     * 根据openid查询学生
     * @param openid
     * @return
     */
    @Select("select * from student where openid = #{openid}")
    Student findByOpenid(String openid);
    /**
     * 分页查询管理员
     * @param studentPageQueryDTO
     * @return
     */
    Page<Student> pageQuery(StudentPageQueryDTO studentPageQueryDTO);
}
