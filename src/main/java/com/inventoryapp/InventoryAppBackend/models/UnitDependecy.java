package com.inventoryapp.InventoryAppBackend.models;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author ale
 */
@Entity
@Table(name = "tb_unit")
public class UnitDependecy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "u_id")
    private Integer uId;
    
    @Column(name = "u_name",unique = true)
    private String uName;
    
    @Column(name = "u_code", unique = true)
    private String uCode;
    
    @Column(name = "u_image")
    private String uImage;
    
    @OneToMany(mappedBy = "bUnit", orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Box> uBoxes;

    public UnitDependecy() {
        this.uName = "";
        this.uCode = "";
        this.uImage = "";
        this.uBoxes = new ArrayList<>();
    }

    public Integer getuId() {
        return uId;
    }

    public void setuId(Integer uId) {
        this.uId = uId;
    }

    public String getuName() {
        return uName;
    }

    public void setuName(String uName) {
        this.uName = uName;
    }

    public String getuCode() {
        return uCode;
    }

    public void setuCode(String uCode) {
        this.uCode = uCode;
    }

    public String getuImage() {
        return uImage;
    }

    public void setuImage(String uImage) {
        this.uImage = uImage;
    }
    
    public List<Box> getuBoxes() {
        return uBoxes;
    }

    public void setuBoxes(List<Box> uBoxes) {
        this.uBoxes = uBoxes;
    }
    
    public void addBox(Box box){
        this.uBoxes.add(box);
    }
    
}
