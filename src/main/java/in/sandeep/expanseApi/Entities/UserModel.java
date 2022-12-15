package in.sandeep.expanseApi.Entities;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class UserModel {

	@NotBlank(message="Please enter name")
	private String name;

	@NotNull(message="Please enter email")
	@Email(message="Please enter Valid Email")
	private String email;

	@NotNull(message="Please enter password")
	@Size(min=5, message="Password mustbe of atleast 5 charcter")
	private String password;

	private Long age=0L;

	
	
}
