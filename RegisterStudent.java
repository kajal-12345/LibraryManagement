package LibraryManagement;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterStudent {
	 String name;
	 String password;
	 String email_id;
	 String phone_no;
	 int roll_no;
	 double wallet;
	 LocalDate issue_date;
	 LocalDate return_date;
	 static ArrayList<UserStudent> studentList = new ArrayList<UserStudent>();
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

public void setRollNo() {
	try {
	Scanner sc = new Scanner(System.in);
	System.out.println("enter roll no");			 
	 int rollNo = sc.nextInt();
	 this.roll_no = rollNo;	
	 if(rollNo < 0) {
		 System.out.println("roll no can't be negative");
		 setRollNo();
	 }
	 for(UserStudent x : studentList) {
		 if(x.roll_no == rollNo) {
			 System.out.println("roll no already exist");
			 setRollNo();
		 }
	 }
}catch(InputMismatchException e) {
	 System.out.println("invalid input type");
  setRollNo();
}
}
	 public void register(RegisterStudent obj) {
		 Scanner sc = new Scanner(System.in);
//		 obj.register(obj);
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
			boolean b = false;
			
			setRollNo();
//			 try {	
//					
//				 do {	
//					 System.out.println("enter roll no");			 
//					 int rollNo = sc.nextInt();
//					 this.roll_no = rollNo;					
//			    		 b = false;
//					 for(UserStudent x : studentList) {
//						 if(x.roll_no == rollNo) {
//							 System.out.println("roll no already exist");
//							 b = true;
//						 }
//					 }	
//				 }while(b);
//		 }catch(InputMismatchException e) {
//			 System.out.println("invalid input type");

		 }
			 
		 
//	 }
	 
	 public static void userStudent() {
			studentList.add(new UserStudent("kajal", "kajal123", "kajal@cvb.com", "1231231232",1,0,new ArrayList<Books>()));
			studentList.add(new UserStudent("allen", "allen123", "allen@cvb.com", "1231231232",2,50,new ArrayList<Books>()));
		}
	 public static void getUser() {
		 for(UserStudent x : studentList ) {
			 System.out.println(x.userName);
			 System.out.println(x.password);
		 }
	 }

	 public void addStudent() {
		 UserStudent user = new UserStudent(name, password, email_id, phone_no, roll_no,wallet,new ArrayList<Books>());
//		System.out.println(user.userName);
		 studentList.add(user);
		 System.out.println("account created");
		 UserLogin.current_user = studentList.indexOf(user);
	 }

}
