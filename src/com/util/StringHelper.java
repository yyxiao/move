/**
 * StringHelper.java
 * com.xyy.util
 * author      date      	
 * ──────────────────────────────────
 * xiao    2015年4月22日 		
 * Copyright (c)2015, All Rights Reserved.
 * Java源代码,未经许可禁止任何人、任何组织通过任何
 * 渠道使用、修改源代码.
*/
package com.util;

import java.io.UnsupportedEncodingException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * ClassName:StringHelper
 *
 * (个人工具类)
 *
 * @project HttpServlet
 *
 * @author xiao
 *
 * @date   2015年4月22日 上午10:03:44	
 *
 * @class com.xyy.util.StringHelper
 *
 */ 
public final class StringHelper {
	
	/**
	 * TODO(发送接口是否成功)
	 * @param type
	 * @return
	*/
	public static String isSuccByType(boolean type) {
		if (type) {
			return "success";
		} else {
			return "error";
		}
	}
	
	public static boolean isChinese(String str) {
		String strTemp = null;
		try { 
			strTemp =  new String(str.getBytes("ISO-8859-1"),"utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		//如果值为空，通过校验
	     if ("".equals(str))
	         return true;
	     Pattern p = Pattern.compile("/[^\u4E00-\u9FA5]/g,''");
	     Matcher m = p.matcher(strTemp);
	     return m.matches();
	}
	
	public static boolean isEmptyObject(Object obj)
	{
			if(StringHelper.toString(obj).equals("")){
				return true;
			}else{
				return false;
		} 
	}
	
	public static String toString(Object obj) {
		if (obj == null || "".equals(obj.toString())
				|| "null".equals(obj.toString())) {
			return "";
		} else {
			String objValue = obj.toString().trim();
			return objValue;
		}
	}
	
	public static boolean isEmptyList(List list)
	{
			if(list!=null&&list.size()>0){
				return false;
			}else{
				return true;
		} 
	}
	
	public static List resultSetToList(ResultSet rs) throws java.sql.SQLException {   
        if (rs == null)   
            return Collections.EMPTY_LIST;   
        ResultSetMetaData md = rs.getMetaData(); //得到结果集(rs)的结构信息，比如字段数、字段名等   
        int columnCount = md.getColumnCount(); //返回此 ResultSet 对象中的列数   
        List list = new ArrayList();   
        Map rowData = new HashMap();   
        while (rs.next()) {   
         rowData = new HashMap(columnCount);   
         for (int i = 1; i <= columnCount; i++) {   
                 rowData.put(md.getColumnName(i), rs.getObject(i));   
         }   
         list.add(rowData);   
         System.out.println("list:" + list.toString());   
        }   
        return list;   
}  
}
