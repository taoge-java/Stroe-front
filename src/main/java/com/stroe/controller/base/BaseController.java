package com.stroe.controller.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import com.jfinal.core.Controller;
import com.jfinal.upload.UploadFile;
import com.stroe.config.StroeConfig;
import com.stroe.constant.Constant;
import com.stroe.dto.UserSession;
import com.stroe.model.system.SystemLog;
import com.stroe.util.DateUtil;
import com.stroe.util.IpUitls;
import com.stroe.util.NumberUtil;


/**
 * 控制器父类
 * @author zengjintao
 * 2017年2月23号  下午21:06
 */
public class BaseController extends Controller{

	public  int pageSize=20;
	
	public  void RenderView(String viewPath){
		render(StroeConfig.BASE_VIEW+viewPath);
	}
	
	public void systemLog(String oper_des,int type){
		UserSession user=getCurrentUser();
		SystemLog systemLog=new SystemLog();
		systemLog.set("oper_user", user.getUserName());
		systemLog.set("user_id", user.getUserId());
		systemLog.set("oper_time", new DateUtil().getDate());
		systemLog.set("oper_ip", IpUitls.getAddressIp(getRequest()));
		systemLog.set("login_type",type);
		systemLog.set("oper_desc",oper_des);
		systemLog.save();
	}

	public UserSession getCurrentUser() {
		return getSessionAttr(Constant.SESSION_ID);
	}
	/**
	 * 文件上传重命名
	 * @param upload
	 * @return
	 */
	public String UploadRename(UploadFile upload){
		File file=upload.getFile();
		FileInputStream in=null;
		String oldName=upload.getFileName();//得到文件名称
		String style=oldName.substring(oldName.lastIndexOf("."),oldName.length()-1);//得到图片格式
		String  name=NumberUtil.getNumberCode(4);
		String newName=name+style;//得到文件新名称
		String basePath=NumberUtil.getImagePath();//文件存放路径
		String filePath=StroeConfig.UploadPath+"/"+basePath;
		try {
			in = new FileInputStream(file);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		File fi=new File(filePath);
		if(!fi.exists()){
			fi.mkdirs();
		}
		File upFile=new File(filePath,newName);
		FileOutputStream out=null;
		try {
			out=new FileOutputStream(upFile);
			byte[] bytes=new byte[1024];
			int flag=0;
			if((flag=in.read())!=-1){
				out.write(bytes, 0, flag);
			}
			file.delete();
			return basePath+"/"+newName;
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(out!=null){
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(in!=null){
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}
}
