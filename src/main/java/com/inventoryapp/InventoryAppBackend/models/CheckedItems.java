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
@Table(name = "tb_checked_items")
public class CheckedItems {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ciId")
    private Long ciId;
    
    @ManyToOne
    @JoinColumn(name = "ci_daily_registry")
    private DailyRegistry ciDailyRegistry;
    
    @ManyToOne
    @JoinColumn(name = "ci_item")
    private DefaultItem ciItem;
    
    @Column(name = "ci_quantity")
    private BigDecimal ciQuantity;
    
    @Column(name = "ci_exists")
    private Boolean ciExists;
    
    @Column(name = "ci_obs")
    private String ciObs;
    
    @Column(name = "ci_image")
    private String ciImage;

    public CheckedItems() {
        this.ciDailyRegistry = new DailyRegistry();
        this.ciItem = new DefaultItem();
        this.ciExists = Boolean.FALSE;
        this.ciObs = "";
        this.ciQuantity = BigDecimal.ZERO;
        this.ciImage = "";
    }
    
    public Long getCiId() {
        return ciId;
    }

    public void setCiId(Long ciId) {
        this.ciId = ciId;
    }

    public DailyRegistry getCiDailyRegistry() {
        return ciDailyRegistry;
    }

    public void setCiDailyRegistry(DailyRegistry ciDailyRegistry) {
        this.ciDailyRegistry = ciDailyRegistry;
    }

    public DefaultItem getCiItem() {
        return ciItem;
    }

    public void setCiItem(DefaultItem ciItem) {
        this.ciItem = ciItem;
    }

    public BigDecimal getCiQuantity() {
        return ciQuantity;
    }

    public void setCiQuantity(BigDecimal ciQuantity) {
        this.ciQuantity = ciQuantity;
    }

    public Boolean getCiExists() {
        return ciExists;
    }

    public void setCiExists(Boolean ciExists) {
        this.ciExists = ciExists;
    }

    public String getCiObs() {
        return ciObs;
    }

    public void setCiObs(String ciObs) {
        this.ciObs = ciObs;
    }

    public String getCiImage() {
        return ciImage;
    }

    public void setCiImage(String ciImage) {
        this.ciImage = ciImage;
    }
}
