package net.lala.CouponCodes.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import net.lala.CouponCodes.CouponCodes;
import net.lala.CouponCodes.api.Coupon;
import net.lala.CouponCodes.api.SQLAPI;
import net.lala.CouponCodes.misc.SQLType;

/**
 * SQL.java - MySQL, SQLite handling
 * @author LaLa
 */
public class SQL extends SQLAPI{
	
	private DatabaseOptions dop;
	
	private SQLType sqltype = SQLType.Unknown;
	private Connection con = null;
	
	public SQL(CouponCodes plugin, DatabaseOptions dop){
		super(plugin);
		this.dop = dop;
		this.sqltype = plugin.getSQLType();
	}
	
	public DatabaseOptions getDatabaseOptions(){
		return dop;
	}
	
	public boolean open() throws SQLException{
		if (sqltype.equals(SQLType.MySQL)){
			con = DriverManager.getConnection("jdbc:mysql://"+dop.getHostname()+":"+dop.getPort()+"/"+dop.getDatabase(), dop.getUsername(), dop.getPassword());
			return true;
		}
		else if (sqltype.equals(SQLType.SQLite)){
			con = DriverManager.getConnection("jdbc:sqlite:"+dop.getSQLFile().getAbsolutePath());
			return true;
		}else{
			return false;
		}
	}
	
	public void close() throws SQLException{
		con.close();
	}
	
	public boolean reload() throws SQLException{
		con.close();
		return open();
	}
	
	public ResultSet query(String query) throws SQLException{
		Statement st = null;
		ResultSet rs = null;
		
		st = con.createStatement();
		if (query.toLowerCase().contains("delete")){
			st.executeUpdate(query);
			return rs;
		}else{
			rs = st.executeQuery(query);
			return rs;
		}
	}
	
	public boolean createTable(String table) throws SQLException{
		Statement st = con.createStatement();
		return st.execute(table);
	}
	
	public boolean addCouponToDatabase(Coupon coupon){
		return false;
	}
}