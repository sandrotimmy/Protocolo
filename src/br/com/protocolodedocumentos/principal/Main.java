package br.com.protocolodedocumentos.principal;

import br.com.protocolodedocumentos.classes.*;
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
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Date;
import org.xml.sax.SAXException;

public class Main {

    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException, SQLException, JAXBException, IOException, SAXException {

        //Cria a Conexão com o Hibernate
        EntityManager em = ConexaoEntityManager.getInstance();
        Manipula m = new Manipula();

        //Instancia um protocolo
        Protocolo protocolo = new Protocolo(new Date(), "Sim", "Todos documentos Originais");
        //Lista que vai receber os documentos
        ArrayList<Documentos> listaDocumentos = new ArrayList();
        //Adicionando documentos na lista
        listaDocumentos.add(new Documentos(protocolo, "Constituicao", "Contrato Social", "Pertencente a Empresa MasterGlass LTDA"));
        listaDocumentos.add(new Documentos(protocolo, "Pessoal", "Documento Identidade RG", "Pertencente ao Socio Anderson Lima"));
        listaDocumentos.add(new Documentos(protocolo, "Imposto", "DAS Simples Nacional", "Competencia 04/2016"));
        //Seta os documentos no protocolo
        protocolo.setDocumentos(listaDocumentos);
        //Instancia um requerente
        Requerente requerente = new Requerente(46325741365L, "Nilton Ferreira", "Gerente", protocolo);
        //Seta o requerente no protocolo
        protocolo.setRequerente(requerente);
        //Instancia a empresa
        Empresa empresa = new Empresa(12658745000174L, "MasterGlass LTDA", "Industria", protocolo);
        //seta a empresa no protocolo
        protocolo.setEmpresa(empresa);

        //persistindo os dados
        em.getTransaction().begin();
        em.persist(empresa);
        em.persist(requerente);
        em.persist(protocolo);
        listaDocumentos.stream().forEach((listaDocumento) -> {
            em.persist(listaDocumento);
        });
        em.getTransaction().commit();

        //Busca protocolo
        
        Documentos documentosBusca = em.find(Documentos.class, 1L);
        Protocolo protocoloBusca = em.find(Protocolo.class, 1L);
        
        
        
        
        
        //Exibe os dados na tela
        m.ExibeDados(protocoloBusca);
//      
//Gera o XML Schema
//        m.geraXmlSchema();
//        //Gera o XML
//        Writer writer = new StringWriter();
////        m.geraXml(protocoloBusca, writer);
//        JAXBContext context = JAXBContext.newInstance(new Class[]{Protocolo.class});
//        Marshaller marshaller = context.createMarshaller();
//        marshaller.setProperty(javax.xml.bind.Marshaller.JAXB_FORMATTED_OUTPUT, true);
//        marshaller.marshal(protocoloBusca, writer);
//
//        //Valida o XML atravez do XMLSchema
////        Manipula valida = new Manipula();
////        System.out.println(valida.ValidaXml(writer.toString(), "XMLSchema.xsd"));
//
//        //
//        Writer file = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("c:\\teste2.xml"), "ISO-8859-1"));
//        file.write(writer.toString());
//        file.close();
        System.exit(0);
    }

}
