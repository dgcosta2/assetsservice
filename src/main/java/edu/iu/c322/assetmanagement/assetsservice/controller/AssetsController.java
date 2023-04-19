package edu.iu.c322.assetmanagement.assetsservice.controller;

import edu.iu.c322.assetmanagement.assetsservice.client.LicensingClient;
import edu.iu.c322.assetmanagement.assetsservice.model.Asset;
import edu.iu.c322.assetmanagement.assetsservice.model.License;
import edu.iu.c322.assetmanagement.assetsservice.repository.AssetsRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/assets")
public class AssetsController {

    private AssetsRepository repository;

    private LicensingClient licensingClient;

    public LicensingController(AssetsRepository repository, LicensingClient licensingClient) {
        this.repository = repository;
        this.licensingClient = licensingClient;
    }
    @GetMapping
    public List<Asset> getAssets(){
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Asset getAsset(@PathVariable int id){
        Optional<Asset> maybeAsset = repository.findById(id);
        if(maybeAsset.isPresent()){
            Asset asset = maybeAsset.get();
            Optional<License> maybeLicense = licensingClient
                    .getLicense(asset.getLicenseId());
            if(maybeLicense.isPresent()){
                License license = maybeLicense.get();
                asset.setLicenseType(license.licenseType());
                asset.setLicenseDescription(license.description());
                return asset;
            }
        } else {
            throw new IllegalStateException("asset id is invalid.");
        }
        return null;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public int create(@RequestBody Asset asset){
        Asset addedAsset = repository.save(asset);
        return addedAsset.getId();
    }
}