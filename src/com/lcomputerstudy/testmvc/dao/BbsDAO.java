package com.lcomputerstudy.testmvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import com.lcomputerstudy.testmvc.database.DBconnection;
import com.lcomputerstudy.testmvc.vo.Pagination;
import com.lcomputerstudy.testmvc.vo.Bbs;

public class BbsDAO {
	private static BbsDAO bbsDao = new BbsDAO();
	
	private BbsDAO() {
		
	}
	
	public static BbsDAO getInstance() {
		return bbsDao;
	}
	
	public ArrayList<Bbs> getList(Pagination pagination) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Bbs> list = null;
		int pageNum = (pagination.getPage()-1)*Pagination.perPage;
		
		try {
			conn = DBconnection.getConnection();
			String query = new StringBuilder()
					.append("SELECT		@ROWNUM := @ROWNUM - 1 AS ROWNUM,\n")
					.append("			ta.*\n")
					.append("From		Bbs ta\n")
					.append("INNER JOIN (SELECT @rownum := (SELECT COUNT(*)-?+1 FROM Bbs ta)) tb ON 1=1\n")
					.append("LIMIT		?, ").append(Pagination.perPage).append("\n")
					.toString();
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, pageNum);
			pstmt.setInt(2, pageNum);
			rs = pstmt.executeQuery();
			list = new ArrayList<Bbs>();
			
			while(rs.next()) {
				Bbs bbs = new Bbs();
				bbs.setRownum(rs.getInt("ROWNUM"));
				bbs.setBbsID(rs.getInt("bbsID"));
				bbs.setBbsTitle(rs.getString("bbsTitle"));
				bbs.setBbsContents(rs.getString("bbsContents"));
				bbs.setBbsViews(rs.getInt("bbsViews"));
				bbs.setBbsUserID(rs.getString("bbsUserID"));
				bbs.setBbsDate(rs.getString("bbsDate"));
				
				list.add(bbs);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) rs.close();
				if (pstmt != null) pstmt.close();
				if (conn != null) conn.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}
		
	return list;
}
	
	public void insertBbs(Bbs bbs) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = DBconnection.getConnection();
			String sql = "INSERT INTO bbs(bbstitle, bbsContents, bbsViews, bbsUserID, bbsDate, bbsorder, bbsdepth, bbsgroup) VALUE(?, ?, 0, ?, now(), 1, 0, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bbs.getBbsTitle());
			pstmt.setString(2, bbs.getBbsContents());
			pstmt.setString(3, bbs.getBbsUserID());
			pstmt.setInt(4, bbs.getBbsgroup());
			pstmt.executeUpdate();
			pstmt.close();
			
			pstmt = conn.prepareStatement("UPDATE bbs SET bbsgroup = (SELECT last_insert_id()) WHERE bbsID = (SELECT last_insert_id());");
			pstmt.executeUpdate();
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				if(conn != null) conn.close();
				if(pstmt != null) pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
			}
		}
	}
	
	public void replyBbs(Bbs bbs) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBconnection.getConnection();
			String sql = new StringBuffer()
					.append("INSERT INTO bbs")
					.append("(bbstitle, bbsContents, bbsViews, bbsUserID, bbsDate, bbsorder, bbsdepth, bbsgroup)")
					.append("VALUE(?, ?, 0, ?, now(), ?, ?, ?)")
					.toString();
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bbs.getBbsTitle());
			pstmt.setString(2, bbs.getBbsContents());
			pstmt.setString(3, bbs.getBbsUserID());
			pstmt.setInt(4, bbs.getBbsgroup());
			pstmt.setInt(5, bbs.getBbsorder()+1);
			pstmt.setInt(6, bbs.getBbsdepth()+1);
			pstmt.executeUpdate();
			pstmt.close();
			
			pstmt = conn.prepareStatement("UPDATE bbs SET bbsorder = bbsorder+1 WHERE bbsgroup = ? AND bbsorder >= ? AND bbsID <> last_insert_id();");
			pstmt.setInt(1, bbs.getBbsgroup());
			pstmt.setInt(2, bbs.getBbsorder());
			pstmt.executeUpdate();
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				if(conn != null) conn.close();
				if(pstmt != null) pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public int getTotalCount() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		
		try {
			conn = DBconnection.getConnection();
			String query = "SELECT COUNT(*) count FROM Bbs";
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				count = rs.getInt("count");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) rs.close();
				if (pstmt != null) pstmt.close();
				if (conn != null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return count;
	}

	public Bbs getDetail(Bbs bbs1) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Bbs bbs = null;
	
		try {
			conn = DBconnection.getConnection();
			String sql = "SELECT * FROM bbs WHERE bbsID=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bbs1.getBbsID());
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				bbs = new Bbs();
				bbs.setBbsID(rs.getInt("bbsID"));
				bbs.setBbsUserID(rs.getString("bbsUserID"));
				bbs.setBbsTitle(rs.getString("bbsTitle"));
				bbs.setBbsContents(rs.getString("bbsContents"));
				bbs.setBbsgroup(rs.getInt("bbsgroup"));
				bbs.setBbsorder(rs.getInt("bbsorder"));
				bbs.setBbsdepth(rs.getInt("bbsdepth"));
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
			if (rs != null) rs.close();
			if (pstmt != null) pstmt.close();
			if (conn != null) conn.close();
			} catch (SQLException e) {
			e.printStackTrace();
			}
		}
		return bbs;
	}
	
	public void getDelete(Bbs bbs4) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = DBconnection.getConnection();
			String sql = "DELETE FROM bbs WHERE bbsID=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bbs4.getBbsID());
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void getEdit(Bbs bbs2) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = DBconnection.getConnection();
			String sql = "UPDATE bbs SET bbsUserID=?, bbsTitle=?, bbsContents=? WHERE bbsID=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bbs2.getBbsUserID());
			pstmt.setString(2, bbs2.getBbsTitle());
			pstmt.setString(3, bbs2.getBbsContents());
			pstmt.setInt(4, bbs2.getBbsID());
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void viewCount(int bbsID) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = DBconnection.getConnection();
			String sql = "UPDATE bbs SET bbsViews = bbsViews + 1 WHERE bbsID = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bbsID);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
}


