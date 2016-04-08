
package br.com.protocolodedocumentos.classes;

import java.io.IOException;
import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public class Valida {

    public String ValidaDoc(String stringXml, String xsdFileName) {

        //define a Linguagem do schema
        String schemaLang = "http://www.w3.org/2001/XMLSchema";
        //valida driver  
        SchemaFactory factory = SchemaFactory.newInstance(schemaLang);

        try {
            //carrega o schema
            Schema schema = factory.newSchema(new StreamSource("C:/Users/Administrador/Documents/RepositorioGIT/ProtocoloDeDocumentos/" + xsdFileName));
            Validator validator = schema.newValidator();
            
            //Procedendo a Validação
            validator.validate(new StreamSource(new StringReader(stringXml)));
            DocumentBuilderFactory fact = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = fact.newDocumentBuilder();
            builder.parse(new InputSource(new StringReader(stringXml)));
            
        } catch (SAXException | IOException | ParserConfigurationException e) {
            if (e instanceof SAXParseException) {
                return "XML Parse Error on Col: " + ((SAXParseException) e).getColumnNumber() + " | Lin: " + ((SAXParseException) e).getLineNumber() + " - " + ((SAXParseException) e).getLocalizedMessage();
            } else {
                return "Unknow error attemping to validate XML.";
            }
        }
        return "XML Validado Com Sucesso!";
    }

}
