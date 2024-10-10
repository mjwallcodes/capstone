package com.aca.capstone.dao;

import java.util.List;

import com.aca.capstone.model.Password;

public interface PasswordDao {
	public Password deletePassword(Integer id);
	public List<Password> getAllPasswords();
	public List<Password> getPasswordsById(Integer id);
	public Password updatePassword(Password pass);
	public Password createPassword(Password pass);
	
}