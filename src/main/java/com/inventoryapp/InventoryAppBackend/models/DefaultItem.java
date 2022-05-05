package com.inventoryapp.InventoryAppBackend.models;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author ale
 */
@Entity
@Table(name = "tb_default_items")
public class DefaultItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "di_id")
    private Long diId;
    
    @Column(name = "di_name")
    private String diName;
    
    @Column(name = "diImage")
    private String diImage;
    
    @ManyToOne
    @JoinColumn(name = "di_box", referencedColumnName = "b_id")
    private Box diBox;
    
    @Column(name = "di_default_qty")
    private BigDecimal diDefaultQty;

    public DefaultItem() {
        this.diId = null;
        this.diName = "";
        this.diDefaultQty = BigDecimal.ZERO;
        this.diImage = "";
        this.diBox = new Box();
    }

    public Long getDiId() {
        return diId;
    }

    public void setDiId(Long diId) {
        this.diId = diId;
    }

    public String getDiName() {
        return diName;
    }

    public void setDiName(String diName) {
        this.diName = diName;
    }

    public String getDiImage() {
        return diImage;
    }

    public void setDiImage(String diImage) {
        this.diImage = diImage;
    }

    public Box getDiBox() {
        return diBox;
    }

    public void setDiBox(Box diBox) {
        this.diBox = diBox;
    }

    public BigDecimal getDiDefaultQty() {
        return diDefaultQty;
    }

    public void setDiDefaultQty(BigDecimal diDefaultQty) {
        this.diDefaultQty = diDefaultQty;
    }
}
