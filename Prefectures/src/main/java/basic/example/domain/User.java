package basic.example.domain;

//import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
//import lombok.ToString;

//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//@Entity
//@Table(name="users")
//@ToString(exclude="prefectures")
public class User {

	@Id
	private String username;

	// REST APIでUserクラスをJSON出力する場合にパスワードフィールドを除外する為、@JsonIgnoreを付ける
	@JsonIgnore
	private String encodedPassword;

//	@JsonIgnore
//	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="name")
//	private List<Prefecture> prefecture;
}