/*Name:Antonio Felix
*Date: 3/7/2019
* Abstract: Creating a vitural ATM that holds customers and the information of the atm
 */

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;


public class ATM {

    private List<Customer> customers=new ArrayList<>( 10 );
    DecimalFormat df=new DecimalFormat( "0.00" );

    int ATMId;
    String bankName;
    String ATMLocation;
    double funds;
    int transactions;
    int withTransaction, withSuccess, withFail;
    int depositTransaction, depSuccess, depFail;
    int transferTransaction, tranSuccess, tranFail;

    public ATM() {
        ATMId=0;
        bankName="UNKNOWN";
        ATMLocation="UNKNOWN";
        funds=100.00;
    }

    public ATM(int ATMId, String bankName, String ATMLocation, double funds) {
        addCustomerData();
        this.ATMId=ATMId;
        this.bankName=bankName;
        this.ATMLocation=ATMLocation;
        this.funds+=funds;
    }

    public ATM(String bankName) {
        addCustomerData();
        ATMId=0;
        this.bankName=bankName;
        this.ATMLocation="UNKNONW";
        funds=100.00;
    }

    public ATM(int ATMId, String bankName, String ATMLocation) {
        addCustomerData();
        this.ATMId=ATMId;
        this.bankName=bankName;
        this.ATMLocation=ATMLocation;
        funds=100.00;
    }

    private void addCustomerData() {
        customers.add( new Customer( "Alice", 1234, 5000, "OtterUnion" ) );
        customers.add( new Customer( "Tom", 2000, 200, "OtterUnion" ) );
        customers.add( new Customer( "Monica", 3000, 50, "OtterUnion" ) );
        customers.add( new Customer( "Michael", 7777, 0, "OtterUnion" ) );
        customers.add( new Customer( "John", 8000, 500, "OtterUnion" ) );
        customers.add( new Customer( "Jane", 2222, 500, "OtterUnion" ) );
        customers.add( new Customer( "Robert", 2323, 200, "BOA" ) );
        customers.add( new Customer( "Owen", 4455, 50, "BOA" ) );
        customers.add( new Customer( "Chris", 8787, 10, "BOA" ) );
        customers.add( new Customer( "Rebecca", 8080, 555.55, "BOA" ) );
    }

    private int findCustomer(String firstName) {
        for (int i=0; i < customers.size(); i++) {
            if (customers.get( i ).getFirstName() == firstName) {
                return i;
            }
        }
        return -1;
    }

    public String toString() {

        return ("Serial Number: " + ATMId + "\n") +
                ("Bank Name: " + bankName + "\n") +
                ("Location: " + ATMLocation + "\n") +
                ("Balance: $" + df.format( funds ) + "\n");

    }

    public boolean equals(ATM obj) {
        return (this.ATMId == obj.ATMId)
                && (this.ATMLocation.equals(obj.ATMLocation));
    }

    public int getATMId() {
        return ATMId;
    }

    public void setATMId(int ATMId) {
        this.ATMId=ATMId;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName=bankName;
    }

    public String getATMLocation() {
        return ATMLocation;
    }

    public void setATMLocation(String ATMLocation) {
        this.ATMLocation=ATMLocation;
    }

    public double getFunds() {
        return funds;
    }

    public void setFunds(double funds) {
        this.funds=funds;
    }

    public void setATM(int ATMId, String ATMLocation) {
        this.ATMId=ATMId;
        this.ATMLocation=ATMLocation;
    }

    public void addFund(double funds) {
        this.funds+=funds;
    }

    public void displayMenu() {
        System.out.println( "===== ATM Transaction Menu ====" );
        System.out.println( "    1.Withdrawal" );
        System.out.println( "    2.Deposit" );
        System.out.println( "    3.Transfer" );
    }

    public void status() {
        System.out.println( "Serial Number: " + ATMId );
        System.out.println( "Bank Name: " + bankName );
        System.out.println( "Location: " + ATMLocation );
        System.out.println( "Balance: $" + df.format( funds ) );
        System.out.println( transactions + " transactions so far: " );
        System.out.println( "  " + "Withdrawal: " + withTransaction + " (" + withSuccess + ", " + withFail + " fail)" );
        System.out.println( "  " + "Deposit: " + depositTransaction + " (" + depSuccess + ", " + depFail + " fail)" );
        System.out.println( "  " + "Transfer: " + transferTransaction + " (" + tranSuccess + ", " + tranFail + " fail)" );
    }

