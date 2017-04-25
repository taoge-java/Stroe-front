package com.stroe.util;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class DoubleUtil {

	public static final MathContext DEFAULT_MATH=new MathContext(8, RoundingMode.HALF_UP);
	/**
	 * 连加
	 * @param d1
	 * @param d2
	 * @return
	 */
	public static double add(double d1,double...d2){
		BigDecimal b1=new BigDecimal(d1);
		for(double d:d2){
			BigDecimal b2=new BigDecimal(d);
			b1.add(b2);
		}
		return b1.doubleValue();
	}
	/**
	 * 连减
	 * @param d1
	 * @param d2
	 * @return
	 */
    public static double subtract(double d1,double...d2){
    	BigDecimal b1=new BigDecimal(d1);
		for(double d:d2){
			BigDecimal b2=new BigDecimal(d);
			b1.subtract(b2);
		}
		return b1.doubleValue();
    }
    /**
     * 连乘
     * @param d1
     * @param d2
     * @return
     */
    public static double multiply(double d1,double...d2){
    	BigDecimal b1=new BigDecimal(d1);
		for(double d:d2){
			BigDecimal b2=new BigDecimal(d);
			b1.multiply(b2);
		}
		return b1.doubleValue();
    }
    /**
     * 连除
     * @param d1
     * @param d2
     * @return
     */
    public static double divide(double d1,double...d2){
    	return divide(DEFAULT_MATH,d1,d2);
    }
    /**
     * 连除 指定精度和位数
     * @param math
     * @param d1
     * @param d2
     * @return
     */
    public static  double divide(MathContext math,double d1,double...d2){
    	BigDecimal b1=new BigDecimal(d1);
		for(double d:d2){
			BigDecimal b2=new BigDecimal(d);
			b1.divide(b2, math.getPrecision(), math.getRoundingMode());
		}
		return b1.doubleValue();
    }
    /**
     * 四舍五入
     * @param value
     * @param num
     * @return
     */
    public static double round(double value,int num){
    	if(num<0)
    		throw new RuntimeException("The scale must be a positive integer or zero");
    	BigDecimal b1=new BigDecimal(value);
    	BigDecimal b2=new BigDecimal(1);
    	return b1.divide(b2,num, BigDecimal.ROUND_HALF_UP).doubleValue();
    }
    
    /**
	 * 提供精确的小数位四舍五入处理。
	 * 小数点后保留2位
	 * @return 四舍五入后的结果
	 */
	public static double roundCurrency(double v) {
		BigDecimal b = new BigDecimal(Double.toString(v));
		BigDecimal one = new BigDecimal("1");
		return b.divide(one, 2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
}
