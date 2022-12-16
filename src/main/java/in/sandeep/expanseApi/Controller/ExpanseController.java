package in.sandeep.expanseApi.Controller;


import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import in.sandeep.expanseApi.Entities.Expanse;
import in.sandeep.expanseApi.Services.ExpanseService;

@RestController
public class ExpanseController {
	
	@Autowired
	private ExpanseService expanseService;
	

	@GetMapping("/expanses")
	 public List<Expanse> getAllExpanse(Pageable page) {
//		int number=1;
//		calculateFactorila(number);
		return expanseService.getAllExpanse(page).toList(); 
	 }

	@GetMapping("/expanses/{id}")
	public Expanse getExpanseById(@PathVariable Long id) {
		return expanseService.getExpanseById(id);
	}
	
	@ResponseStatus(value=HttpStatus.NO_CONTENT)
	@DeleteMapping("/expanses")
	public void  deleteExpanseById(@RequestParam Long id) {
		expanseService.deleteExpanseById(id);
	}
	
	@ResponseStatus(value=HttpStatus.CREATED)
	@PostMapping("/expanses")
	public void saveExpanseDetails( @Valid @RequestBody Expanse expanse) {
		expanseService.saveExpanseDeatils(expanse);
	}
	
	
	@PutMapping("/expanses/{id}")
	public Expanse updateExpanseDetails(@RequestBody Expanse expanse,@PathVariable Long id) {
		return expanseService.updateExpanseDetails(id, expanse);
	}
	
	@GetMapping("/expanses/category")
	public List<Expanse> getExpansesByCategory(@RequestParam String category,Pageable page){
		return expanseService.readByCategory(category, page);
		
	}
	@GetMapping("/expanses/name")
	public List<Expanse> getExpansesByName(@RequestParam String keyword,Pageable page){
		return expanseService.readByName(keyword, page);
	}
	@GetMapping("/expanses/date")
	public List<Expanse> getExpansesByDates(
			                @RequestParam(required = false)Date startDate,
			                @RequestParam(required = false)Date endDate,
			                Pageable page){
		return expanseService.readByDate(startDate, endDate, page);
	}
	
	//Creating a Exception
//	public int calculateFactorila(int number) {
//		return number * calculateFactorila(number -1);
////	}
	
//    @GetMapping("/users/{userId}/expanses/{id}")
//	public List<Expanse>getExpanseByUserId(@PathVariable("UserId")Long user,
//			                                 @PathVariable Long id){
//			                return null;                	 
//			                                 }
//	
}
