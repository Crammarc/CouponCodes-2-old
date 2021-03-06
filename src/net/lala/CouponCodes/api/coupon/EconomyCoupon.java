package net.lala.CouponCodes.api.coupon;

import java.util.HashMap;

public class EconomyCoupon extends Coupon {

	private int money;
	
	public EconomyCoupon(String name, int usetimes, int time, HashMap<String, Boolean> usedplayers, int money) {
		super(name, usetimes, time, usedplayers);
		this.money = money;
	}
	
	public Integer getMoney() {
		return money;
	}
	
	public void setMoney(Integer newmoney) {
		this.money = newmoney;
	}
}
