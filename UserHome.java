package LibraryManagement;
import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Scanner;

public class UserHome {	
			
	public  void returnBook() {
		Scanner sc = new Scanner(System.in);
		if(!RegisterStudent.studentList.get(UserLogin.current_user).cart.isEmpty()) {
			System.out.println("Enter Book_id to submit book");
			int id = sc.nextInt();
			boolean isPresent = false;
			for( Books x:RegisterStudent.studentList.get(UserLogin.current_user).cart ) {
				if(x.book_id == id) {
					  checkPenalty(RegisterStudent.studentList.get(UserLogin.current_user).cart.indexOf(x));	  
					isPresent = true;
					break;
				}
			}
			if(isPresent) {
				for(int j = 0 ; j < RegisterAdmin.books.size();j++ ) {
					if(RegisterAdmin.books.get(j).book_id == id) {
//						checkPenalty(id-1);
						RegisterAdmin.books.get(j).quantity++;
					}
				}
			}
			else {
				System.out.println("id not present");
			}
		
		}
	
	}
	public  void checkPenalty(int indexof_studentBook) {
			
//			LocalDate nextWeek = RegisterStudent.studentList.get(indexofUser).cart.get(indexof_studentBook).issue_date.plusDays(7);
//			for(int index = 0 ; index < RegisterStudent.studentList.get(UserLogin.current_user).cart.size();index++) {
				if(RegisterStudent.studentList.get(UserLogin.current_user).cart.get(indexof_studentBook).return_date.isBefore(LocalDate.now().plusDays(9))) {
					RegisterStudent.studentList.get(UserLogin.current_user).wallet -= 5;
					RegisterAdmin.wallet += 5;
//					System.out.println("charge penalty");
				}
				RegisterStudent.studentList.get(UserLogin.current_user).cart.remove(indexof_studentBook);
//			}
			}
		
	public  void userChoice() {
		try {
			System.out.println("Enter 1 : Home page");
			System.out.println("Enter 2 : view cart");
			System.out.println("Enter 3 : wallet ");
			System.out.println("Enter 0 : return book");
			System.out.println("Enter 4 : logout");
			Scanner sc = new Scanner(System.in);
			int input = sc.nextInt();
			switch(input) {
			case 1:
				Home.home();
				System.out.println("enter 1 to add books  and 0 to go back");
				int value = sc.nextInt();
				switch(value) {
				case 1:Cart cart = new Cart();
					  cart.addToCart();
				break;
				case 0:userChoice();
				break;
				}
				userChoice();
				break;
			case 2:Cart.showCart();
			userChoice();
			break;
			case 3:Wallet obj = new Wallet();
			obj.wallet();
			userChoice();
			break;
			case 0 : returnBook();

			userChoice();
			break;
			case 4: RegisterAdmin registeradmin = new RegisterAdmin();
			registeradmin.choice();
			break;
			}
		}catch(InputMismatchException e) {
			System.out.println("invalid data!!");
			userChoice();
		}

	}
}
