package org.fransanchez.exercises.usecases.files;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.HashSet;
import java.util.Set;

public class CheckDupeXmlFile {

    public static void main(String[] args) {

        Set paymentsSet = new HashSet<String>();

        try {
            File file = new File("/Users/fransanchez/Documents/workspace/orange-bank/dotfiles/scripts/aws/10274e59-5a23-4b5c-901f-fc3f5b057b41_remesas_OB_20190204.xml");

            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(file);
            doc.getDocumentElement().normalize();

            System.out.println("Root element: " + doc.getDocumentElement().getNodeName());

            NodeList nodeList = doc.getElementsByTagName("Payment");

            for (int i = 0; i < nodeList.getLength(); i++) {
                final var payment = (Element) nodeList.item(i);
                final var vapId = payment.getElementsByTagName("VAP_ID").item(0).getTextContent();
                final var effectNumber = payment.getElementsByTagName("EffectNumber").item(0).getTextContent();

                final var inserted = paymentsSet.add(vapId);

                if (!inserted) {
                    throw new RuntimeException("Duplicated value!!!! " + vapId+effectNumber);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
