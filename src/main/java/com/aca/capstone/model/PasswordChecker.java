package com.aca.capstone.model;

public class PasswordChecker {
	public static boolean isValid(String password)
	{
		System.out.println(password);
		boolean length = ((password.length() >= 8)
				&& (password.length() <= 30));
		boolean doesNotContainSpace = !password.contains(" ");
		boolean containsNumber = false;
		boolean specialCharacter = (password.contains("@") || password.contains("#")
				|| password.contains("!") || password.contains("~")
				|| password.contains("$") || password.contains("%")
				|| password.contains("^") || password.contains("&")
				|| password.contains("*") || password.contains("(")
				|| password.contains(")") || password.contains("-")
				|| password.contains("+") || password.contains("/")
				|| password.contains(":") || password.contains(".")
				|| password.contains(", ") || password.contains("<")
				|| password.contains(">") || password.contains("?")
				|| password.contains("|"));
		boolean containsCapitalLetter = false;
		boolean containsLowercaseLetter = false;
		for (int i = 0; i <= 9; i++) {
			String str1 = Integer.toString(i);
			if (password.contains(str1)) {
				containsNumber = true;
				break;
			}
		}
		for (int i = 65; i <= 90; i++) {
			char c = (char)i;
			String str1 = Character.toString(c);
			if (password.contains(str1)) {
				containsCapitalLetter = true;
				break;
			}
		}
		for (int i = 97; i <= 122; i++) {
			char c = (char)i;
			String str1 = Character.toString(c);
			if (password.contains(str1)) {
				containsLowercaseLetter = true;
				break;
			}
		}
		System.out.println("Length: " + length);
		System.out.println("No spaces: " + doesNotContainSpace);
		System.out.println("Number: " + containsNumber);
		System.out.println("Special Characters: " + specialCharacter);
		System.out.println("Capital Letter: " + containsCapitalLetter);
		System.out.println("Lowercase Letter: " + containsLowercaseLetter);
		return length && doesNotContainSpace && containsNumber && specialCharacter && containsCapitalLetter && containsLowercaseLetter;
	}

}