package com.revature;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.revature.customers.Customer;
import com.revature.customers.RegularCustomer;
import com.revature.customers.SaveTheChangeCustomer;
import com.revature.customers.SavingsCustomer;
import com.revature.dao.CustomerDao;

public class TheGUI extends JFrame{
	
	private JButton btn_signIn = new JButton("Sign In"),
			btn_new_acct = new JButton("Create Account"),
			btn_submit = new JButton ("Submit"), 
			btn_deposit = new JButton ("Deposit funds"),
			btn_withdraw = new JButton ("Withdraw funds"),
			btn_balance = new JButton ("View Balance"),
			btn_transfer = new JButton ("Transfer funds"),
			btn_logOut = new JButton("Sign Out"),
			btn_updateInfo = new JButton("Update profile"),
			btn_register = new JButton("Register"),
			btn_Create_Another = new JButton("Add new account"),
			btn_add_new = new JButton("Add account"), 
			btn_selectAccount = new JButton("Select"),
			btn_select_deduction_acct = new JButton("Select"),
			btn_done = new JButton("Done"),
			btn_transfer_from_to = new JButton("Transfer"),
			btn_update_user = new JButton("Update"), 
			btn_another_account = new JButton("Enter");
	
	private JPanel cp, cp2, cp3, cpCreate, cp_another, cp_selectAcct, cp_balances, cp_transfer;
	private JPasswordField pf, pfCreate, pfConfirm;
	private JTextField tf, hint1, hint2, fName, lName, address, city, state, zip, phone, email;
	private JTextField fNameHint, lNameHint, addressHint, cityHint, stateHint, zipHint;
	private JTextField phoneHint, emailHint, pfConfirmHint, pfCreateHint, fundsToAdd, fundsHint, selectAcctHint, fundsToDeduct, fundsDHint;
	private JTextField amtToTransfer;
	private JFrame jf  = new JFrame();
	//private JFrame anotherJf = new JFrame();
	private JComboBox<String> jcb, jcb_from, jcb_to;
	private String accountChoice, emailAddress, acctChoiceFrom, acctChoiceTo;
	
	private ArrayList<String> list;
	
	private CustomerDao custDao = new CustomerDao();
	MyButtonHandler bh = new MyButtonHandler();
	
	RegularCustomer c;
	
	
	
	public TheGUI() {
		
		
		setTitle("New World Bank App");
		setSize(450, 200);
		setLocation(250, 200);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		
		// must add an action listener to each button to make it respond to presses
		
		btn_signIn.addActionListener(bh);
		btn_new_acct.addActionListener(bh);
				
		cp = new JPanel(new FlowLayout());
		
		cp.add(btn_signIn);
		cp.add(btn_new_acct);
		
		setContentPane(cp);
		setVisible(true);
	}
	
	public void paint(Graphics draw){
	      getContentPane().setBackground(Color.LIGHT_GRAY);
	    }
	
	public void logInScreen() {
		
		System.out.println("LogInClicked");
		setVisible(false);
		setTitle("New World Bank App");
		setLocation(250, 200);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		
		pf = new JPasswordField(20);
		pf.setActionCommand("OK");
		pf.addActionListener(bh);
		
		tf = new JTextField();
		hint1 = new JTextField("Email");
		hint1.setEditable(false);
		hint2 = new JTextField("Password");
		hint2.setEditable(false);
		
		btn_submit.addActionListener(bh);
		
		//Container c = new Container();
		cp2 = new JPanel(new GridLayout(3, 2));
		
		cp2.add(tf);
		cp2.add(hint1);
		cp2.add(pf);
		cp2.add(hint2);
		cp2.add(btn_submit);
		setContentPane(cp2);
		setVisible(true);
	}
	
