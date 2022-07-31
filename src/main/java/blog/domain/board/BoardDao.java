package blog.domain.board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import blog.config.DB;
import blog.domain.board.dto.DetailResDto;
import blog.domain.board.dto.SaveReqDto;



public class BoardDao {

	public int updateReadCount(int id) { // 글쓰기

		String sql = "UPDATE board SET readCount = readCount+1 WHERE id = ?";
		Connection conn = DB.getConnection();
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			int result = pstmt.executeUpdate();
			return result;
		} catch (Exception e) {
			e.printStackTrace(); 
		} finally {
			DB.close(conn, pstmt);
		}
		return -1;
	}
	public DetailResDto findById(int id){
		StringBuffer sb = new StringBuffer();
		sb.append("select b.id, b.title, b.content, b.readCount, u.username ");
		sb.append("from board b inner join user u ");
		sb.append("on b.userId = u.id ");
		sb.append("where b.id = ?");
		
		String sql = sb.toString();
		Connection conn = DB.getConnection();
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			// executeUpdate -> INSERT OR UPDATE 경우
			// executeQuery -> SELECT 시
			rs = pstmt.executeQuery(); 
			//Spring시 Persistence API
			if(rs.next()) {	
				DetailResDto dto = new DetailResDto();
				dto.setId(rs.getInt("b.id"));
				dto.setTitle(rs.getString("b.title"));
				dto.setContent(rs.getString("b.content"));
				dto.setReadCount(rs.getInt("b.readCount"));
				dto.setUsername(rs.getString("u.username"));
				return dto;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DB.close(conn, pstmt, rs);
		}
		return null;
	}
	
	public int count() {
		String sql = "SELECT count(*) FROM board"; // 전체 행 갯수 가져오기 count함수
		Connection conn = DB.getConnection();
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		
		try {
			pstmt=conn.prepareStatement(sql);
			rs = pstmt.executeQuery(); 
			//Spring시 Persistence API
		if(rs.next()) {
			return rs.getInt(1);
		}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DB.close(conn, pstmt, rs);
		}
		return -1;
	}
	
	public List<Board> findAll(int page){
		String sql = "SELECT * FROM board ORDER BY id DESC LIMIT ?, 4";
		Connection conn = DB.getConnection();
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		List<Board> boards = new ArrayList();
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, page*4);
			// executeUpdate -> INSERT OR UPDATE 경우
			// executeQuery -> SELECT 시
			rs = pstmt.executeQuery(); 
			//Spring시 Persistence API
			while(rs.next()) {	
				Board board= Board.builder()
						.id(rs.getInt("id"))
						.title(rs.getString("title"))
						.content(rs.getString("content"))
						.readCount(rs.getInt("readCount"))
						.createDate(rs.getTimestamp("createDate"))
						.build();
				boards.add(board);
			}
			return boards;
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DB.close(conn, pstmt, rs);
		}
		return null;
	}
	
public int save(SaveReqDto dto) { // 글쓰기
		
		String sql = "INSERT INTO board(userId, title, content, createDate) VALUES(?,?,?,now())";
		Connection conn = DB.getConnection();
		PreparedStatement pstmt=null;
		
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, dto.getUserId());
			pstmt.setString(2, dto.getTitle());
			pstmt.setString(3, dto.getContent());
			int result = pstmt.executeUpdate();
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DB.close(conn, pstmt);
		}
		return -1;
	}
}
