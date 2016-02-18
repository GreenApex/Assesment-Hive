package com.ga.hive.service.impl;

import java.util.List;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ga.hive.common.ErrorCodes;
import com.ga.hive.exception.GAException;
import com.ga.hive.persistence.entity.Principle;
import com.ga.hive.persistence.mapper.IPrincipalMapper;
import com.ga.hive.service.IPrincipalService;

@Service
public class PrincipalServiceImpl implements IPrincipalService {

    @Autowired
    IPrincipalMapper principleMapper;

    private static final Logger LOGGER = Logger.getLogger(PrincipalServiceImpl.class);

    @Override
    public Boolean createPrincipal(Principle principal) throws GAException {
        LOGGER.info("createPrincipal: " + principal);
        principal.setPrincipleID(UUID.randomUUID().toString().replaceAll("-", ""));
        principal.setUserID(null);
        return principleMapper.createprincipal(principal);

    }

    @Override
    public List<Principle> getAllPrincipal() throws GAException {
        List<Principle> retrivedPrincipal = principleMapper.getAllPrincipal();
        LOGGER.info("principalList :" + retrivedPrincipal);
        return retrivedPrincipal;
    }

    @Override
    public Principle getPrincipalByID(String principalID) throws GAException {
        Principle dbPrincipal = principleMapper.getPrincipalByprincipalID(principalID);
        LOGGER.info("dbPrincipal :" + dbPrincipal);

        return dbPrincipal;
    }

    @Override
    public Boolean updateprinciple(Principle principle, String principalID) throws GAException {
        LOGGER.info("updateprinciple by principalID service called : " + principalID);
        Principle dbPrincipal = getPrincipalByID(principalID);
        dbPrincipal.setPrincipleName(principle.getPrincipleName());
        dbPrincipal.setDescription(principle.getDescription());
        principleMapper.updatePrincipalData(dbPrincipal);
        return true;
    }

    @Override
    public Boolean deletePrincipleByID(String principleID) throws GAException {
        LOGGER.info("deleteCategoryByID :");
        Principle dbPrinciple = getPrincipalByID(principleID);
        if (dbPrinciple == null) {
            throw new GAException(ErrorCodes.GA_DATA_NOT_FOUND, "No Principle found");
        } else {
            return principleMapper.deletePrincipleByID(principleID);
        }
    }

}
