package blog.domain.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.mysql.cj.CharsetSettings;

import blog.config.DB;
import blog.domain.user.dto.JoinReqDto;

public class UserDao {

	public int findByUsername(String username) { //유저네임 찾기
		
		String sql = "SELECT * FROM user WHERE username = ?";
		Connection conn = DB.getConnection();
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1,username);
			rs = pstmt.executeQuery();  // executeUpdate -> INSERT OR UPDATE 경우
																						// executeQuery -> SELECT 시
			if(rs.next()) {	
				return 1;
			}
	
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DB.close(conn, pstmt, rs);
		}
		return -1;
	}
	
	
	
	public int save(JoinReqDto dto) { //회원가입
		
		String sql = "INSERT INTO user(username, password, email, address, userRole, createDate) VALUES(?,?,?,?,'USER',now())";
		Connection conn = DB.getConnection();
		PreparedStatement pstmt=null;
		
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, dto.getUsername());
			pstmt.setString(2, dto.getPassword());
			pstmt.setString(3, dto.getEmail());
			pstmt.setString(4, dto.getAddress());
			int result = pstmt.executeUpdate();
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DB.close(conn, pstmt);
		}
		return -1;
	}
	
	public void update() { // 회원수정
		
	}
	
	public void usernamecheck() { // 아이디 중복 체크
		
	}
	
	public void findById() { // 회원정보 보기
		
	}
}

