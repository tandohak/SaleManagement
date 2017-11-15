package kr.or.dgit.SaleManagement.typehandlers;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import kr.or.dgit.SaleManagement.dto.AccountLevel;

public class AccountLevelTypeHandler extends BaseTypeHandler<AccountLevel> {

	@Override
	public void setNonNullParameter(PreparedStatement ps, int i, AccountLevel parameter, JdbcType jdbcType)
			throws SQLException {
		ps.setString(i, parameter.getAccLevel());
	}

	@Override
	public AccountLevel getNullableResult(ResultSet rs, String columnName) throws SQLException {
		return new AccountLevel(rs.getString(columnName));
	}

	@Override
	public AccountLevel getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
		return new AccountLevel(rs.getString(columnIndex));
	}

	@Override
	public AccountLevel getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
		return new AccountLevel(cs.getString(columnIndex));
	}

}
