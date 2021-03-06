/**
 * 
 */
package com.mycompany.spring.spring_framework.dataaccess.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mycompany.spring.spring_framework.dataaccess.jdbc.model.Customer;

/**
 * @author colin
 *
 */
@Repository("customerDaoImpl")
public class CustomerDaoImpl implements CustomerDao {
	
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private AddressDao addressDao;
	

	
	@Override
	public Customer findById(int id) {
		String sql = "SELECT * FROM CUSTOMER WHERE customer_id = ?";
		
		Connection con = null;
		Customer customer = null;
		
		try {
			con = dataSource.getConnection();
			
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			
			if(rs.next()) {
				customer = new Customer();
				customer.setCustomerId(id);
				customer.setFirstName(rs.getString("customer_firstname"));
				customer.setLastName(rs.getString("customer_lastname"));
				customer.setDateOfBirth(rs.getDate("customer_date_of_birth"));
				customer.setEmail(rs.getString("customer_email_address"));
				customer.setAddress(addressDao.findById(rs.getInt("customer_address_id")));
			}
			
			rs.close();
			stmt.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(con != null) {
				try {
					con.close();
				} catch(SQLException e) {}
				
			}
		}
		
		return customer;
	}

	@Override
	public void insertCustomer(Customer customer) {
		String sql = "INSERT INTO CUSTOMER (customer_firstname, customer_lastname, customer_date_of_birth, customer_email_address, customer_address_id) "
				+ "VALUES (?,?,?,?,?)";
		
		Connection con = null;
		
		try {
			con = dataSource.getConnection();
			
			PreparedStatement stmt = con.prepareStatement(sql);
			
			stmt.setString(1, customer.getFirstName());
			stmt.setString(2, customer.getLastName());
			stmt.setDate(3, new java.sql.Date(customer.getDateOfBirth().getTime()));
			stmt.setString(4, customer.getEmail());
			stmt.setInt(5, customer.getAddress().getAddressId());
			
			stmt.executeUpdate();
			stmt.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(con != null) {
				try {
					con.close();
				} catch(SQLException e) {}
				
			}
		}
		
		
	}

	@Override
	public List<Customer> findAll() {
		
		List<Customer> customers = new ArrayList<>();
		String sql = "SELECT * FROM CUSTOMER;";
		
		Connection con = null;
		Customer customer = null;
		
		try {
			con = dataSource.getConnection();
			
			PreparedStatement stmt = con.prepareStatement(sql);
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				customer = new Customer();
				customer.setCustomerId(rs.getInt("customer_id"));
				customer.setFirstName(rs.getString("customer_firstname"));
				customer.setLastName(rs.getString("customer_lastname"));
				customer.setDateOfBirth(rs.getDate("customer_date_of_birth"));
				customer.setEmail(rs.getString("customer_email_address"));
				customer.setAddress(addressDao.findById(rs.getInt("customer_address_id")));
				customers.add(customer);
			}
			
			rs.close();
			stmt.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(con != null) {
				try {
					con.close();
				} catch(SQLException e) {}
				
			}
		}
		
		return customers;
	}

	@Override
	public String findCustomerLastNameById(int id) {
		String sql = "SELECT customer_lastname FROM CUSTOMER WHERE customer_id = ?";
		
		Connection con = null;
		String customerName = null;
		
		try {
			con = dataSource.getConnection();
			
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			
			if(rs.next()) {
				customerName = rs.getString("customer_lastname");
			}
			
			rs.close();
			stmt.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(con != null) {
				try {
					con.close();
				} catch(SQLException e) {}
				
			}
		}
		
		return customerName;
	}

	@Override
	public void insertBatch1(List<Customer> customers) {
		throw new UnsupportedOperationException("UnsupportedOperation");
	}

	@Override
	public void insertBatch2(String sql) {
		throw new UnsupportedOperationException("UnsupportedOperation");
	}
	

	
}
