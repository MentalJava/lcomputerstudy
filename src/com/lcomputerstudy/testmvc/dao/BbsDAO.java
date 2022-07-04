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
			String sql = "INSERT INTO bbs(bbstitle, bbsContents, bbsViews, bbsUserID, bbsDate) VALUE(?, ?, 0, ?, now())";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bbs.getBbsTitle());
			pstmt.setString(2, bbs.getBbsContents());
			pstmt.setString(3, bbs.getBbsUserID());
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
	
	public String getDate() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT NOW()";
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				return rs.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	
	public int getNext() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT bbsID FROM bbs ORDER BY bbsID DESC";
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				return rs.getInt(1)+1;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	public int write(String bbsTitle, String bbsUserID, String bbsContents) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "INSERT INTO bbs VALUES(?, ?, ?, ?, ?, ?)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, getNext());
			pstmt.setString(2, bbsTitle);
			pstmt.setString(3, bbsUserID);
			pstmt.setString(4, getDate());
			pstmt.setString(5, bbsContents);
			pstmt.setInt(6, 1);
			
			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
}
