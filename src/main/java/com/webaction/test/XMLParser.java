package com.webaction.test;

import java.io.*;
import javax.xml.bind.*;
import javax.xml.stream.*;
import javax.xml.transform.stream.StreamSource;
import java.util.*;
import com.webaction.test.jacocobean.*;

public class XMLParser {

	private static final String XML_PATH = "/Users/souvik/Product/report_tcp.xml";

	public static void main(String[]args) {
		try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Report.class);
			XMLInputFactory xmlInputFactory = XMLInputFactory.newFactory();
        	xmlInputFactory.setProperty(XMLInputFactory.SUPPORT_DTD, false);
        	XMLStreamReader xmlStreamReader = xmlInputFactory.createXMLStreamReader(new StreamSource(XML_PATH));


            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            Report report = (Report) unmarshaller.unmarshal(xmlStreamReader);

         	List<com.webaction.test.jacocobean.Package> packageList = report.getPackageList();
         	for(com.webaction.test.jacocobean.Package packageE : packageList) {
         		String packageName = packageE.getName();
         		List<com.webaction.test.jacocobean.Class> classList = packageE.getClassList();
         		for(com.webaction.test.jacocobean.Class classS : classList) {
         			String className = classS.getName();
         			List<Method> methodList = classS.getMethodList();
         			for(Method method : methodList) {
         				String methodName = method.getName();
         				List<Counter> counterList = method.getCounterList();
         				for(Counter counter : counterList) {
         					if(counter.getType().equals("METHOD") && counter.getCovered() != 0) {
         						System.out.println(packageName + ", " + className.substring(className.lastIndexOf('/') + 1, className.length()) + ", " + methodName);
         					}
         				}
         			}
         		}
         	}

        } catch (JAXBException e) {
            e.printStackTrace();
        }
        catch (XMLStreamException e) {
        	e.printStackTrace();
        }
	}
}