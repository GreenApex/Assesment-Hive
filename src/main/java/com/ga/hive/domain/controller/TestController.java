package com.ga.hive.domain.controller;

import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ga.hive.common.ErrorCodes;
import com.ga.hive.domain.util.JsonUtility;
import com.ga.hive.persistence.HiveCreateTable;
import com.google.gson.Gson;

@RestController
@RequestMapping(value = "/test")
public class TestController {
	private static final Logger LOGGER = Logger.getLogger(TestController.class);

	@RequestMapping(value = "/t", method = RequestMethod.GET)
	public @ResponseBody String testMethod(@RequestParam("tablename") String tablename) {
		LOGGER.info("TestController controller");
		try {
			HiveCreateTable hiveCreateTable = new HiveCreateTable();
			hiveCreateTable.insertTable(tablename);
			LOGGER.info("table created");

			Gson gson = new Gson();
			String jsonUser = gson.toJson("success");

			return JsonUtility.getJson(ErrorCodes.GA_TRANSACTION_OK, jsonUser);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

}
