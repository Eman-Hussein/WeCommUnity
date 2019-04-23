/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.vt.EntityBeans;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author emanhussein
 */
@Entity
@Table(name = "CommunityContent")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CommunityContent.findAll", query = "SELECT c FROM CommunityContent c")
    , @NamedQuery(name = "CommunityContent.findById", query = "SELECT c FROM CommunityContent c WHERE c.id = :id")
    , @NamedQuery(name = "CommunityContent.findByContentname", query = "SELECT c FROM CommunityContent c WHERE c.contentname = :contentname")
    , @NamedQuery(name = "CommunityContent.findByDateEntered", query = "SELECT c FROM CommunityContent c WHERE c.dateEntered = :dateEntered")
    , @NamedQuery(name = "CommunityContent.findByCategory", query = "SELECT c FROM CommunityContent c WHERE c.category = :category")})
public class CommunityContent implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 256)
    @Column(name = "contentname")
    private String contentname;
    @Basic(optional = false)
    @NotNull
    @Column(name = "date_entered")
    @Temporal(TemporalType.DATE)
    private Date dateEntered;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(name = "category")
    private String category;
    @JoinColumn(name = "community_id", referencedColumnName = "id")
    @ManyToOne
    private Community communityId;

    public CommunityContent() {
    }

    public CommunityContent(Integer id) {
        this.id = id;
    }

    public CommunityContent(Integer id, String contentname, Date dateEntered, String category) {
        this.id = id;
        this.contentname = contentname;
        this.dateEntered = dateEntered;
        this.category = category;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContentname() {
        return contentname;
    }

    public void setContentname(String contentname) {
        this.contentname = contentname;
    }

    public Date getDateEntered() {
        return dateEntered;
    }

    public void setDateEntered(Date dateEntered) {
        this.dateEntered = dateEntered;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Community getCommunityId() {
        return communityId;
    }

    public void setCommunityId(Community communityId) {
        this.communityId = communityId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CommunityContent)) {
            return false;
        }
        CommunityContent other = (CommunityContent) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.vt.EntityBeans.CommunityContent[ id=" + id + " ]";
    }
    
}
