package kr.or.dgit.SaleManagement.typehandlers;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import kr.or.dgit.SaleManagement.dto.UserAddr;

public class UserAddrTypeHandler extends BaseTypeHandler<UserAddr> {

	@Override
	public void setNonNullParameter(PreparedStatement ps, int i, UserAddr parameter, JdbcType jdbcType)
			throws SQLException {
		ps.setInt(i, parameter.getAddrNo());
		
	}

	@Override
	public UserAddr getNullableResult(ResultSet rs, String columnName) throws SQLException {
		return new UserAddr(rs.getInt(columnName));
	}

	@Override
	public UserAddr getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
		return new UserAddr(rs.getInt(columnIndex));
	}

	@Override
	public UserAddr getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
		return new UserAddr(cs.getInt(columnIndex));
	}

}
