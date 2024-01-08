package LibraryManagement;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterAdmin {
	String name;
	String password;
	String email_id;
	public String phone_no;
	static double wallet ;
	static ArrayList <UserAdmin> adminList = new ArrayList<UserAdmin>();
	static ArrayList <Books> books = new ArrayList<Books>();

	public  void setuserName(String name) {
		Scanner sc = new Scanner(System.in);
		name = sc.next();
		this.name = name;
	}
	public void setPassword(String password) {	
		Scanner sc = new Scanner(System.in);
		password = sc.next();
		this.password = password;		
	}

	public void setEmail(String email_id) {
		Scanner sc = new Scanner(System.in);
		email_id = sc.next();
		this.email_id = email_id;
	}
	public void setPhone_no(String phone_no) {
		Scanner sc = new Scanner(System.in);
		phone_no = sc.next();
		this.phone_no = phone_no;
	}
	public   boolean isValidPassword() {
		String password = this.password;
		String regex = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&])(?=\\S+$).{8,20}";
		Pattern p = Pattern.compile(regex);
		Matcher matcher = p.matcher(password);
		boolean matchfound = matcher.find();
		return matchfound;
	}

	public  boolean isValidEmail() {
		String Email = this.email_id;
		String regex ="^([\\w-\\.]+){1,64}@([\\w&&[^_]]+){2,255}.[a-z]{2,}$";
		Pattern p = Pattern.compile(regex);
		Matcher matcher = p.matcher(Email);
		boolean matchfound = matcher.find();
		return matchfound;
	}

	public  boolean isValidPh() {
		String phone_number = this.phone_no;
		String regex = "\\d{10}";
		Pattern p = Pattern.compile(regex);
		Matcher matcher = p.matcher(phone_number);
		boolean matchfound = matcher.find();
		return matchfound;
	}

	public void register(RegisterAdmin obj) {
		boolean isValidPassword = true;
		System.out.println("enter user name:");
		obj.setuserName(name);		
		do {
			System.out.println("enter password");
			obj.setPassword(password);
			if(obj.isValidPassword()) {
				isValidPassword = false;
			}	
			else {
				System.out.println("weak password!!");
			}
		}while(isValidPassword);
		boolean isValidEmail = true;
		do {
			System.out.println("enter email-id");
			obj.setEmail(email_id);
			if(obj.isValidEmail()) {
				isValidEmail = false;
			}	
			else {
				System.out.println("invalid email format!!");
			}
		}while(isValidEmail);
		boolean isValidPhone = true;
		do {
			System.out.println("enter phone-no");
			obj.setPhone_no(phone_no);
			if(obj.isValidPh() && obj.phone_no.length() == 10) {
				isValidPhone = false;
			}	
			else {
				System.out.println("invalid phone number!!");
			}
		}while(isValidPhone);


	}

	private  void addUser() {
		UserAdmin user = new UserAdmin(name, password, email_id, phone_no,wallet);
		adminList.add(user);
		System.out.println("account created");
	}
	public static void createUser() {
		adminList.add(new UserAdmin("Kajal", "Kajal@123", "kajal@cvb.com", "1231231232",0));
		adminList.add(new UserAdmin("sejal", "Sejal@123", "sejal@cvb.com", "1231231232",0));
	}
	public static void getUser() {
		for(UserAdmin x: adminList) {
			System.out.println(x.userName);
			System.out.println(x.password);
		}
	}
	public  void choice() {
		Scanner sc = new Scanner(System.in);
		try {
			System.out.println("enter 1  for admin account and 0 for student account");
			int choice = sc.nextInt();
			switch(choice) {
			case 1:
				boolean loop = true;
				do{
					System.out.println("Enter 0 for login and 1 for register");
					int input = sc.nextInt();
					AdminLogin  adminlogin = new AdminLogin();
					try{
						switch(input) {
						case 1:RegisterAdmin ob = new RegisterAdmin();
						ob.register(ob);
						ob.addUser();
						Home obj = new Home();
						
						obj.adminChoice();
						loop = false;
						break;
						case 0: adminlogin.login();
						
						loop = false;
						}
					}catch(InputMismatchException e) {
						System.out.println("invalid input!!");
						//						loop = true;				
					}
				}while(loop);
				break;
			case 0:System.out.println("..................Login................");
				UserHome userhome = new UserHome();
				UserLogin userlogin = new UserLogin(); 
				userlogin.login();
				userhome.userChoice();
				break;

//				loop1 = false;			
//				boolean loop1 = true;
//				do{
//					System.out.println("Enter 0 for login");
//					int input = sc.nextInt();
//					try{
//						switch(input) {
//					case 1:
//							RegisterStudent ob = new RegisterStudent();
//						ob.register(ob);
//						ob.addStudent();
//						//					ob.getUser();
//						userhome.userChoice();
//
//						loop1 = false;
//						break;
//						case 0:
//								break;
//						}
//					}catch(InputMismatchException e) {
//						System.out.println("invalid input !");
//						//										loop1 = false;				
//					}
//				}while(loop1);
			default:System.out.println("invalid data !");
			choice();
			}

		}catch(InputMismatchException e) {
			System.out.println("invalid input type!!");
			choice();
		}

	}

}
