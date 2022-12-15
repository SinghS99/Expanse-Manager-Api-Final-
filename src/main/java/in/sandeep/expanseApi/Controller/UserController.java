package in.sandeep.expanseApi.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import in.sandeep.expanseApi.Entities.User;
import in.sandeep.expanseApi.Entities.UserModel;
import in.sandeep.expanseApi.Services.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService userService;
	
	
	@GetMapping("/profile")
	public ResponseEntity<User> readUser(){
		return new ResponseEntity<User>(userService.read(),HttpStatus.OK);
	}
	
	@PutMapping("/profile")
	public ResponseEntity<User> updateUser(@RequestBody UserModel user){
	
		return new ResponseEntity<User>(userService.update(user),HttpStatus.OK);
	}
	
	@DeleteMapping("/deactivate")
	public ResponseEntity<HttpStatus> deleteUser(){
	userService.delete();
		return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
	}
}
