package kr.or.dgit.SaleManagement.jdbc_setting.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import kr.or.dgit.SaleManagement.jdbc_setting.jdbc.DBCon;
import kr.or.dgit.SaleManagement.jdbc_setting.jdbc.jdbcUtil;

public class DatabaseDao {
	private static final DatabaseDao instance = new DatabaseDao();

	public static DatabaseDao getInstance() {
		return instance;
	}

	public DatabaseDao() {}
	
	public void executeUpdateSQL(String sql){
		Connection con = DBCon.getInstance().getConnection();
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.err.printf("%s - %s\n", e.getErrorCode(), e.getMessage());
			e.printStackTrace();
		} finally {
			jdbcUtil.close(pstmt);
		}
	}
	
	public ResultSet executeQuerySQL(String sql) throws SQLException{
		Connection con = DBCon.getInstance().getConnection();
		PreparedStatement pstmt = con.prepareStatement(sql);
		return pstmt.executeQuery();
	}
}
