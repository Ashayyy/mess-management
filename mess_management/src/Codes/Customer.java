//Customer id(int) , first name(String), last name (String), email(String), password(String), Address String
//, RegisterDate(LocalDate), planEndDate(LocalDate), Address(String), Phone No(String),plan Enum, final_amount double

//Customer id(int) , first name(String), last name (String), email(String), password(String), Address String, RegisterDate(LocalDate), planEndDate(LocalDate), Address(String), Phone No(String),plan Enum, final_amount double
//Messplan(Plan : enum) Plans Are as follows:-
// Monthly (3000), Quarterly (11700), Half Year (17500), Yearly (32000)

package Codes;
import java.time.*;
public class Customer implements Comparable{


    private double regamount;
	private int custid;
	private String firstname,lastname,email,password,address,phoneno;
	private LocalDate registerdate;
	private LocalDate enddate;
	private double finalamount;
	private MessPlan plan;
	
	public Customer(int custid) {
		this.custid = custid;
	}
	
	public Customer(int id , String firstname , String lastname , String email , String password , String address ,LocalDate registerdate , LocalDate enddate,String phoneno, MessPlan plan,double regamount)
	{
	   this.custid = custid;
	   this.firstname=firstname;
	   this.lastname=lastname;
	   this.email=email;
	   this.password=password;
	   this.address=address;
	   this.registerdate=registerdate;
	   this.phoneno=phoneno;
	   this.plan=plan;
	   this.enddate=enddate;
	   this.regamount=regamount;
	}
	
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhoneno() {
		return phoneno;
	}
	public void setPhoneno(String phoneno) {
		this.phoneno = phoneno;
	}
	public LocalDate getRegisterdate() {
		return registerdate;
	}
	public void setRegisterdate(LocalDate registerdate) {
		this.registerdate = registerdate;
	}

	public double getFinalamount() {
		return finalamount;
	}
	public void setFinalamount(double finalamount) {
		this.finalamount = finalamount;
	}
	public MessPlan getPlan() {
		return plan;
	}

	public void setPlan(MessPlan plan) {
		this.plan = plan;
	}

	@Override
	public String toString() {
		return "Customer [custid=" + custid + ", firstname=" + firstname + ", lastname=" + lastname + ", email=" + email
				+ ", password=" + password + ", address=" + address + ", phoneno=" + phoneno + ", registerdate="
				+ registerdate +"enddate = "+enddate +", finalamount=" + regamount + ", plan=" + plan + "]";
	}

	public double getRegamount() {
		return regamount;
	}

	public void setRegamount(double regamount) {
		this.regamount = regamount;
	}

	public LocalDate getEnddate() {
		return enddate;
	}

	public void setEnddate(LocalDate enddate) {
		this.enddate = enddate;
	}

	public int getCustid() {
		return custid;
	}
	public int getId() {
		return custid;
	}


	@Override
	public int compareTo(Object o) {
		if( o instanceof Customer)
		return this.getFirstname().compareTo( ((Customer)o).getFirstname() );
		return -1;
	}
	
	
}
