import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

import model.entities.CarRental;
import model.entities.Vehicle;
import model.services.BrazilTaxService;
import model.services.RentalService;

public class Program {

	public static void main(String[] args) throws ParseException {
		
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:ss");
		
		System.out.println("Enter rental data:");
		System.out.println("Car model:");
		String model = sc.nextLine();
		System.out.println("Pickup (dd/MM/yyyy hh:mm:ss):");
		Date start = sdf.parse(sc.nextLine());
		System.out.println("Return: ");
		Date finish = sdf.parse(sc.nextLine());
		
		CarRental cr = new CarRental(start, finish, new Vehicle(model));
		
		System.out.println("Enter price per hour:");
		double pricePerHours = sc.nextDouble();
		System.out.println("Enter price per day:");
		double pricePerDay = sc.nextDouble();
		
		RentalService rentalService = new RentalService(pricePerHours, pricePerDay, new BrazilTaxService());
		
		rentalService.processInvoice(cr);
		
		System.out.println("Invoice:");
		System.out.println("Basic payment: " + String.format("%.2f", cr.getInvoice().getBasicPayment()));
		System.out.println("Tax: " + String.format("%.2f", cr.getInvoice().getTax()));
		System.out.println("Total payment: " + String.format("%.2f", cr.getInvoice().getTotalPayment()));
		
		sc.close();
		
	}

}
