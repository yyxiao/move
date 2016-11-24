/**
 * EmployeeService.java
 * com.service
 * author      date      	
 * ──────────────────────────────────
 * xiaoyy    2016年1月19日 		
 * Copyright (c)2016, All Rights Reserved.
 * Java源代码,未经许可禁止任何人、任何组织通过任何
 * 渠道使用、修改源代码.
*/
package com.service;

import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.entity.Employee;
import com.jfinal.aop.Before;
import com.jfinal.kit.HashKit;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.tx.Tx;


 
public class EmployeeService {
	
	private Logger logger = Logger.getLogger(EmployeeService.class);
	
	/**
	 * (备份员工的积分数据)
	 * @param departmentId
	 * @return
	 * @throws SQLException
	*/
	@Before(Tx.class)
	public boolean saveReward(String departmentId){
		Boolean bl = true;
		String sql = "insert into bak_sys_reward select * from sys_reward where employee_id in (select id from sys_employee where department_id = "+departmentId+")";
		int save = Db.update(sql);
		if (save > 0) {
        	logger.info("保存积分备份信息成功！");
		} else {
			bl = false;
			logger.info("保存积分备份信息失败！");
		}
		return bl;
	}
	
	/**
	 * (删除原始积分)
	 * @param departmentId
	 * @return
	 * @throws SQLException
	*/
	@Before(Tx.class)
	public boolean deleteReward(String departmentId){
		Boolean bl = true;
		String sql = "DELETE FROM sys_reward WHERE employee_id IN ( SELECT id FROM sys_employee WHERE department_id = "+departmentId+")";
		int delete = Db.update(sql);
		// 执行语句，得到结果集
		if (delete > 0) {
			// 删除成功
        	logger.info("删除积分信息成功！");
		} else {
			bl = false;
			logger.info("删除积分信息失败！");
		}
		return bl;
	}
	
	/**
	 * (添加积分结余总数)
	 * @param departmentId
	 * @return
	 * @throws SQLException
	*/
	@Before(Tx.class)
	public boolean addReward(String departmentId){
		Boolean bl = true;
		//填写sql语句
		String sql = "INSERT INTO sys_reward ( employee_id, reason, award, _create_time, application_id, recom_wxuser, update_time ) "
				+ " SELECT employee_id, \"迁移\", SUM(award) AS award, _create_time, application_id, recom_wxuser, update_time "
				+ " FROM bak_sys_reward WHERE employee_id IN ( SELECT id FROM sys_employee WHERE department_id = "+departmentId+") GROUP BY employee_id";
		int save = Db.update(sql);
		// 执行语句，得到结果集
		if (save > 0) {
        	logger.info("添加积分信息成功！");
		} else {
			bl = false;
			logger.info("添加积分信息失败！");
		}
		return bl;
	}
	
	/**
	 * (备份这些认证员工相关的申请状态操作记录)
	 * @param departmentId
	 * @return
	 * @throws SQLException
	*/
	@Before(Tx.class)
	public boolean saveOperation(String departmentId){
		Boolean bl = true;
		//填写sql语句
		String sql = "insert into bak_hr_operation_record(id, admin_id, department_id, app_id, status_id, opt_time) "
				+ " select id, admin_id, department_id, app_id, status_id, opt_time from hr_operation_record where department_id = " + departmentId;
		int save = Db.update(sql);
		
		if (save > 0) {
			// 添加成功
        	logger.info("备份操作记录成功！");
		} else {
			bl = false;
			logger.info("备份操作记录失败！");
		}
		return bl;
	}
	
	/**
	 * (删除操作记录)
	 * @param departmentId
	 * @return
	*/
	@Before(Tx.class)
	public boolean deleteOperation(String departmentId){
		Boolean bl = true;
		//填写sql语句
		String sql = "DELETE FROM hr_operation_record WHERE department_id = " + departmentId;
		int del = Db.update(sql);
		
		if (del > 0) {
			// 删除成功
        	logger.info("删除操作记录成功！");
		} else {
			bl = false;
			logger.info("删除操作记录失败！");
		}
		return bl;
	}
	
	/**
	 * (添加公司职位)
	 * @param position
	 * @return
	*/
	@Before(Tx.class)
	public Employee add(Employee employee){
		boolean bl = employee.save();
		if(bl){
			logger.info("Employee保存成功！id为" + employee.getInt("id"));
		}else{
			logger.info("Employee保存失败！");
		}
		return employee;
	}
	
	
	public String encode(String pwd){
		String a1 = HashKit.md5(pwd);
		System.out.println(a1);
		
		String b1 = HashKit.sha1(a1);
		System.out.println(b1);
		return b1;
	}
}
