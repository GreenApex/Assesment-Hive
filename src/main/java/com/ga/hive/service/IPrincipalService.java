package com.ga.hive.service;

import java.util.List;

import com.ga.hive.exception.GAException;
import com.ga.hive.persistence.entity.Principle;

public interface IPrincipalService {

    Boolean createPrincipal(Principle principal) throws GAException;

    List<Principle> getAllPrincipal() throws GAException;

    Principle getPrincipalByID(String principalID) throws GAException;

    Boolean updateprinciple(Principle principle, String principalID) throws GAException;

    Boolean deletePrincipleByID(String principleID) throws GAException;

}
