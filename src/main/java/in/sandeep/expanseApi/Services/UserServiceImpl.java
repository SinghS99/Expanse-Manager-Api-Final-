package in.sandeep.expanseApi.Services;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import in.sandeep.expanseApi.Entities.User;
import in.sandeep.expanseApi.Entities.UserModel;
import in.sandeep.expanseApi.Exceptions.ItemAlreadyExistsException;
import in.sandeep.expanseApi.Exceptions.ResourceNotFoundException;
import in.sandeep.expanseApi.Repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder bcryptEncoder;
	
	@Override
	public User createUser(UserModel user) {
		if(userRepository.existsByEmail(user.getEmail())) {
			throw new ItemAlreadyExistsException("User is already registered with email :"+user.getEmail());
		}
		User newUser=new User();
		BeanUtils.copyProperties(user, newUser);
		newUser.setPassword(bcryptEncoder.encode(newUser.getPassword()));
		return userRepository.save(newUser);
	}

	@Override
	public User read()  {
		Long userId=getLoggedInUser().getId();
		return userRepository.findById(userId).orElseThrow(() ->new ResourceNotFoundException("User not found with :" +userId));
	}

	@Override
	public User update(UserModel user) {
		User oUser=read();
		oUser.setName(user.getName() !=null ? user.getName() : oUser.getName());
		oUser.setEmail(user.getEmail() !=null ? user.getEmail() : oUser.getEmail());
		oUser.setPassword(user.getPassword() !=null ? bcryptEncoder.encode(user.getPassword()) : oUser.getPassword());
		oUser.setAge(user.getAge() !=null ? user.getAge() : oUser.getAge());
		return userRepository.save(oUser);
	}

	@Override
	public void delete() {
		User user=read();
		userRepository.delete(user);
	}

	@Override
	public User getLoggedInUser() {
		Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
		String email=authentication.getName();
		
		return userRepository.findByEmail(email).orElseThrow(() 
				->new UsernameNotFoundException("user not found for email :"+email));
	}

}
