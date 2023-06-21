package com.solvd.constructionco.util;

import com.solvd.constructionco.Main;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class SQLSessionUtil {

    public static final Logger logger = LogManager.getLogger(Main.class);
    private static final String BATIS_CONFIG = "src/main/resources/config/mybatis-config.xml";

    public SQLSessionUtil(){

    }

    public SqlSession retrieveSqlSession(){
        SqlSession session = null;
        try {
            InputStream stream = Resources.getResourceAsStream(BATIS_CONFIG);
            session = new SqlSessionFactoryBuilder().build(stream, this.retrieveProperties()).openSession();
        } catch (IOException e) {
            logger.info("File Not Found: Exception --- " + e.getMessage());
        }
        return session;
    }


    private Properties retrieveProperties() {
        Properties properties = new Properties();
        try (FileInputStream fis = new FileInputStream("src/main/resources/database.properties")) {
            properties.load(fis);
            return  properties;
        } catch (FileNotFoundException e) {
            logger.info("File not found" + e);
        } catch (IOException e) {
            logger.info("Input Output Exception Occured" + e);
        }
        return properties;
    }

}
