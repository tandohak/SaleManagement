package kr.or.dgit.SaleManagement.typehandlers;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class IntegerPropertyTypeHandler extends BaseTypeHandler<IntegerProperty> {
	IntegerProperty integer = new SimpleIntegerProperty();
	@Override
	public void setNonNullParameter(PreparedStatement ps, int i, IntegerProperty parameter, JdbcType jdbcType)
			throws SQLException {
		ps.setInt(i, parameter.get());		
	}

	@Override
	public IntegerProperty getNullableResult(ResultSet rs, String columnName) throws SQLException {
		
		integer.set(rs.getInt(columnName));
		return integer;
	}

	@Override
	public IntegerProperty getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
		
		integer.set(rs.getInt(columnIndex));
		return integer;
	}

	@Override
	public IntegerProperty getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
		
		integer.set(cs.getInt(columnIndex));
		return integer;
	}

}

