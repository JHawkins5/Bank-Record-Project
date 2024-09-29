package CW2;

public abstract class Loan {
    protected String recordID;
    protected String loanType;
    protected double interestRate;
    protected double amountDue;
    protected double loanTermLeft;
    protected double paymentAmount;

    // Getters and setters for recordID, interestRate, amountDue, loanTermLeft, and paymentAmount.
    // There is no setter for loanType as this is dependent on the class.

    public String getRecordID() {
        return recordID;
    }

    public void setRecordID(String recordID) {
        this.recordID = recordID;
    }

    public String getLoanType() {
        return loanType;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    public double getAmountDue() {
        return amountDue;
    }

    public void setAmountDue(double amountDue) {
        this.amountDue = amountDue;
    }

    public double getLoanTermLeft() {
        return loanTermLeft;
    }

    public void setLoanTermLeft(double loanTermLeft) {
        this.loanTermLeft = loanTermLeft;
    }

    public double getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(double paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public String display() {
        // This method returns a string containing all of the instance variables.

        return "RecordID: " + recordID + ". Loan type: " + loanType + ". Interest rate: " + interestRate + "%. Amount due: £" + amountDue * 1000 + ". Loan term left: " + loanTermLeft + " years.";
    }

    public void pay() {
        // This method allows the user to pay the next instalment of their loan back.

        System.out.println("The current amount due is £" + XYZBank.roundTwoDecimalPlaces(amountDue * 1000));
        amountDue -= paymentAmount;

        if (amountDue < 0) {
            // If the amount due is less than zero, set the amount due to zero and tell the user the loan has been repaid.

            amountDue = 0;
            System.out.println("The new amount due is £" + XYZBank.roundTwoDecimalPlaces(amountDue * 1000));
            System.out.println("The loan has been repaid.");
        }
        else {
            // Else, display the new amount due.

            System.out.println("The new amount due is £" + XYZBank.roundTwoDecimalPlaces(amountDue * 1000));
        }
    }

    public void addInterest() {
        // This method adds interest to the amount due.

        System.out.println("The current amount due is £" + XYZBank.roundTwoDecimalPlaces(amountDue * 1000));
        double interest = amountDue * (interestRate / 100);
        amountDue += interest;
        System.out.println("The new amount due is £" + XYZBank.roundTwoDecimalPlaces(amountDue * 1000));
    }
}