package kr.or.dgit.SaleManagement.typehandlers;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class StringPropertyTypeHandler extends BaseTypeHandler<StringProperty> {
	StringProperty stringProperty = new SimpleStringProperty();
	@Override
	public void setNonNullParameter(PreparedStatement ps, int i, StringProperty parameter, JdbcType jdbcType)
			throws SQLException {
		ps.setString(i, parameter.get());
	}

	@Override
	public StringProperty getNullableResult(ResultSet rs, String columnName) throws SQLException {
		
		stringProperty.set(rs.getString(columnName));
		return stringProperty;
	}

	@Override
	public StringProperty getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
		
		stringProperty.set(rs.getString(columnIndex));
		return stringProperty;
	}

	@Override
	public StringProperty getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
	
		stringProperty.set(cs.getString(columnIndex));
		return stringProperty;
	}

}
