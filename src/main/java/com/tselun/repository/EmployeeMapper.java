package com.tselun.repository;

import com.tselun.model.Employee;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface EmployeeMapper {

    @Insert("insert into employees(name, phone, email, branch) values (#{name}, #{phone}, #{email}, #{branch})")
    int insert(Employee employee);

    @Select("select * from employees")
    List<Employee> findAll();

    @Select("select * from employees where id = #{id}")
    Employee findById(@Param("id") long id);

    @Update("update employees set name = #{name}, phone = #{phone}, email = #{email}, branch = #{branch} where id = #{id}")
    int update(Employee employee);

    @Delete("delete from employees where id = #{id}")
    int deleteById(@Param("id") long id);

}
