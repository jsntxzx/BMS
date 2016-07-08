package com.oa.util;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;


public class SCDDH {
	
	/**
	 * 创建当前时间
	 * @return
	 */
	public static String CJDate() 
	{
	    SimpleDateFormat ft=null;
	    Date date=null;
	    Calendar cl= Calendar.getInstance();
	    cl.setTime(new java.util.Date());
	    date=cl.getTime();
	    //格式可以自己根据需要修改
	    ft=new SimpleDateFormat("HHmmss");
	    String dateTime = ft.format(date);
	    return dateTime;
    }
	/**
	 * 创建当前日期
	 * @return
	 */
	public static String CJRiqi() {
	    SimpleDateFormat ft=null;
	    Date date=null;
	    Calendar cl= Calendar.getInstance();
	    cl.setTime(new java.util.Date());
	    date=cl.getTime();
	    //格式可以自己根据需要修改
	    ft=new SimpleDateFormat("yyyy-MM-dd");
	    String dateTime = ft.format(date);
	    return dateTime;
    }
	/**
	 *字符串转十六进制 
	 */
	public static String toHexString(String s) 
	{ 
	String str=""; 
	for (int i=0;i<s.length();i++) 
	{ 
	int ch = (int)s.charAt(i); 
	String s4 = Integer.toHexString(ch); 
	str = str + s4; 
	}
	return str;
	}
	/**
	 * 转换成16进制
	 * @param source
	 * @return
	 */
	public static char ZiFu(int source) {  
	      
	    char code ;  
	     code = (char)source; 
	     return code;
	}  

	/**
	 * 转ASCIIS
	 * @param str
	 * @return
	 */
	public static String ASCIIS(String str){//字符串转换为ASCII码
		String sss="";

	  char[]chars=str.toCharArray(); //把字符串转换为字符数组 
	  for(int i=0;i<chars.length;i++)
	  {//输出结果
		  sss+=(int)chars[i];	        
	  }
	  return sss;
} 
	/**
	 * 左补空格
	 * @param str
	 * @param n
	 * @return
	 */
	public static String ZBKG(String str,int n)
	{
//		 System.out.println( "我收到汉字:"+str);
//		 System.out.println("长度："+str.getBytes().length);
		int len=n-str.getBytes().length;
		if (str.getBytes().length < n)
		{
			  for(int i=0;i<len;i++)
			  {
				  str = " "+str;
			  }  
			 
		//	  System.out.println(str.length());
		}
//		System.out.println( "处理后汉字收到汉字:"+str);
//		 System.out.println("处理后汉字长度："+str.getBytes().length);
		return str;
	}
	/**
	 * utf-8转gbk
	 * @param str
	 * @return
	 */
	public static String UTF8GBK (String str)
	{
		String newStr="";
		   try {
	            byte[] temp=str.getBytes("utf-8");//这里写原编码方式
	            newStr =new String(temp,"utf-8");//这里写转换后的编码方式
	            
		   } catch (UnsupportedEncodingException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
		   return newStr;
	}
	/**
	 * 生成订单号
	 * @return
	 */
	public static String DingDanHao()
	{
		String ss="";
		ss=getStrRandom()+getStrRandom()+getStrRandom();
		ss+=getRiqi();
		ss+=getShiJian();
		
		return ss;
	}
	
	/**
	 * 生成流水号
	 * @return
	 */
	public static String DingDanLSH()
	{
		String ss="";
		ss=getStrRandom()+getStrRandom()+getStrRandom()+getStrRandom();
		ss+=get1ShiJian();
		return ss;
	}
	
	/**
	 * 获取当前日期
	 * @return
	 */
	public static String getSysRiQi() 
	{
	    SimpleDateFormat ft=null;
	    Date date=null;
	    Calendar cl= Calendar.getInstance();
	    cl.setTime(new java.util.Date());
	    date=cl.getTime();
	    //格式可以自己根据需要修改
	    ft=new SimpleDateFormat("yyyy-MM-dd");
	    String dateTime = ft.format(date);
	    return dateTime;
    }
	/**
	 * 获取当前时间
	 * @return
	 */
	public static String getSysDate() 
	{
	    SimpleDateFormat ft=null;
	    Date date=null;
	    Calendar cl= Calendar.getInstance();
	    cl.setTime(new java.util.Date());
	    date=cl.getTime();
	    //格式可以自己根据需要修改
	    ft=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    String dateTime = ft.format(date);
	    return dateTime;
    }
	/**
	 * 获取当前日期
	 * @return
	 */
	static String getRiqi() {
	    SimpleDateFormat ft=null;
	    Date date=null;
	    Calendar cl= Calendar.getInstance();
	    cl.setTime(new java.util.Date());
	    date=cl.getTime();
	    //格式可以自己根据需要修改
	    ft=new SimpleDateFormat("yyyyMMdd");
	    String dateTime = ft.format(date);
	    return dateTime;
    }
	
	/**
	 * 获取当前日期转换成yyyyMMdd
	 * @return
	 */
	public static String getDQRQ(Date dt) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");

		String hehe = dateFormat.format( dt ); 
	    return hehe;
    }
	
	/**
	 * 获取当前时间转换成HHmmss
	 * @return
	 */
	public static String getDQSJ(Date dt) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("HHmmss");

		String hehe = dateFormat.format( dt ); 
	    return hehe;
    }
	/**
	 * 获取当前时间(不包括小时)
	 * @return
	 */
	static String getShiJian() {
	    SimpleDateFormat ft=null;
	    Date date=null;
	    Calendar cl= Calendar.getInstance();
	    cl.setTime(new java.util.Date());
	    date=cl.getTime();
	    //格式可以自己根据需要修改
		ft=new SimpleDateFormat("mmss");
		String dateTime = ft.format(date);
		return dateTime;
	}
	/**
	 * 获取当前时间(包括小时)
	 * @return
	 */
	static String get1ShiJian() {
	    SimpleDateFormat ft=null;
	    Date date=null;
	    Calendar cl= Calendar.getInstance();
	    cl.setTime(new java.util.Date());
	    date=cl.getTime();
	    //格式可以自己根据需要修改
		ft=new SimpleDateFormat("HHmmss");
		String dateTime = ft.format(date);
		return dateTime;
	}
	/**
	 * 产生随机数
	 * @return
	 */
	static String getStrRandom()
	{
		Random random = new Random();
	    int a=random.nextInt(10);
		 return String.valueOf(a);
	}
	}
