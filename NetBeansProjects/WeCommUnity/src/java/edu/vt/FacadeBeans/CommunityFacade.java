/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.vt.FacadeBeans;

import edu.vt.EntityBeans.Community;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author emanhussein
 */
@Stateless
public class CommunityFacade extends AbstractFacade<Community> {

    @PersistenceContext(unitName = "WeCommUnityPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CommunityFacade() {
        super(Community.class);
    }
    
    /*
    ====================================================
    The following method is added to the generated code.
    ====================================================
     */
    /**
     * Find all Communities to which a User belong, the user id is its database primary key 
     * dbPrimaryKey
     *
     * @param dbPrimaryKey is the Primary Key of the user entity in the database
     * @return a list of object references of UserCommunities 
     */
    public List<Community> findUserCommunitiesByUserPrimaryKey(Integer dbPrimaryKey) {
        /*
        The following @NamedQuery is defined in UserSurvey.java entity class file:
        @NamedQuery(name = "UserSurvey.findSurveysByUserPrimaryKey", 
            query = "SELECT u FROM UserSurvey u WHERE u.userId.id = :primaryKey")
        
        The following statement obtaines the results from the named database query.
         */
        List<Community> userCommunities = em.createNamedQuery("Community.findCommunitiesByUserPrimaryKey")
                .setParameter("primaryKey", dbPrimaryKey)
                .getResultList();

        return userCommunities;
    }

   
    
}
