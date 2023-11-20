//a) Validate Customer check for Duplication
//b) Validatecustomer login if not exist or invalid mail & invalid password
//Email must be like regex provided in :
//"[A-Z0-9a-z._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}"
//e.g user1.dac@iacsd.com
//c) ParseValidatePlan , registration amount must be match with plan
//d) Parse local Date
//registration date can be only todays date or after todays date and end date must be depend on plan selected
//e.g if user reg date is 11/10/2023 and selected plan is of 1 year then end date set automatically as 11/12/2024
//e) Phone number must be only 10 character and the number could be between 0 to 9. 
//Hint : regex

package Codes;

import java.util.Scanner;
import java.time.LocalDate;
import java.util.Map;

public class CustomerValidations {

	// ---------------------- Validate Registeration Date -----------------------

	public static LocalDate ValidateDateAndGetPlan(String date, String plan, double regamount)
			throws InavlidCustomerException {

		if (MessPlan.valueOf(plan).getPrice() != regamount) {
			throw new InavlidCustomerException(" Registeration Amount doesn't matches Subscription type..!! ");
		}

		LocalDate enddate = null;
		LocalDate regdate = LocalDate.parse(date);
		if (LocalDate.now().isBefore(LocalDate.parse(date)) || LocalDate.now().isEqual(LocalDate.parse(date))) {
			if (MessPlan.valueOf(plan).name().equals("YEARLY")) {
				enddate = regdate.plusMonths(12);
			} else if (MessPlan.valueOf(plan).name().equals("HALFYEARLY")) {
				enddate = regdate.plusMonths(6);
			} else if (MessPlan.valueOf(plan).name().equals("QUARTERLY")) {
				enddate = regdate.plusMonths(3);
			} else if (MessPlan.valueOf(plan).name().equals("MONTHLY")) {
				enddate = regdate.plusMonths(1);
			} else {
				throw new InavlidCustomerException("Invalid Plan Opted..!!");
			}
		} else {
			throw new InavlidCustomerException(" Date Must be Today's or of future. ");
		}

		return enddate;
	}

	/// ---------------------- Validate Phone Number
	/// -------------------------------------

	public static void ValidatePhoneNumber(String phonenumber) throws InavlidCustomerException {

		if (phonenumber.length() != 10 && !phonenumber.matches("[0-9]")) {
			throw new InavlidCustomerException("Phone Number must be of Length 10.");
		}

	}

	// ----------------------------- Validate password
	// ----------------------------------

//	   public static void ValidatePassword(String password) throws InavlidCustomerException {
//		
//		       if(! password.matches("[A-Z0-9][a-z]+[!@#$%^&*()]+[A-Z0-9]+[a-z0-9]"))
//				   {
//			   throw new InavlidCustomerException("Inavlid Password Format..!! \n Valid Password Format -> [A-Z0-9][a-z]+[!@#$%^&*()]+[A-Z0-9]+[a-z0-9]");
//				   }
//		   
//	   }

	// --------------------------- Validate Email
	// --------------------------------------

	public static void ValidateEmail(String email) throws InavlidCustomerException {

		if (!email.matches("[a-z0-9]+@+[a-z]+[.]+[a-z]")) {
			throw new InavlidCustomerException(
					"Invalid Email Format..!!\n Email Format must consist of -> [a-z]+[0-9]+@+[a-z]+.+com/org/in ");
		}

	}

	// ---------------- Duplicacy Customer Checking ----------------------

	public static void checkDuplicacy(Map<Integer, Customer> customer, int custid) throws InavlidCustomerException {

		if (customer.containsKey(custid)) {
			throw new InavlidCustomerException("Duplicate Customer ID. Customer Already Exists");
		}
	}

	// ----------------------------- Validate SignUp ------------------------------

	public static Customer ValidateSignUp(int id, String firstname, String lastname, String email, String password,
			String Address, String RegisterationDate, String PhoneNumber, String Plan, double regamount)
			throws InavlidCustomerException {

		// ValidateEmail(email);
		LocalDate enddate = ValidateDateAndGetPlan(RegisterationDate, Plan.toUpperCase(), regamount);
		return new Customer(id, firstname, lastname, email, password, Address, LocalDate.parse(RegisterationDate),
				enddate, PhoneNumber, MessPlan.valueOf(Plan), regamount);

	}

	// ----------------------------- Validate Login ------------------------------

	public static void ValidateLogin(Map<Integer, Customer> customer, int id, String password)
			throws InavlidCustomerException {

		boolean status = customer.containsKey(id);

		if (status == false)
			throw new InavlidCustomerException("Customer ID does not Exists..!!");

		Customer index = customer.get(id);
		if (index.getPassword().equals(password)) {
			System.out.println("Welcome Back. You Have SuccessFully Logged In..!!");
			System.out.println(index.toString());
		} else
			throw new InavlidCustomerException("Invalid Password, please try again..!!");
	}

	// -------------------- Update Password -----------------------------

	public static void ChangePassword(int custid, String password, Map<Integer, Customer> customer)
			throws InavlidCustomerException {

		boolean status = customer.containsKey(custid);
		Scanner sc = new Scanner(System.in);
		if (!status)
			throw new InavlidCustomerException("Customer ID does not Exists..!!");

		Customer index = customer.get(custid);
		if (index.getPassword().equals(password)) {
			System.out.println("Enter new password ");
			String newpassword = sc.next();
			// ValidatePassword(newpassword);
			index.setPassword(newpassword);
			System.out.println("PassWord Updated Successfully..!!");
		} else {
			throw new InavlidCustomerException("Invalid Password, please try again..!!");
		}

		// --------------------- Display Emails of Customer who took subscription in
		// january

	}

}
