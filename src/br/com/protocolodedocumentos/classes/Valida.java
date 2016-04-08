/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.protocolodedocumentos.classes;

import java.io.StringReader;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.xml.sax.InputSource;
import org.xml.sax.SAXParseException;

public class Valida {

    public String ValidaDoc(String stringXml, String xsdFileName) {

        //Define o tipo de  - we use W3C  
        String schemaLang = "http://www.w3.org/2001/XMLSchema";
        //valida driver  
        SchemaFactory factory = SchemaFactory.newInstance(schemaLang);

        try {
//            URL xsdPath = "c:\\teste.txt";

            Schema schema = factory.newSchema(new StreamSource("C:\\Users\\Sandro\\Documents\\RepositorioGIT\\ProtocoloDeDocumentos\\"+xsdFileName));
            Validator validator = schema.newValidator();
            //Perform the validation:  
            validator.validate(new StreamSource(new StringReader(stringXml)));
            DocumentBuilderFactory fact = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = fact.newDocumentBuilder();
            builder.parse(new InputSource(new StringReader(stringXml)));
        } catch (Exception e) {
            if (e instanceof SAXParseException) {
                return "XML Parse Error on Col: " + ((SAXParseException) e).getColumnNumber() + " | Lin: " + ((SAXParseException) e).getLineNumber() + " - " + ((SAXParseException) e).getLocalizedMessage();
            } else {
                return "Unknow error attemping to validate XML.";
            }
        }
        return "";
    }

}
