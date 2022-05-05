package com.flight.controller;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flight.dao.CredentionalRepo;
import com.flight.dao.UserProfileRepo;
import com.flight.models.Credentialsbean;
import com.flight.models.Profilebean;
import com.flight.models.Status;

@RestController
@RequestMapping("/userlogin-admin-controller")
public class UserLoginController {

@Autowired
CredentionalRepo cred;
@Autowired
UserProfileRepo prof;
@PostMapping(value="/users/register",consumes = "application/json")

public Status registerUser(@RequestBody Profilebean newUser) {

     Credentialsbean c = new Credentialsbean();
	 c.setLoginstatus(1);
	 c.setPassword(newUser.getPassword());
	 c.setUsertype("C");
	 Random rand = new Random();
	 int resRandom = rand.nextInt((9999 - 100) + 1) + 10;
	 c.setUserID(newUser.getFirstName().substring(0,2) + Integer.toString(resRandom));    
   
	newUser.setCred(c);
	prof.save(newUser);
    cred.save(c);
    
    System.out.println(c.getUserID());
    return Status.Registered_successfully;
}


@GetMapping("/users/profilelist")
public List<Profilebean> profiles(){
	return (List<Profilebean>) prof.findAll();
}
@GetMapping("users/credentialslist")
public List<Credentialsbean> Cred(){
	return cred.findAll();
}

@PostMapping(value="/users/login",consumes = "application/json")
public Status loginUser(@RequestBody Credentialsbean user) {
    List<Credentialsbean> users = cred.findAll();
    for (Credentialsbean other : users) {
        if (other.equals(user)&& other.getLoginstatus()==0) {
            user.setLoginstatus(1);
            if(user.getUserID().equals("admin123")) {
            	user.setUsertype("A");
            }
            else {
            	user.setUsertype("C");
            }
            cred.save(user);
            return Status.Login_Successfully;
        }
        if(other.equals(user)&&other.getLoginstatus()==1) {
        	user.setLoginstatus(1);
        	if(user.getUserID().equals("admin123")) {
            	user.setUsertype("A");
            	
            }
            else {
            	user.setUsertype("C");
            	
            }
        	cred.save(user);
        	return Status.User_Already_Logined;
        }
    }
    return Status.User_not_found;
}
@PostMapping("/users/logout")
public Status logUserOut(@RequestBody Credentialsbean user) {
    List<Credentialsbean> users = cred.findAll();
    for (Credentialsbean other : users) {
        if (other.equals(user)&&other.getLoginstatus()==1) {
            user.setLoginstatus(0);
            if(user.getUserID().equals("admin123")) {
            	user.setUsertype("A");
            }
            else {
            	user.setUsertype("C");
            }
            cred.save(user);
            return Status.Logout_Successfully;
        }
    }
    return Status.Failure;
}

    @PutMapping(path="/users/changepassword",consumes= {"application/json"})
	public Status ChangePassword(@RequestBody Credentialsbean user) {
          cred.save(user);
	      return Status.Updated_successfully;
	       
	       
	}
    
}


