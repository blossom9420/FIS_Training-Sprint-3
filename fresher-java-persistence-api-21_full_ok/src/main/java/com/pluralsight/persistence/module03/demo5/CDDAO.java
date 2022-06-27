package com.pluralsight.persistence.module03.demo5;

import com.pluralsight.persistence.module03.model.CD;

import javax.persistence.EntityManager;

public class CDDAO {
    private EntityManager em;

    public CDDAO (EntityManager em){
        this.em = em;
    }

    public void persistCD(CD cd) {
        em.getTransaction().begin();
        em.persist(cd);
        em.getTransaction().commit();
    }

    public CD findCD(Long id) {
        return em.find(CD.class, id);
    }
}
