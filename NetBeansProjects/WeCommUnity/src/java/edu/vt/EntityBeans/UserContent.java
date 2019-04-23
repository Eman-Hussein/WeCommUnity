/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.vt.EntityBeans;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author emanhussein
 */
@Entity
@Table(name = "UserContent")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UserContent.findAll", query = "SELECT u FROM UserContent u")
    , @NamedQuery(name = "UserContent.findById", query = "SELECT u FROM UserContent u WHERE u.id = :id")
    , @NamedQuery(name = "UserContent.findByContentname", query = "SELECT u FROM UserContent u WHERE u.contentname = :contentname")
    , @NamedQuery(name = "UserContent.findByDateEntered", query = "SELECT u FROM UserContent u WHERE u.dateEntered = :dateEntered")
    , @NamedQuery(name = "UserContent.findByCategory", query = "SELECT u FROM UserContent u WHERE u.category = :category")
    , @NamedQuery(name = "UserContent.findByType", query = "SELECT u FROM UserContent u WHERE u.type = :type")
    , @NamedQuery(name = "UserContent.findByLocation", query = "SELECT u FROM UserContent u WHERE u.location = :location")})
public class UserContent implements Serializable {

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
    @Size(max = 256)
    @Column(name = "type")
    private String type;
    @Size(max = 256)
    @Column(name = "location")
    private String location;
    @ManyToMany(mappedBy = "userContentCollection")
    private Collection<Member1> member1Collection;
    @JoinColumn(name = "creator_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Member1 creatorId;

    public UserContent() {
    }

    public UserContent(Integer id) {
        this.id = id;
    }

    public UserContent(Integer id, String contentname, Date dateEntered, String category) {
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @XmlTransient
    public Collection<Member1> getMember1Collection() {
        return member1Collection;
    }

    public void setMember1Collection(Collection<Member1> member1Collection) {
        this.member1Collection = member1Collection;
    }

    public Member1 getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Member1 creatorId) {
        this.creatorId = creatorId;
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
        if (!(object instanceof UserContent)) {
            return false;
        }
        UserContent other = (UserContent) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.vt.EntityBeans.UserContent[ id=" + id + " ]";
    }
    
}