	public void createCustomer() {
		
		setVisible(false);
		setTitle("New World Bank App");
		setSize(new Dimension (400, 300));
		setLocation(250, 200);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		accountChoice = "Regular Checking";
		final String[] choices = {"Regular Checking", "Save the Change Checking", "Savings"};
		jcb = new JComboBox<String> (choices);
		jcb.setMaximumRowCount(3);
		
		jcb.addItemListener(
				new ItemListener() {
					public void itemStateChanged(ItemEvent e) {
						accountChoice = choices[jcb.getSelectedIndex()];
					}
		});
		System.out.println(accountChoice);
		pfCreate = new JPasswordField(20);
		pfCreate.setActionCommand("OK");
		pfCreate.addActionListener(bh);
		
		pfConfirm = new JPasswordField(20);
		pfConfirm.setActionCommand("OK");
		pfConfirm.addActionListener(bh);
		fName = new JTextField();
		lName = new JTextField();
		address = new JTextField();
		city = new JTextField();
		state = new JTextField();
		zip = new JTextField();
		phone = new JTextField();
		email = new JTextField();
		fNameHint = new JTextField("First Name");
		fNameHint.setEditable(false);
		lNameHint = new JTextField("Last Name");
		lNameHint.setEditable(false);
		addressHint = new JTextField("Address");
		addressHint.setEditable(false);
		cityHint = new JTextField("City");
		cityHint.setEditable(false);
		stateHint = new JTextField("State");
		stateHint.setEditable(false);
		zipHint = new JTextField("Zip Code");
		zipHint.setEditable(false);
		phoneHint = new JTextField("Phone");
		phoneHint.setEditable(false);
		emailHint = new JTextField("Email");
		emailHint.setEditable(false);
		pfCreateHint = new JTextField("Password");
		pfCreateHint.setEditable(false);
		pfConfirmHint = new JTextField("Confirm Password");
		pfConfirmHint.setEditable(false);
		
		pfConfirm = new JPasswordField(20);
		pfConfirm.setActionCommand("OK");
		pfConfirm.addActionListener(bh);
		btn_register.addActionListener(bh);
		
		cpCreate = new JPanel(new GridLayout(11, 2));
		
		cpCreate.add(fName);
		cpCreate.add(fNameHint);
		cpCreate.add(lName);
		cpCreate.add(lNameHint);
		cpCreate.add(address);
		cpCreate.add(addressHint);
		cpCreate.add(city);
		cpCreate.add(cityHint);
		cpCreate.add(state);
		cpCreate.add(stateHint);
		cpCreate.add(zip);
		cpCreate.add(zipHint);
		cpCreate.add(phone);
		cpCreate.add(phoneHint);
		cpCreate.add(email);
		cpCreate.add(emailHint);
		cpCreate.add(pfCreate);
		cpCreate.add(pfCreateHint);
		cpCreate.add(pfConfirm);
		cpCreate.add(pfConfirmHint);
		cpCreate.add(jcb);
		cpCreate.add(btn_register);
		
		
		setContentPane(cpCreate);
		setVisible(true);
		
	}
	
	public void goToHomeScreen(String email) {
		accountChoice = null;
		emailAddress = email;
		setVisible(false);
		setTitle("New World Bank App");
		setSize(new Dimension (350, 350));
		setLocation(250, 200);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		btn_deposit.addActionListener(bh);
		btn_withdraw.addActionListener(bh);
		btn_balance.addActionListener(bh);
		btn_transfer.addActionListener(bh);
		btn_logOut.addActionListener(bh);
		btn_updateInfo.addActionListener(bh);
		btn_Create_Another.addActionListener(bh);
		
		cp3 = new JPanel(new GridLayout(4, 2));
		cp3.add(btn_deposit);
		cp3.add(btn_withdraw);
		cp3.add(btn_balance);
		cp3.add(btn_transfer);
		cp3.add(btn_updateInfo);
		cp3.add(btn_Create_Another);
		cp3.add(btn_logOut);
		
		setContentPane(cp3);
		setVisible(true);
	}
	
	public void storeCustomer (String fName, String lName, String address, String city, String state, String zip, String phone,
			String email, String password, String acctType) {
		boolean success = false;
		if (acctType.equals("Regular Checking")) {
			RegularCustomer rc = new RegularCustomer(fName, lName, address, city, state, zip, phone, email, password);
			//custDao = new CustomerDao();
			success = custDao.createRegCheckAccount(rc);
			
		} else if (acctType.equals("Save the Change Checking")) {
			SaveTheChangeCustomer stcc = new SaveTheChangeCustomer(fName, lName, address, city, state, zip, phone, email, password);
			//custDao = new CustomerDao();
			System.out.println(acctType);
			success = custDao.createSpecialCheckAccount(stcc);
			
		} else if (acctType.equals("Savings")){
			SavingsCustomer sc = new SavingsCustomer(fName, lName, address, city, state, zip, phone, email, password);
			//custDao = new CustomerDao();
			success = custDao.createSavingsAccount(sc);
		}
		
		if (success){
			goToHomeScreen(email);
		}
	}
	
