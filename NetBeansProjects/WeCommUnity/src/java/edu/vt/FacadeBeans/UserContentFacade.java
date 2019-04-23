/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.vt.FacadeBeans;

import edu.vt.EntityBeans.UserContent;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author emanhussein
 */
@Stateless
public class UserContentFacade extends AbstractFacade<UserContent> {

    @PersistenceContext(unitName = "WeCommUnityPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UserContentFacade() {
        super(UserContent.class);
    }
    
}
