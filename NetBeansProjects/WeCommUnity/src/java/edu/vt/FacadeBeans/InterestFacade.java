/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.vt.FacadeBeans;

import edu.vt.EntityBeans.Interest;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author emanhussein
 */
@Stateless
public class InterestFacade extends AbstractFacade<Interest> {

    @PersistenceContext(unitName = "WeCommUnityPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public InterestFacade() {
        super(Interest.class);
    }
    
}
