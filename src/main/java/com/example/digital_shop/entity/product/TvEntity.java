package com.example.digital_shop.entity.product;

import com.example.digital_shop.entity.BaseEntity;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity(name = "tv")
//@DiscriminatorColumn(name = "tv")
//@DiscriminatorValue("tv")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TvEntity extends ProductEntity {
    private Double isSmart;
    private Integer Size;
    private Integer ScreenSpeed;
//    private String model;
//    private String name;
//    private Double cost;
//    private UUID userId;
}



