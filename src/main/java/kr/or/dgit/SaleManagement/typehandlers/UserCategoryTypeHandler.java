package kr.or.dgit.SaleManagement.typehandlers;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import kr.or.dgit.SaleManagement.dto.UserCategory;

public class UserCategoryTypeHandler extends BaseTypeHandler<UserCategory> {

	@Override
	public void setNonNullParameter(PreparedStatement ps, int i, UserCategory parameter, JdbcType jdbcType)
			throws SQLException {
		ps.setInt(i, parameter.getCategoryNo());

	}

	@Override
	public UserCategory getNullableResult(ResultSet rs, String columnName) throws SQLException {
		return new UserCategory(rs.getInt(columnName));
	}

	@Override
	public UserCategory getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
		return new UserCategory(rs.getInt(columnIndex));
	}

	@Override
	public UserCategory getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
		return new UserCategory(cs.getInt(columnIndex));
	}

}
