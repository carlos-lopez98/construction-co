package com.solvd.constructionco.util.parse.impl;

import com.solvd.constructionco.Main;
import com.solvd.constructionco.models.Invoice;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class JAXBParser {

    private static final Logger logger = LogManager.getLogger(Main.class);
    private static final String OUTPUT_JAXB = "src/main/resources/xml/output_jaxb.xml";


    public void marshall(Invoice invoice) {
        try {
            JAXBContext context = JAXBContext.newInstance(Invoice.class);
            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            m.marshal(invoice, new File(OUTPUT_JAXB));
        } catch (JAXBException e) {
            logger.info("JAXB Exception occurred during marshall" + e.getMessage());
        }
            logger.info("Marshalling Successful");
    }


    public Invoice unmarshall(String filePath) {
        Unmarshaller unmarshaller = null;
        Invoice invoice = null;
        try {
            JAXBContext context = JAXBContext.newInstance(Invoice.class);
            unmarshaller = context.createUnmarshaller();
            invoice = (Invoice) unmarshaller.unmarshal(new File(filePath));
            logger.info("UnMarshalling Successful");
        } catch (JAXBException e) {
            logger.info("JAXB Exception occurred during unmarshall" + e.getMessage());
        }
        return invoice;
    }
}
