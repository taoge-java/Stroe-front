package com.stroe.config;

import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.ext.handler.ContextPathHandler;
import com.jfinal.ext.plugin.tablebind.AutoTableBindPlugin;
import com.jfinal.ext.route.AutoBindRoutes;
import com.jfinal.kit.PropKit;
import com.jfinal.log.Logger;
import com.jfinal.plugin.druid.DruidPlugin;
import com.jfinal.plugin.ehcache.EhCachePlugin;
import com.jfinal.plugin.redis.RedisPlugin;
import com.jfinal.render.ViewType;
import com.stroe.interceptor.ViewContextInterceptor;
import com.stroe.model.BaseModel;

public class StroeConfig extends JFinalConfig{

	private Logger log=Logger.getLogger(StroeConfig.class);
	
	public static final String BASE_VIEW="/WEB-INF/views";
	
	public static  String redisHost;
	public static String UploadPath;
	public static String smsUsername;
	public static String smsPassword;
	public static String UploadDown;
	public static String redisPassword;
	public static String smsUrl; 
	public static String ApiKey;
	public static String email_password;
	@Override
	public void configConstant(Constants constants) {
		constants.setBaseViewPath(BASE_VIEW);
		constants.setViewType(ViewType.VELOCITY);
		constants.setDevMode(true);
		PropKit.use("config.properties");//加载config配置文件
		redisHost=PropKit.get("db.redis.host");
		redisPassword=PropKit.get("db.redis.password");
		UploadPath=PropKit.get("resource.upload.path");
		UploadDown=PropKit.get("resource.upload.domain");
		smsUsername=PropKit.get("sms.user");
		smsPassword=PropKit.get("sms.password");
		smsUrl=PropKit.get("url");
		email_password=PropKit.get("email_password");
		ApiKey=PropKit.get("sms.apikey");
		constants.setUploadedFileSaveDirectory(UploadPath);//设置文件上传路径
		constants.setError404View(BASE_VIEW+"/error/404.vm");
		constants.setError500View(BASE_VIEW+"/error/500.vm");
	}

	
	@Override
	public void configHandler(Handlers handlers) {
		handlers.add(new ContextPathHandler("BASE_PATH"));
	}

	@Override
	public void configInterceptor(Interceptors interceptors) {
		interceptors.add(new ViewContextInterceptor());
	}

	@SuppressWarnings("unchecked")
	@Override
	public void configPlugin(Plugins plugins) {
		/**配置cp3p0数据库连接池**/
		DruidPlugin druid = new DruidPlugin(PropKit.get("jdbcUrl"), PropKit.get("user"), PropKit.get("password"));
		plugins.add(druid);
		//配置数据表自动路由映射
		AutoTableBindPlugin table=new AutoTableBindPlugin(druid);
		table.autoScan(true);
		table.addExcludeClasses(BaseModel.class);
		plugins.add(table);
		//配置redis插件
		RedisPlugin redis=new RedisPlugin("stroe", redisHost,6379,redisPassword);
		plugins.add(redis);
		//配置缓存插件
		plugins.add(new EhCachePlugin());
	}

	@Override
	public void configRoute(Routes routes) {
		routes.add(new AutoBindRoutes());
	}
}

//	@Override
//	public void afterJFinalStart() {
//       new Thread(new Runnable() {
//			@Override
//			public void run() {
//				log.info("系统数据初始化完成。。。。。。");
//			}
//		}).start();
//	}
