package com.oa.util;

import java.io.IOException;
import java.util.Properties;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.lang3.StringUtils;

public class PaginationTag extends TagSupport{
	private static final long serialVersionUID = 1L;

	private String curPage;
	private String totalPage;
	private String pageSize;
	private String language ;
	
	public int doStartTag() throws JspException {
		
	 totalPage=StringUtils.isBlank(totalPage)==false?totalPage:"0";
	 language = StringUtils.isBlank(language)==false?language:"en" ;
	 
	 Properties prop = new Properties();  
	 try {
		prop.load(PaginationTag.class.getClassLoader().getResourceAsStream("message_" + language + ".properties"));
	} catch (IOException e1) {
		e1.printStackTrace();
	}  
	 
	  JspWriter out = pageContext.getOut();
	  if (pageSize == null || pageSize == "") {
		  pageSize = "1";
	  }
	  int pageNumber=0;
	  if (Integer.parseInt(totalPage)%Integer.parseInt(pageSize) == 0 ){ 
		  pageNumber = Integer.parseInt(totalPage)/Integer.parseInt(pageSize);
	  } else{
		  pageNumber = Integer.parseInt(totalPage)/Integer.parseInt(pageSize) + 1; 
	  }
	  if(curPage.equals("")){
		  curPage = "1";
	  }else if (Integer.parseInt(curPage) < 0) {
		  curPage = "1";
	  }
	  try {
		  out.print("<span id='fenye'>");
		  if(pageNumber>1){
		  out.print("<span onclick=page(0) style=\"cursor: hand;cursor:pointer; border: 1px solid #DBDBDB; padding:3px;background-color:#F5F5F4\">&nbsp;" + 
			prop.getProperty("firstpage") +"&nbsp;</span>&nbsp;");
		  if((Integer.parseInt(curPage)-1<0)){
			  out.print("<span style=\"cursor: hand;cursor:pointer; border: 1px solid #DBDBDB; padding:3px;background-color:#F5F5F4\"><< " + prop.getProperty("previouspage") + "</span>&nbsp;");
		  }else{
			  out.print("<span style=\"cursor: hand;cursor:pointer; border: 1px solid #DBDBDB; padding:3px;background-color:#F5F5F4\" " +
				  		"onclick=page("+(Integer.parseInt(curPage) - 1)+")><< " +  prop.getProperty("previouspage") + "</span>&nbsp;&nbsp;");
		  }
		 
		  
		  int start=(Integer.parseInt(curPage))-4;
		  if(start<=0){
			  start = 0;
		  }
		  int end=7;
		  if(Integer.parseInt(curPage)-start==4){
			  start=start+1;
			  end=end+start;
			  if(end>=pageNumber){
				  end=pageNumber;
			  }
		  }
		  if(end>=pageNumber){
			  end=pageNumber;
		  }
		  for (int i = start+1; i <=end; i++) {
			    if(i-1== Integer.parseInt(curPage)){
			    	out.print("<span style=\"cursor: hand;cursor:pointer; color:#fff;background-color:#333333;border: 1px solid #D5D5D5;padding:3px; \" onclick=page("+(i-1)+")>&nbsp;<b>"+i+"</b>&nbsp;</span>&nbsp;&nbsp;");
			    }else{
			    	out.print("<span style=\"cursor: hand;cursor:pointer; border: 1px solid #DBDBDB; padding:3px;background-color:#F5F5F4\" onclick=page("+(i-1)+")>&nbsp;"+i+"&nbsp;</span>&nbsp;&nbsp;");
			    }
		  }
		  if(Integer.parseInt(curPage)>=pageNumber-1){
			  out.print("<span style=\"cursor: hand;cursor:pointer; border: 1px solid #DBDBDB; padding:3px;background-color:#F5F5F4\">" +  prop.getProperty("lastpage") + " >></span>&nbsp;");
		  }else{
			  out.print("<span style=\"cursor: hand;cursor:pointer; border: 1px solid #DBDBDB; padding:3px;background-color:#F5F5F4\" " +
				  		"onclick=page("+(Integer.parseInt(curPage)+1)+")>" +  prop.getProperty("nextpage") + " >></span><span class=f7_1>" +
				  				"</span><span></span><span></span>&nbsp;");
		  }
		  if(Integer.parseInt(curPage)>=pageNumber-1){
			  out.print("<span></span>");
		  }else{
			  out.print("<span onclick=page("+(pageNumber-1)+") style=\"cursor: hand;cursor:pointer; border: 1px solid #DBDBDB; padding:3px;background-color:#F5F5F4\">&nbsp;" + prop.getProperty("lastpage") + "&nbsp;</span>");
		  }
		  out.print("&nbsp;<input maxlength=4 style=\"height:24px;\" id=\"jump\" size=\"1\">&nbsp;");
		  out.print("<span style=\"cursor: hand;cursor:pointer; border: 1px solid #DBDBDB; padding:3px;background-color:#F5F5F4\" " +
			  		"onclick=jumppage()>"+  prop.getProperty("jumppage") + " -></span>&nbsp;");
		  out.print("</span>");
		  out.println("<input type=hidden id='curPage' name=curPage value='0'>");
		  out.println("<p> " + totalPage + " " +  prop.getProperty("totalrecords") + "</p>");
		  out.println("<script type=\"text/javascript\">");
		  out.println("function page(n){");
		  out.println("document.getElementById(\"curPage\").value=n;");
		  out.println("document.getElementById(\"myform\").submit();");
		  out.println("}");
		  out.println("</script>");
		  out.println("<script type=\"text/javascript\">");
		  out.println("function jumppage(){");
		  out.println("var jump = document.getElementById(\"jump\").value ; ");
		  out.println("if(isNaN(jump))");
		  out.println("{");
		  out.println(" alert(\"" +  prop.getProperty("alert1") + "\");");
		  out.println("return false;");
		  out.println("}");
		  out.println("var page = jump - 1;");
		  out.println("if( page <0 || page >=" + Integer.toString(pageNumber) + ")");
		  out.println("{");
		  out.println(" alert(\"" +  prop.getProperty("alert2") +"\");");
		  out.println("return false;");
		  out.println("}");
		  out.println("else{");
		  out.println("document.getElementById(\"curPage\").value= page;");
		  out.println("document.getElementById(\"myform\").submit();");
		  out.println("}");
		  out.println("}");
		  out.println("</script>");
		  }
		  
	  } catch (IOException e) {
		  e.printStackTrace();
	  }
	  return super.doStartTag();
	}

	public void setCurPage(String curPage) {
	  this.curPage = curPage;
	}

	public void setPageSize(String pageSize) {
	  this.pageSize = pageSize;
	}
	public void setTotalPage(String totalPage) {
	  this.totalPage = totalPage;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

}
