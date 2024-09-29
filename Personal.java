package CW2;

public class Personal extends Loan {
    public Personal() {
        // Default constructor containing no arguments, sets default values.

        recordID = "XXXXXX";
        loanType = "Personal";
        interestRate = 0;
        amountDue = 0;
        loanTermLeft = 0;
        paymentAmount = 0;
    }

    public Personal(String recordID) {
        // This constructor takes a recordID and creates a record with that recordID, and sets defaults values for the other variables.

        this.recordID = recordID;
        loanType = "Personal";
        interestRate = 0;
        amountDue = 0;
        loanTermLeft = 0;
        paymentAmount = 0;
    }

    public Personal(String recordID, double interestRate, double amountDue, double loanTermLeft, double paymentAmount) {
        // This constructor takes inputs for all variables and sets the attributes appropriately.
        
        this.recordID = recordID;
        loanType = "Personal";
        this.interestRate = interestRate;
        this.amountDue = amountDue;
        this.loanTermLeft = loanTermLeft;
        this.paymentAmount = paymentAmount;
    }
}