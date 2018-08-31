/**
 * 認証の為のログイン画面を返すコントローラ
 */
package basic.example.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
	@GetMapping(path="loginForm")
	String loginForm() {
		return "loginForm";
	}
}