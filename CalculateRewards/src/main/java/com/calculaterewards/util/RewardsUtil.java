package com.calculaterewards.util;

import java.time.LocalDate;

public class RewardsUtil {
	
	//returns rewards points against transaction amount
	public int getRewards(int txnAmount) {
		if (txnAmount <= 50) {
			return 0;
		}
		if (txnAmount <= 100) {
			return txnAmount - 50;
		}
		return (txnAmount - 100) * 2 + 50;
	}
	
	//returns true if transaction date is within three months
	public boolean checkLast3Months(int year, int month, int date) {

		LocalDate currentDate = LocalDate.now();
		LocalDate currentDateMinus3Months = currentDate.minusMonths(3);
		LocalDate date1 = LocalDate.of(year, month, date);
		if (date1.isBefore(currentDateMinus3Months)) {
			return false;
		} else {
			return true;
		}
	}
}
