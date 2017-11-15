package kr.or.dgit.SaleManagement.typehandlers;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import kr.or.dgit.SaleManagement.dto.SalesLevel;

public class SalesLevelTypeHandler extends BaseTypeHandler<SalesLevel> {

	@Override
	public void setNonNullParameter(PreparedStatement ps, int i, SalesLevel parameter, JdbcType jdbcType)
			throws SQLException {
		 ps.setString(i, parameter.getSalesLevel());
	}

	@Override
	public SalesLevel getNullableResult(ResultSet rs, String columnName) throws SQLException {
		return new SalesLevel(rs.getString(columnName));
	}

	@Override
	public SalesLevel getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
		return new SalesLevel(rs.getString(columnIndex));
	}

	@Override
	public SalesLevel getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
		return new SalesLevel(cs.getString(columnIndex));
	}

}
