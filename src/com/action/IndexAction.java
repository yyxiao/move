/**
 * IndexAction.java
 * com.action
 * author      date      	
 * ──────────────────────────────────
 * xiaoyy    2016年3月1日 		
 * Copyright (c)2016, All Rights Reserved.
 * Java源代码,未经许可禁止任何人、任何组织通过任何
 * 渠道使用、修改源代码.
*/
package com.action;

import com.jfinal.core.Controller;

 
public class IndexAction extends Controller{
	
	public void index(){
		renderJsp("jsp/index.jsp");
	}
	
}
