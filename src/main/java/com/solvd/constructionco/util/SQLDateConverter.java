package com.solvd.constructionco.util;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.sql.Date;
import java.text.SimpleDateFormat;

public class SQLDateConverter extends XmlAdapter<String, Date> {

    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public Date unmarshal(String dateString) throws Exception {
        synchronized (dateFormat){
            Date utilDate = (Date) dateFormat.parse(dateString);
            return new Date(utilDate.getTime());
        }
    }

    @Override
    public String marshal(Date sqlDate) throws Exception {
        synchronized (dateFormat) {
            java.util.Date utilDate = new java.util.Date(sqlDate.getTime());
            return dateFormat.format(utilDate);
        }
    }
}
