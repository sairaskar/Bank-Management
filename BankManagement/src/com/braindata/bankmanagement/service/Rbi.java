package com.braindata.bankmanagement.service;

import java.sql.SQLException;
public interface Rbi {
	public void createAccount() throws ClassNotFoundException, SQLException ;
	public void displayAllDetails() throws ClassNotFoundException, SQLException;
	public void depositeMoney() throws ClassNotFoundException, SQLException;
	public void withdrawal() throws ClassNotFoundException, SQLException;
	public void balanceCheck() throws ClassNotFoundException, SQLException;
}
