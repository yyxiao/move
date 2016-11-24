/**
 * WechatService.java
 * com.service
 * author      date      	
 * ──────────────────────────────────
 * xiaoyy    2016年2月19日 		
 * Copyright (c)2016, All Rights Reserved.
 * Java源代码,未经许可禁止任何人、任何组织通过任何
 * 渠道使用、修改源代码.
 */
package com.service;

import java.util.List;

import org.apache.log4j.Logger;

import com.jfinal.aop.Before;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.plugin.activerecord.tx.Tx;
import com.util.StringHelper;

public class WechatService {
	private Logger logger = Logger.getLogger(WechatService.class);

	/**
	 * (查询departmentId)
	 * 
	 * @param wechatId
	 * @return
	 */
	public String findDepartmentIdByWechatId(String wechatId) {
		String departmentId = null;
		String sql = "SELECT department_id FROM `sys_wechat` WHERE id = "
				+ wechatId;
		List<Record> department = Db.find(sql);
		if (!StringHelper.isEmptyObject(department)) {
			for (Record record : department) {
				departmentId = record.getLong("department_id").toString();
				System.out.println(departmentId);
			}
		}
		logger.info("查询成功！");
		return departmentId;
	}

	/**
	 * (删除积分配置)
	 * 
	 * @param departmentId
	 * @return
	 */
	@Before(Tx.class)
	public boolean deleteAwardConfig(String departmentId) {
		Boolean bl = true;
		// 填写sql语句
		String sql = "delete from hr_award_config where department_id = "
				+ departmentId;
		int del = Db.update(sql);

		if (del > 0) {
			// 删除成功
			logger.info("删除配置记录成功！");
		} else {
			bl = false;
			logger.info("删除配置记录失败！");
		}
		return bl;
	}

	/**
	 * (添加积分配置)
	 * 
	 * @param departmentId
	 * @return
	 */
	@Before(Tx.class)
	public boolean saveAwardConfig(String departmentId) {
		Boolean bl = true;
		// 填写sql语句
		String sql = "insert into hr_award_config(department_id, status_name, reward, description, template_id, tag) "
				+ " select "+ departmentId+", status as status_name, award as reward, description,id as template_id,tag "
				+ " from hr_award_config_template where award > 0";
		int save = Db.update(sql);
		
		if (save > 0) {
			// 删除成功
			logger.info("添加配置成功！");
		} else {
			bl = false;
			logger.info("添加配置失败！");
		}
		return bl;
	}
	
	/**
	 * (撤下职位)
	 * @param departmentId
	 * @return
	*/
	@Before(Tx.class)
	public boolean updatePosition(String departmentId){
		Boolean bl = true;
		// 填写sql语句
		String sql = "update hr_position set status = 1 where department_id = "+departmentId+" and status = 0 and stop_date is not null and stop_date < now()";
		int save = Db.update(sql);
		
		if (save > 0) {
			// 撤下职位成功
			logger.info("撤下职位成功！");
		} else {
			bl = false;
			logger.info("撤下职位失败！");
		}
		return bl;
	}
	
	/**
	 * (迁移公司下所有的职位申请)
	 * @param departmentId
	 * @return
	*/
	@Before(Tx.class)
	public boolean updateApplication(String departmentId){
		Boolean bl = true;
		// 填写sql语句
		String sql = "update job_application set app_tpl_id= 1 where department_id = " + departmentId;
		int save = Db.update(sql);
		
		if (save > 0) {
			// 职位申请迁移成功
			logger.info("职位申请迁移成功！");
		} else {
			bl = false;
			logger.info("职位申请迁移失败！");
		}
		return bl;
	}

	/**
	 * (查询公司Id)
	 * @param departmentId
	 * @return
	*/
	public String findCompanyIdByDepartmentId(String departmentId) {
		String companyId = null;
		String sql = "SELECT company_id FROM `sys_department` WHERE id = "
				+ departmentId;
		List<Record> department = Db.find(sql);
		if (!StringHelper.isEmptyObject(department)) {
			for (Record record : department) {
				companyId = record.getInt("company_id").toString();
				System.out.println(companyId);
			}
		}
		logger.info("查询成功公司Id！");
		return companyId;
	}
	
	/**
	 * (公司更新)
	 * @param companyId
	 * @param employeeId
	 * @return
	*/
	@Before(Tx.class)
	public boolean updateCompany(String companyId,int employeeId){
		Boolean bl = true;
		// 填写sql语句
		String sql = "UPDATE sys_company SET employee_id = " + employeeId
				+ " , update_time = (date_add(NOW(), INTERVAL - 1 MONTH)) WHERE id = " + companyId;
		int save = Db.update(sql);
		
		if (save > 0) {
			// 公司更新成功
			logger.info("公司更新成功！");
		} else {
			bl = false;
			logger.info("公司更新失败！");
		}
		return bl;
	}
	
	/**
	 * (更新迁移标识)
	 * @param wechatId
	 * @return
	*/
	@Before(Tx.class)
	public boolean updateWechat(String wechatId) {
		Boolean bl = true;
		// 填写sql语句
		String sql = "UPDATE `sys_wechat` SET is_migrate = 0 WHERE id = " + wechatId;
		int save = Db.update(sql);
		
		if (save > 0) {
			// 公司更新成功
			logger.info("更新迁移标识成功！");
		} else {
			bl = false;
			logger.info("更新迁移标识失败！");
		}
		return bl;
	}
}
