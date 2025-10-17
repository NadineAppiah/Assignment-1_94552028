public enum AccountType {
    CURRENT, SAVINGS

}

//Description: This method tries to deduct the specified amount from the account
//balance and increments the number of withdrawals. If the transaction is successful,
//it returns true. Otherwise, it returns false. The transaction will be unsuccessful if the
//number of allowed withdrawals has been exceeded, the bank account is “in the red”
//or the bank account does not have a sufficient balance (i.e. removing the amount
//would leave less than the required minimum balance in the account). If the
//transaction is not successful, the method should print out an explanation before it
//returns. E.g.:
//Sorry, could not perform withdrawal: Insufficient balance.
//Remember that when there is no withdrawal limit, the withdrawalLimit member
//variable is set to -1, so your code must check for this case and behave appropriately.
//