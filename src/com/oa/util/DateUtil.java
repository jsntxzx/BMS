package com.oa.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

public class DateUtil {
	
	/**
	 * 英文简写（默认）如：2010-12-01
	 */
	public static String FORMAT_SHORT = "yyyy-MM-dd";
	
	/**
	 * 英文全称  如：2010-12-01 23:15:06
	 */
	public static String FORMAT_LONG = "yyyy-MM-dd HH:mm:ss";
	
	/**
	 * 精确到毫秒的完整时间    如：yyyy-MM-dd HH:mm:ss.S
	 */
	public static String FORMAT_FULL = "yyyy-MM-dd HH:mm:ss.S";
	
	/**
	 * 英文全称  如：20130728111506
	 */
	public static String FORMAT_EASY = "yyyyMMddHHmmss";
	public static String FORMAT_EASY_E = "yyMMdd";
	/**
	 * 汉字加年月
	 */
	public static String FORMAT_CHINA = "yyyy年MM月dd日";
	/**
	 * 获得默认的 date pattern
	 */
	public static String getDatePattern() {
		return FORMAT_LONG;
	}

	/**
	 * 根据预设格式返回当前日期
	 * @return
	 */
	public static String getNow() {
		return format(new Date());
	}
	
	/**
	 * 根据用户格式返回当前日期
	 * @param format
	 * @return
	 */
	public static String getNow(String format) {
		return format(new Date(), format);
	}

	
	/**
	 * 使用预设格式格式化日期
	 * @param date
	 * @return
	 */
	public static String format(Date date) {
		return format(date, getDatePattern());
	}

	/**
	 * 使用用户格式格式化日期
	 * @param date 日期
	 * @param pattern 日期格式
	 * @return
	 */
	public static String format(Date date, String pattern) {
		String returnValue = "";
		if (date != null) {
			SimpleDateFormat df = new SimpleDateFormat(pattern);
			returnValue = df.format(date);
		}
		return (returnValue);
	}

	/**
	 * 使用预设格式提取字符串日期
	 * @param strDate 日期字符串
	 * @return
	 */
	public static Date parse(String strDate) {
		return parse(strDate, getDatePattern());
	}

	/**
	 * 使用用户格式提取字符串日期
	 * @param strDate 日期字符串
	 * @param pattern 日期格式
	 * @return
	 */
	public static Date parse(String strDate, String pattern) {
		SimpleDateFormat df = new SimpleDateFormat(pattern);
		try {
			return df.parse(strDate);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

    /**
     * 计算当月的天数
     * @return
     */
    public static int getCurrentMonthLastDay(Date date)
    {
        Calendar a = Calendar.getInstance();
        a.setTime(date);
        a.set(Calendar.DATE, 1);//把日期设置为当月第一天
        a.roll(Calendar.DATE, -1);//日期回滚一天，也就是最后一天
        int maxDate = a.get(Calendar.DATE);
        return maxDate;
    }
	/**
	 * 在日期上增加数个整月
	 * @param date 日期
	 * @param n 要增加的月数
	 * @return
	 */
	public static Date addMonth(Date date, int n) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MONTH, n);
		return cal.getTime();
	}

