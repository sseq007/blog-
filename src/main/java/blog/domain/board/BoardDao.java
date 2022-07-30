package blog.domain.board;

import java.sql.Connection;
import java.sql.PreparedStatement;

import blog.config.DB;
import blog.domain.board.dto.SaveReqDto;


public class BoardDao {

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
