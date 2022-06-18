import java.util.Scanner;

public class Meal {
	//Meal Attributes
	String name;
	int price;
	int quantity;
	String instruction;

	//Meal constructor
	public Meal(String name, int price, int quantity, String instruction) {
		this.name = name;
		this.price = price;
		this.quantity = quantity;
		this.instruction = instruction;
	}

	//function to add a new Meal's objects
	public static Meal addMeal() {
		Scanner input = new Scanner(System.in);
	
		String name;
		int price;
		int quantity;
		String instruction;
		
		System.out.println("-----New Meal-----");
		System.out.print("Meal name : ");
		name = input.nextLine();
		System.out.print("Price  : ");
		price = input.nextInt();
		System.out.print("Quantity : ");
		quantity = input.nextInt();
		input.nextLine();
		System.out.print("Instruction : ");
		instruction = input.nextLine();
		
		return new Meal(name,price,quantity,instruction);
	}
}
