package kr.or.dgit.SaleManagement.dao;

import java.util.List;

import kr.or.dgit.SaleManagement.dto.User;

public interface UserDao {
	public User selectUserById(User user);
	public List<User> selectUserByAll();
	public int insertUser(User user);
}
