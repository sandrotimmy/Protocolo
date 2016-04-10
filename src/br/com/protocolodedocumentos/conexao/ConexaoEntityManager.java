
package br.com.protocolodedocumentos.conexao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ConexaoEntityManager {

    private static EntityManager em;

    private ConexaoEntityManager() {

    }

    public static EntityManager getInstance() {
        if (em == null) {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProtocoloDB");
            em = emf.createEntityManager();
        }
        return em;
    }
}
