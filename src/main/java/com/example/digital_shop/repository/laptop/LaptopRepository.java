package com.example.digital_shop.repository.laptop;

import com.example.digital_shop.entity.product.LaptopEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
@Repository
public interface LaptopRepository extends JpaRepository<LaptopEntity, UUID> {

    List<LaptopEntity> searchLaptopEntitiesByNameContainingIgnoreCase(String name, Pageable pageable);
}
