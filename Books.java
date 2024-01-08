package LibraryManagement;

import java.time.LocalDate;

public class Books {
 int book_id;
String book_name;
String author_name;
double price;
int quantity;
LocalDate issue_date;
LocalDate return_date;
String returnDate;
Books(int book_id,String book_name,String author_name,double price,int quantity){
	this.book_id = book_id;
	this.book_name = book_name;
	this.author_name = author_name;
	this.price = price;
	this.quantity = quantity;
}
Books(int book_id,String book_name,String author_name,double price,int quantity ,LocalDate issue_date, LocalDate return_date){
	this.book_id = book_id;
	this.book_name = book_name;
	this.author_name = author_name;
	this.price = price;
	this.quantity = quantity;
	this.issue_date = issue_date;
	this.return_date = return_date;
}

}
