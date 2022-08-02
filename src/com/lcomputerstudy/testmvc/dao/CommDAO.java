package com.lcomputerstudy.testmvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import com.lcomputerstudy.testmvc.database.DBconnection;
import com.lcomputerstudy.testmvc.vo.Pagination;
import com.lcomputerstudy.testmvc.vo.Bbs;
import com.lcomputerstudy.testmvc.vo.Comm;

public class CommDAO {
	
	private static CommDAO commDao = new CommDAO();
	
	private CommDAO() {
		
	}
	
	public static CommDAO getInstance() {
		return commDao;
	}
	
	public ArrayList<Comm> getList(Pagination pagination, Bbs bbs1) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Comm>	list = null;
		int pageNum = (pagination.getPage()-1)*Pagination.perPage;
		int b_id = bbs1.getBbsID();
		
		try {
			conn = DBconnection.getConnection();
			String query = new StringBuilder()
					.append("SELECT		@ROWNUM := @ROWNUM - 1 AS ROWNUM,\n")
					.append("			ta.*\n")
					.append("From		comm ta\n")
					.append("INNER JOIN (SELECT @rownum := (SELECT COUNT(*)-?+1 FROM comm ta)) tb ON 1=1\n")
					.append("WHERE      b_id = ?\n")
					.append("ORDER BY c_group DESC, c_order ASC\n")
					.append("LIMIT		?, ").append(Pagination.perPage).append("\n")
					.toString();
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, pageNum);
			pstmt.setInt(2, b_id);
			pstmt.setInt(3, pageNum);
			rs = pstmt.executeQuery();
			list = new ArrayList<Comm>();
			
			while(rs.next()) {
				Comm comm = new Comm();
				comm.setRownum(rs.getInt("ROWNUM"));
				comm.setB_id(rs.getInt("b_id"));
				comm.setC_id(rs.getInt("c_id"));
				comm.setC_group(rs.getInt("c_group"));
				comm.setC_order(rs.getInt("c_order"));
				comm.setC_depth(rs.getInt("c_depth"));
				comm.setC_userid(rs.getString("c_userid"));
				comm.setC_comments(rs.getString("c_comments"));
				comm.setC_date(rs.getString("c_date"));
				
				list.add(comm);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) rs.close();
				if (conn != null) conn.close();
				if (pstmt != null) pstmt.close();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
		
		return list;
	}
	
	public void insertComments(Comm comm) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = DBconnection.getConnection();
			String sql = "INSERT INTO comm(b_id, c_id, c_group, c_order, c_depth, c_userid, c_comments, c_date) VALUE(? ,?, ?, 1, 0, ?, ?, now())";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, comm.getB_id());
			pstmt.setInt(2, comm.getC_id());
			pstmt.setInt(3, comm.getC_group());
			pstmt.setString(4, comm.getC_userid());
			pstmt.setString(5, comm.getC_comments());
			pstmt.executeUpdate();
			pstmt.close();
		
			pstmt = conn.prepareStatement("UPDATE comm SET c_group = (SELECT last_insert_id()) WHERE c_id = (SELECT last_insert_id());");
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		try {
			if(conn != null) conn.close();
			if(pstmt != null) pstmt.close();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}	
		}
	}
	
	public void replyComments(Comm comm) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = DBconnection.getConnection();
			String sql = new StringBuffer()
					.append("INSERT INTO comm")
					.append("(b_id, c_id, c_group, c_order, c_depth, c_userid, c_comments, c_date)")
					.append("VALUE(?, ?, ?, ?, ?, ?, ?, now()")
					.toString();
			int rOrder = comm.getC_order()+1;
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, comm.getB_id());
			pstmt.setInt(2, comm.getC_id());
			pstmt.setInt(3, comm.getC_group());
			pstmt.setInt(4, rOrder);
			pstmt.setInt(5, comm.getC_depth()+1);
			pstmt.setString(6, comm.getC_userid());
			pstmt.setString(7, comm.getC_comments());
			pstmt.executeUpdate();
			pstmt.close();
			
			pstmt = conn.prepareStatement("UPDATE comm SET c_order = c_order + 1 WHERE c_group = ? AND c_order >= ? AND c_id != last_insert_id();");
			pstmt.setInt(1, comm.getC_group());
			pstmt.setInt(2, rOrder);
			pstmt.executeUpdate();
			pstmt.close();
			
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
	
	public void deleteComments(Comm comm) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = DBconnection.getConnection();
			String sql = "DELETE FROM comm WHERE c_id = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, comm.getC_id());
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(conn != null) conn.close();
				if(pstmt != null) pstmt.close();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
	}
	
	public void editComments(Comm comm) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = DBconnection.getConnection();
			String sql = "UPDATE comm SET c_userid = ?, c_comments = ? WHERE c_id = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, comm.getC_userid());
			pstmt.setString(2, comm.getC_comments());
			pstmt.setInt(3, comm.getC_id());
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(conn != null) conn.close();
				if(pstmt != null) pstmt.close();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
	}
	
	public int getCount() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		
		try {
			conn = DBconnection.getConnection();
			String query = "SELECT COUNT(*) count FROM comm";
			
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				count = rs.getInt("count");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(conn != null) conn.close();
				if(pstmt != null) pstmt.close();
				if(rs != null) rs.close();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
		return count;
	}
}
