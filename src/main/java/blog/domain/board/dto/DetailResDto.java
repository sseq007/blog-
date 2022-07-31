package blog.domain.board.dto;

import lombok.Data;

@Data
public class DetailResDto {

	private int id;
	private String title;
	private String content;
	private int readCount;
	private String username;
	
	public String getTitle() {
		return title.replaceAll("<", "&lt;").replaceAll(">", "&gt;");
	}
}
