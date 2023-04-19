package edu.iu.c322.assetmanagement.assetsservice.repository;

import edu.iu.c322.assetmanagement.assetsservice.model.Asset;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssetRepository extends JpaRepository<Asset, Integer> {
}
