package basic.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import basic.example.domain.Prefecture;
import basic.example.domain.User;
import basic.example.repository.PrefectureRepository;

@Service
@Transactional
public class PrefectureService {
	@Autowired
	PrefectureRepository prefectureRepository;
	
	public List<Prefecture> findAll() {
		return prefectureRepository.findAllWithUserOrderByName();
	}
	
	public Prefecture findOne(Integer id) {
		return prefectureRepository.findOne(id);
	}
	
	public Prefecture create(Prefecture prefecture, User user) {
		prefecture.setUser(user);
		return prefectureRepository.save(prefecture);
	}
	
	public Prefecture update(Prefecture prefecture, User user) {
		prefecture.setUser(user);
		return prefectureRepository.save(prefecture);
	}
	
	public void delete(Integer id) {
		prefectureRepository.delete(id);
	}
}