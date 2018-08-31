/**
 * 認証ユーザのインターフェイスUserDetailsの実装クラス
 */
package basic.example.service;

import org.springframework.security.core.authority.AuthorityUtils;

import basic.example.domain.User;

import lombok.Data;

@Data
public class LoginUserDetails extends org.springframework.security.core.userdetails.User {
	
	private final User user;	// Spring Securityの認証ユーザから実際のUserオブジェクトを取得できる様、フィールドを追加
	
	public LoginUserDetails(User user) {
		// コンストラクタを使って、ユーザ名、パスワード、認可用ロールを設定
		super(user.getUsername(), user.getEncodedPassword(), AuthorityUtils.createAuthorityList("ROLE_USER"));
		this.user=user;
	}
}

