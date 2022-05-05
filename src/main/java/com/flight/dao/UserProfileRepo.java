package com.flight.dao;

import org.springframework.data.repository.CrudRepository;

import com.flight.models.Profilebean;

public interface UserProfileRepo extends CrudRepository<Profilebean, String>{

}
