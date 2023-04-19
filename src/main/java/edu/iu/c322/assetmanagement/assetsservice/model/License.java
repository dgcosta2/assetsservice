package edu.iu.c322.assetmanagement.assetsservice.model;

public record License(int id,
                           String description,
                           int organizationID,
                           String licenseType,
                           String comment,
                          String organizationName,
                          String contactName,
                          String contactEmail) {
}
