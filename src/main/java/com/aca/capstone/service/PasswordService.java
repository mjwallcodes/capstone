package com.aca.capstone.service;

import java.util.List;
import com.aca.capstone.dao.PasswordDao;
import com.aca.capstone.dao.PasswordDaoImpl;
import com.aca.capstone.model.Password;
import com.aca.capstone.model.PasswordChecker;
import com.aca.capstone.model.RequestError;

import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;

public class PasswordService {
	private PasswordDao impl = new PasswordDaoImpl();
	
	public Password createPassword(Password pass) {
		return impl.createPassword(pass);
	}
	public List<Password> getPasswords(){
		return impl.getAllPasswords();
	}
	
	public Password updatePassword(Password pass) {
		return impl.updatePassword(pass);
	}

	public Password getPasswordsById(Integer id) {
		return impl.getPasswordsById(id).size() > 0 ? impl.getPasswordsById(id).get(0) : null;
	}
	public Password deletePassword(Integer id) {
		return impl.deletePassword(id);
	}
	public void validatePassword(String pass) {
		String check = "";
		System.out.println(pass);
		boolean result = PasswordChecker.isValid(pass);
		
		check += result ? "Password Secure" : "Password Insecure";
		System.out.println(pass);
		System.out.println(result);
		System.out.println(check);
		if(!check.equalsIgnoreCase("Password Secure")) {
			sendError();
		}
	}
	
	public static void sendError() {
		RequestError error = new RequestError(1,
				"Password entered doesn't meet requirements, "
				+ "Password must contain at least 8 characters, "
				+ "at uppercase letter, "
				+ "at least one lowercase letter, "
				+ "and at least one number");
		Response res = Response.status(400).entity(error).build();
		throw new WebApplicationException(res);
	}
	
}