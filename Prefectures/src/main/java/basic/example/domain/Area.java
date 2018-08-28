package basic.example.domain;

import java.util.List;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="areas")
public class Area {

	@Id
	private Integer area_code;

	@Column(nullable=false)
	private String area_name;

	@Column
	private String area_name_ank;
	
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="area")	// １対多の関係にする
	// cascade=CascadeType.ALL ：areasの永続化操作や削除操作を関連先のprefectureにも伝播させる事が出来る
	// fetch=FetchType.LAZY ：関連するエンティティを遅延ロードさせる事が出来る（既定値）
	// mappedBy ：双方向の関連にする為、関連先（Prefectureクラス参照）でのプロパティ名を指定
	private List<Prefecture> prefectures;
}
