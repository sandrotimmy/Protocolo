package br.com.protocolodedocumentos.principal;

import br.com.protocolodedocumentos.classes.Protocolo;
import br.com.protocolodedocumentos.classes.Valida;
import br.com.protocolodedocumentos.conexao.ConexaoEntityManager;
import java.io.BufferedWriter;
import java.io.StringWriter;
import java.io.Writer;
import javax.swing.*;
import java.sql.SQLException;
import javax.persistence.EntityManager;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

public class Main {

    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException, SQLException, JAXBException, IOException {

        UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        EntityManager em = ConexaoEntityManager.getInstance();
//        
//        JAXBContext jc = JAXBContext.newInstance(Requerente.class);
//        
//        SchemaOutputResolver sor = new MySchemaOutputResolver ();
//        
//        jc.generateSchema(sor);
//        

//        
//        //Objeto que vai receber os valores
//        Protocolo protocolo = new Protocolo(new Date(), "Sim", "Documentos originais");
//                
//        //Lista que vai receber os documentos
//        ArrayList<Documentos> listaDocumentos = new ArrayList();
//        //adicionando documentos na lista
//        listaDocumentos.add(new Documentos(protocolo, "Empresarial", "Contrato Social", "Pertencente a Empresa Fulano de Tal LTDA"));
//        listaDocumentos.add(new Documentos(protocolo, "Pessoal", "Documento Identidade", "Socio Anderson Lima"));
//        listaDocumentos.add(new Documentos(protocolo, "Imposto", "SIMPLES NACIONAL", "Competencia 04/2016"));
//        //seta os documentos no protocolo
//        protocolo.setDocumentos(listaDocumentos);
//        //Instancia o objeto requerente
//        Requerente requerente = new Requerente(4568, "Sandro Machado", "Tec.Contabil", protocolo);
//        //seta o requerente no protocolo
//        protocolo.setRequerente(requerente);
//        //instancia a empresa
//        Empresa empresa = new Empresa(12548, "EMPRESA DE FAIXADA LTDA", "Industria", protocolo);
//        //seta a empresa no protocolo
//        protocolo.setEmpresa(empresa);
//
//        //persistindo os dados
//        em.getTransaction().begin();
//
//        em.persist(empresa);
//        em.persist(requerente);
//        em.persist(protocolo);
//        for (Documentos listaDocumento : listaDocumentos) {
//            em.persist(listaDocumento);
//        }
//        em.getTransaction().commit();
        Protocolo p = em.find(Protocolo.class, 1L);
//            System.out.println(
//             "\n======= CONSULTA PROTOCOLO ======="
//            +"\n"
//            +"\nPROTOCOLO: "+p.getId()
//            +"\nDATA: "+p.getDataProtocolo()
//            +"\nOBSERVAÇÕES: "+p.getObservacoes()
//            +"\nRETORNO? "+p.getRetorno()
//            +"\nID EMPRESA: "+p.getEmpresa().getId()
//            +"\nCNPJ: "+p.getEmpresa().getCNPJ()
//            +"\nRAZÃO SOCIAL: "+p.getEmpresa().getRazaoSocial()
//            +"\nSEGMENTO: "+p.getEmpresa().getSegmento()
//            +p.getDocumentos()
//            +"\nID REQUERENTE: "+p.getRequerente().getId()
//            +"\nCPF: "+p.getRequerente().getCPF()
//            +"\nNOME REQUERENTE: "+p.getRequerente().getNome()
//            +"\nProfissão: "+p.getRequerente().getProfissao());
//            Requerente requerente = new Requerente(1L,125444L,"Sandro Machado", "nada");
        Writer writer = new StringWriter();

        JAXBContext context = JAXBContext.newInstance(new Class[]{Protocolo.class});
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(javax.xml.bind.Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(p, writer);

//        String xmlString = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\"></soap:Envelope>";
//
//        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
//
//        DocumentBuilder builder;
//        try {
//            builder = factory.newDocumentBuilder();
//
//            // Use String reader
//            Document document = builder.parse(new InputSource(
//                    new StringReader(writer.toString())));
//
//            TransformerFactory tranFactory = TransformerFactory.newInstance();
//            Transformer aTransformer = tranFactory.newTransformer();
//            Source src = new DOMSource(document);
//            Result dest = new StreamResult(new File("xmlFileName.xml"));
//            aTransformer.transform(src, dest);
//        } catch (Exception e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//        Writer file = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("c:\\teste.xml"), "ISO-8859-1"));
//        file.write(writer.toString());
//        file.close();
        
        Valida valida = new Valida();
        
        System.out.println(valida.ValidaDoc(writer.toString(), "testeXSD.xsd"));
        
        System.exit(0);
    }

}
