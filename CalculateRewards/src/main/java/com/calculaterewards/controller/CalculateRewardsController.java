package com.calculaterewards.controller;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.calculaterewards.util.RewardsUtil;
import com.calculaterewards.vo.CustRewardVO;

@RestController
public class CalculateRewardsController {

	@GetMapping(value = "/getThreeMonthsRewards")
	public static void getThreeMonthsRewards() {

		String file = "rewards.txt"; // Input format::: Name, Transaction Amount, YYYY-MM-DD
		RewardsUtil rewardsUtil = new RewardsUtil();
		HashMap<String, CustRewardVO> customerRewards = new HashMap<>();
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {

			String line;
			while ((line = br.readLine()) != null) {
				String[] tokens = line.split(",");
				String custName = tokens[0];
				int saleAmount = Integer.parseInt(tokens[1]);
				int reward = rewardsUtil.getRewards(saleAmount);
				String dates[] = tokens[2].split("-");
				int year = Integer.parseInt(dates[0]);
				int month = Integer.parseInt(dates[1]);
				int date = Integer.parseInt(dates[2]);
				if (rewardsUtil.checkLast3Months(year, month, date)) {
					if (customerRewards.containsKey(custName)) {
						customerRewards.get(custName).addReward(month, reward);
					} else {
						CustRewardVO custRewardVO = new CustRewardVO(custName, new int[13]);
						custRewardVO.addReward(month, reward);
						customerRewards.put(custName, custRewardVO);
					}
				}

			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		customerRewards.forEach(
				(key, value) -> System.out.println("Customer Name: " + key + "\nReward Points:" + value + "\n"));
	}
	
	@GetMapping(value = "/getRewardsCalcualtion")
	public static void getRewardsCalcualtion() {

		String file = "rewards2.txt"; // Input format::: Name, Transaction Amount, YYYY-MM-DD
		RewardsUtil rewardsUtil = new RewardsUtil();
		HashMap<String, CustRewardVO> customerRewards = new HashMap<>();
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {

			String line;
			while ((line = br.readLine()) != null) {
				String[] tokens = line.split(",");
				String custName = tokens[0];
				int saleAmount = Integer.parseInt(tokens[1]);
				int reward = rewardsUtil.getRewards(saleAmount);
				String dates[] = tokens[2].split("-");
				int month = Integer.parseInt(dates[1]);
					if (customerRewards.containsKey(custName)) {
						customerRewards.get(custName).addReward(month, reward);
					} else {
						CustRewardVO custRewardVO = new CustRewardVO(custName, new int[13]);
						custRewardVO.addReward(month, reward);
						customerRewards.put(custName, custRewardVO);
					}
				}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		customerRewards.forEach(
				(key, value) -> System.out.println("Customer Name: " + key + "\nReward Points:" + value + "\n"));
	}

}
