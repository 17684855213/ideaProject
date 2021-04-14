package com.example.demo.service.Impl;

import com.example.demo.exception.RRException;
import com.example.demo.mapper.EmployeeMapper;
import com.github.pagehelper.PageHelper;
import com.sun.tools.corba.se.idl.ParameterGen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.entity.Employee;

import com.example.demo.service.EmployeeService;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    @Autowired
    private EmployeeMapper employeeMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return employeeMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Employee record) {
        int i= employeeMapper.selectByPhoneNumber(record.getPhoneNumber());
        if(i!=0){
            throw  new RRException("手机号重复");
        }
        return employeeMapper.insert(record);
    }

    @Override
    public int insertSelective(Employee record) {
        return employeeMapper.insertSelective(record);
    }

    @Override
    public Employee selectByPrimaryKey(Long id) {
        return employeeMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Employee record) {
        return employeeMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Employee record) {
        return employeeMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<Employee> selectSelective(Employee employee) {

        if(employee.getPageSize()!=null && employee.getPageNum()!=null){
            PageHelper.startPage(employee.getPageNum(),employee.getPageSize());
        }

        return employeeMapper.selectSelective(employee);

    }

}
