package Main;

import java.util.*;
import Exceptions.*;
import operations.*;

public class App {
	private static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		int loginCount = 0;
		while (loginCount < 3) {
			String name = null;
			String pass = null;
			try {
				System.out.println("------------------Welcome---------------------");
				System.out.println(" Please enter your Id :");
				name = sc.next();
				System.out.println(" Please enter your password :");
				pass = sc.next();
			} catch (InputMismatchException e) {
				System.out.println(" invalid values please enter again: ");
				loginCount++;
				name = null;
				pass = null;
				System.out.println(" " + (4 - loginCount) + " chance left.. ");
				continue;
			} catch (Exception e) {
				System.out.println(" Exception occure : ");
				loginCount++;
				name = null;
				pass = null;
				System.out.println(" " + (4 - loginCount) + " chance left.. ");
				continue;
			}
			if (Employee.Login(name, pass)) {
				int choice = 1;
				while (choice != 4) {
					System.out.println(
							" 1 for show all account number :\n 2 for Create new Account : \n 3 for Existing account : \n 4 for Logout ");
					try {
						String choicetemp = sc.next();
						choice = Integer.parseInt(choicetemp);
					} catch (Exception e) {
						System.out.println(" Enter a valid choice : ");
						continue;
					}
					if (choice == 1) {
						AccountOperations.showAllDetails();
					} else if (choice == 2) {
						AccountOperations.createNewAccount();
					} else if (choice == 3) {
						int counter = 3;
						boolean mycount = false;
						do {
							System.out.println(" Enter Account number ");
							int accountnum = 0;
							try {
								String s = sc.next();
								accountnum = Integer.parseInt(s);
							} catch (Exception e) {
								System.out.println(" Account dosen't Exist | enter valid account number :  ");
								counter--;
								System.out.println(" " + counter + " chances left ");
								continue;
							}
							if (AccountOperations.checkExistance(accountnum)) {
								int choice2 = 0;
								boolean t = false;
								do {
									int trance = 0;
									if (t) {
										while (trance != 2 || trance != 1) {
											System.out
													.println(" 1 for continue transaction \n 2 for new transaction :");
											try {
												String tranceTemp = sc.next();
												trance = Integer.parseInt(tranceTemp);
												if (trance == 1) {
													System.out.println(" Continue same transaction ");
													break;
												} else if (trance == 2) {
													System.out.println(" Another transaction ");
													break;
												} else {
													throw new Exception();
												}
											} catch (InputMismatchException e) {
												System.out.println(" Enter the valid choice :");
												continue;
											} catch (Exception e) {
												System.out.println(" Enter the valid choice :");
												continue;
											}
										}
										if (trance == 2)
											break;
									}
									try {
										System.out.println(
												" 1 for Deposite \n 2 for withdraw \n 3 for checkDetails \n 4 for delete account \n 5 for previous menu");
										t = true;
										String choice2temp = sc.next();
										choice2 = Integer.parseInt(choice2temp);

										if (choice2 == 1) {
											AccountOperations.deposite(accountnum);
											continue;
										} else if (choice2 == 2) {
											AccountOperations.withdraw(accountnum);
											continue;
										} else if (choice2 == 3) {
											System.out.println(AccountOperations.fetchSingleRecord(accountnum));
											continue;
										} else if (choice2 == 4) {
											if (AccountOperations.delete(accountnum)) {
												mycount = true;
												choice2 = 5;
											}
											continue;
										} else if (choice2 == 5) {
											mycount = true;
											break;
										} else {
											throw new InvalidChoiceException(
													" Operation for this choice is not avilable : ");
										}
									} catch (InvalidChoiceException e) {
										System.out.println(e);
										continue;
									} catch (Exception e) {
										System.out.println(" Invalid type of input :");
									}

								} while (choice2 != 5);

								if (mycount)
									break;
							} else {

								System.out.println(" Account dosen't Exist | enter valid account number :  ");
								counter--;
								System.out.println(" " + counter + " chances left ");
							}
						} while (counter > 0 || mycount);
					} else if (choice == 4) {
						System.out.println(" Loged out successfully ");
						continue;
					} else {
						System.out.println(" Opetion is not available : ");
					}
				}
			} else {
				loginCount++;
				System.out.println((3 - loginCount) + " chance left ");
				continue;
			}
		}
	}
}
