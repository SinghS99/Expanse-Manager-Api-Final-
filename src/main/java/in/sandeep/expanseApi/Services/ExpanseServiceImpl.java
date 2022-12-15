package in.sandeep.expanseApi.Services;


import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import in.sandeep.expanseApi.Entities.Expanse;
import in.sandeep.expanseApi.Exceptions.ResourceNotFoundException;
import in.sandeep.expanseApi.Repository.ExpanseRepository;

@Service
public class ExpanseServiceImpl implements ExpanseService {
	
	@Autowired
	private ExpanseRepository expanseRepo;
	@Autowired
    private UserService userService;

	@Override
	public Page<Expanse> getAllExpanse(Pageable page) {
		return  expanseRepo.findByUserId(userService.getLoggedInUser().getId(), page);
	}
	

	@Override
	public Expanse getExpanseById(Long id) {
		Optional<Expanse>expanse=expanseRepo.findByUserIdAndId(userService.getLoggedInUser().getId(), id);
		if(expanse.isPresent()) {
			return expanse.get();
		}
		throw new ResourceNotFoundException("Expanse is not found for the id"+id);
	}

	@Override
	public void deleteExpanseById(Long id) {
    Expanse expanse=getExpanseById(id);		
    expanseRepo.delete(expanse);
	}

	@Override
	public Expanse saveExpanseDeatils(Expanse expanse) {
		expanse.setUser(userService.getLoggedInUser());
		return expanseRepo.save(expanse);
	}

	@Override
	public Expanse updateExpanseDetails(Long id, Expanse expanse) {
		Expanse existingExpanse=getExpanseById(id);
		existingExpanse.setName(expanse.getName() !=null ?expanse.getName() : existingExpanse.getName());
		existingExpanse.setDescription(expanse.getDescription() !=null ?expanse.getDescription() : existingExpanse.getDescription());
		existingExpanse.setCategory(expanse.getCategory() !=null ? expanse.getCategory() : existingExpanse.getCategory());
		existingExpanse.setAmount(expanse.getAmount() !=null ? expanse.getAmount(): existingExpanse.getAmount());
		existingExpanse.setDate(expanse.getDate() !=null ? expanse.getDate(): existingExpanse.getDate() );
		return expanseRepo.save(existingExpanse);
	}


	@Override
	public List<Expanse> readByCategory(String category, Pageable page) {
		return expanseRepo.findByUserIdAndCategory(userService.getLoggedInUser().getId(),category, page).toList();
	}


	@Override
	public List<Expanse> readByName(String keyword, Pageable page) {
		return expanseRepo.findByUserIdAndNameContaining(userService.getLoggedInUser().getId(),keyword, page).toList();
	}


	@Override
	public List<Expanse> readByDate(Date startDate, Date endDate, Pageable page) {
		if(startDate ==null) {
			startDate=new Date(0);
		}
		if(endDate ==null) {
			endDate =new Date(System.currentTimeMillis());
		}
		return expanseRepo.findByUserIdAndDateBetween(userService.getLoggedInUser().getId(),startDate, endDate, page).toList();
		
	}

	

	

}
