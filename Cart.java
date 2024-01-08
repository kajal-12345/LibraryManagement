package LibraryManagement;
import java.time.LocalDate;
import java.util.Scanner;
public class Cart {	
	LocalDate issue_date;
	int id;
	int roll_no;
	public  void addToCart() {
		if(RegisterStudent.studentList.get(UserLogin.current_user).wallet >= 20) {
			Scanner sc = new Scanner(System.in);
			System.out.println("Enter Book_id to add book to cart");
			id = sc.nextInt();
			boolean isPresent = false;

			for( Books x:RegisterStudent.studentList.get(UserLogin.current_user).cart ) {
				if(x.book_id == id) {
					isPresent = true;
				}
			}
			if(isPresent) {
				System.out.println("sorry!,you can't issue two books of same id ");
			}
			else {
				boolean isPresentInStock = false;
				boolean isZeroQty = false;
				for(Books x : RegisterAdmin.books) {
					if(x.book_id == id && x.quantity > 0) {
						issue_date = java.time.LocalDate.now();

						RegisterStudent.studentList.get(UserLogin.current_user).cart.add(new Books(x.book_id,x.book_name,x.author_name,x.price,x.quantity,issue_date,issue_date.plusDays(7)));

						isPresentInStock = true;
						x.quantity--;
						break;
					}
				}
					for(Books y : RegisterAdmin.books) {
						if(y.quantity  == 0 ) {
							isZeroQty =true;
							break;
						}
					}
					if(isZeroQty) {
						System.out.println("currently book is not available");
						addToCart();
					}
					if(!isPresentInStock) {
						System.out.println("Wrong book-id,please enter again");
						addToCart();
					}
				}
			}
		else {
			System.out.println("you must have atleat 20rs to issue book");
			Wallet obj = new Wallet();
			obj.wallet();
		}
	}
	public static void  showCart() {
		System.out.println("Book_id   "+"BookName   "+"Author    "+"price       "+"issue_date"+"     "+"return date");
		for(Books x : RegisterStudent.studentList.get(UserLogin.current_user).cart) {	
			System.out.println(x.book_id+"       "+x.book_name+"       "+x.author_name+"       "+x.price+"      "+x.issue_date+"        "+x.return_date);	
		}	
		//	buyBook();
	}


	//public static void buyBook() {
	//if(RegisterStudent.studentList.get(UserLogin.current_user).cart.size() > 0) {
	//	Scanner sc = new Scanner(System.in);
	//	try {
	//		System.out.println("Enter 1 to borrow book or Enter 0 to go back");
	//		int input = sc.nextInt();
	//		switch(input) {
	//		case 1:
	//			if(RegisterStudent.studentList.get(UserLogin.current_user).wallet >= 20) {
	//				RegisterStudent.studentList.get(UserLogin.current_user).cart.clear();
	//				roll_no = RegisterStudent.studentList.get(UserLogin.current_user).roll_no;
	//				issue_date = java.time.LocalDate.now();
	//				System.out.println("return before 7 days");
	//			}
	//			else {
	//				System.out.println("you must have atleast 20rs to borrow book");
	//				Wallet obj = new Wallet();
	//				obj.wallet();
	//			}
	//		case 0:UserHome.userChoice();
	//		      break;
	//		}
	//	}catch(InputMismatchException e) {
	//		System.out.println("invalid input type!");
	//		buyBook();
	//	}
	//	
	//}
	//}
}


