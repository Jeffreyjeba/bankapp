package utility;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation {
	
	public static boolean validatePassword(CharSequence passWord) {
		return comparator("((?=.*[A-Z]{1,})(?=.*[a-z]{1,})(?=.*[0-9]{1,})(?=.*\\p{Punct}{1,})).{8,}", passWord);
	}
	public static boolean validateEmail(CharSequence emailId) {
		return comparator("^[^@\\.]{1}[^@]+[@]{1}[^@\\.]+[\\.]{1}[^@\\.]+$",emailId);
	}
	public static boolean validatePhoenNo(CharSequence phoneNumber) {
		return comparator("^[7-9]{1}[0-9]{9}$",phoneNumber);
	}
	public static void validateAlphaNume(CharSequence input) throws BankException {
		xssInjectionCheck(input);
		if(!comparator("\\P{Alnum}", input)) {
			throw new BankException("NO Special Characters");
		}
	}
	public static void validateUserId(long userId) throws BankException {
		
		 if(!comparator("^[1-9][0-9]{0,5}$", Long.toString(userId))) {
			 throw new BankException("Invalid ID");
		 }
	}
	public static void validateAccountNumber(long accountNumber) throws BankException {
		 if(!comparator("^[1-9][0-9]{0,5}$", Long.toString(accountNumber))) {
			 throw new BankException("Invalid Account Number");
		 }
	}
	public static void validateAmount(long amount) throws BankException {
		 if(!comparator("^[1-9][0-9]{0,8}$", Long.toString(amount))) {
			 throw new BankException("Invalid amount");
		 }
	}
	
	public  static boolean validateAdharNumber(CharSequence aadharNumber) {
		return comparator("[0-9]{12}", aadharNumber);
	}
	public  static boolean validatePanNumber(CharSequence panNumber) {
		return comparator("^[A-Za-z]{5}[0-9]{4}[A-Za-z]{1}$", panNumber);
	}
	
	public  static void name(CharSequence name) throws BankException {
		if(!comparator("^[a-zA-Z.]{4,25}$", name)) {
			throw new BankException("Invalid Name");
		}
	}
	
	public  static void branchName(CharSequence name) throws BankException {
		if(!comparator("^[a-zA-Z._]{4,25}$", name)) {
			throw new BankException("Invalid Branch Name");
		}
	}
	
	public  static void address(CharSequence address) throws BankException {
		xssInjectionCheck(address);
		if(address.length()>50) {
			throw new BankException("Address size exceeded");
		}
		if(address.length()<5) {
			throw new BankException("Address size must be greater than 5");
		}
		if(!comparator("[^@!#$%^*_+]*",address)) {
			throw new BankException("@!#$%^*_+ characters not allowed in address");
		}
		if(comparator("^[\\s]{1}", address)) {
			throw new BankException("No white space at begining of address");
		}
	}
	
	public  static void description(CharSequence address) throws BankException {
		xssInjectionCheck(address);
		if(address.length()>30) {
			throw new BankException("Description size exceeded");
		}
	}
	
	public  static void validateId(CharSequence address) throws BankException {
		xssInjectionCheck(address);
		
	}
	
	public static void xssInjectionCheck(CharSequence data) throws BankException {
		if(comparator("[<>]",data)){
			throw new BankException("< > characeters not allowed");
		}
	}
	
	public static void validateIfsc(CharSequence ifsc) throws BankException {
		if(!comparator("^[a-zA-z]{3}[0-9]{5}$", ifsc)) {
			throw new BankException("invalid IFSC format");
		}
	}
	
		
	
	private static boolean comparator(String regex,CharSequence subject) {
		Pattern pattern= Pattern.compile(regex);
		Matcher matcher=pattern.matcher(subject);
		return matcher.find();
	}
}
