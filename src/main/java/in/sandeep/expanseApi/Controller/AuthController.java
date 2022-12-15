package in.sandeep.expanseApi.Controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import in.sandeep.expanseApi.Entities.JwtResponse;
import in.sandeep.expanseApi.Entities.LoginModel;
import in.sandeep.expanseApi.Entities.User;
import in.sandeep.expanseApi.Entities.UserModel;
import in.sandeep.expanseApi.Security.CustomUserDetailsService;
import in.sandeep.expanseApi.Services.UserService;
import in.sandeep.expanseApi.Util.JwtTokenUtil;

@RestController
public class AuthController {
	
	@Autowired
	private UserService userService;
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private CustomUserDetailsService userDetailsService;
	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@PostMapping("/login")
	public ResponseEntity<JwtResponse> login(@RequestBody LoginModel loginModel) throws Exception{
		
		authenticate(loginModel.getEmail(), loginModel.getPassword());
		final UserDetails userDetails=userDetailsService.loadUserByUsername(loginModel.getEmail());
		final String token=jwtTokenUtil.generateToken(userDetails);
		
		return new ResponseEntity<JwtResponse>(new JwtResponse(token),HttpStatus.OK);
	}
	
	private void authenticate(String email, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
		} catch (DisabledException e) {
			throw new Exception("User Disbale");
		}catch(BadCredentialsException ex) {
			throw new Exception("Bad Credentials");
		}
	}  
		
	
	@PostMapping("/register")
	public ResponseEntity<User>save(@Valid  @RequestBody UserModel user){
		return new ResponseEntity<User>(userService.createUser(user),HttpStatus.CREATED);
		
	}
}
