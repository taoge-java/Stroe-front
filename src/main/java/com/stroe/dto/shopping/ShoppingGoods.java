package com.stroe.dto.shopping;

public class ShoppingGoods {

	private int count;
	
	private float money;

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public ShoppingGoods(int count, float money) {
		super();
		this.count = count;
		this.money = money;
	}

	public ShoppingGoods() {
		
	}

	public float getMoney() {
		return money;
	}

	public void setMoney(float money) {
		this.money = money;
	}
}
