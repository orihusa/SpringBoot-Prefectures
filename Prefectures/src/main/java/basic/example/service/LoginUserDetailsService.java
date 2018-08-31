package basic.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import basic.example.domain.User;
import basic.example.repository.UserRepository;

@Service	// コンポーネントスキャンの対象とする
public class LoginUserDetailsService implements UserDetailsService {
	
	@Autowired
	UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findOne(username);	// ユーザ名からUserオブジェクト取得
		if (user ==  null) {
			// Userオブジェクトが見つからない場合、例外UsernameNotFoundExceptionを発生させる
			throw new UsernameNotFoundException("The requested user is not found.");
		}
		return new LoginUserDetails(user);
	}
}
