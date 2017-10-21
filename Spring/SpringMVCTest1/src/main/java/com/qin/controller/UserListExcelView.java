package com.qin.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;

import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.view.document.AbstractExcelView;
import org.springframework.web.servlet.view.document.AbstractXlsView;

import com.qin.domain.User;

public class UserListExcelView extends AbstractXlsView {

	@Override
	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		//报文头设置 直接显示excel，不是下载
		//response.setCharacterEncoding("utf-8");  
		response.setHeader("Content-Disposition", "inline;fileName= "+ new String("用户列表.xls".getBytes("gbk"),"iso8859-1"));
		//response.setHeader("Content-Disposition", "attachment;fileName="+ new String("用户列表".getBytes(),"iso8859-1"));
		List<User> userList = (List<User>) model.get("userList");
		HSSFSheet sheet = (HSSFSheet) workbook.createSheet("users");
		
		//表头
		HSSFRow header= sheet.createRow(0);
		header.createCell(0).setCellValue("账号");
		header.createCell(1).setCellValue("姓名");
		header.createCell(2).setCellValue("注册日期");
		
		int rowNum = 1;
		for (User user : userList) {
			HSSFRow row = sheet.createRow(rowNum++);
			row.createCell(0).setCellValue(user.getuId());
			row.createCell(1).setCellValue(user.getUserName());
			String registerTime = DateFormatUtils.format(user.getRegisterTime(),"yyyy-MM-dd");
			row.createCell(2).setCellValue(registerTime);
		}
		
		
		
		
	}


}
