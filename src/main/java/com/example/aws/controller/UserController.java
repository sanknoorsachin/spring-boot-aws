package com.example.aws.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.aws.entity.User;
import com.example.aws.exception.ResourceNotFoundException;
import com.example.aws.repository.UserRepository;

@RestController
@RequestMapping("/api/user")
public class UserController {
	
	
	@Autowired
	public UserRepository userRepository;
	
	
	@GetMapping("/getall")
	public List<User> getAllUsers(){
		return this.userRepository.findAll();
	}

	@GetMapping("/{id}")
	public User getUserById(@PathVariable(value ="id") long id) {
		return this.userRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("User Not Found"+id));
		
	}
	
	@PostMapping("/createuser")
	public User createUser(@RequestBody User user) {
		return this.userRepository.save(user);
		
	}
	
	@PutMapping("update/{id}")
	public User updateUser(@RequestBody User user, @PathVariable (value = "id") long id) {
	
		User existingData= this.userRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("User Not Found"+id));
		
		existingData.setFirstName(user.getFirstName());
		existingData.setLastName(user.getLastName());
		existingData.setEmail(user.getEmail());
		
		return this.userRepository.save(existingData);
		
		
		
	}
	
	@PatchMapping("patch/{id}")
	public User updateRequiredField(@RequestBody User user, @PathVariable (value = "id") long id) {
	
		User existingData= this.userRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("User Not Found"+id));
		
		if(user.getFirstName()!=null) {
			existingData.setFirstName(user.getFirstName());
		}else if(user.getLastName()!=null) {
			existingData.setLastName(user.getLastName());
		}else if(user.getEmail()!=null) {
			existingData.setEmail(user.getEmail());
		}
		
		
		return this.userRepository.save(existingData);
		
		
		
	}
}
