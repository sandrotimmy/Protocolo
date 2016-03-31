
package br.com.protocolodedocumentos.principal;

import br.com.protocolodedocumentos.conexao.ConexaoEntityManager;
import javax.swing.*;
import java.sql.SQLException;
import javax.persistence.EntityManager;

public class Main {

    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException, SQLException {

        UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        EntityManager em = ConexaoEntityManager.getInstance();
//        em.getTransaction();
//        em.persist(new Requerente (1,"teste", "teste"));
//        em.getTransaction().commit();
//        HistoricoPadrao historico = new HistoricoPadrao(105,"teste","teste");
//        em.getTransaction().begin();
//        em.persist(historico);
//        em.getTransaction().commit();
//        JOptionPane.showMessageDialog(null, "Historico Cadastrado com Sucesso!");
        System.exit(0);
    }
}
