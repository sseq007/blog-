package blog.domain.board;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Board {

	private int id;
	private int userId;
	private String title;
	private String content;
	private int readCount;
	private Timestamp createDate;
	
	//루시 필터 적용할수 있다
	public String getTitle() {
		return title.replaceAll("<", "&lt;").replaceAll(">", "&gt;");
	}
}
