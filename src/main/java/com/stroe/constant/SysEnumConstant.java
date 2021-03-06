package com.stroe.constant;

public final class SysEnumConstant {

	/**
	 * 登录类型
	 * @author zengjintao
	 *
	 */
	public enum PlatformType{
		ADMIN(1,"管理后台"),
		WEB(2,"web前端"),
		APP(3,"手机app前端"),
		WAP(4,"手机wap端"),
		WEIXIN(5,"微信端");
		int value;
		String name;
		private PlatformType(int value, String name) {
			this.value = value;
			this.name = name;
		}
		public int getValue() {
			return value;
		}
		public void setValue(int value) {
			this.value = value;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
	}
	
	public enum FlagType{
		YES(1,"是"),
		NO(0,"否");
	    int value;
	    private FlagType(int value, String name) {
			this.value = value;
			this.name = name;
		}
		public int getValue() {
			return value;
		}
		public void setValue(int value) {
			this.value = value;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		String name;
	}
	/**
	 * 短信发送状态
	 * @author zengjintao
	 * @version 1.0
	 * @create_at 2017年4月25日 下午3:22:42
	 */
	public enum SendStatus{
		SUCCESS(1,"成功"),
		FAIL(2,"失败");
		int value;
		String name;
		private SendStatus(int value,String name){
			this.value=value;
			this.name=name;
		}
		public int getValue() {
			return value;
		}
		public void setValue(int value) {
			this.value = value;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
	}
}
