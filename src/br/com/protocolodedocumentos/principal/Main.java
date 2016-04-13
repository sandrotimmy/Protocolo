package br.com.protocolodedocumentos.principal;

import br.com.protocolodedocumentos.classes.*;
import br.com.protocolodedocumentos.conexao.ConexaoEntityManager;
import java.io.StringWriter;
import java.io.Writer;
import javax.swing.*;
import java.sql.SQLException;
import javax.persistence.EntityManager;
import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import org.xml.sax.SAXException;

public class Main {

    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException, SQLException, JAXBException, IOException, SAXException {

        //Cria a Conexão com o Hibernate
        EntityManager em = ConexaoEntityManager.getInstance();

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
        //Seta a empresa no protocolo
        protocolo.setEmpresa(empresa);

        //Persistindo os dados
        em.getTransaction().begin();
        em.persist(empresa);
        em.persist(requerente);
        em.persist(protocolo);
        listaDocumentos.stream().forEach((listaDocumento) -> {
            em.persist(listaDocumento);
        });
        em.getTransaction().commit();
        
        //Busca dados do Banco
        Documentos documentosBusca = em.find(Documentos.class, 1L);
        System.out.println(documentosBusca.toString());
        Empresa empresaBusca = em.find(Empresa.class, 1L);
        System.out.println(empresaBusca.toString());
        Requerente requerenteBusca = em.find(Requerente.class, 1L);
        System.out.println(requerenteBusca);
        Protocolo protocoloBusca = em.find(Protocolo.class, 1L);
        System.out.println(protocoloBusca);

        //Instancia a Classe que vai manipular os dados
        Manipula m = new Manipula();
        //Exibe os dados consolidados
        m.ExibeDados(protocoloBusca);
        //Gera o XML Schema
        m.geraXmlSchema();
        //Gera o XML
        Writer writer = new StringWriter();
        m.geraXml(protocoloBusca, writer);
        //Valida o XML atravez do XMLSchema
        System.out.println(m.ValidaXml(writer.toString(), "XMLSchema.xsd"));
        //Gera JSON
        m.geraJson();
        
        //Encerra a aplicação
        System.exit(0);
    }
}