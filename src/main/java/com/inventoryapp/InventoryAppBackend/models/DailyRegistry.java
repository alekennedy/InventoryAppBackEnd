package com.inventoryapp.InventoryAppBackend.models;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import java.time.LocalDateTime;
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
@Table(name = "tb_daily_registry")
public class DailyRegistry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dr_id")
    private Long drId;
    
    @Column(name = "dr_datetime")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime drDateTime;
    
    @Column(name = "dr_userid")
    private String drUserId;
    
    @ManyToOne
    @JoinColumn(name = "dr_unit")
    private UnitDependecy drUnit;

    public DailyRegistry() {
        this.drDateTime = LocalDateTime.now();
        this.drUserId = "";
        this.drUnit = new UnitDependecy();
    }

    public Long getDrId() {
        return drId;
    }

    public void setDrId(Long drId) {
        this.drId = drId;
    }

    public LocalDateTime getDrDateTime() {
        return drDateTime;
    }

    public void setDrDateTime(LocalDateTime drDateTime) {
        this.drDateTime = drDateTime;
    }

    public String getDrUserId() {
        return drUserId;
    }

    public void setDrUserId(String drUserId) {
        this.drUserId = drUserId;
    }

    public UnitDependecy getDrUnit() {
        return drUnit;
    }

    public void setDrUnit(UnitDependecy drUnit) {
        this.drUnit = drUnit;
    }
    
}
