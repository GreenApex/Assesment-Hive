package com.ga.hive.persistence.mapper;

import java.util.List;

import com.ga.hive.exception.GAException;
import com.ga.hive.persistence.entity.Principle;

public interface IPrincipalMapper {

    Principle getPrincipalByprincipalID(String principleID) throws GAException;

    Boolean createprincipal(Principle principal) throws GAException;

    void updatePrincipal(Principle dbPrincipal) throws GAException;

    List<Principle> getAllPrincipal() throws GAException;

    void updatePrincipalData(Principle dbPrincipal) throws GAException;

    boolean deletePrincipleByID(String principleID) throws GAException;

    List<Principle> getMyAssidnedPrinciples(String userid) throws GAException;
    
}
