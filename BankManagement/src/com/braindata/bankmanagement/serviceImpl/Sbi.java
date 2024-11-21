package com.braindata.bankmanagement.serviceImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import com.braindata.bankmanagement.service.*;
import com.braindata.bankmanagement.configue.DBConfigue;
import com.braindata.bankmanagement.model.*;

public class Sbi implements Rbi {
	Account ac;
	Scanner sc = new Scanner(System.in);

	@Override
	public void createAccount() throws ClassNotFoundException, SQLException {
		ac = new Account();

		Connection con = DBConfigue.getConnection();

		System.out.println("Account number: ");
		int accno = sc.nextInt();
		ac.setAccNo(accno);

		System.out.println("Coustomer name : ");
		String cName = sc.next();
		ac.setName(cName);

		System.out.println("Mobile Number : ");
		String mob = sc.next();
		ac.setMobNo(mob);

		System.out.println("Adhar Number : ");
		String adhar = sc.next();
		ac.setAdharNo(adhar);

		System.out.println("Gender : ");
		String genderC = sc.next();
		ac.setGender(genderC);

		System.out.println("age : ");
		int ageC = sc.nextInt();
		ac.setAge(ageC);

		System.out.println("Balance : ");
		double balance = sc.nextDouble();
		ac.setBalance(balance);

		String sql1 = "insert into Account values(?,?,?,?,?,?,?)";

		PreparedStatement ps = con.prepareStatement(sql1);

		ps.setInt(1, ac.getAccNo());
		ps.setString(2, ac.getName());
		ps.setString(3, ac.getMobNo());
		ps.setString(4, ac.getAdharNo());
		ps.setString(5, ac.getGender());
		ps.setInt(6, ac.getAge());
		ps.setDouble(7, ac.getBalance());

		ps.execute();
		con.close();
	}

	@Override
	public void displayAllDetails() throws ClassNotFoundException, SQLException {

		Connection con = DBConfigue.getConnection();
		System.out.println("Enter Account No ");
		int acno = sc.nextInt();

		String sql = "select * from Account where Account_Number=?";

		PreparedStatement ps = con.prepareStatement(sql);

		ps.setInt(1, acno);

		ResultSet rs = ps.executeQuery();

		if (rs.next()) {
			System.out.println("Account No : " + rs.getInt(1));
			System.out.println("Account Name : " + rs.getString(2));
			System.out.println("Accout Mobile No : " + rs.getLong(3));
			System.out.println("Account Adhar No : " + rs.getLong(4));
			System.out.println("Account Holder Gender : " + rs.getString(5));
			System.out.println("Account Holder Age : " + rs.getInt(6));
			System.out.println("Account Holder Balance : " + rs.getDouble(7));
		}
	}

	@Override
	public void depositeMoney() throws ClassNotFoundException, SQLException {

		System.out.println("Enter Account No To Deposite Money : ");
		int acno = sc.nextInt();

		System.out.println("Enter Amount to Deposite");
		double u_amount = sc.nextDouble();

		String sql = "select Balance from account where Account_Number = ?";

		Connection con = DBConfigue.getConnection();

		PreparedStatement ps = con.prepareStatement(sql);

		ps.setInt(1, acno);

		ResultSet rs = ps.executeQuery();

		if (rs.next()) {
			double available_balance = rs.getDouble("Balance");

			double total_Balance = available_balance + u_amount;

			String sql1 = "update account set Balance = ? Where Account_Number=? ";
			PreparedStatement ps1 = con.prepareStatement(sql1);
			ps1.setDouble(1, total_Balance);
			ps1.setInt(2, acno);
			ps1.executeUpdate();

			System.out.println("Amount Deposite SucessFully");

		}
	}

	@Override
	public void withdrawal() throws ClassNotFoundException, SQLException {

		System.out.println("Enter Account No To Withdrawal Money : ");
		int acno = sc.nextInt();

		System.out.println("Enter Amount to Withdrawal");
		double u_amount = sc.nextDouble();

		String sql = "select Balance from account where Account_Number =?";

		Connection con = DBConfigue.getConnection();

		PreparedStatement ps = con.prepareStatement(sql);
		ps.setLong(1, acno);

		ResultSet rs = ps.executeQuery();

		if (rs.next()) {

			double available_balance = rs.getDouble("Balance");
			if (u_amount < available_balance) {
				double total_Balance = available_balance - u_amount;

				String sql1 = "update account set Balance = ? Where Account_Number= ?";
				PreparedStatement ps1 = con.prepareStatement(sql1);
				ps1.setDouble(1, total_Balance);
				ps1.setInt(2, acno);
				ps1.executeUpdate();
				System.out.println("Amount Withdrawal Sucessfully");
			} else {
				System.out.println("Insufficent Balance");
			}
		}
	}

	@Override
	public void balanceCheck() throws ClassNotFoundException, SQLException {
		System.out.println("Enter account No. to Check Balance : ");
		int acno = sc.nextInt();

		String sql = "select Balance from account where Account_Number = " + acno + "";

		Connection con = DBConfigue.getConnection();

		Statement st = con.createStatement();

		ResultSet rs = st.executeQuery(sql);

		if (rs.next()) {
			System.out.println("Your Account Balance is : " + rs.getDouble("Balance"));
		} else {
			System.out.println("First Create Account");
		}
	}
}
