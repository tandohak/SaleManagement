package kr.or.dgit.SaleManagement.typehandlers;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import kr.or.dgit.SaleManagement.dto.User;

public class UserTypeHandler extends BaseTypeHandler<User> {

	@Override
	public void setNonNullParameter(PreparedStatement ps, int i, User parameter, JdbcType jdbcType)
			throws SQLException {
		ps.setString(i, parameter.getUserId());
	}

	@Override
	public User getNullableResult(ResultSet rs, String columnName) throws SQLException {
		return new User(rs.getString(columnName));
	}

	@Override
	public User getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
		
		return new User(rs.getString(columnIndex));
	}

	@Override
	public User getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
		return new User(cs.getString(columnIndex));
	}

}
