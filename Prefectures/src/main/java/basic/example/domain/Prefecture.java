/**
 * 都道府県クラス
 */
package basic.example.domain;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="prefectures")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Prefecture {
	@Id
	@GeneratedValue
	private Integer id;
	
	@Column(nullable=false)
	private Integer prefecture_code;

	@Column(nullable=false)
	private String prefecture_name;

	@Column
	private String prefecture_name_ank;

	@Column
	private Integer prefecture_area_code;

	@ManyToOne(fetch=FetchType.LAZY)				// 多対一の関係を宣言
	@JoinColumn(nullable=true, name="username")		// 外部キーのカラム名を指定する
	private User user;

}