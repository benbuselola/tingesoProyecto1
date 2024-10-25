package com.example.backendBanco.repositories;

import com.example.backendBanco.entities.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface ClientRepository extends JpaRepository<ClientEntity, Long> {
    public ClientEntity findByRut(String rut);
    public ClientEntity findByEmail (String email);
    public List<ClientEntity> findByName(String name);
    public List<ClientEntity> findByLastName(String lastName);
    public List<ClientEntity> findByAge(int age);
    @Query(value = "SELECT * FROM clients WHERE clients.rut = :rut", nativeQuery = true)
    ClientEntity findByRutNativeQuery(@Param("rut") String rut);
    @Query(value = "SELECT * FROM clients WHERE clients.email = :email", nativeQuery = true)
    ClientEntity findByEmailNativeQuery(@Param("email") String email);
}
