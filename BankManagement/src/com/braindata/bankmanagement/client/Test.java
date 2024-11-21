package com.braindata.bankmanagement.client;
import java.sql.SQLException;
import java.util.Scanner;

import com.braindata.bankmanagement.service.*;
import com.braindata.bankmanagement.serviceImpl.*;
public class Test {
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Scanner sc = new Scanner(System.in);
		Rbi bank = new Sbi();
		boolean flag = true;
		while(flag) {
			System.out.println("==========Welcome to SBI Bank===========");
			System.out.println("Enter 1 for Create Account");
			System.out.println("Enter 2 for Display All Details");
			System.out.println("enter 3 for Deposite Money ");
			System.out.println("Enter 4 for Withdrawal Money");
			System.out.println("Enter 5 for Balance Check");
			System.out.println("Enter 6 for Exit");
			System.out.println("=========================================");
			
			System.out.println("Enter your Choice Number : ");
			int ch = sc.nextInt();
			switch(ch) {
			case 1:
				bank.createAccount();
				break;
			case 2:
				bank.displayAllDetails();
				break;
			case 3:
				bank.depositeMoney();
				break;
			case 4:
				bank.withdrawal();
				break;
			case 5:
				bank.balanceCheck();
				break;
			case 6 : 
				flag = false;
				System.out.println("Thanks for visiting.....");
				break;
			default :
				System.out.println("Wrong Input Provided....");
				break;	
			}
		}	
	}
}
