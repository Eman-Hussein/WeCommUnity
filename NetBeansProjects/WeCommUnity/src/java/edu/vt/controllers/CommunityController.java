package edu.vt.controllers;

import edu.vt.EntityBeans.Community;
import edu.vt.controllers.util.JsfUtil;
import edu.vt.controllers.util.JsfUtil.PersistAction;
import edu.vt.FacadeBeans.CommunityFacade;
import edu.vt.globals.Methods;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@Named("communityController")
@SessionScoped
public class CommunityController implements Serializable {

    @EJB
    private edu.vt.FacadeBeans.CommunityFacade communityFacade;
    private List<Community> userCommunitiesList = null;
    private List<Community> allCommunitiesList = null;
    private Community selected;

    /**
     * ************************************************************************************
     */
    public void setCommunityFacade(CommunityFacade cFacade) {
        this.communityFacade = cFacade;
    }

    private CommunityFacade getCommunityFacade() {
        return communityFacade;
    }

    /**
     * ************************************************************************************
     */
    public void setUserCommunitiesList(List<Community> userCommunitiesList) {
        this.userCommunitiesList = userCommunitiesList;
    }

    //need to get the user id to retrieve the communities he/she joined
    public List<Community> getuserCommunitiesList() {
        if (userCommunitiesList == null) {
             /*
            user_id (database primary key) was put into the SessionMap in the
            initializeSessionMap() method in LoginManager upon user's sign in.
             */
            int userPrimaryKey = (int) Methods.sessionMap().get("user_id");

            userCommunitiesList = getCommunityFacade().findUserCommunitiesByUserPrimaryKey(userPrimaryKey);
        }
        return userCommunitiesList;
    }

    /**
     * ************************************************************************************
     */
    public void setAllCommunitiesList(List<Community> allCommunitiesList) {
        this.allCommunitiesList = allCommunitiesList;
    }

    public List<Community> getAllCommunitiesList() {
        if (allCommunitiesList == null) {
            allCommunitiesList = getCommunityFacade().findAll();
        }
        return allCommunitiesList;
    }

    /**
     * ************************************************************************************
     */
    public Community getSelected() {
        return selected;
    }
    public void setSelected(Community selected) {
        this.selected = selected;
    }

    /**
     * ************************************************************************************
     */
    public CommunityController() {
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    public Community prepareCreate() {
        selected = new Community();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("CommunityCreated"));
        if (!JsfUtil.isValidationFailed()) {
            userCommunitiesList = null;    // Invalidate list of userCommunitiesList to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("CommunityUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("CommunityDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            userCommunitiesList = null;    // Invalidate list of userCommunitiesList to trigger re-query.
        }
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
                    getCommunityFacade().edit(selected);
                } else {
                    getCommunityFacade().remove(selected);
                }
                JsfUtil.addSuccessMessage(successMessage);
            } catch (EJBException ex) {
                String msg = "";
                Throwable cause = ex.getCause();
                if (cause != null) {
                    msg = cause.getLocalizedMessage();
                }
                if (msg.length() > 0) {
                    JsfUtil.addErrorMessage(msg);
                } else {
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            }
        }
    }

    public Community getCommunity(java.lang.Integer id) {
        return getCommunityFacade().find(id);
    }

    public List<Community> getuserCommunitiesListAvailableSelectMany() {
        return getCommunityFacade().findAll();
    }

    public List<Community> getuserCommunitiesListAvailableSelectOne() {
        return getCommunityFacade().findAll();
    }

    @FacesConverter(forClass = Community.class)
    public static class CommunityControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            CommunityController controller = (CommunityController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "communityController");
            return controller.getCommunity(getKey(value));
        }

        java.lang.Integer getKey(String value) {
            java.lang.Integer key;
            key = Integer.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Integer value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Community) {
                Community o = (Community) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Community.class.getName()});
                return null;
            }
        }

    }

}
