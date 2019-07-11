package com.revature.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.pojos.AccountInfo;
import com.revature.pojos.User;
import com.revature.pojos.UserInformation;
import com.revature.util.ConnectionFactory;

public class UserDAO {
	
	public List<User> findAll(){
		List<User> Users = new ArrayList<User>();
		try(Connection conn = ConnectionFactory.getInstance().getConnection()){
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM BANK_USER");
			while(rs.next()) {
				User a = new User();
				a.setId(rs.getInt(1));
				a.setFirstname(rs.getString(2));
				a.setLastname(rs.getString(3));
				a.setUsername(rs.getString(4));
				a.setPassword(rs.getString(5));
				Users.add(a);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Users;
	}
	
	public User getByUsername(String username) {
		User u = null;
		//List<User> User = new ArrayList<User>();
		try(Connection conn = ConnectionFactory.getInstance().getConnection()){
			String query = "SELECT * FROM BANK_USER WHERE lower(USERNAME) = ?";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1,username.toLowerCase());
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				u = new User();
				u.setId(rs.getInt(1));
				u.setFirstname(rs.getString(2));
				u.setLastname(rs.getString(3));
				u.setUsername(rs.getString(4));
				u.setPassword(rs.getString(5));
				return u;
				}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return u; 
		}
	
	public UserInformation getUserInfo(User u) {
		try(Connection conn = ConnectionFactory.getInstance().getConnection()){
			String sql = "SELECT ACC.ACC_ID, ACC.BALANCE, ACC.ACC_TYPE " +
					" FROM BANK_ACCOUNT ACC" +
					" INNER JOIN BANK_ACCOUNT_TYPE BA ON ACC.ACC_TYPE = BA.ACCTYPE" +
					" WHERE  ACC.ACC_OWNER = ?";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, u.getId());

			UserInformation info = new UserInformation();
			info.setUser(u);
			List<AccountInfo> accounts = new ArrayList<AccountInfo>();

			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				AccountInfo temp = new AccountInfo();
				temp.setId(rs.getInt(1));
				temp.setBalance(rs.getInt(2));
				temp.setType(rs.getString(3));
				accounts.add(temp);
			}
			info.setAccounts(accounts);
			return info;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
		
	}


