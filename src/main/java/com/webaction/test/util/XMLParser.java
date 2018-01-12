package com.webaction.test.util;

import java.io.*;
import javax.xml.bind.*;
import javax.xml.stream.*;
import javax.xml.transform.stream.StreamSource;
import java.util.*;
import com.webaction.test.jacocobean.*;

public class XMLParser {

	private static final String XML_PATH = "/Users/souvik/Product/report_tcp.xml";

    public Report parseAndGetReport(String xmlPath) {
        Report report = null;

        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Report.class);
            XMLInputFactory xmlInputFactory = XMLInputFactory.newFactory();
            xmlInputFactory.setProperty(XMLInputFactory.SUPPORT_DTD, false);
            XMLStreamReader xmlStreamReader = xmlInputFactory.createXMLStreamReader(new StreamSource(xmlPath));


            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            report = (Report) unmarshaller.unmarshal(xmlStreamReader);
            

        } catch (JAXBException e) {
            e.printStackTrace();
        }
        catch (XMLStreamException e) {
            e.printStackTrace();
        }
        return report;
    }

    public Report getReport() {
        return parseAndGetReport(XML_PATH);
    }

	public static void main(String[]args) {
		try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Report.class);
			XMLInputFactory xmlInputFactory = XMLInputFactory.newFactory();
        	xmlInputFactory.setProperty(XMLInputFactory.SUPPORT_DTD, false);
        	XMLStreamReader xmlStreamReader = xmlInputFactory.createXMLStreamReader(new StreamSource(XML_PATH));


            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            Report report = (Report) unmarshaller.unmarshal(xmlStreamReader);

            

        } catch (JAXBException e) {
            e.printStackTrace();
        }
        catch (XMLStreamException e) {
        	e.printStackTrace();
        }
	}
}