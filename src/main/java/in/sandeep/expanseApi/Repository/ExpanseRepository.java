package in.sandeep.expanseApi.Repository;


import java.util.Date;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.sandeep.expanseApi.Entities.Expanse;

@Repository
public interface ExpanseRepository extends JpaRepository<Expanse, Long> {

	//SELECT * FROM tbl_expanses WHERE user_id=? AND category=?
	Page<Expanse> findByUserIdAndCategory(Long userId,String category,Pageable page);
	
	//SELECT * FROM tbl_expanses WHERE user_id=? AND  name LIKE '%keyword%'
	Page<Expanse> findByUserIdAndNameContaining(Long userId,String keyword,Pageable page);
	
	//SELECT * FROM tbl_expanses WHERE user_id=? AND date BETWEEN 'startDate' AND 'endDate'
	Page<Expanse> findByUserIdAndDateBetween(Long userId,Date startDate,Date endDate,Pageable page);
	
	//SELECT * from tbl_expanse WHERE user_id=?
	Page<Expanse> findByUserId(Long userId,Pageable page);
	
	//SELECT * FROM tbl_expanse WHERE user_id=? AND id=?
	Optional<Expanse>findByUserIdAndId(Long userId,Long expanseId);
}
