package com.calculaterewards.vo;

public class CustRewardVO {

	String name;
	int rewards[];
	
	public CustRewardVO(String name, int rewards[]) {
		this.name = name;
		this.rewards = rewards;
	}
	
	public void addReward(int month, int reward) {
		rewards[month] = reward + rewards[month];
	}
	
	@Override
	public String toString() {
		String s = "\n";
		for(int i=1; i<=12; i++) {
			s += "Month"+ i + ": Rewards " + rewards[i] + "\n";
		}
		return s;
	}
}
	