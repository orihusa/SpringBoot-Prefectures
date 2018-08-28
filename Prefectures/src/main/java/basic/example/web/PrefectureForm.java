package basic.example.web;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class PrefectureForm {
	@NotNull
	private Integer prefecture_code;

	@NotNull
	@Size(min=1, max=10)
	private String prefecture_name;

	@Size(min=1, max=20)
	private String prefecture_name_ank;
	
	@NotNull
	private Integer prefecture_area_code;
}