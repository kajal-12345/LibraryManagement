package LibraryManagement;

import java.util.ArrayList;

public class UserStudent {
	 String userName ;
     String password;
     String email_id;
     String phone_no;
	int roll_no;
//	int book_id;
	double wallet;
	 ArrayList <Books> cart = new ArrayList<Books>();
     UserStudent(String userName,String password,String email_id,String phone_no,int roll_no,double wallet,ArrayList<Books>cart){
  	   this.userName = userName;
  	   this.password = password;
  	   this.email_id = email_id;
  	   this.phone_no = phone_no;
  	   this.roll_no = roll_no;
  	   this.wallet = wallet;
     }

}
