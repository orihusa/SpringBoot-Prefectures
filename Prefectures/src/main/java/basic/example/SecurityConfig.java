/**
 * JavaConfigクラス
 * Spring Securityに特化したクラスを作成
 */
package basic.example;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	// 既定の設定に対して、追加したい箇所だけオーバーライドする
	@Override
	public void configure(WebSecurity web) throws Exception {	// 特定のリクエストに対してセキュリティ設定を無視する
		web.ignoring().antMatchers("/webjars/**", "/css/**");
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {	// 認可の設定や、ログイン、ログアウトに関する設定
		http
			.authorizeRequests()	// 認可に関する設定
				.antMatchers("/loginForm").permitAll()	// loginFormには任意のユーザがアクセス可能
				.anyRequest().authenticated()			// それ以外のパスは認証無しでアクセス不可
			.and()
			.formLogin()			// ログインに関する設定
			.loginProcessingUrl("/login")				// 認証処理のパス
				.loginPage("/loginForm")				// ログインフォーム表示のパス
				.failureUrl("/loginForm?error")			// 認証失敗時の遷移先
				.defaultSuccessUrl("/prefectures", true)	// 認証成功時の遷移先
				.usernameParameter("username").passwordParameter("password")
			.and()
			.logout()				// ログアウトに関する設定
				.logoutSuccessUrl("/loginForm");
	}

	// パスワードをハッシュ化する
	@Bean
	PasswordEncoder passwordEncoder() {
		return new Pbkdf2PasswordEncoder();		// ハッシュ化アルゴリズムのPBKDF2を選択
	}
}