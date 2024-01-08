package LibraryManagement;

public class DriverClass {
public static void main(String[] args) {

	RegisterAdmin.createUser();
	RegisterStudent.userStudent();
	RegisterAdmin registeradmin = new RegisterAdmin();
	registeradmin.choice();
//	RegisterAdmin.createUser();
//	RegisterStudent.getUser();
}
}
