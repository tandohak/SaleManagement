package kr.or.dgit.SaleManagement.dao;

import java.util.List;

import kr.or.dgit.SaleManagement.dto.SmallClass;

public interface SmallClassDao {
	public List<SmallClass> selectAll();
	public SmallClass selectBySmallClass(SmallClass Smallclass);
	public SmallClass selectBySmallName(SmallClass Smallclass);
	public List<SmallClass> selectByBigClass(SmallClass Smallclass);
	public List<SmallClass> selectBySmallClasslike(SmallClass Smallclass);
	
	public int insertSmallClass(SmallClass Smallclass);
	
	int updateSmallClass(SmallClass Smallclass);
	int deleteSmallClass(int SmallClass);
}	
