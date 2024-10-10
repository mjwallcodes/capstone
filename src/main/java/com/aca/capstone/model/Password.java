package com.aca.capstone.model;

import java.util.Base64;

public class Password {
	private String password;
	private Integer id;
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public static Password encryptPassword(Password pass) {
		Password encrypted = pass;
		encrypted.setPassword(encryptRot13(encryptBase64(pass.getPassword())));
		return encrypted;
	}
	public static String encryptBase64(String input) {
		return Base64.getEncoder().encodeToString(input.getBytes());
		
	}
	public static String encryptRot13(String input) {
		String output = "";
		StringBuilder sb = new StringBuilder();
	    for (int i = 0; i < input.length(); i++) {
	        char c = input.charAt(i);
	        if       (c >= 'a' && c <= 'm') c += 13;
	        else if  (c >= 'A' && c <= 'M') c += 13;
	        else if  (c >= 'n' && c <= 'z') c -= 13;
	        else if  (c >= 'N' && c <= 'Z') c -= 13;
	        sb.append(c);
	    }
	    output = sb.toString();
	    return output;
	}
}