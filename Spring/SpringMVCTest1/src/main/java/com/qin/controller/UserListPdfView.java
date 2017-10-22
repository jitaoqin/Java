package com.qin.controller;

import java.awt.Color;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfWriter;
import com.qin.domain.User;

public class UserListPdfView extends AbstractPdfView{

	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		response.setHeader("Content-type","inline;fileName = "+ new String("用户列表.pdf".getBytes("gbk") + "iso8859-1"));
		List<User> userList = (List<User>) model.get("userList");
		
		Table table = new Table(3);
		table.setWidth(80);
		table.setBorder(1);
		table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		table.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);
		BaseFont cnBaseFont = BaseFont.createFont("STSongStd", "UniGB-UCS2-H", false);
		Font cnFont = new Font(cnBaseFont,10,Font.NORMAL,Color.BLUE);
		
		table.addCell(buildFontCell("账号",cnFont));
		table.addCell(buildFontCell("姓名",cnFont));
		table.addCell(buildFontCell("注册日期",cnFont));
		
		for(User user : userList){
			table.addCell(user.getuId());
			table.addCell(buildFontCell(user.getUserName(),cnFont));
			String registerTime = DateFormatUtils.format(user.getRegisterTime(),"yyyy-MM-dd");
			table.addCell(registerTime);			
		}
		
		document.add(table);
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	private Cell buildFontCell(String content, Font font) throws BadElementException{
		Phrase phrase = new Phrase(content,font);
		return  new Cell(phrase);
	}

}