	public void createAnotherAccount(String email) {
		setVisible(false);
		setTitle("New World Bank App");
		setLocation(250, 200);
		setSize(new Dimension (275, 200));
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		JTextField type = new JTextField("Which type of account?");
		JTextField nothing = new JTextField();
		nothing.setEditable(false);
		type.setEditable(false);
		btn_add_new.addActionListener(bh);
		cp_another = new JPanel(new GridLayout(2, 2));
		
		final String[] choices = {"Regular Checking", "Save the Change Checking", "Savings"};
		accountChoice = choices[0];
		jcb = new JComboBox<String> (choices);
		jcb.setMaximumRowCount(3);
		
		jcb.addItemListener(
				new ItemListener() {
					public void itemStateChanged(ItemEvent e) {
						accountChoice = choices[jcb.getSelectedIndex()];
					}
		});
		
		cp_another.add(type);
		cp_another.add(nothing);
		cp_another.add(jcb);
		cp_another.add(btn_add_new);
		setContentPane(cp_another);
		setVisible(true);
		
	}
	
	public boolean passwordVerified(String email, String password) {
		System.out.println("verifying password");
		//custDao = new CustomerDao();
		return custDao.verifyPassword(email, password);
	}
	
	public boolean isEmailThere (String email) {
		//custDao = new CustomerDao();
		return custDao.customerVerification(email);
	}
	
	public void whichAccount() {
		
		setVisible(false);
		setTitle("New World Bank App");
		setSize(new Dimension (400, 300));
		setLocation(250, 200);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		ArrayList<String> wh_acct = custDao.getAccountNums(emailAddress);
		final String[] accts = new String[wh_acct.size()];
		accountChoice = wh_acct.get(0);
		for(int i = 0; i < wh_acct.size(); i++){
			accts[i] = wh_acct.get(i);
		}
		jcb = new JComboBox<String> (accts);
		jcb.setMaximumRowCount(wh_acct.size());
		jcb.addItemListener(
				new ItemListener() {
					public void itemStateChanged(ItemEvent e) {
						accountChoice = accts[jcb.getSelectedIndex()];
					}
		});
		fundsToAdd = new JTextField();
		fundsHint = new JTextField("Enter amount to add");
		fundsHint.setEditable(false);
		selectAcctHint = new JTextField("Select account");
		selectAcctHint.setEditable(false);
		btn_selectAccount.addActionListener(bh);
		cp_selectAcct = new JPanel(new GridLayout(3, 2));
		cp_selectAcct.add(jcb);
		cp_selectAcct.add(selectAcctHint);
		cp_selectAcct.add(fundsToAdd);
		cp_selectAcct.add(fundsHint);
		cp_selectAcct.add(btn_selectAccount);
		setContentPane(cp_selectAcct);
		setVisible(true);
	}
	public void whichAccountDeduct(){
		setVisible(false);
		setTitle("New World Bank App");
		setSize(new Dimension (400, 300));
		setLocation(250, 200);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		ArrayList<String> wh_acct = custDao.getAccountNums(emailAddress);
		final String[] accts = new String[wh_acct.size()];
		accountChoice = wh_acct.get(0);
		for(int i = 0; i < wh_acct.size(); i++){
			accts[i] = wh_acct.get(i);
		}
		jcb = new JComboBox<String> (accts);
		jcb.setMaximumRowCount(wh_acct.size());
		jcb.addItemListener(
				new ItemListener() {
					public void itemStateChanged(ItemEvent e) {
						accountChoice = accts[jcb.getSelectedIndex()];
						System.out.println(accountChoice);
					}
		});
		fundsToDeduct = new JTextField();
		fundsDHint = new JTextField("Enter amount of withdrawal (or card use)");
		fundsDHint.setEditable(false);
		selectAcctHint = new JTextField("Select account");
		selectAcctHint.setEditable(false);
		btn_select_deduction_acct.addActionListener(bh);
		cp_selectAcct = new JPanel(new GridLayout(3, 2));
		cp_selectAcct.add(jcb);
		cp_selectAcct.add(selectAcctHint);
		cp_selectAcct.add(fundsToDeduct);
		cp_selectAcct.add(fundsDHint);
		cp_selectAcct.add(btn_select_deduction_acct);
		setContentPane(cp_selectAcct);
		setVisible(true);
	}
	
