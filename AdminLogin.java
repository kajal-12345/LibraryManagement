package LibraryManagement;

import java.util.Scanner;

public class AdminLogin {
static String name;
static String pass;
public  void login() {
	Scanner sc = new Scanner(System.in);
	System.out.println("Enter user name");
	name = sc.next();
	System.out.println("Enter password");
	pass = sc.next();
	if(checkCredential()) {
		System.out.println("login sucessful!");
		Home obj = new Home();
		obj.adminChoice();
	}
	else {
		System.out.println("invalid username or password");
		RegisterAdmin registeradmin = new RegisterAdmin();
		registeradmin.choice();
	}
}
public   boolean checkCredential() {
	boolean checkCredential = false ;
//	System.out.println(RegisterAdmin.adminList.size());
	for(int i = 0 ; i < RegisterAdmin.adminList.size();i++) {
				if(RegisterAdmin.adminList.get(i).userName.equals(name) && RegisterAdmin.adminList.get(i).password.equals(pass)) {
					checkCredential = true;
		}		
	}
	return checkCredential;
}
}
