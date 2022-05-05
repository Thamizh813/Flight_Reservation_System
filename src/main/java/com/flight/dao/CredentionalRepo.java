package com.flight.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.flight.models.Credentialsbean;

public interface CredentionalRepo extends JpaRepository<Credentialsbean, String> {

}
