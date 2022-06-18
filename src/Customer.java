import java.util.Scanner;

public class Customer {
	//Customer Attributes
	String name;
	String surname;
	String email;
	String phone;
	String location;
	String fullAddress;

	//Customer Constructor
	public Customer(String name, String surname, String email, String phone, String location,String fullAddress) {
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.phone = phone;
		this.location = location;
		this.fullAddress=fullAddress;
	}

	//Function to add new Customer's objects
	public static Customer addCustomer() {
		Scanner input = new Scanner(System.in);
	
		String name;
		String surname;
		String email;
		String phone;
		String location;
		String fullAddress;
		
		System.out.println("-----New User-----");
		System.out.print("First Name : ");
		name = input.next();
		System.out.print("Surname : ");
		surname = input.next();
		System.out.print("Email address : ");
		email = input.next();
		System.out.print("Phone number : ");
		phone = input.next();
		System.out.print("Location : ");
		input.nextLine();
		location = input.nextLine();
		System.out.print("Physical address : ");
		fullAddress = input.nextLine();
		
		return new Customer(name,surname,email,phone,location,fullAddress);
		
	}
}
