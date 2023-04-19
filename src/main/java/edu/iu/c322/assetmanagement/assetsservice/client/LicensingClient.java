package edu.iu.c322.assetmanagement.assetsservice.client;

import edu.iu.c322.assetmanagement.assetsservice.model.License;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Component
public class LicensingClient {
    private RestTemplate restTemplate;

    public LicensingClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Optional<License> getLicense(int licenseId){
        ResponseEntity<License> restExchange =
                restTemplate.exchange(
                        "http://licensing-service/licensings/{licenseId}",
                        HttpMethod.GET,
                        null, License.class, licenseId);

        return Optional.ofNullable(restExchange.getBody());
    }
}
