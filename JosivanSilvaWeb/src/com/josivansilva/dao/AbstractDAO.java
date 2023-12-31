package com.josivansilva.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.josivansilva.exceptions.DataAccessException;

/**
 * Abstract class for all DAO domain objects.
 * 
 * @author Josivan Silva
 * @param <T>
 *
 */
public abstract class AbstractDAO<T> implements DAOOperations<T> {
	/**
	 * Database configuration constants. 
	 */
	private static String DRIVER_NAME = "com.mysql.jdbc.Driver";
	private static String URL         = "jdbc:mysql://localhost:3306/";
    private static String DB_NAME     = "mydb";
	private static String DB_USERNAME = "root";
	private static String DB_PWD      = "admroot";
			
	/**
	 * Gets a new database connection.
	 * 
	 * @return an object of connection.
	 * @throws DataAccessException
	 */
	private Connection getConnection () throws DataAccessException {
		Connection conn = null;
		try {
			Class.forName (DRIVER_NAME).newInstance();
			conn = DriverManager.getConnection (URL + DB_NAME, DB_USERNAME, DB_PWD);
		} catch (Exception e) {
			String error = "An error occurred while openning the database connection. " + e.getMessage();
			throw new DataAccessException (error);
		}
		return conn;
	}
	
	public int updateDb (final String sql) throws DataAccessException {
		Connection conn         = null;
		QueryRunner queryRunner = null;
		int affectedRows        = 0;
		try {
			//DbUtils.loadDriver (DRIVER_NAME);
		    //conn = DriverManager.getConnection (URL, DB_USERNAME, DB_PWD);
			conn = getConnection();
		    queryRunner = new QueryRunner();
		    affectedRows = queryRunner.update (conn, sql);
		} catch (SQLException e) {
		    String error = "An error occurred while executing the sql insert or update. " + e.getMessage();
		    throw new DataAccessException (error);
		}
		return affectedRows;
	}
	
	@Override
	public int selectRowCount (final String sql) throws DataAccessException {
		Connection conn = null;
		Statement stmt  = null;
		ResultSet rs    = null;
		int rowCount    = 0;
		try {
			conn = getConnection ();
			stmt = conn.createStatement();
	        rs = stmt.executeQuery (sql);
	        while (rs.next()) {
	        	rowCount = rs.getInt ("COUNT(*)");
			}
	        rs.close();
		} catch (Exception e) {
			String error = "An error occurred while getting the row count. " + e.getMessage();
			throw new DataAccessException (error);			
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
				if (stmt != null) {
					stmt.close();
				}				
			} catch (SQLException e) {
				String error = "An SQL error occurred while trying closing the connection and statement. " + e.getMessage();
				throw new DataAccessException (error);
			}
		}
		return rowCount;
	}
	
	@SuppressWarnings({ "unchecked", "hiding" })
	public <T> List<T> selectDb (final String sql, T bean) throws DataAccessException {
		Connection conn         = null;
		QueryRunner queryRunner = null;
		List<T> list            = new ArrayList<T>();
		try {
			//DbUtils.loadDriver (DRIVER_NAME);
		    //conn = DriverManager.getConnection (URL, DB_USERNAME, DB_PWD);
		    conn = getConnection();
		    queryRunner = new QueryRunner();
		    // use the BeanListHandler implementation to convert all
		    // ResultSet rows into a List of JavaBeans		    
			ResultSetHandler<List<T>> handler = new BeanListHandler<T>((Class<T>) bean.getClass());
            // execute the SQL statement and return the result in a List of
		    // objects generated by the BeanListHandler
		    list = queryRunner.query (conn, sql, handler);
		} catch (SQLException e) {
			String error = "An error occurred while executing a query. " + e.getMessage();
		    throw new DataAccessException (error);
		} finally {
			DbUtils.closeQuietly (conn);
		}
		return list;
	}
	
		
}
