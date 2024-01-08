package LibraryManagement;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Home {
	int book_id;
	String book_name;
	String author_name;
	double price;
	int quantity;	
	static int indexofUser = 0 ; 
	ArrayList<Books> date_info = new ArrayList<Books>();
	public void removeBook() {
		boolean isPresent = false;
		try {
			Scanner sc = new Scanner(System.in);
			System.out.println("Enter book_id to delete book");
			int input = sc.nextInt();
			for(int i = 0 ; i<RegisterAdmin.books.size();i++) {
				if(RegisterAdmin.books.get(i).book_id == input) {
					RegisterAdmin.books.remove(i);
					isPresent = true;
				}
			}
			if(!isPresent) {
				System.out.println("invalid id");
			}
		}catch(InputMismatchException e) {
			System.out.println("invalid data !!");
			removeBook();
		}

	}

	public void addBook() {
		try {
			boolean	isPresent = false;
			
			Scanner sc = new Scanner(System.in);
			System.out.println("Enter book_id");
			int bookId = sc.nextInt();
//			this.book_id = bookId;

			System.out.println("Enter quantity");
			int qty = sc.nextInt();
//			this.quantity = qty;



			
			for(int i = 0 ; i < RegisterAdmin.books.size();i++) {
				if(RegisterAdmin.books.get(i).book_id == bookId) {
					isPresent = true;
					RegisterAdmin.books.get(i).quantity += qty;
					break;
				}
			}
			if(!isPresent) {
				
				System.out.println("Enter book name");
				String bookName = sc.next();
//				this.book_name = bookName;
				System.out.println("Enter author name");
				String authorName = sc.next();
//				this.author_name = authorName;
				System.out.println("Enter price");
				double bookPrice = sc.nextDouble();
//				this.price = bookPrice;

				RegisterAdmin.books.add( new Books(bookId, bookName, authorName, bookPrice, qty));
				System.out.println("added!");
			}

		}catch(InputMismatchException e) {
			System.out.println("invalid data!!");
			addBook();
		}
	}

	//	
	public void showUserList() {
		System.out.println("userName  "+"roll-no    "+"book-id     "+"issue_date   "+"return_date");
		//	try {
		for(int i = 0 ; i<RegisterStudent.studentList.size();i++) {
//			if(RegisterStudent.studentList.get(i).cart.isEmpty()) {
//				String uname = RegisterStudent.studentList.get(i).userName;
//				int roll_no = RegisterStudent.studentList.get(i).roll_no;
//
//				String id = null;
//				String issue_date = null;
//				String return_date = null;
//				System.out.println(uname+"        "+roll_no+"        "+id+"            "+issue_date+"            "+return_date);
//			}
//			else {
				for(int index = 0 ; index < RegisterStudent.studentList.get(i).cart.size();index++) {
					String uname = RegisterStudent.studentList.get(i).userName;
					int roll_no = RegisterStudent.studentList.get(i).roll_no;
					int id = RegisterStudent.studentList.get(i).cart.get(index).book_id;
					LocalDate issue_date = RegisterStudent.studentList.get(i).cart.get(index).issue_date;
					LocalDate return_date = RegisterStudent.studentList.get(i).cart.get(index ).return_date  ;
					System.out.println(uname+"        "+roll_no+"        "+id+"          "+issue_date+"       "+return_date);
				}
		}	 
	}

	public void issue_Book() {
		System.out.println("Enter username to issue book");
		Scanner sc  = new Scanner(System.in);
		String name = sc.next();
		boolean isUserPresent = false;
		for(int i = 0 ; i < RegisterStudent.studentList.size();i++) 
		{

			if(RegisterStudent.studentList.get(i).userName.equals(name)) {
				isUserPresent = true;
				
				System.out.println("Enter Book_id to add book to cart");
				int id = sc.nextInt();
				boolean isPresent = false;

				for( Books x:RegisterStudent.studentList.get(i).cart ) {
					if(x.book_id == id) {
						isPresent = true;
					}
				}
				if(isPresent) {
					System.out.println("user already have this book ");
				}
				else {
					boolean isPresentInStock = false;
					for(Books x : RegisterAdmin.books) {
						boolean checkWallet = false;
						if(x.book_id == id && x.quantity > 0) {
							if(RegisterStudent.studentList.get(i).wallet > 5) {
								RegisterStudent.studentList.get(i).cart.add(new Books(x.book_id,x.book_name,x.author_name,x.price,x.quantity,LocalDate.now(),LocalDate.now().plusDays(7)));
								checkWallet = true;
								x.quantity--;
							}
							if(!checkWallet) {
								System.out.println("sorry you can't issue book to user ask to add money");
							}
							isPresentInStock = true;

						}
					}
					
					if(!isPresentInStock) {
						System.out.println("Wrong book-id,please enter again");
					}
					
				}
				
			}
		}
		if(!isUserPresent) {
        	System.out.println("user does not exist");
        }
	}
	public  void collect_Book() {
		System.out.println("Enter user name to collect book");
		Scanner sc = new Scanner(System.in);
		String uname = sc.next();
		boolean isUserPresent = false;
		for(int i = 0 ; i < RegisterStudent.studentList.size();i++) {
			if(RegisterStudent.studentList.get(i).userName.equals(uname)) {
				//			Cart.addToCart();
				indexofUser = i;
				isUserPresent = true;
				if(!RegisterStudent.studentList.get(i).cart.isEmpty()) {
					System.out.println("Enter Book_id to collect from user");
					int id = sc.nextInt();
					boolean isPresent = false;
					//					for(Books x:RegisterStudent.studentList.get(indexofUser).cart) {
					//						System.out.println(x.book_id +  x.book_name);
					//						System.out.println(RegisterStudent.studentList.get(indexofUser).cart.indexOf(x));
					////						x.book_id
					//					}
					for(Books x:RegisterStudent.studentList.get(i).cart ) {
						//						if(x!=null) {


						if(x.book_id == id) {
							System.out.println(RegisterStudent.studentList.get(i).cart.indexOf(x));
							checkPenalty(RegisterStudent.studentList.get(i).cart.indexOf(x));
							isPresent = true;
							break;
						}}
					//					}
					if(isPresent) {
						for(int j = 0 ; j < RegisterAdmin.books.size();j++ ) {
							if(RegisterAdmin.books.get(j).book_id == id) {
								RegisterAdmin.books.get(j).quantity++;
							}							
						}
					}
					else {
						System.out.println("id not present");
					}
				}
				else {
					System.out.println("this user does not have book");
				}
			}
			
		}
		
		if(!isUserPresent) {
			System.out.println("user does not exist!!");
			collect_Book();
		}
	}
	public  void checkPenalty(int indexof_studentBook) {
		//	System.out.println(RegisterStudent.studentList.get(indexofUser).cart.get(indexof_studentBook).return_date);

		//		LocalDate nextWeek = RegisterStudent.studentList.get(indexofUser).cart.get(indexof_studentBook).issue_date.plusDays(7);
		//		for(int index = 0 ; index < RegisterStudent.studentList.get(indexofUser).cart.size();index++) {
		if(RegisterStudent.studentList.get(indexofUser).cart.get(indexof_studentBook).return_date.isBefore(LocalDate.now().plusDays(9))) {
			if(RegisterStudent.studentList.get(indexofUser).wallet > 5) {
				RegisterStudent.studentList.get(indexofUser).wallet -= 5;
				RegisterAdmin.wallet += 5;
			}
			else {
				System.out.println("user has insufficient money");
			}
			RegisterStudent.studentList.get(indexofUser).cart.remove(indexof_studentBook);

			//				System.out.println("charge penalty");

		}
		//		}

	}
	public void adminChoice() {

		try {
			System.out.println("Enter 1: Home page");
			System.out.println("Enter 2: Add book");
			System.out.println("Enter 3: Remove book");
			System.out.println("Enter 4: issue book");
			System.out.println("Enter 5: collect book");
			System.out.println("Enter 6: show user list");
			System.out.println("Enter 7: wallet");
			System.out.println("Enter 8: add user");
			System.out.println("Enter 0: logout");
			Scanner sc = new Scanner(System.in);
			int input = sc.nextInt();
			switch (input) {
			case 1:
				if (RegisterAdmin.books.isEmpty()) {
					System.out.println("books not available!!,add books");
					adminChoice();
				} else {
					home();
					adminChoice();
				}
				break;
			case 2:
				addBook();
				adminChoice();
				break;
			case 3:removeBook();
			adminChoice();
			break;
			case 4:issue_Book();
			adminChoice();
			break;
			case 5:collect_Book();

			adminChoice();
			break;
			case 6:showUserList();
			adminChoice();
			break;
			case 7:AdminWallet adminwallet = new AdminWallet();
			adminwallet.wallet();
			adminChoice();
			break;
			case 8:RegisterStudent ob = new RegisterStudent();
			ob.register(ob);
			ob.addStudent();
			adminChoice();
			break;
			case 0:RegisterAdmin registeradmin = new RegisterAdmin();
			registeradmin.choice();
			break;
			default : System.out.println("invalid input!");
			adminChoice();
			}

		}catch(InputMismatchException e) {
			System.out.println("invalid data!!");
			adminChoice();
		}
	}

	public static void home() {
		System.out.println("book-id  " + "book_name  " + "author_name  " + "price  " + "quantity  ");

		for (Books x : RegisterAdmin.books) {

			System.out.println(
					x.book_id + "         " + x.book_name + "         " + x.author_name + "     " + x.price + "        " + x.quantity);
		}
	}
}
