package com.aca.capstone.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.aca.capstone.model.Password;

public class PasswordDaoImpl implements PasswordDao{
	private static String getAllPasswordsQuery = "SELECT id, passphrase FROM passwords";
	private static String queryCreatePassword = "INSERT INTO passwords (passphrase) VALUES(?);";
	private static String selectNewPasswordId = "SELECT LAST_INSERT_ID() AS 'id' ";
	private static String queryUpdatePassword = "UPDATE passwords SET passphrase = ? WHERE id = ?";
    private static String queryDeletePassword = "DELETE FROM passwords WHERE id = ?";
    public List<Password> listOfPasswords = getAllPasswords();
	public int getNewPasswordId(Connection conn) {
		ResultSet rs = null;
		Statement statement = null;
		int newId = 0;
		try {
			statement = conn.createStatement();
			rs = statement.executeQuery(selectNewPasswordId);
			while (rs.next()) {
				newId = rs.getInt("id");
			}
		} catch (SQLException e) {
		} finally {
			try {
				rs.close();
				statement.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return newId;
	}
	@Override
	public Password createPassword(Password newPassword) {
		System.out.println(newPassword);
		PreparedStatement ps = null;
		Connection conn = MariaDB.getConnection();
		int id = 0;
		try {
			ps = conn.prepareStatement(queryCreatePassword);
			Password encryptedPassword = Password.encryptPassword(newPassword);
			ps.setString(1, encryptedPassword.getPassword());
			int rowCount = ps.executeUpdate();
			System.out.println("Insert row count: " + rowCount);
			id = getNewPasswordId(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				ps.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		Password p = new Password();
		p.setId(id);
		p.setPassword(newPassword.getPassword());
		return p;
	}
	public String convertJsonToString(String jsonToConvert){
		System.out.println(jsonToConvert);
		String[] s = jsonToConvert.split(":");
	    String output;
	    if (s.length > 1) {
	        output = s[1].split("\n}")[0].split("\"")[1].split("\"")[0];
	    } else {
	        output = "";
	    }
	    return output;
	    
	}

	@Override
	public List<Password> getAllPasswords() {
		List<Password> allPasswords = new ArrayList<Password>();
		ResultSet results = null;
		Statement statement = null;
		Connection conn = MariaDB.getConnection();
		try {
			statement = conn.createStatement();
			results = statement.executeQuery(getAllPasswordsQuery);
			allPasswords = makePassword(results);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		listOfPasswords = allPasswords;
		return listOfPasswords;
	}
	@Override
	public List<Password> getPasswordsById(Integer id) {
		List<Password> allPasswords = new ArrayList<Password>();
		List<Password> output = new ArrayList<Password>();
		String s = getAllPasswordsQuery + " WHERE id = "+ id;
		ResultSet results = null;
		Statement statement = null;
		Connection conn = MariaDB.getConnection();
		try {
			statement = conn.createStatement();
			results = statement.executeQuery(s);
			allPasswords = makePassword(results);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		for(Password p : allPasswords) {
			if(p.getId() == id) {
				output.add(p);
			}
		}
		return output;
		
	}
	
	private List<Password> makePassword(ResultSet results) throws SQLException {
		List<Password> tmp = new ArrayList<Password>();
		while (results.next()) {
			Password pass = new Password();
			pass.setId(results.getInt("id"));
			pass.setPassword(results.getString("passphrase"));
			tmp.add(pass);
		}
		return tmp;
	}

	@Override
	public Password updatePassword(Password pass) {
		List<Password> passwordResults = getPasswordsById(pass.getId());
		if(passwordResults.size() > 0)
		{
			int rowCount = 0;
		    PreparedStatement ps = null;
		    Connection conn = MariaDB.getConnection();
		    try {
		        ps = conn.prepareStatement(queryUpdatePassword);
		        Password encrypted = Password.encryptPassword(pass);
		        ps.setString(1, encrypted.getPassword());
		        ps.setInt(2, pass.getId());
		        rowCount = ps.executeUpdate();
		        System.out.println("Update row count: " + rowCount);
		    } catch (SQLException e) {
		        e.printStackTrace();
		    } finally {
		        try {
		            ps.close();
		            conn.close();
		        } catch(SQLException e) {
		            e.printStackTrace();
		        }
		    }
		}
		return pass;
	}

	@Override
	public Password deletePassword(Integer id) {
		List<Password> passwords = getPasswordsById(id);
		Password passwordToDelete = null;
		if (passwords.size() > 0) {
			passwordToDelete = passwords.get(0);
			int updateRowCount = 0;
			PreparedStatement ps = null;
			Connection connection = MariaDB.getConnection();
			try {
				ps = connection.prepareStatement(queryDeletePassword);
				ps.setInt(1, id);
				updateRowCount = ps.executeUpdate();
				System.out.println("rows deleted: " + updateRowCount);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			finally {
				try {
					ps.close();
					connection.close();
				}
				catch (SQLException e) {
					e.printStackTrace();
					// TODO: handle exception
				}
			}
		}
		return passwordToDelete;
		
	}
}