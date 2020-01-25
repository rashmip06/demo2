package com.example.demo;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Employee;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class Controller {

	 List<Employee> employees = createList();

	@RequestMapping(value = "/employees", method = RequestMethod.GET, produces = "application/json")
	public List<Employee> firstPage() {
		return employees;
	}

	private static List<Employee> createList(){
		System.out.println("Starting connection");
		final List<Employee> tempEmployees = new ArrayList<>();
		try(Connection con=DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/Login", "postgres", "tiger"))
		{
			if(con!=null)
			{
				System.out.println("Connection Established");
				String sel="SELECT * FROM security";
		System.out.println("Select executed");
		//PreparedStatement preparedStatement = con.prepareStatement(select);
		Statement preparedStatement=con.createStatement();
		System.out.println("Select executed");
		//preparedStatement.executeQuery("create table security(EmployeeId varchar(30), EmployeeName varchar(30),ParcelName varchar(30),CompanyName varchar(30), Rack (30))");
		//preparedStatement.execute("insert into Login values('50000','GM','ABC','123')");
		
		ResultSet resultSet = preparedStatement.executeQuery(sel);
		System.out.println("Select executed");
		while(resultSet.next())
		{
			
			String pnumber=resultSet.getString("ParcelName");
			System.out.println(pnumber);
			String name=resultSet.getString("EmployeeName");
			String empId=resultSet.getString("EmployeeId");
			String cname=resultSet.getString("CompanyName");
			int rack=Integer.parseInt(resultSet.getString("Rack"));
			Employee e=new Employee();

			e.setCname(cname);
			e.setEmpId(empId);
			e.setName(name);
			System.out.println(pnumber);
			e.setParcelnumber("12wq");
			e.setRack(rack);

			tempEmployees.add(e);

			

		}
/*
		Employee emp1 = new Employee();
		emp1.setName("emp1");
		emp1.setDesignation("manager");
		emp1.setEmpId("1");
		emp1.setSalary(3000);
		
		
		
		 Employee emp2 = new Employee();
		emp2.setName("emp2");
		emp2.setDesignation("developer");
		emp2.setEmpId("2");
		emp2.setSalary(3000);
		tempEmployees.add(emp1);
		tempEmployees.add(emp2);*/
		
			
			
			}
			else
			{
				System.out.println("Failed!");
			}
		}
		catch(SQLException s)
		{
			s.printStackTrace();
			System.out.println("In catch");
		}


		
		
		
		
		
		return tempEmployees;
	}

	

}