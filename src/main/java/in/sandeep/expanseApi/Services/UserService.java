package in.sandeep.expanseApi.Services;

import in.sandeep.expanseApi.Entities.User;
import in.sandeep.expanseApi.Entities.UserModel;


public interface UserService {
	
	User createUser(UserModel user);
	User read();
	User update(UserModel user);
	void delete();
	
	User getLoggedInUser();

}
