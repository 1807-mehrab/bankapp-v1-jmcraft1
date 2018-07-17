package com.revature.dao;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.revature.customers.Account;
import com.revature.customers.Customer;
import com.revature.customers.RegularCustomer;
import com.revature.customers.SaveTheChangeCustomer;
import com.revature.customers.SavingsCustomer;
import com.revature.util.GetConnected;

public class CustomerDao {
	
	String pass = null;
	Account ra;
	public boolean verifyPassword(String email, String password) {
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try (Connection conn = GetConnected.getConnection()) {
			String sql = "SELECT PASSWORD FROM CUSTOMER WHERE EMAIL = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, email);
			
			rs = ps.executeQuery();
			
			while (rs.next()) {
				pass = rs.getString("PASSWORD");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
				
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		md.update(password.getBytes());
		byte[] bytes = md.digest();
		StringBuilder sb = new StringBuilder();
        for(int i=0; i< bytes.length ;i++)
        {
            sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
        }
        String generatedPassword = sb.toString(); 
		if (generatedPassword.equals(pass)) {
			return true;
		}
		return false;
	}
	
	public boolean customerVerification(String email) {
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try (Connection conn = GetConnected.getConnection()) {
			String sql = "SELECT * FROM CUSTOMER WHERE EMAIL = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, email);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				if (email.equals(rs.getString("EMAIL"))) {
					return true;
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
				
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
		return false;
	}
	
	public boolean createRegCheckAccount(RegularCustomer c) {
		
		CallableStatement cs = null;
		
		try (Connection conn = GetConnected.getConnection()) {
			String sql = "INSERT INTO CUSTOMER (First_name, Last_name, Address, City, State, Zip_code, Phone, Email, Password)";
			sql += " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
			cs = conn.prepareCall(sql);
			cs.setString(1, c.getfName());
			cs.setString(2, c.getlName());
			cs.setString(3, c.getAddress());
			cs.setString(4, c.getCity());
			cs.setString(5, c.getState());
			cs.setString(6, c.getZip());
			cs.setString(7, c.getPhone());
			cs.setString(8, c.getEmail());
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(c.getPassword().getBytes());
			byte[] bytes = md.digest();
			StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++)
            {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            String generatedPassword = sb.toString(); 
			cs.setString(9, generatedPassword);
			Boolean result = cs.execute();
			cs.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		String acctNum = "";
		for (int i = 0; i < 16; i++) {
			acctNum += (int)(Math.random() * 10);
		}
		
		int custId = 0;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try (Connection conn = GetConnected.getConnection()) {
			String sql = "SELECT CUSTOMER_ID FROM CUSTOMER WHERE EMAIL = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, c.getEmail());
			
			rs = ps.executeQuery();
			
			while (rs.next()) {
				custId = rs.getInt("CUSTOMER_ID");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
				
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
		//String acct = new String(acctNum);
		ra = new Account(acctNum, "Regular Checking", custId, 0);
		return createAccount();
		
	}
	
	public boolean createSpecialCheckAccount(SaveTheChangeCustomer c) {
		
		CallableStatement cs = null;
		System.out.println("entered savechangedao");
		try (Connection conn = GetConnected.getConnection()) {
			String sql = "INSERT INTO CUSTOMER (First_name, Last_name, Address, City, State, Zip_code, Phone, Email, Password)";
			sql += " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
			cs = conn.prepareCall(sql);
			cs.setString(1, c.getfName());
			cs.setString(2, c.getlName());
			cs.setString(3, c.getAddress());
			cs.setString(4, c.getCity());
			cs.setString(5, c.getState());
			cs.setString(6, c.getZip());
			cs.setString(7, c.getPhone());
			cs.setString(8, c.getEmail());
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(c.getPassword().getBytes());
			byte[] bytes = md.digest();
			StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++)
            {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            String generatedPassword = sb.toString(); 
			cs.setString(9, generatedPassword);
			Boolean result = cs.execute();
			cs.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		String acctNum = "";
		for (int i = 0; i < 16; i++) {
			acctNum += (int)(Math.random() * 10);
		}
		
		int custId = 0;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try (Connection conn = GetConnected.getConnection()) {
			String sql = "SELECT CUSTOMER_ID FROM CUSTOMER WHERE EMAIL = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, c.getEmail());
			
			rs = ps.executeQuery();
			
			while (rs.next()) {
				custId = rs.getInt("CUSTOMER_ID");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
				
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
		//String acct = new String(acctNum);
		ra = new Account(acctNum, "Save the Change Checking", custId, 0);
		return createAccount();
	}
	
	public boolean createSavingsAccount(SavingsCustomer c) {
		
		CallableStatement cs = null;
		
		try (Connection conn = GetConnected.getConnection()) {
			String sql = "INSERT INTO CUSTOMER (First_name, Last_name, Address, City, State, Zip_code, Phone, Email, Password)";
			sql += " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
			cs = conn.prepareCall(sql);
			cs.setString(1, c.getfName());
			cs.setString(2, c.getlName());
			cs.setString(3, c.getAddress());
			cs.setString(4, c.getCity());
			cs.setString(5, c.getState());
			cs.setString(6, c.getZip());
			cs.setString(7, c.getPhone());
			cs.setString(8, c.getEmail());
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(c.getPassword().getBytes());
			byte[] bytes = md.digest();
			StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++)
            {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            String generatedPassword = sb.toString(); 
			cs.setString(9, generatedPassword);
			Boolean result = cs.execute();
			cs.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		String acctNum = "";
		for (int i = 0; i < 16; i++) {
			acctNum += (int)(Math.random() * 10);
		}
		
		int custId = 0;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try (Connection conn = GetConnected.getConnection()) {
			String sql = "SELECT CUSTOMER_ID FROM CUSTOMER WHERE EMAIL = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, c.getEmail());
			
			rs = ps.executeQuery();
			
			while (rs.next()) {
				custId = rs.getInt("CUSTOMER_ID");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
				
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
		//String acct = new String(acctNum);
		ra = new Account(acctNum, "Savings", custId, 0);
		return createAccount();
	}
	
	public boolean createAccount() {
		CallableStatement cs = null;
		
		try (Connection conn = GetConnected.getConnection()) {
			String sql = "INSERT INTO ACCOUNT";
			sql += " VALUES (?, ?, ?, ?)";
			cs = conn.prepareCall(sql);
			cs.setString(1, ra.getAccount_id());
			cs.setInt(2, ra.getCustomer_id());
			cs.setString(3, ra.getAccount_type());
			cs.setDouble(4, ra.getBalance());
			
			cs.executeQuery();
			cs.close();
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return false;	
	}
	
	public boolean createNewAccount(String email, String acctType) {
		System.out.println("entered overloaded create account method");
		PreparedStatement ps = null;
		ResultSet rs = null;
		int custId = 0;
		try (Connection conn = GetConnected.getConnection()) {
			String sql = "SELECT * FROM CUSTOMER WHERE EMAIL = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, email);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				custId = rs.getInt("CUSTOMER_ID");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
				
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
		System.out.println("custId: " + custId);
		CallableStatement cs = null;
		String acctNum = "";
		for (int i = 0; i < 16; i++){
			acctNum += (int)(Math.random() * 10);
		}
		System.out.println("new acct #: " + acctNum);
		try (Connection conn = GetConnected.getConnection()) {
			String sql = "INSERT INTO ACCOUNT";
			sql += " VALUES (?, ?, ?, ?)";
			cs = conn.prepareCall(sql);
			cs.setString(1, acctNum);
			cs.setInt(2, custId);
			cs.setString(3, acctType);
			cs.setDouble(4, 0);
			
			cs.executeQuery();
			cs.close();
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return false;
		
	}
	
	public boolean createAnother(String email, String acctType) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		int cust_id = 0;
		try (Connection conn = GetConnected.getConnection()) {
			String sql = "SELECT CUSTOMER_ID FROM CUSTOMER WHERE EMAIL = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, email);
			
			rs = ps.executeQuery();
			
			while (rs.next()) {
				cust_id = rs.getInt("CUSTOMER_ID");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
				
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
		
		String acctNum = "";
		for (int i = 0; i < 16; i++) {
			acctNum +=(int)(Math.random() * 10);
		}
		CallableStatement cs = null;
		
		try (Connection conn = GetConnected.getConnection()) {
			String sql = "INSERT INTO ACCOUNT";
			sql += " VALUES (?, ?, ?, ?)";
			cs = conn.prepareCall(sql);
			cs.setString(1, acctNum);
			cs.setInt(2, cust_id);
			cs.setString(3, acctType);
			cs.setDouble(4, 0);
			
			cs.executeQuery();
			cs.close();
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return false;
	}
	
	public ArrayList<String> Balance(String email) {
		System.out.println("getting balance");
		System.out.println(email);
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<String> acct_Types = new ArrayList<>();
		
		try (Connection conn = GetConnected.getConnection()) {
			System.out.println("connecting");
			String sql = "SELECT * FROM ACCOUNT WHERE CUSTOMER_ID = (SELECT CUSTOMER_ID FROM CUSTOMER WHERE EMAIL = ?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, email);
			rs = ps.executeQuery();
			while (rs.next()) {
				String toAddToArray = " " + rs.getString("Account_type");
				toAddToArray += " ";
				toAddToArray += (rs.getString("Account_id"));
				toAddToArray += "   $";
				toAddToArray += (rs.getString("Balance"));
				System.out.println("toAddToArray");
				acct_Types.add(toAddToArray);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
				
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
		return acct_Types;
		
	}
	
	public boolean deposits(String acctNum, double funds) {
		CallableStatement cs = null;
		System.out.println(acctNum + "   " + funds);
		if (acctNum.length() > 16) {
			acctNum = acctNum.substring(acctNum.length() - 16);
		}
		System.out.println(acctNum + "    " + acctNum.length());
		try (Connection conn = GetConnected.getConnection()) {
			System.out.println("got connected for a deposit");
			String sql = "{CALL SP_add_money(?, ?)}";
			cs = conn.prepareCall(sql);
			cs.setDouble(1, funds);
			cs.setString(2, acctNum);
			cs.executeQuery();
			cs.close();
			System.out.println(acctNum + "   " + funds);
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return false;
	}
	
	public ArrayList<String> getAccountNums(String email) {
		ArrayList<String> accts = new ArrayList<>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try (Connection conn = GetConnected.getConnection()) {
			String sql = "SELECT * FROM ACCOUNT WHERE CUSTOMER_ID = (SELECT CUSTOMER_ID FROM CUSTOMER WHERE EMAIL = ?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, email);
			
			rs = ps.executeQuery();
			
			while (rs.next()) {
				String toAddToArray = " $";
				toAddToArray += (rs.getString("Balance"));
				toAddToArray += "  " + rs.getString("Account_type");
				toAddToArray += " ";
				toAddToArray += (rs.getString("Account_id"));
				System.out.println(toAddToArray);
				accts.add(toAddToArray);
			}
		} catch (Exception ex) {
			//ex.printStackTrace();
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
				
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
		return accts;
	}
	
	public boolean withdraw(String acctNum, double funds, String email) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		String a = null;
		double t2 = 0;
		String savings = null;
		acctNum = acctNum.substring(acctNum.length() - 16);
		try (Connection conn = GetConnected.getConnection()) {
			String sql = "SELECT BALANCE, ACCOUNT_TYPE FROM ACCOUNT WHERE ACCOUNT_ID = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, acctNum);
			rs = ps.executeQuery();
			while (rs.next()) {
				double d = rs.getDouble("Balance");
				a = rs.getString("ACCOUNT_TYPE");
				if (funds > d) {
					System.out.println("connecting");
					return false;
				}
			}
		} catch (Exception ex) {
			//ex.printStackTrace();
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
				
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
		System.out.println(a);
		
		
		
		System.out.println("deposit into savings must be true");
		CallableStatement cs = null;
		System.out.println(acctNum + "   " + funds);
		System.out.println(acctNum + "    " + acctNum.length());
		try (Connection conn = GetConnected.getConnection()) {
			String sql = "{CALL SP_withdraw_money(?, ?)}";
			cs = conn.prepareCall(sql);
			cs.setDouble(1, funds);
			cs.setString(2, acctNum);
			System.out.println(funds);
			cs.execute();
			System.out.println(acctNum);
			cs.close();
			System.out.println(acctNum + "   " + funds);
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		return false;
		
	}
	
	public boolean withdrawSpecial(String acctNum, double funds, String email) {
		System.out.println("withdraw special");
		PreparedStatement ps = null;
		ResultSet rs = null;
		String a = null;
		double t2 = 0;
		String savings = null;
		acctNum = acctNum.substring(acctNum.length() - 16);
		try (Connection conn = GetConnected.getConnection()) {
			String sql = "SELECT BALANCE FROM ACCOUNT WHERE ACCOUNT_ID = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, acctNum);
			rs = ps.executeQuery();
			while (rs.next()) {
				double d = rs.getDouble("Balance");
				//a = rs.getString("ACCOUNT_TYPE");
				if (funds > d) {
					System.out.println("connecting");
					return false;
				}
			}
		} catch (Exception ex) {
			//ex.printStackTrace();
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
				
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
		
		funds = funds * 100;
		int temp = (int)(funds % 100);
		t2 = temp/100.0;
		t2 = 1 - t2;
		funds = (funds + t2 * 100)/100;
		
		ps = null;
		rs = null;
		String savingsAcctNum = null;
		try (Connection conn = GetConnected.getConnection()) {
			System.out.println("save the change acct number: " + acctNum);
			String sql = "SELECT * FROM ACCOUNT WHERE CUSTOMER_ID = (SELECT CUSTOMER_ID FROM CUSTOMER WHERE EMAIL = ?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, email);
			rs = ps.executeQuery();
			while (rs.next()) {
				savings = rs.getString("ACCOUNT_TYPE");
				//a = rs.getString("ACCOUNT_TYPE");
				if (savings.equals("Savings")) {
					savingsAcctNum = rs.getString("ACCOUNT_ID");
					System.out.println("savings acct num: " + savingsAcctNum + "!!");
					break;
				}
			}
		} catch (Exception ex) {
			//ex.printStackTrace();
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
				
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
		System.out.println("savings acct num: " + acctNum + "!! again");
		CallableStatement cs = null;
		//boolean result = false;
		System.out.println("!!" + funds + "!!" + acctNum + "!!");
		try (Connection conn = GetConnected.getConnection()) {
			String sql = "{CALL SP_withdraw_money(?, ?)}";
			cs = conn.prepareCall(sql);
			cs.setDouble(1, funds);
			cs.setString(2, acctNum);
			System.out.println(funds);
			boolean result = cs.execute();
			System.out.println(acctNum);
			cs.close();
			System.out.println(acctNum + "   " + funds);
			System.out.println(result);
			System.out.println("savings acct num: " + savingsAcctNum + "!! again");
			boolean resultDepo = deposits(savingsAcctNum, t2);
			
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return false;
	}
	
	public boolean transfer(String acctFrom, String acctTo, double moneyToTransfer) {
		acctFrom = acctFrom.substring(acctFrom.length() - 16);
		acctTo = acctTo.substring(acctTo.length() - 16);
		CallableStatement cs = null;
		
		try(Connection conn = GetConnected.getConnection()) {
			String sql = "{CALL SP_TRANSFER_MONEY(?, ?, ?)}";
			cs = conn.prepareCall(sql);
			cs.setString(1, acctFrom);
			cs.setString(2, acctTo);
			cs.setDouble(3, moneyToTransfer);
			
			boolean result = cs.execute();
			cs.close();
			System.out.println(result);
			return result;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		return false;
	}
	
	public RegularCustomer getCustomer(String email) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		RegularCustomer c = null;
		String fName = null, lName = null, address = null, city = null, state = null, zip = null, phone = null;
		try (Connection conn = GetConnected.getConnection()) {
			String sql = "SELECT * FROM CUSTOMER WHERE EMAIL = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, email);
			
			rs = ps.executeQuery();
			
			while (rs.next()) {
				fName = rs.getString("FIRST_NAME");
				lName = rs.getString("LAST_NAME");
				address = rs.getString("ADDRESS");
				city = rs.getString("CITY");
				state = rs.getString("STATE");
				zip = rs.getString("ZIP_CODE");
				phone = rs.getString("PHONE");
			}
		} catch (Exception ex) {
			//ex.printStackTrace();
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
				
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
		c = new RegularCustomer(fName, lName, address, city, state, zip, phone, email, null);
		return c;
	}
	
	public boolean updateUser(RegularCustomer c, RegularCustomer cUpdated) {
		String fName, lName, address, city, state, zip, phone, emailAddress;
		CallableStatement cs = null;
		if (cUpdated.getfName() != null){
			fName = cUpdated.getfName();
		}
		else{
			fName = c.getfName();
		}
		if (cUpdated.getlName() != null){
			lName = cUpdated.getlName();
		}
		else{
			lName = c.getlName();
		}
		if (cUpdated.getAddress() != null){
			address = cUpdated.getAddress();
		}
		else{
			address = c.getAddress();
		}
		if (cUpdated.getCity() != null){
			city = cUpdated.getCity();
		}
		else{
			city = c.getCity();
		}
		if (cUpdated.getState() != null){
			state = cUpdated.getState();
		}
		else{
			state = c.getState();
		}
		if (cUpdated.getZip() != null){
			zip = cUpdated.getZip();
		}
		else{
			zip = c.getZip();
		}
		if (cUpdated.getPhone() != null){
			phone = cUpdated.getPhone();
		}
		else{
			phone = c.getPhone();
		}
		if (cUpdated.getEmail() != null){
			emailAddress = cUpdated.getEmail();
		}
		else{
			emailAddress = c.getEmail();
		}
		try (Connection conn = GetConnected.getConnection()) {
			String sql = "UPDATE CUSTOMER SET FIRST_NAME = ?, LAST_NAME = ?, ADDRESS = ?, CITY = ?, STATE = ?, ZIP_CODE  = ?, ";
			sql += "PHONE = ?, EMAIL = ? WHERE EMAIL = ?";
			cs = conn.prepareCall(sql);
			cs.setString(1, fName);
			cs.setString(2, lName);
			cs.setString(3, address);
			cs.setString(4, city);
			cs.setString(5, state);
			cs.setString(6, zip);
			cs.setString(7, phone);
			cs.setString(8, emailAddress);
			cs.setString(9,  c.getEmail());
			
			cs.execute();
			cs.close();
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return false;
		
	}
	
	public boolean addNew (String acctChoice, String email) {
		
		return false;
		
	}
}
