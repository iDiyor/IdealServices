package com.vodiy.idealservices;

import com.vodiy.idealservices.service.serviceprovider.ServiceProvider;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

/**
 * Created by idiyor on 03/01/2016.
 */
public class DataProvider {

    private DocumentBuilderFactory docFactory;
    private DocumentBuilder docBuilder;
    private final List<ServiceProvider> mServiceProvidersList;
    private final HashMap<String, List<ServiceProvider>> mChildServiceAndProvidersList;

    private final HashMap<String, List<String>> mParentAndChildServicesList;

    public DataProvider() {

        mServiceProvidersList = new ArrayList<ServiceProvider>();
        mChildServiceAndProvidersList = new HashMap<String, List<ServiceProvider>>();
        mParentAndChildServicesList = new HashMap<String, List<String>>();

    }


    private String getNodeValue(NamedNodeMap map, String key) {
        String nodeValue = null;
        Node node = map.getNamedItem(key);
        if (node != null) {
            nodeValue = node.getNodeValue();
        }
        return nodeValue;
    }

    public HashMap<String, List<ServiceProvider>> getChildServicesListWithServiceProviders() {
        return mChildServiceAndProvidersList;
    }

    public HashMap<String, List<String>> getParentAndChildServicesList() {
        return mParentAndChildServicesList;
    }

    public ArrayList<ServiceProvider> getServiceProvidersListForSubService(String subService) {
        for (Map.Entry<String, List<ServiceProvider>> entry : mChildServiceAndProvidersList.entrySet()) {
            if (entry.getKey().equals(subService)) {
                return (ArrayList<ServiceProvider>)entry.getValue();
            }
        }
        return null;
    }

    public void parseData(InputStream inStream) {
        try {
            this.docFactory = DocumentBuilderFactory.newInstance();
            this.docBuilder = this.docFactory.newDocumentBuilder();
            this.docBuilder.isValidating();
            Document doc = this.docBuilder.parse(inStream, null);

            doc.getDocumentElement().normalize();

            NodeList services = doc.getElementsByTagName("service");
            //loop services
            for (int i = 0; i < services.getLength(); i++) {
                Node aService = services.item(i); // serivce
                Element aServiceElement = (Element)aService; // service

                final NamedNodeMap servicesArguments = aServiceElement.getAttributes();
                final String serviceName = getNodeValue(servicesArguments, "name");


                NodeList subServices = aServiceElement.getElementsByTagName("subservice");
                List<String> subServicesNameArray = new ArrayList<>();

                //loop subservices
                for (int j = 0; j < subServices.getLength(); j++) {
                    Element aSubServiceElement = (Element) subServices.item(j);

                    final NamedNodeMap subServicesArguments = aSubServiceElement.getAttributes();
                    final String subServiceName = getNodeValue(subServicesArguments, "name");

                    subServicesNameArray.add(subServiceName);


                    NodeList items = aSubServiceElement.getElementsByTagName("item");
                    ArrayList<ServiceProvider> serviceProvidersList = new ArrayList<>();

                    //loop items
                    for (int k = 0; k < items.getLength(); k++) {
                        final NamedNodeMap item = items.item(k).getAttributes();
                        final String name = getNodeValue(item, "name");
                        final String skills = getNodeValue(item, "skills");
                        final String phone = getNodeValue(item, "phone");
                        final String profilePicture = getNodeValue(item, "image");

                        ServiceProvider serviceProvider = new ServiceProvider(name, skills, phone, profilePicture);
                        serviceProvidersList.add(serviceProvider);
                    }

                    mChildServiceAndProvidersList.put(subServiceName, serviceProvidersList);
                }

                mParentAndChildServicesList.put(serviceName, subServicesNameArray);

            }
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
    }
}
