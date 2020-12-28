package com.mycompany.projeto1;

import java.io.FileOutputStream;
import java.io.OutputStream;

import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

public class HTMLViewer {

    public static void main(String[] args) {
        try {
            TransformerFactory tFactory = TransformerFactory.newInstance();
            Source xslFile = new StreamSource("../projeto2/XSLtransformation.xsl");
            Source xmlFile = new StreamSource("../projeto2/XMLafterProcessor.xml");
            OutputStream htmlFile = new FileOutputStream("outputHTML.html");
            Transformer transform = tFactory.newTransformer(xslFile);
            transform.transform(xmlFile, new StreamResult(htmlFile));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
