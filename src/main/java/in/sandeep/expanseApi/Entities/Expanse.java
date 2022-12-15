package in.sandeep.expanseApi.Entities;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="tbl_expanses")
public class Expanse {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	 private Long id;
	@Column(name="expanse_name")
	@NotBlank(message="expanse name must not be null")
	@Size(min=3, message="Expanse name must be atleast 3 charcter")
	 private String name;
	
	
	 private String description;
	 
	 @Column(name="expanse_amount")
	 @NotNull(message="expanse amount must not be null")
	 private BigDecimal amount;
	 
	 @NotBlank(message="expanse category must not be null")
	 @Column(name="category")
	 private String category;
	 @NotNull(message="expanse date must not be null")
	 private Date date;
	 
	 @Column(name="created_at",nullable=false,updatable=false)
	 @CreationTimestamp
	 private Timestamp createdAt;
	 
	 @Column(name="updated_at")
	 @UpdateTimestamp
	 private Timestamp updatedAt;
	 
	 @ManyToOne(fetch = FetchType.LAZY,optional = false)
	 @JoinColumn(name="user_id",nullable = false)
	 @OnDelete(action = OnDeleteAction.CASCADE)
	 @JsonIgnore
	 private User user;
	 
	 
	
	 

}
