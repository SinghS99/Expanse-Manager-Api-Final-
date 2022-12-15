package in.sandeep.expanseApi.Services;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import in.sandeep.expanseApi.Entities.Expanse;

public interface ExpanseService {

	 Page<Expanse>getAllExpanse(Pageable page);
	Expanse getExpanseById(Long id);
	void deleteExpanseById(Long id);
	
	Expanse saveExpanseDeatils(Expanse expanse);
	Expanse updateExpanseDetails(Long id, Expanse expanse);
	
	List<Expanse> readByCategory(String category,Pageable page);
	List<Expanse> readByName(String keyword,Pageable page);
	List<Expanse> readByDate(Date startDate,Date endDate,Pageable page);
}
