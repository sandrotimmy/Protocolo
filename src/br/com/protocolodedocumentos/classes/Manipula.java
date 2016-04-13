package br.com.protocolodedocumentos.classes;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
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
import org.apache.commons.io.FileUtils;
import org.json.JSONObject;
import org.json.XML;
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

    public void geraXml(Protocolo protocoloBusca, Writer writer) throws PropertyException, JAXBException, UnsupportedEncodingException, FileNotFoundException, IOException {

        JAXBContext context = JAXBContext.newInstance(Protocolo.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(javax.xml.bind.Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(protocoloBusca, writer);

        Writer file = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("C:/Users/Sandro/Documents/RepositorioGIT/ProtocoloDeDocumentos/ArquivoXML.xml"), "ISO-8859-1"));
        file.write(writer.toString());
        file.close();

    }

    public String ValidaXml(String stringXml, String xsdFileName) {

        //define a Linguagem do schema
        String schemaLang = "http://www.w3.org/2001/XMLSchema";
        //valida driver  
        SchemaFactory factory = SchemaFactory.newInstance(schemaLang);

        try {
            //carrega o schema
            Schema schema = factory.newSchema(new StreamSource("C:/Users/Sandro/Documents/RepositorioGIT/ProtocoloDeDocumentos/" + xsdFileName));
            Validator validator = schema.newValidator();

            //Procedendo a Validação
            validator.validate(new StreamSource(new StringReader(stringXml)));
            DocumentBuilderFactory fact = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = fact.newDocumentBuilder();
            builder.parse(new InputSource(new StringReader(stringXml)));

        } catch (SAXException | IOException | ParserConfigurationException e) {
            if (e instanceof SAXParseException) {
                return "XML Erro de Parse na Col: " + ((SAXParseException) e).getColumnNumber() + " | Lin: " + ((SAXParseException) e).getLineNumber() + " - " + ((SAXParseException) e).getLocalizedMessage();
            } else {
                return "Erro desconhecido ao tentar validar o XML.";
            }
        }
        return "\n\n==============\nXML Validado Com Sucesso!";
    }

    public void geraJson() throws IOException {
        
// Monta o objeto em uma string 

        System.out.println("\n\n=========\nGERANDO JSON");
        File f = new File("C:/Users/Sandro/Documents/RepositorioGIT/ProtocoloDeDocumentos/ArquivoXML.xml");
        //Passa o Arquivo XML para String
        String xml = FileUtils.readFileToString(f);
        // Cria um JSONObject a partir do arquivo XML em string
        JSONObject protocoloJson = XML.toJSONObject(xml);
        //Identa
        String protocoloJsonIndent = protocoloJson.toString(4);
        System.out.println(protocoloJsonIndent);
        //Grava no arquivo
        try {
            try (FileWriter writeFile = new FileWriter("ArquivoEmJSON.json")) {
                writeFile.write(protocoloJsonIndent);
            }
        } catch (IOException e) {
        }
        //Imprime
        System.out.println("\n\nJSON GERADO COM SUCESSO!");

    }
}