    public boolean isCustomer(String firstName) {
        int i=findCustomer( firstName );

        if (i == -1) {
            return false;
        } else {
            return true;
        }
    }

    public Customer getCustomer(String firstName) {
        return (Customer) customers.get( findCustomer( firstName ) );
    }

    public void withdrawal(String customerName, int accountPin, double amount) {
        transactions++;
        withTransaction++;
        int i=findCustomer( customerName );

        if (i == -1) {
            System.out.println( "Fail - Withdrawal" );
            withFail++;
            return;
        } else {
            if (bankName.equals( customers.get( i ).getCustomerBank() )) {
                if (accountPinCheck( i, accountPin )) {
                    if (amountCheck( i, amount ) && funds >= amount) {
                        System.out.println( "Succeed - Withdrawal" );
                        withdrawalMoney( i, amount );
                        withSuccess++;
                        return;
                    }
                }
            }

        }
        System.out.println( "Fail - Withdrawal" );
        withFail++;
    }

    public void deposit(String firstName, int accountPin, double newBalance) {
        transactions++;
        depositTransaction++;
        int i=findCustomer( firstName );

        if (i == -1) {
            System.out.println( "Fail - Deposit" );
            depFail++;
            return;
        } else {
            if (bankName.equals( customers.get( i ).getCustomerBank() )) {
                if (accountPinCheck( i, accountPin )) {
                    System.out.println( "Succeed - Deposit" );
                    depSuccess++;
                    depositMoney( i, newBalance );
                    return;
                }
            }
        }
        System.out.println( "Fail - Deposit" );
        depFail++;
    }

    public boolean transfer(String sourceName, int sourcePin, double tranBalance, String destName, int destPin) {
        transactions++;
        transferTransaction++;
        int sourceIndex=findCustomer( sourceName );
        int destIndex=findCustomer( destName );

        if (sourceIndex == -1 || destIndex == -1) {
            System.out.println( "Fail - Transfer" );
            tranFail--;
            return false;
        } else {
            if (bankName.equals( customers.get( sourceIndex ).getCustomerBank() ) && bankName.equals( customers.get( destIndex ).getCustomerBank() )) {
                if (accountPinCheck( sourceIndex, sourcePin ) && accountPinCheck( destIndex, destPin )) {
                    if (amountCheck( sourceIndex, tranBalance )) {
                        moneyTransfer( sourceIndex, destIndex, tranBalance );
                        System.out.println( "Success - Transfer" );
                        tranSuccess++;
                        return true;
                    }
                }
            }
        }
        System.out.println( "Failed - Transfer" );
        tranFail++;
        return false;
    }

    public boolean accountPinCheck(int index, int accountPin) {
        if (customers.get( index ).getAccountPin() == accountPin) {
            return true;
        } else {
            return false;
        }
    }

    public boolean amountCheck(int index, double amount) {
        double customerBalance=customers.get( index ).getCustomerBalance();
        if (customerBalance >= amount) {
            return true;
        } else {
            return false;
        }
    }

    public void withdrawalMoney(int index, double amount) {
        double customerBalance=customers.get( index ).getCustomerBalance();
        customerBalance-=amount;
        funds-=amount;
        setFunds( funds );
        customers.get( index ).setCustomerBalance( customerBalance );
    }

    public void moneyTransfer(int index, int index2, double tranBalance) {
        double sourceBalance=customers.get( index ).getCustomerBalance();
        double destBalance=customers.get( index2 ).getCustomerBalance();
        sourceBalance=-tranBalance;
        destBalance=+tranBalance;
        customers.get( index ).setCustomerBalance( sourceBalance );
        customers.get( index ).setCustomerBalance( destBalance );
    }

    public void depositMoney(int index, double addedMoney) {
        double customerBalance=customers.get( index ).getCustomerBalance();
        customerBalance+=addedMoney;
        funds+=addedMoney;
        customers.get( index ).setCustomerBalance( customerBalance );
        setFunds( funds );
    }

}