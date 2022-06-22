import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.Random;
import java.util.Scanner;

public class Main {

	public static void main(String args[]) {
		//Initialising file paths
		String filePath="src/drivers.txt";
		String newFilePath="src/Invoice.txt";
		
		/*------------These are example objects that can be used to test the program--------*/
		Customer cust1 = new Customer("Yannick","Makwenge","Yannick@hotmail.com","07845166818","Bloemfontein","31 Wilderness Road");
		Restaurant rest1 = new Restaurant("Steers","Bloemfontein","07845166818","02 longstreet");
		
		//Creating new Objects of customer and restaurant
		//Customer cust1 = Customer.addCustomer();
		//Restaurant rest1 = Restaurant.addRestaurant();

		//calling the function writeInvoice to generate a new Invoice file
		 writeInvoice(newFilePath,filePath,cust1,rest1);
		
	}

	//This function read the driver.txt file and stores the drivers into a Arraylist
	public static ArrayList<String> readDriver(String filePath) {
		ArrayList<String> drivers = new ArrayList();

		try {
			File x = new File(filePath);
			Scanner sc = new Scanner(x);

			while (sc.hasNext()) {
				drivers.add(sc.nextLine());
			}
			sc.close();
		}

		catch (FileNotFoundException e) {
			System.out.println("This file cannot be found in this directory");
		}

		return drivers;
	}

	// This function finds the nearest driver from the restaurant
	public static String findDriver(ArrayList<String> arr, Restaurant rest) {
		// Initialising variables
		ArrayList<String> nearestDrivers = new ArrayList();
		int loadNumber = 0;
		String selectedDriver = "";
		int lowestLoad = (int) Double.POSITIVE_INFINITY;

		// Get the list of the drivers in the same area as the restaurant
		for (int i = 0; i < arr.size(); i++) {
			if (arr.get(i).contains(rest.location)) {
				nearestDrivers.add(arr.get(i));
			}
		}

		// Getting the smallest number of load from the drivers that are near the
		// restaurant
		if (nearestDrivers.isEmpty()) {
			selectedDriver = "No drivers are in the same area of this restaurant";
		} else {
			for (int i = 0; i < nearestDrivers.size(); i++) {

				char[] chars = nearestDrivers.get(i).toCharArray();
				for (char c : chars) {
					if (Character.isDigit(c)) {
						loadNumber = Character.getNumericValue(c);
					}
				}
				if (loadNumber < lowestLoad) {
					lowestLoad = loadNumber;
				}
			}
		}

		// Selecting the driver with the smallest load near the restaurant
		for (int i = 0; i < nearestDrivers.size(); i++) {
			if (nearestDrivers.get(i).contains(String.valueOf(lowestLoad))) {
				selectedDriver = nearestDrivers.get(i);
			}
		}

		return selectedDriver;
	}

	//This function is the function that creates the Invoice.txt file with all the required details
	public static void writeInvoice(String newFilePath, String filePath,Customer cust,Restaurant rest) {
		
		//Creating 2 instances of Meal object for 2 different meals
		Meal meal1 = Meal.addMeal();
		Meal meal2 = Meal.addMeal();
		
		//Initialising totals variables for the invoice
		int totalMeal = meal1.price * meal1.quantity;
		int total = 0; 
		
		String driver = findDriver(readDriver(filePath),rest);//call the function findDriver and stores the nearest driver to a variable driver
		Boolean isTooFar= checkDriverArea(driver,cust);//call the function Check driver area that return a boolean value

		//Generating a random number that will be used as Invoice number
		Random random = new Random();
		int orderNum = random.nextInt(1000000);
		
		try {
			Formatter f = new Formatter(newFilePath);
			
			//If the driver is in the same area as the customer It will generate an invoice, otherwise it will print a message
			if(!isTooFar) {
			f.format("%s", "--------------------FOOD QUICK INVOICE-------------------\r\n");
			f.format("%s %s","Driver : " , driver+ "\r\n");
			f.format("%s %s %s", "Order ", "number ", orderNum + "\r\n");
			f.format("%s %s", "Customer: ", cust.name + " " + cust.surname + "\r\n");
			f.format("%s %s", "Email: ", cust.email + "\r\n");
			f.format("%s %s", "Phone number: ", cust.phone + "\r\n");
			f.format("%s %s", "Location: ", cust.location + "\r\n");
			f.format("%s", "\r\n");
			f.format("%s %s %s %s %s", "You have ordered the following from ", rest.name + " ", "in ",
					rest.location +" ",rest.fullAddress+"\r\n");
			f.format("%s", "\r\n");
			f.format("%s %s %s", meal1.quantity + "x ", meal1.name, "(" + totalMeal + " R)\r\n");
			if(!meal1.instruction.equals("")) {
				f.format("%s %s", "Special instructions : ", meal1.instruction+ "\r\n");
				f.format("%s", "\r\n");
			}
			total+=totalMeal;
			totalMeal = meal2.price * meal2.quantity;
			f.format("%s %s %s", meal2.quantity + "x ", meal2.name, "(" + totalMeal + " R)\r\n");
			if(!meal2.instruction.equals("")) {
				f.format("%s %s", "Special instructions : ", meal2.instruction+ "\r\n");
			}
			total+=totalMeal;
			f.format("%s", "\r\n");
			f.format("%s %s", "Total :  ", total + " R\r\n");
			f.format("%s %s", "John Krill ", "is nearest to the restaurant and so he will be delivering your\r\n");
			f.format("%s", "order to you at:\r\n");
			f.format("%s", "\r\n");
			f.format("%s", cust.fullAddress + "\r\n");
			f.format("%s", "\r\n");
			f.format("%s %s", "If you need to contact the restaurant, their number is ", rest.phone + "\r\n");
			
			}else {
				f.format("%s", "Sorry! Our\r\n"
						+ "drivers are too far away from you to be able to\r\n"
						+ "deliver to your location.");
			}

			f.close();
			System.out.println("Invoice.txt file generated");
		} catch (Exception e) {
			System.out.println("An error occured while generating the invoice file");
		}
		
	}		
	
	//This function checks if the driver is in the same area as the customer
	public static Boolean checkDriverArea(String driver, Customer cust) {
			Boolean isTooFar=false;
			if (driver.contains(cust.location)){
				isTooFar =false;
			}else {
				isTooFar =true;
			}
			return isTooFar;		
			}
}
