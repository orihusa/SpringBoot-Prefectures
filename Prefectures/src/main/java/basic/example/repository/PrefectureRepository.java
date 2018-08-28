package basic.example.repository;

import basic.example.domain.Prefecture;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PrefectureRepository extends JpaRepository<Prefecture, Integer> {
	@Query("SELECT x FROM Prefecture x ORDER BY x.prefecture_name_ank")
	List<Prefecture> findAllOrderByName();

	@Query("SELECT x FROM Prefecture x ORDER BY x.prefecture_name_ank")
	Page<Prefecture> findAllOrderByName(Pageable pageable);

	// 「N+1 SELECT問題」の対応
	@Query("SELECT DISTINCT x FROM Prefecture x JOIN FETCH x.user ORDER BY x.prefecture_name_ank")
	List<Prefecture> findAllWithUserOrderByName();
}