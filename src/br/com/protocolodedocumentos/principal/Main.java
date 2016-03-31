package br.com.protocolodedocumentos.principal;

import br.com.protocolodedocumentos.classes.Documentos;
import br.com.protocolodedocumentos.classes.Protocolo;
import br.com.protocolodedocumentos.classes.Requerente;
import br.com.protocolodedocumentos.conexao.ConexaoEntityManager;
import javax.swing.*;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.persistence.EntityManager;

public class Main {

    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException, SQLException {

        UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        EntityManager em = ConexaoEntityManager.getInstance();

        ArrayList<Documentos> listaDocumentos = new ArrayList();
        Protocolo protocolo = new Protocolo("EMPRESA STAHL BRASIL LTDA");
        listaDocumentos.add(new Documentos(protocolo, "Empresarial", "Contrato Social", "Empresa Fulano de Tal LTDA"));
        listaDocumentos.add(new Documentos(protocolo, "Pessoal", "Documento Identidade", "Sócio fulano de Tal"));
        listaDocumentos.add(new Documentos(protocolo, "Imposto", "SIMPLES NACIONAL", "Competencia 04/2016"));
        protocolo.setDocumentos(listaDocumentos);
        em.getTransaction().begin();
        em.persist(protocolo);
        for (Documentos listaDocumento : listaDocumentos) {
            em.persist(listaDocumento);
        }
         em.getTransaction().commit();

//        em.getTransaction().begin();
//        em.persist(new Requerente (2323,"teste", "teste"));
//        em.getTransaction().commit();
//        HistoricoPadrao historico = new HistoricoPadrao(105,"teste","teste");
//        em.getTransaction().begin();
//        em.persist(historico);
//        em.getTransaction().commit();
//        JOptionPane.showMessageDialog(null, "Historico Cadastrado com Sucesso!");
        System.exit(0);
    }
}
