package br.com.protocolodedocumentos.principal;

import br.com.protocolodedocumentos.classes.Documentos;
import br.com.protocolodedocumentos.classes.Empresa;
import br.com.protocolodedocumentos.classes.MySchemaOutputResolver;
import br.com.protocolodedocumentos.classes.Protocolo;
import br.com.protocolodedocumentos.classes.Requerente;
import br.com.protocolodedocumentos.conexao.ConexaoEntityManager;
import java.io.IOException;
import javax.swing.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import javax.persistence.EntityManager;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.SchemaOutputResolver;

public class Main {

    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException, SQLException, JAXBException, IOException {

        UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        EntityManager em = ConexaoEntityManager.getInstance();
        
//        JAXBContext jc = JAXBContext.newInstance(Requerente.class);
//        
//        SchemaOutputResolver sor = new MySchemaOutputResolver ();
//        
//        jc.generateSchema(sor);
        
        
        
        
        
        //Objeto que vai receber os valores
        Protocolo protocolo = new Protocolo(new Date(), "Sim", "Documentos originais");
                
        //Lista que vai receber os documentos
        ArrayList<Documentos> listaDocumentos = new ArrayList();
        //adicionando documentos na lista
        listaDocumentos.add(new Documentos(protocolo, "Empresarial", "Contrato Social", "Empresa Fulano de Tal LTDA"));
        listaDocumentos.add(new Documentos(protocolo, "Pessoal", "Documento Identidade", "Socio fulano de Tal"));
        listaDocumentos.add(new Documentos(protocolo, "Imposto", "SIMPLES NACIONAL", "Competencia 04/2016"));
        //seta os documentos no protocolo
        protocolo.setDocumentos(listaDocumentos);
        //Instancia o objeto requerente
        Requerente requerente = new Requerente(4568, "Sandro Machado", "Tec.Contabil", protocolo);
        //seta o requerente no protocolo
        protocolo.setRequerente(requerente);
        //instancia a empresa
        Empresa empresa = new Empresa(12548, "EMPRESA DE FAIXADA LTDA", "Industria", protocolo);
        //seta a empresa no protocolo
        protocolo.setEmpresa(empresa);

        //persistindo os dados
        em.getTransaction().begin();

        em.persist(empresa);
        em.persist(requerente);
        em.persist(protocolo);
        for (Documentos listaDocumento : listaDocumentos) {
            em.persist(listaDocumento);
        }
        em.getTransaction().commit();

        System.exit(0);
    }
}
