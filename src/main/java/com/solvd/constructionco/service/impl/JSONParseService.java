package com.solvd.constructionco.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.solvd.constructionco.Main;
import com.solvd.constructionco.models.Customer;
import com.solvd.constructionco.util.parse.JSONMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;

public class JSONParseService {

        private static final Logger logger = LogManager.getLogger(Main.class);
        private static final ObjectMapper mapper = JSONMapper.getObjectMapper();

        public JSONParseService(){

        }

    public void serializeCustomerToJsonFile(Customer customer, String writeFilePath) {
        try {
            mapper.writeValue(new File(writeFilePath), customer);
            logger.info("Customer object serialized and written to file: " + writeFilePath);
        } catch (IOException e) {
           logger.info("Error occurred while serializing customer to JSON file: " + e.getMessage());
        }
    }

    public Customer deserializeCustomerFromJsonFile(String filePath ) {
        try {
            return mapper.readValue(new File(filePath), Customer.class);
        } catch (IOException e) {
            logger.info("Error occurred while deserializing customer from JSON file: " + e.getMessage());
            return null;
        }
    }
}
