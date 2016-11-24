/**
 * TestService.java
 * com.service
 * author      date      	
 * ──────────────────────────────────
 * xiaoyy    2016年3月2日 		
 * Copyright (c)2016, All Rights Reserved.
 * Java源代码,未经许可禁止任何人、任何组织通过任何
 * 渠道使用、修改源代码.
*/
package com.service;

 
public interface TestService {
	
	default double sqrt(int a){
		return Math.sqrt(a);
	}
	
}
