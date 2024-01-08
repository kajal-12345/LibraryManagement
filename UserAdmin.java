package LibraryManagement;

public class UserAdmin {
       String userName ;
       String password;
       String email_id;
       String phone_no;
	public double wallet;
	
          UserAdmin(String userName,String password,String email_id,String phone_no, double wallet){
    	   this.userName = userName;
    	   this.password = password;
    	   this.email_id = email_id;
    	   this.phone_no = phone_no;
       }
}