	public void viewBalances(ArrayList<String> balances) {
		setVisible(false);
		setTitle("New World Bank App");
		setSize(new Dimension (700, 150));
		setLocation(250, 200);
		setResizable(false);
		int s = balances.size();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		cp_balances = new JPanel(new GridLayout(s, 1));
		btn_done.addActionListener(bh);
		for (int i = 0; i < balances.size(); i++) {
			JTextField jtf = new JTextField(balances.get(i));
			jtf.setEditable(false);
			cp_balances.add(jtf);
		}
		cp_balances.add(btn_done);
		setContentPane(cp_balances);
		setVisible(true);
	}
	
	public void transfers() {
		setTitle("New World Bank App");
		setSize(700, 200);
		setLocation(250, 200);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		JTextField from = new JTextField("From");
		from.setEditable(false);
		JTextField to = new JTextField("To");
		to.setEditable(false);
		amtToTransfer = new JTextField();
		JTextField transferHint = new JTextField("Amount");
		transferHint.setEditable(false);
		cp_transfer = new JPanel(new GridLayout(4, 2));
		ArrayList<String> wh_acct = custDao.getAccountNums(emailAddress);
		final String[] accts = new String[wh_acct.size()];
		acctChoiceFrom = wh_acct.get(0);
		acctChoiceTo = wh_acct.get(0);
		for(int i = 0; i < wh_acct.size(); i++){
			accts[i] = wh_acct.get(i);
		}
		jcb_from = new JComboBox<String> (accts);
		jcb_from.setMaximumRowCount(wh_acct.size());
		jcb_from.addItemListener(
				new ItemListener() {
					public void itemStateChanged(ItemEvent e) {
						acctChoiceFrom = accts[jcb_from.getSelectedIndex()];
					}
		});
		jcb_to = new JComboBox<String> (accts);
		jcb_to.setMaximumRowCount(wh_acct.size());
		jcb_to.addItemListener(
				new ItemListener() {
					public void itemStateChanged(ItemEvent e) {
						acctChoiceTo = accts[jcb_to.getSelectedIndex()];
					}
		});
		btn_transfer_from_to.addActionListener(bh);
		cp_transfer.add(from);
		cp_transfer.add(jcb_from);
		cp_transfer.add(to);
		cp_transfer.add(jcb_to);
		cp_transfer.add(transferHint);
		cp_transfer.add(amtToTransfer);
		cp_transfer.add(btn_transfer_from_to);
		setContentPane(cp_transfer);
		setVisible(true);
	}
	
	public void updateInfo() {
		c = custDao.getCustomer(emailAddress);
		setVisible(false);
		setTitle("New World Bank App");
		setSize(new Dimension (400, 300));
		setLocation(250, 200);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		
		fName = new JTextField(c.getfName());
		lName = new JTextField(c.getlName());
		address = new JTextField(c.getAddress());
		city = new JTextField(c.getCity());
		state = new JTextField(c.getState());
		zip = new JTextField(c.getZip());
		phone = new JTextField(c.getPhone());
		email = new JTextField(emailAddress);
		fNameHint = new JTextField("First Name");
		fNameHint.setEditable(false);
		lNameHint = new JTextField("Last Name");
		lNameHint.setEditable(false);
		addressHint = new JTextField("Address");
		addressHint.setEditable(false);
		cityHint = new JTextField("City");
		cityHint.setEditable(false);
		stateHint = new JTextField("State");
		stateHint.setEditable(false);
		zipHint = new JTextField("Zip Code");
		zipHint.setEditable(false);
		phoneHint = new JTextField("Phone");
		phoneHint.setEditable(false);
		emailHint = new JTextField("Email");
		emailHint.setEditable(false);
		
		btn_update_user.addActionListener(bh);
		
		cpCreate = new JPanel(new GridLayout(9, 2));
		
		cpCreate.add(fName);
		cpCreate.add(fNameHint);
		cpCreate.add(lName);
		cpCreate.add(lNameHint);
		cpCreate.add(address);
		cpCreate.add(addressHint);
		cpCreate.add(city);
		cpCreate.add(cityHint);
		cpCreate.add(state);
		cpCreate.add(stateHint);
		cpCreate.add(zip);
		cpCreate.add(zipHint);
		cpCreate.add(phone);
		cpCreate.add(phoneHint);
		cpCreate.add(email);
		cpCreate.add(emailHint);
		cpCreate.add(btn_update_user);
		
		setContentPane(cpCreate);
		setVisible(true);
	}
	
