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
@Table(name = "PublicContent")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PublicContent.findAll", query = "SELECT p FROM PublicContent p")
    , @NamedQuery(name = "PublicContent.findById", query = "SELECT p FROM PublicContent p WHERE p.id = :id")
    , @NamedQuery(name = "PublicContent.findByPublicContentame", query = "SELECT p FROM PublicContent p WHERE p.publicContentame = :publicContentame")
    , @NamedQuery(name = "PublicContent.findByDateEntered", query = "SELECT p FROM PublicContent p WHERE p.dateEntered = :dateEntered")
    , @NamedQuery(name = "PublicContent.findByCategory", query = "SELECT p FROM PublicContent p WHERE p.category = :category")})
public class PublicContent implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 256)
    @Column(name = "publicContentame")
    private String publicContentame;
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

    public PublicContent() {
    }

    public PublicContent(Integer id) {
        this.id = id;
    }

    public PublicContent(Integer id, String publicContentame, Date dateEntered, String category) {
        this.id = id;
        this.publicContentame = publicContentame;
        this.dateEntered = dateEntered;
        this.category = category;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPublicContentame() {
        return publicContentame;
    }

    public void setPublicContentame(String publicContentame) {
        this.publicContentame = publicContentame;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PublicContent)) {
            return false;
        }
        PublicContent other = (PublicContent) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.vt.EntityBeans.PublicContent[ id=" + id + " ]";
    }
    
}
