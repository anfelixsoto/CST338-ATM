/*Name:Antonio Felix
 *Date: 3/7/2019
 * Abstract: Allows user to create customers in their system to use around in ATM class methods.
 */
import java.text.DecimalFormat;

public class Customer {

    DecimalFormat df=new DecimalFormat( "0.00" );

    String firstName;
    int accountPin;
    double customerBalance;
    String customerBank;


    public Customer() {
        firstName="UNKNOWN";
        accountPin=0;
        customerBalance=0;
        customerBank="UNKNOWN";
    }


    public Customer(String firstName, int accountPin, double customerBalance, String customerBank) {
        this.firstName=firstName;
        this.accountPin=accountPin;
        this.customerBalance=customerBalance;
        this.customerBank=customerBank;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName=firstName;
    }

    public int getAccountPin() {
        return accountPin;
    }

    public void setAccountPin(int accountPin) {
        this.accountPin=accountPin;
    }

    public double getCustomerBalance() {
        return customerBalance;
    }

    public void setCustomerBalance(double customerBalance) {
        this.customerBalance=customerBalance;
    }

    public String getCustomerBank() {
        return customerBank;
    }

    public void setCustomerBank(String customerBank) {
        this.customerBank=customerBank;
    }

    public String toString() {
        return (firstName +
                ": Balance $" + df.format( customerBalance ));
    }

}
