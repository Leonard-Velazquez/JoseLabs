package com.josetechblog.service;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.josetechblog.dao.EmployeeDetailsDaoImpl;

import com.josetechblog.model.Employee;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;

@Path("/emp")
@Api(value = "/emp", description = "This resource supports crud operations for employee")
public class EmployeeDetailsServiceImpl {

	@GET
	@Path("/employee")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.TEXT_HTML, MediaType.APPLICATION_XML })
	@ApiOperation(notes = "This will retrieve employee by id", value = "single customer")
	public List<Employee> getEmpDetailsById(@QueryParam("id") String empId) {
		EmployeeDetailsDaoImpl empDaoImpl = new EmployeeDetailsDaoImpl();
		List<Employee> empDetailsList = null;
		empDetailsList = empDaoImpl.getEmpDetailsById(empId);
		return empDetailsList;
	}

	@POST
	@Path("/employee")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@ApiOperation(notes = "This will add employee ", value = "add employee")

	public void insertEmployeeRecords(Employee json) {
		EmployeeDetailsDaoImpl empDaoImpl = new EmployeeDetailsDaoImpl();
		String name = json.getName();
		String design = json.getDesignation();
		int sal = json.getSalary();
		int dnumber = json.getAddress().getDoorno();
		String street = json.getAddress().getStreet();
		String loc = json.getAddress().getLocation();
		String city = json.getAddress().getCity();
		empDaoImpl.addEmployeeDetails(name, design, sal, dnumber, street, loc, city);
		System.out.println("test" + json);
	}

	@PUT
	@Path("/employee/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@ApiOperation(notes = "This will update employee by id", value = "update employee")
	public Response updateEmployeeDetails(@PathParam("id") int id, Employee empUpdate) {
		EmployeeDetailsDaoImpl empDaoImpl = new EmployeeDetailsDaoImpl();
    List<Employee> emp = empDaoImpl.updateEmpById(id);
    return Response.ok().entity(emp).build();
	}
}
