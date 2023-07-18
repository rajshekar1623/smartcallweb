package com.aakhya.smartcall.application.integration.service;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.aakhya.smartcall.application.integration.entity.IntegrationMaster;
import com.aakhya.smartcall.application.transaction.data.entity.TransactionDataSet;

public class XmlFileReader implements FileReader {

	@Override
	public boolean checkFile(IntegrationMaster im, List<String> linesFromFile) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public List<TransactionDataSet> processFile(IntegrationMaster im, List<String> linesFromFile) {
		List<TransactionDataSet> transactionDataSets = new ArrayList<TransactionDataSet>();
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		try {
			String fileName = new SimpleDateFormat("yyyy-Mon-dd hh:mm:ss").format(new Date());
			Path path = Paths.get("D:\\documentManagement\\xml\\"+fileName+".xml");
			for(String str:linesFromFile) {
			 Files.writeString(path, str,
                     StandardCharsets.UTF_8);
			}
			File file = path.toFile();
			
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(file);
			doc.getDocumentElement().normalize();
			NodeList nodeList = doc.getElementsByTagName("transactionData");
			for(int itr=0;itr<nodeList.getLength();itr++) {
				TransactionDataSet transactionDataSet = new TransactionDataSet();
				Node node = nodeList.item(itr);
				if (node.getNodeType() == Node.ELEMENT_NODE){  
					Element eElement = (Element) node;
					String attributeName = eElement.getTagName();
					Field f = transactionDataSet.getClass().getDeclaredField(attributeName);
					f.set(transactionDataSet, eElement.getNodeValue());
					transactionDataSets.add(transactionDataSet);
				}
			}
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DOMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return transactionDataSets;
	}

}
