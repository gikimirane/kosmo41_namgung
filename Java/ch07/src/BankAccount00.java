class BankAccount {
	int balance =0;
	public int deposit (int amount) {
		balance = balance+amount;
		return amount;
	}
	public int withdraw (int amount) {
		balance = balance-amount;
		return amount;
	}
	public int checkMyBalance() {
		System.out.println("잔액 : "+balance);
		return balance;
	}
}

public class BankAccount00 {

	public static void main(String[] args) {
		
		BankAccount yoon = new BankAccount();
		//BankAccount park = new BankAccount();
		BankAccount park = yoon;
		
		yoon.deposit(5000);
		park.deposit(3000);
		
		yoon.withdraw(400);
		park.withdraw(300);
		
		yoon.checkMyBalance();
		park.checkMyBalance();
	}

}
