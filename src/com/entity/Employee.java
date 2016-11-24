/**
 * Employee.java
 * com.entity
 * author      date      	
 * ──────────────────────────────────
 * xiaoyy    2016年2月23日 		
 * Copyright (c)2016, All Rights Reserved.
 * Java源代码,未经许可禁止任何人、任何组织通过任何
 * 渠道使用、修改源代码.
*/
package com.entity;

import com.jfinal.plugin.activerecord.Model;

 
public class Employee extends Model<Employee>{

	private static final long serialVersionUID = 1L;
	
	public static final Employee dao = new Employee();

}