	/**
	 * 在日期上增加天数
	 * @param date 日期
	 * @param n 要增加的天数
	 * @return
	 */
	public static Date addDay(Date date, int n) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, n);
		return cal.getTime();
	}

	/**
	 * 获取时间戳
	 */
	public static String getTimeString() {
		SimpleDateFormat df = new SimpleDateFormat(FORMAT_FULL);
		Calendar calendar = Calendar.getInstance();
		return df.format(calendar.getTime());
	}

	/**
	 * 获取日期年份
	 * @param date 日期
	 * @return
	 */
	public static String getYear(Date date) {
		return format(date).substring(0, 4);
	}
	
	/**
	 * 按默认格式的字符串距离今天的天数
	 * @param date 日期字符串
	 * @return
	 */
	public static int countDays (String date) {
		long t = Calendar.getInstance().getTime().getTime();
		Calendar c = Calendar.getInstance();
		c.setTime(parse(date));
		long t1 = c.getTime().getTime();
		return (int)(t/1000 - t1/1000)/3600/24;
	}
	/**
	 * 按默认格式的字符串距离今天的差几个月
	 * @param date
	 * @return
	 */
	public static int countMonth (String date) {
		//把当前日期换成当月的一号
		Calendar t = Calendar.getInstance();
        t.set(Calendar.DATE,1);
        t.set(Calendar.HOUR_OF_DAY,0);
        t.set(Calendar.MINUTE,0);
        t.set(Calendar.SECOND,0);
        long t2=t.getTime().getTime(); 
        
		Calendar c = Calendar.getInstance();
		c.setTime(parse(date));
		c.set(Calendar.DATE,1);
	    c.set(Calendar.HOUR_OF_DAY,0);
	    c.set(Calendar.MINUTE,0);
	    c.set(Calendar.SECOND,0);
		long t1 = c.getTime().getTime();
		return (int)(t2/1000 - t1/1000)/3600/24/30;
	}
	/**
	 * 按用户格式字符串距离今天的天数
	 * @param date 日期字符串
	 * @param format 日期格式
	 * @return
	 */
	public static int countDays (String date, String format) {
		long t = Calendar.getInstance().getTime().getTime();
		Calendar c = Calendar.getInstance();
		c.setTime(parse(date, format));
		long t1 = c.getTime().getTime();
		return (int)(t/1000 - t1/1000)/3600/24;
	}
	/**
	 * 计算小时差
	 * @param date1
	 * @param date2
	 * @return
	 * @throws Exception
	 */
	public static double jisuan(String date1, String date2) throws Exception {
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm");
		java.util.Date start = sdf.parse(date1);
		java.util.Date end = sdf.parse(date2);
		long cha = end.getTime() - start.getTime();
		double result = cha * 1.0 / (1000 * 60 * 60);
		
		return result;
	}
	/**
	 * 日期向后，前推n年，n月，n天,n时，n分，n秒
	 * @param date : 需要处理的日期
	 * @param num : 处理的数量,+为向后退，-为向前推
	 * @param type ： 类型：年-Y,月-M,日-D,时-H,分-MIN,秒-S
	 * @return
	 */
	public static Date addDate(Date date,int num,String type)  {
		try {
			Calendar calendar = new GregorianCalendar(); 
			calendar.setTime(date); 
			if(type.equals("Y")) {
				calendar.add(Calendar.YEAR,num);//把日期往后增加n年.整数往后推,负数往前移动 
			}
			else if(type.equals("M")) {
				calendar.add(Calendar.MONTH,num);//把日期往后增加n月.整数往后推,负数往前移动 
			}
			else if(type.equals("D")) {
				calendar.add(Calendar.DATE,num);//把日期往后增加n天.整数往后推,负数往前移动 
			}
			else if(type.equals("H")) {
				calendar.add(Calendar.HOUR,num);//把日期往后增加n小时.整数往后推,负数往前移动 
			}
			else if(type.equals("MIN")) {
				calendar.add(Calendar.MINUTE,num);//把日期往后增加n分.整数往后推,负数往前移动 
			}
			else if(type.equals("S")) {
				calendar.add(Calendar.SECOND,num);//把日期往后增加n秒.整数往后推,负数往前移动 
			}
			
			date = calendar.getTime();   //这个时间就是日期往后推一天的结果 
		} catch (Exception e) {
			e.printStackTrace();
		}
		return date;
	}
	
	

	public static Date toDate(String d) throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if(d != null){
			Date date = sdf.parse(d);
			return date;
		}
		return null;
	}
	
	public static Date toMonth(String d) throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		if(d != null){
			Date date = sdf.parse(d);
			return date;
		}
		return null;
	}
	
	public static Date toDateTime(String d) throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if(d != null){
			Date date = sdf.parse(d);
			return date;
		}
		return null;
	}
	
	public static Date toDateHour(String d) throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH");
		if(d != null){
			Date date = sdf.parse(d);
			return date;
		}
		return null;
	}
	
	public static Date toDateMin(String d) throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		if(d != null){
			Date date = sdf.parse(d);
			return date;
		}
		return null;
	}
	
	/**
	 * 获取月份
	 * @author 孙东泉
	 * 2014-2-10 上午10:28:46
	 */
	public static Date getMonth(String yearMonth){
		String year = yearMonth.substring(0, 3);
		String month = yearMonth.substring(5, 6);
		Calendar calendar = Calendar.getInstance();
		calendar.set(Integer.parseInt(year), Calendar.YEAR);
		calendar.set(Integer.parseInt(month), Calendar.MONTH);
		return calendar.getTime();
	}
	
	/**
	 * 获取本周的周一和周日
	 */
	public static Map getWeekDay() {
		Map<String, String> map = new HashMap<String, String>();
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY); // 获取本周一的日期
		map.put("mon", df.format(cal.getTime()));
		// System.out.println("********得到本周一的日期*******" + df.format(cal.getTime()));
		// 这种输出的是上个星期周日的日期，因为老外那边把周日当成第一天
		cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		// 增加一个星期，才是我们中国人理解的本周日的日期
		cal.add(Calendar.WEEK_OF_YEAR, 1);
		map.put("sun", df.format(cal.getTime()));
		// System.out.println("********得到本周天的日期*******" + df.format(cal.getTime()));
		return map;
	}

	/**
	 * 获取两个日期相差天数
	 * @param begin
	 * @param end
	 * @return
	 */
	public static int getDaysBetween(String begin,String end) {
		Calendar c1 = Calendar.getInstance();
		c1.setTime(parse(begin,FORMAT_SHORT));
		
		Calendar c2 = Calendar.getInstance();
		c2.setTime(parse(end,FORMAT_SHORT));
		
		long t1 = c1.getTime().getTime();
		long t2 = c2.getTime().getTime();
		return (int)(t1/1000 - t2/1000)/3600/24;
	}
	/**
	 * 图片和视频上传
	 * @param imgpath 上传的文件名
	 * @param request 
	 * @return
	 */
	public static String picvideoupload(String imgpath,HttpServletRequest request){
		String imgFileName = "";
		MultipartHttpServletRequest filerequest = (MultipartHttpServletRequest)request;
		String uploadImgPath = request.getRealPath(imgpath);
		File imgfile = new File(uploadImgPath);
		if(!imgfile.isFile()){
			imgfile.mkdirs();
		}
		MultipartFile upimgfile = (MultipartFile)filerequest.getFile("videoImgFile");
        if(upimgfile!=null && !upimgfile.isEmpty()) {
        	imgFileName = upimgfile.getOriginalFilename();
        	
        	Random rd = new Random();
        	imgFileName = rd.nextInt(100000000) + imgFileName.substring(imgFileName.lastIndexOf("."),imgFileName.length());
        	File uploadFile = new File(uploadImgPath +"/"+ imgFileName);
        	try {
				OutputStream os = new FileOutputStream(uploadFile);
				FileInputStream is = (FileInputStream)upimgfile.getInputStream();
				byte[] bytes = new byte[1024];
				int c;
				while ((c = is.read(bytes)) != -1) {
					os.write(bytes, 0, c);
				}
				System.out.println("img upload success");
				is.close();
				os.close();
			} catch (Exception ex) {
				System.out.println("img not upload");
				System.out.println(ex.getMessage());
			}
        }
        return imgFileName;
	}
	
	
	public static Date randomDate(Date start, Date end ) {
            if (start.getTime() >= end.getTime()) {
                return null;
            }
            long date = random(start.getTime(), end.getTime()); 
            return new Date(date);
        
    }
 
	public static long random(long begin, long end) {
        long rtnn = begin + (long) (Math.random() * (end - begin));
        if (rtnn == begin || rtnn == end) {
            return random(begin, end);
        }
        return rtnn;
    }

}
