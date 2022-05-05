package com.inventoryapp.InventoryAppBackend.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author ale
 */
@Entity
@Table(name = "tb_box")
public class Box {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "b_id")
    private Long bId;
    
    @ManyToOne
    @JoinColumn(name = "b_unit", referencedColumnName = "u_id")
    private UnitDependecy bUnit;
    
    @Column(name = "b_name")
    private String bName;
    
    @Column(name = "b_image")
    private String bImage;
    
    @Transient
    @JsonIgnore
    private List<DefaultItem> defaultItems;

    
    public Box() {
        this.bUnit = new UnitDependecy();
        this.bName = "";
        this.bImage = "";
        this.defaultItems = new ArrayList<>();
    }
    
    public Long getbId() {
        return bId;
    }

    public void setbId(Long bId) {
        this.bId = bId;
    }

    public UnitDependecy getbUnit() {
        return bUnit;
    }

    public void setbUnit(UnitDependecy bUnit) {
        this.bUnit = bUnit;
    }

    public String getbName() {
        return bName;
    }

    public void setbName(String bName) {
        this.bName = bName;
    }

    public String getbImage() {
        return bImage;
    }

    public void setbImage(String bImage) {
        this.bImage = bImage;
    }
    
    public void addDefaultItem(DefaultItem defaultItem){
        this.defaultItems.add(defaultItem);
    }
    
    public List<DefaultItem> getDefaultItems() {
        return defaultItems;
    }

    public void setDefaultItems(List<DefaultItem> defaultItems) {
        this.defaultItems = defaultItems;
    }
    
}
