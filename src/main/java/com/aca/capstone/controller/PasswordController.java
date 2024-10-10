package com.aca.capstone.controller;

import java.sql.SQLException;
import java.util.List;

import com.aca.capstone.model.Password;
import com.aca.capstone.service.PasswordService;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.DELETE;

import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/passwords")
public class PasswordController {
	private PasswordService service = new PasswordService();
	@PUT
	@Path("/update/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Password updatePassword(Password pass) throws SQLException {
	    service.validatePassword(pass.getPassword());
	    return service.updatePassword(pass);
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Password> getPasswords() {
		return service.getPasswords();
	}
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Password getPasswordById(@PathParam("id") Integer id) {
		return service.getPasswordsById(id);
	}
	@POST
	@Path("/create")
	@Consumes(MediaType.APPLICATION_JSON)
	public Password createPassword(Password pass) {
		service.validatePassword(pass.getPassword());
		return service.createPassword(pass);
	}
	@DELETE
	@Path("/delete/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Password deletePassword(@PathParam("id") Integer id) {
	    return service.deletePassword(id);
	}
}