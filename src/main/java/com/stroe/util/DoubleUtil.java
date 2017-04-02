package com.stroe.util;

import java.math.BigDecimal;

public class DoubleUtil {

	public static double add(double d1,double...d2){
		double sum=d1;
		BigDecimal bigdecimal=new BigDecimal(sum);
		for(int i=0;i<d2.length;i++){
			BigDecimal b1=new BigDecimal(d2[i]);
			bigdecimal=bigdecimal.add(b1);
		}
		return bigdecimal.doubleValue();
	}
	
}
