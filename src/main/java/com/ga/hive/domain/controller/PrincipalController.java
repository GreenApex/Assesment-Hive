package com.ga.hive.domain.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ga.hive.common.ErrorCodes;
import com.ga.hive.domain.util.JsonUtility;
import com.ga.hive.exception.GAException;
import com.ga.hive.persistence.entity.Principle;
import com.ga.hive.service.IPrincipalService;

@RestController
@RequestMapping(value = "/principal")
public class PrincipalController {

    private static final Logger LOGGER = Logger.getLogger(PrincipalController.class);

    @Autowired
    IPrincipalService principleservice;

    @RequestMapping(value = "/addprincipal", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody String createPrincipal(@RequestBody Principle principle) {
        LOGGER.info("create Principal");
        try {
            Boolean result = principleservice.createPrincipal(principle);
            if (result) {
                return JsonUtility.getJson(ErrorCodes.GA_TRANSACTION_OK, result);
            } else
                return JsonUtility.getJson(ErrorCodes.GA_INTERNAL, false);
        } catch (GAException e) {
            LOGGER.error(" " + e);
            return JsonUtility.getJson(e.getCode(), e.getDescription());
        }
    }

    @RequestMapping(value = "/getallprincipal", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody String getAllPrincipal() throws GAException {
        LOGGER.info("getALlPrincipal controller");
        try {
            List<Principle> principalList = principleservice.getAllPrincipal();
            if (principalList != null) {
                return JsonUtility.getJson(ErrorCodes.GA_TRANSACTION_OK, principalList);
            } else {
                return JsonUtility.getJson(ErrorCodes.GA_DATA_NOT_FOUND, "Internal error");
            }
        } catch (GAException e) {
            LOGGER.error(" " + e);
            return JsonUtility.getJson(e.getCode(), e.getDescription());
        }

    }

    @RequestMapping(value = "/getprinciplabyid", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody String getPrincipleByID(@RequestParam("principalID") String principalID) throws GAException {
        LOGGER.info("get principalID controller");
        try {
            Principle principal = principleservice.getPrincipalByID(principalID);
            if (principal != null) {
                return JsonUtility.getJson(ErrorCodes.GA_TRANSACTION_OK, principal);
            } else {
                return JsonUtility.getJson(ErrorCodes.GA_DATA_NOT_FOUND, "Internal Error!!");
            }
        } catch (GAException e) {
            LOGGER.error(" " + e);
            return JsonUtility.getJson(e.getCode(), e.getDescription());
        }
    }

    @RequestMapping(value = "/updateprincipal", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody String updatePrincipal(@RequestBody Principle principle,
            @RequestParam("principalID") String principalID) throws GAException {
        LOGGER.info("updatePrincipal controller");
        Boolean result = principleservice.updateprinciple(principle, principalID);
        if (result) {
            return JsonUtility.getJson(ErrorCodes.GA_TRANSACTION_OK, "principal updated successfully");
        } else
            return JsonUtility.getJson(ErrorCodes.GA_INTERNAL, false);

    }

    @RequestMapping(value = "/deleteprinciple", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody String deletePrinciple(@RequestParam("principleid") String principleID) throws GAException {
        LOGGER.info("deletePrinciple controller");
        Boolean result = principleservice.deletePrincipleByID(principleID);
        if (result) {
            return JsonUtility.getJson(ErrorCodes.GA_TRANSACTION_OK, "principle deleted successfully");
        } else
            return JsonUtility.getJson(ErrorCodes.GA_INTERNAL, false);

    }

}
