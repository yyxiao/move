/**
 * MoveAction.java
 * com.action
 * author      date      	
 * ──────────────────────────────────
 * xiaoyy    2016年2月19日 		
 * Copyright (c)2016, All Rights Reserved.
 * Java源代码,未经许可禁止任何人、任何组织通过任何
 * 渠道使用、修改源代码.
*/
package com.action;

import org.apache.log4j.Logger;

import com.entity.Employee;
import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.ext.interceptor.POST;
import com.service.EmployeeService;
import com.service.WechatService;
import com.util.StringHelper;

 
public class MoveAction extends Controller{
	
	private static Logger logger = Logger.getLogger(MoveAction.class);
	
	private static WechatService ws = new WechatService();
	private static EmployeeService es = new EmployeeService();
	
	public void index(){
		renderJson(String.format("{\"href\":\"%s%s\"}", this.getRequest().getLocalAddr(), this.getRequest().getContextPath()));
	}
	
	
	@Before(POST.class)
	public void go(){
		//微信公共号ID
		String wechatId = getPara("wechatId");
		String mobile = getPara("mobile");
		String email = getPara("email");
		String pwd = getPara("password","123456a");
		//获取departmentId
		String departmentId = ws.findDepartmentIdByWechatId(wechatId);
		//获取companyId
		String companyId = ws.findCompanyIdByDepartmentId(departmentId);
		if(!StringHelper.isEmptyObject(departmentId)){
			logger.info("//获取departmentId:"+departmentId);
			//备份积分信息
			boolean savaReward = es.saveReward(departmentId);
			//删除原积分信息
			boolean deleteReward = es.deleteReward(departmentId);
			//添加积分结余总数
			boolean addReward = es.addReward(departmentId);
			//备份这些认证员工相关的申请状态操作记
			boolean saveOperation = es.saveOperation(departmentId);
			//删除操作记录
			boolean delOperation = es.deleteOperation(departmentId);
			//删除积分配置
			boolean delAwardConfig = ws.deleteAwardConfig(departmentId);
			//添加积分配置
			boolean saveAwardConfig = ws.saveAwardConfig(departmentId);
			//加密
			pwd = es.encode(pwd);
			Employee em = new Employee();
			em.set("employeeid", mobile)
				.set("department_id", departmentId)
				.set("password", pwd)
				.set("is_admin", 1)
				.set("status", 0)
				.set("mobile", mobile)
				.set("email", email)
				.set("source", 1)
				.set("auth_level", 0);
			em = es.add(em);
			//查询插入的hr账号ID
			int employeeId = em.getInt("id");
			//撤下职位
			boolean updatePosition = ws.updatePosition(departmentId);
			//迁移职位申请
			boolean updateApp = ws.updateApplication(departmentId);
			//公司更新关联hr
			boolean updateCompany = ws.updateCompany(companyId, employeeId);
			//更新迁移标识
			boolean updateWechat = ws.updateWechat(wechatId);
			setAttr("employeeId", employeeId);
		}else{
			logger.info("wechatId="+wechatId+"不存在！");
		}
		renderJsp("/jsp/success.jsp");
	}
}
