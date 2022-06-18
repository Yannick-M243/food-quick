import java.util.Scanner;

public class Restaurant {
	//Restaurant Attributes
	String name;
	String location;
	String phone;
	String fullAddress;

	//Restaurant constructor
	public Restaurant(String name, String location, String phone,String fullAddress) {
		this.name = name;
		this.location = location;
		this.phone = phone;
		this.fullAddress=fullAddress;
	}
	
	//Function to add new Restaurant's objects
	public static Restaurant addRestaurant() {
		Scanner input = new Scanner(System.in);
	
		String name;
		String fullAddress;
		String phone;
		String location;
		
		System.out.println("-----New Restaurant-----");
		System.out.print("Restaurant Name : ");
		name = input.nextLine();
		System.out.print("Phone number : ");
		phone = input.next();
		System.out.print("Location : ");
		input.nextLine();
		location = input.nextLine();
		System.out.print("Physical address : ");
		fullAddress = input.nextLine();
		
		return new Restaurant(name,location,phone,fullAddress);
	}

}
