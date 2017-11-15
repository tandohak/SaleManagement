package kr.or.dgit.SaleManagement.typehandlers;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import kr.or.dgit.SaleManagement.dto.BigClass;
import kr.or.dgit.SaleManagement.dto.RowClass;

public class BigClassTypehandlers extends BaseTypeHandler<BigClass> {

	@Override
	public void setNonNullParameter(PreparedStatement ps, int i, BigClass parameter, JdbcType jdbcType)
			throws SQLException {
		ps.setInt(i, parameter.getBigClass());
		
	}

	@Override
	public BigClass getNullableResult(ResultSet rs, String columnName) throws SQLException {
		return new BigClass(rs.getInt(columnName));
	}

	@Override
	public BigClass getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
		return new BigClass(rs.getInt(columnIndex));
	}

	@Override
	public BigClass getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
		return new BigClass(cs.getInt(columnIndex));
	}


	

}
