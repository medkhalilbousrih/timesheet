package esprit.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RestController;



@RestController
public class Health {
	
	@GetMapping("/health")
	public ResponseEntity<String> getHealth(){
		return new ResponseEntity<>("God Ho", HttpStatus.OK);
	}
	
}
