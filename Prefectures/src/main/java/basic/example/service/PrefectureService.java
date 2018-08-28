/**
 * 
 */
/**
 * @author yasuhiro
 *
 */
package basic.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import basic.example.domain.Prefecture;
import basic.example.repository.PrefectureRepository;

@Service
@Transactional
public class PrefectureService {
	@Autowired
	PrefectureRepository prefectureRepository;
	
	public List<Prefecture> findAll() {
		return prefectureRepository.findAllOrderByName();
	}
	
	public Prefecture findOne(Integer id) {
		return prefectureRepository.findOne(id);
	}
	
	public Prefecture create(Prefecture prefecture) {
		return prefectureRepository.save(prefecture);
	}
	
	public Prefecture update(Prefecture prefecture) {
		return prefectureRepository.save(prefecture);
	}
	
	public void delete(Integer id) {
		prefectureRepository.delete(id);
	}
}