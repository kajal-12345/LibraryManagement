package LibraryManagement;

import java.util.Scanner;

public class UserLogin {
	static String name;
	static String pass;
	static int current_user = 0;
	public void login() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter user name");
		name = sc.next();
		System.out.println("Enter password");
		pass = sc.next();
		if(checkCredential()) {
			System.out.println("login sucessful!");
			UserHome userhome = new UserHome();
			userhome.userChoice();
		}
		else {
			System.out.println("invalid username or password");
//			RegisterAdmin.choice();
			login();
		}
	}
	public static  boolean checkCredential() {
		boolean checkCredential = false ;
//		System.out.println(RegisterAdmin.adminList.size());
		
		for(int i = 0 ; i < RegisterStudent.studentList.size();i++) {
					if(RegisterStudent.studentList.get(i).userName.equals(name) && RegisterStudent.studentList.get(i).password.equals(pass)) {
						current_user = i;
						checkCredential = true;
			}		
		}
		return checkCredential;
	}

}
