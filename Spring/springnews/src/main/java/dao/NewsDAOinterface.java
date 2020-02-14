package dao;

import java.util.List;

import vo.NewsVO;

public interface NewsDAOinterface {
	public boolean insert(NewsVO vo);
	public boolean update(NewsVO vo);
	public boolean delete(int id);
	public List<NewsVO> listAll();
	public NewsVO listOne(int id) ;
	public List<NewsVO> listWriter(String writer);
	public List<NewsVO> search(String key, String searchType);
}
