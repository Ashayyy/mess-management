package Tester;

import static Codes.CustomerValidations.ChangePassword;
import static Codes.CustomerValidations.ValidateLogin;
import static Codes.CustomerValidations.ValidateSignUp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.Period;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

import Codes.Customer;
import Codes.InavlidCustomerException;

public class MessServiceTester {

	public static void main(String[] args) throws InavlidCustomerException {
		try (Scanner sc = new Scanner(System.in)) {
			int choice;
			Map<Integer, Customer> customer = new HashMap<>();

			System.out.println("1. Sign up Customer\r\n" + "2. sign in\r\n" + "3. Change Password  \r\n"
					+ "4. Sort Customer as per \r\n" + "         A) First name \r\n"
					+ "         B) According to plan\r\n" + "         C) According to date of registration\r\n"
					+ "5. Unsubscribe customer according plan duration (month wise (1, 3, 6, 12))\r\n"
					+ "6. Display all Customers\r\n"
					+ "7) Modify all customers first name (make first Letter capital of customers first name)\r\n"
					+ "8) Display email addresses of the customers who did registration in month of January\r\n"
					+ "9) Display count of the Customers who have register for plan: Monthly\r\n"
					+ "10) Search the Customer who lived in Akurdi.\r\n"
					+ "11) Give the 20% discount to the customers who have selected plan for 1 year.\n"
					+ "12) Store Data in Char\n"
					+ "13) Restore data from text file\n" 
					+ "14) EXIT");
			do {
				System.out.println("Enter your choice - ");
				choice = sc.nextInt();
				try {
					
					switch (choice) {
					case 1: {
						System.out.println(
								"Enter Customer id,  first name, last name, email,password, Address , RegisterationDate ,Phone Number , Plan and Registeration Amount");
						int custid = sc.nextInt();
						//checkDuplicacy(customer, custid);
						Customer c = ValidateSignUp(custid, sc.next(), sc.next(), sc.next(), sc.next(), sc.next(),
								sc.next(), sc.next(), sc.next().toUpperCase(),sc.nextDouble());
						customer.put(custid, c);
						System.out.println("New Customer Added Successfully..!! ");

					}
						break;

					case 2: {
						System.out.println("Enter Customer ID and password");
						ValidateLogin(customer, sc.nextInt(), sc.next());

					}
						break;

					case 3: {
						System.out.println("Enter customer ID and Current Password");
						ChangePassword(sc.nextInt(), sc.next(), customer);

					}
						break;
					case 4: {
						System.out.println(
								"Sort As per \n 1). First Name \n 2).According to Plan \n 3).According to Date");
						switch (sc.nextInt()) {
						case 1: {
							customer.values().stream().sorted().forEach(System.out::println);
						}
							break;
						case 2: {
							customer.values().stream().sorted((c1, c2) -> {
								return c1.getPlan().compareTo(c2.getPlan());
							}).forEach(System.out::println);
						}
							break;
						case 3: {
							customer.values().stream().sorted((c1, c2) -> {
								return c1.getRegisterdate().compareTo(c2.getRegisterdate());

							}).forEach(System.out::println);
						}
							break;
						default:
							throw new InavlidCustomerException("Invalid Input..!!");
						}
					}
						break;
					case 5: {
						
						Iterator<Customer> itr = customer.values().iterator();
						while (itr.hasNext()) {
							long time = Period.between(itr.next().getRegisterdate(), LocalDate.now()).toTotalMonths();
							if (time > 12 && itr.next().getPlan().equals("YEARLY")) {
								itr.remove();
								System.out.println(
										itr.next() + "'s yearly Subscription has been removed Successfully..!!");
							} else if (time > 6 && itr.next().getPlan().equals("HALFYEARLY")) {
								itr.remove();
								System.out.println(
										itr.next() + "'s half-year Subscription has been removed Successfully..!!");
							} else if (time > 3 && itr.next().getPlan().equals("QUARTERLY")) {
								itr.remove();
								System.out.println(
										itr.next() + "'s quarter Subscription has been removed Successfully..!!");
							} else if (time > 1 && itr.next().getPlan().equals("MONTHLY")) {
								itr.remove();
								System.out.println(
										itr.next() + "'s Monthly Subscription has been removed Successfully..!!");
							}
						}

					}
						break;
						
					case 6: {
						Iterator<Customer> itr = customer.values().iterator();
						while(itr.hasNext()) {
							System.out.println(itr.next());
						}
					}
						break;
					case 7: {
						
						for(Customer c : customer.values())
						{
							String s = c.getFirstname().substring(0, 1).toUpperCase() + c.getFirstname().substring(1);
							c.setFirstname(s);
						}
                       System.out.println("Successfully Updated..!!");
					}
						break;
					case 8: {
						customer.values().stream().filter(i->i.getRegisterdate().getMonth().getValue()==1).forEach(System.out::println);
					}
						break;
					case 9: {
						long c =customer.values().stream().filter(i->i.getPlan().name().equals("MONTHLY")).count();
						System.out.println("No of Monthly Subscribed Customers = "+c);
					}
						break;
					case 10: {
						customer.values().stream().filter(i->i.getAddress().equals("Akurdi")).forEach(System.out::println);
					}
						break;
					case 11: {
						List<Customer> list =customer.values().stream()
								.filter(i->i.getPlan().name().equalsIgnoreCase("YEARLY"))
								.peek(p->p.setRegamount(p.getRegamount()*0.80))
								.collect(Collectors.toList());
						
						list.stream().forEach(System.out::println);
					}
						break;
						
					case 12:{
						System.out.println("Enter the file name");
						
						try(PrintWriter pw = new PrintWriter(new FileWriter(sc.next()))){
							customer.values().stream().forEach(pw::println);
						}
						System.out.println("DATA ADDED SUCCESSFULLY..!!");
						
					} 	break;
					
					case 13:{
						System.out.println("Enter file name ");
						try(BufferedReader br = new BufferedReader(new FileReader(sc.next()))){
					     br.lines().forEach(System.out::println);		
						}
						
					} break;
					
					case 14:break;
					
					default:
						System.out.println("Invalid Input..!!");
						break;
					}
				} catch (Exception e) {
					System.out.println(e);
				}
			} while (choice != 12);
		}
	}

}
