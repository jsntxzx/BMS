package com.oa.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

public class FileUpload {
	/**
	 * 图片和视频上传
	 * @param imgpath 上传路径
	 * @param imageName 上传的文件名
	 * @param request 
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static String picvideoupload(String imgpath,String imageName,HttpServletRequest request){
		String imgFileName = "";
		MultipartHttpServletRequest filerequest = (MultipartHttpServletRequest)request;
		String uploadImgPath = request.getRealPath(imgpath);
		File imgfile = new File(uploadImgPath);
		if(!imgfile.isFile()){
			imgfile.mkdirs();
		}
		MultipartFile upimgfile = (MultipartFile)filerequest.getFile(imageName);
        if(upimgfile!=null && !upimgfile.isEmpty()) {
        	imgFileName = upimgfile.getOriginalFilename();
        	
        	Random rd = new Random();
        	
        	imgFileName = DateUtil.format(new Date(), "yyyyMMddHHmmss")+rd.nextInt(10000) + imgFileName.substring(imgFileName.lastIndexOf("."),imgFileName.length());
        	File uploadFile = new File(uploadImgPath +"/"+ imgFileName);
        	try {
				OutputStream os = new FileOutputStream(uploadFile);
				InputStream is = upimgfile.getInputStream();
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
}
