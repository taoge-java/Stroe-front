package com.stroe.controller.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import sun.reflect.ReflectionFactory.GetReflectionFactoryAction;

import com.jfinal.core.Controller;
import com.jfinal.upload.UploadFile;
import com.stroe.config.StroeConfig;
import com.stroe.constant.Constant;
import com.stroe.constant.SysEnumConstant.PlatformType;
import com.stroe.dto.UserSession;
import com.stroe.model.system.SystemLog;
import com.stroe.model.user.UserInfo;
import com.stroe.util.DateUtil;
import com.stroe.util.IpUitls;
import com.stroe.util.NumberUtil;
import com.stroe.util.Result;
import com.stroe.util.ResultCode;


/**
 * 控制器父类
 * @author zengjintao
 * 2017年2月23号  下午21:06
 */
public class BaseController extends Controller{

	public  int pageSize=20;
	/**
	 * 页面跳转
	 * @param viewPath
	 */
	public  void RenderView(String viewPath){
		render(StroeConfig.BASE_VIEW+viewPath);
	}
	
	/**
	 * 保存系统操作日志
	 * @param oper_des
	 * @param type
	 */
	public void systemLog(String oper_des,int type){
		UserSession user=getCurrentUser();
		SystemLog systemLog=new SystemLog();
		systemLog.set("oper_name", user.getUserName());
		systemLog.set("user_id", user.getUserId());
		systemLog.set("oper_time", new DateUtil().getDate());
		systemLog.set("oper_ip", IpUitls.getAddressIp(getRequest()));
		systemLog.set("login_type",type);
		systemLog.set("oper_desc",oper_des);
		systemLog.save();
	}

	/**
	 * 获取用户session信息
	 * @return
	 */
	public UserSession getCurrentUser() {
		return getSessionAttr(Constant.SESSION_ID);
	}
	
	/**
	 * 登录成功
	 * @param userInfo
	 * @param res
	 */
	public void loginSuccess(UserInfo userInfo,HttpServletRequest request) {
		userInfo.set("ip", IpUitls.getAddressIp(request));
		userInfo.update();
		UserSession session=new UserSession();
		session.setFlag(userInfo.getBoolean("disabled_flag")?true:false);
		session.setMobile(userInfo.getStr("mobile"));
		session.setUserId(userInfo.getInt("id"));
		session.setUserName(userInfo.getStr("login_name"));
		request.getSession().setAttribute(Constant.SESSION_ID, session);
		systemLog(session.getUserId()+"登录",PlatformType.WEB.getValue());
	}
    public static void main(String[] args) {
		new BaseController().test();
	}
    public void test(){
    	System.out.println(IpUitls.getAddressIp(getRequest()));
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