	public void updateCustomer(RegularCustomer cUpdated) {
		if (custDao.updateUser(c, cUpdated)) {
			JOptionPane.showMessageDialog(jf, "Your account has been updated", 
					"Success Message", JOptionPane.INFORMATION_MESSAGE);
		}
		goToHomeScreen(cUpdated.getEmail());
	}
	
	
	private class MyButtonHandler implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
			Object src = e.getSource();
			
			if (src == btn_signIn) {
				logInScreen();
			} else if (src == btn_new_acct) {
				createCustomer();
			}else if (src == btn_submit) {
				System.out.println("submit clicked");
				String email = tf.getText();
				char[] pw1 = pf.getPassword();
				String pass1 = new String(pw1);
				
				if(email.length() == 0 || pass1.length() == 0) {
					JOptionPane.showMessageDialog(jf, "Please enter email and password!", "Log In Error", JOptionPane.ERROR_MESSAGE);
				}
				else if (!isEmailThere(email)) {
					JOptionPane.showMessageDialog(jf, "Email not found!", "Log In Error", JOptionPane.ERROR_MESSAGE);
				}
				
				else {
					if (passwordVerified(email, pass1)) {
						goToHomeScreen(email);
					}else {
						JOptionPane.showMessageDialog(jf, "Email and password mismatch!", "Password Error", JOptionPane.ERROR_MESSAGE);
					}
				}
				
			} else if (src == btn_deposit) {
				whichAccount();
			}else if (src == btn_withdraw) {
				System.out.println("withdraw");
				whichAccountDeduct();
				
			}else if (src == btn_balance) {
				System.out.println("balance");
				list = custDao.Balance(emailAddress);
				System.out.println(list.toString());
				viewBalances(list);
			}else if (src == btn_transfer) {
				System.out.println("transfer");
				transfers();
				
			} else if (src == btn_updateInfo) {
				updateInfo();
			} else if (src == btn_logOut) {
				setVisible(false);
				TheGUI tg2 = new TheGUI();
			} else if (src == btn_register) {
				String firstName = fName.getText();
				String lastName = lName.getText();
				String addy = address.getText();
				String town = city.getText();
				String st = state.getText();
				String zipcode = zip.getText();
				String phoneNumber = phone.getText();
				emailAddress = email.getText();
				char[] CreatePassword = pfCreate.getPassword();
				char[] ConfirmPassword = pfConfirm.getPassword();
				String CreatePass = new String(CreatePassword);
				String ConfirmPass = new String(ConfirmPassword);
				if (firstName.length() == 0 || lastName.length() == 0 || addy.length() == 0 || town.length() == 0 ||
						st.length() == 0 || zipcode.length() == 0 || phoneNumber.length() == 0 || emailAddress.length() == 0 ||
						CreatePass.length() == 0 || ConfirmPass.length() == 0) {
					JOptionPane.showMessageDialog(jf, "Please enter all fields", "Account Creation Error", JOptionPane.ERROR_MESSAGE);
				} else if (!CreatePass.equals(ConfirmPass)) {
					JOptionPane.showMessageDialog(jf, "Passwords do not match!", "Password Error", JOptionPane.ERROR_MESSAGE);
				} else {
	
					storeCustomer(firstName, lastName, addy, town, st, zipcode, phoneNumber, emailAddress, CreatePass, accountChoice);
				}
			} else if(src == btn_Create_Another) {
				createAnotherAccount(emailAddress);
			} else if (src == btn_add_new) {
				//custDao = new CustomerDao();
				//custDao.createAnother(emailAddress, accountChoice);
				System.out.println("button add account clicked");
				if(custDao.createNewAccount(emailAddress, accountChoice)) {
					JOptionPane.showMessageDialog(jf, "New account added with a $0 balance", 
							"Success", JOptionPane.INFORMATION_MESSAGE);
				}
				goToHomeScreen(emailAddress);
			} else if (src == btn_selectAccount) {
				String money = fundsToAdd.getText();
				double funds = 0;
				try {
					funds = Double.parseDouble(money);
				}catch(Exception ex) {
					JOptionPane.showMessageDialog(jf, "Please enter a correct amount of funds to add", 
							"Incorrect input error", JOptionPane.ERROR_MESSAGE);
				}
				if(custDao.deposits(accountChoice, funds)) {
					JOptionPane.showMessageDialog(jf, "Funds have successfully been added to your account", 
							"Success", JOptionPane.INFORMATION_MESSAGE);
				}else{
					System.out.println("Some error");
				}
				goToHomeScreen(emailAddress);
			} else if (src == btn_select_deduction_acct) {
				System.out.println("!!" + accountChoice + "!!");
				String[] sp = accountChoice.split("\\s+");
				String money = fundsToDeduct.getText();
				double funds = 0;
				System.out.println("!!" + sp[2] + "!!");
				String acctT = accountChoice.substring(0, accountChoice.length() - 17);
				System.out.println(acctT + "!!");
				try {
					funds = Double.parseDouble(money);
				}catch(Exception ex) {
					JOptionPane.showMessageDialog(jf, "Please enter a correct amount of funds to deduct", 
							"Incorrect input error", JOptionPane.ERROR_MESSAGE);
				}
				if (sp[2].equals("Save")) {
					if(custDao.withdrawSpecial(accountChoice, funds, emailAddress)) {
						JOptionPane.showMessageDialog(jf, "Funds have successfully been deducted from your account", 
								"Success", JOptionPane.INFORMATION_MESSAGE);
					}else{
						JOptionPane.showMessageDialog(jf, "Insufficient funds", 
								"Input too large error", JOptionPane.INFORMATION_MESSAGE);
					}
				} else {
					if(custDao.withdraw(accountChoice, funds, emailAddress)) {
						JOptionPane.showMessageDialog(jf, "Funds have successfully been deducted from your account", 
								"Success", JOptionPane.INFORMATION_MESSAGE);
					}else{
						JOptionPane.showMessageDialog(jf, "Insufficient funds", 
								"Input too large error", JOptionPane.INFORMATION_MESSAGE);
					}
				}
				goToHomeScreen(emailAddress);
			}else if(src == btn_done) {
				goToHomeScreen(emailAddress);
			}else if (src == btn_transfer_from_to) {
				System.out.println("!!" + acctChoiceFrom + "!!" + acctChoiceTo + "!!");
				if(acctChoiceFrom.equals(acctChoiceTo)) {
					JOptionPane.showMessageDialog(jf, "Please choose different accounts to transfer from and to", 
							"Wrong account", JOptionPane.ERROR_MESSAGE);
				}else {
					String moneyToTransfer = amtToTransfer.getText();
					double moneyTT = 0;
					try {
						moneyTT = Double.parseDouble(moneyToTransfer);
					}catch(Exception ex) {
						JOptionPane.showMessageDialog(jf, "Please enter a correct amount of funds to deduct", 
								"Incorrect input error", JOptionPane.ERROR_MESSAGE);
					} 
					if(custDao.transfer(acctChoiceFrom, acctChoiceTo, moneyTT)) {
						JOptionPane.showMessageDialog(jf, "Funds have successfully been transfered", 
								"Success", JOptionPane.INFORMATION_MESSAGE);
					}
						
				}
				goToHomeScreen(emailAddress);
			}
			else if (src == btn_update_user) {
				String newEmail;
				String firstName = fName.getText();
				String lastName = lName.getText();
				String addy = address.getText();
				String town = city.getText();
				String st = state.getText();
				String zipcode = zip.getText();
				String phoneNumber = phone.getText();
				newEmail = email.getText();
				
				RegularCustomer cUpdated = new RegularCustomer(firstName, lastName, addy, town, st, zipcode, phoneNumber, newEmail, null);
				updateCustomer(cUpdated);
				
			}
		}
	}
}
