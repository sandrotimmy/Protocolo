package br.com.protocolodedocumentos.classes;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.PropertyException;
import javax.xml.bind.SchemaOutputResolver;
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

public class Manipula {

    public void geraXmlSchema() throws JAXBException, IOException {
        JAXBContext jc = JAXBContext.newInstance(Protocolo.class);
        SchemaOutputResolver sor = new MySchemaOutputResolver();
        jc.generateSchema(sor);
    }

    public void ExibeDados(Protocolo protocolo) {
        System.out.println(
                "\n======= CONSULTA PROTOCOLO ======="
                + "\n"
                + "\nPROTOCOLO: " + protocolo.getId()
                + "\nDATA: " + protocolo.getDataProtocolo()
                + "\nOBSERVAÇÕES: " + protocolo.getObservacoes()
                + "\nRETORNO? " + protocolo.getRetorno()
                + "\nID EMPRESA: " + protocolo.getEmpresa().getId()
                + "\nCNPJ: " + protocolo.getEmpresa().getCNPJ()
                + "\nRAZÃO SOCIAL: " + protocolo.getEmpresa().getRazaoSocial()
                + "\nSEGMENTO: " + protocolo.getEmpresa().getSegmento()
                + "\n" + protocolo.getDocumentos()
                + "\nID REQUERENTE: " + protocolo.getRequerente().getId()
                + "\nCPF: " + protocolo.getRequerente().getCPF()
                + "\nNOME REQUERENTE: " + protocolo.getRequerente().getNome()
                + "\nProfissão: " + protocolo.getRequerente().getProfissao());
    }

    public void geraXml(Protocolo protocolo, Writer writer) throws PropertyException, JAXBException {


        JAXBContext context = JAXBContext.newInstance(new Class[]{Protocolo.class});
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(javax.xml.bind.Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(protocolo, writer);

    }

    public String ValidaXml(String stringXml, String xsdFileName) {

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
