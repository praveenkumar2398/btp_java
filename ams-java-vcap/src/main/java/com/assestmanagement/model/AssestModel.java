package com.assestmanagement.model;


import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class AssestModel {
        @Id
    	@GeneratedValue(strategy = GenerationType.AUTO)
	    private Long assetId;

	    private String assetName;
	    private String managedBy;
	    private String remark;
	    private String serialNumber;

	    @Enumerated(EnumType.STRING)
	    private Status status;

	    @Enumerated(EnumType.STRING)
	    private OperationalStatus operationalStatus;

	    @Enumerated(EnumType.STRING)
	    private AssetType assetType;

	    @Enumerated(EnumType.STRING)
	    private Category category;

	    private double cost;
	    private String brand;
	    private String modelNumber;
		public Long getAssetId() {
			return assetId;
		}
		public void setAssetId(Long assetId) {
			this.assetId = assetId;
		}
		public String getAssetName() {
			return assetName;
		}
		public void setAssetName(String assetName) {
			this.assetName = assetName;
		}
		public String getManagedBy() {
			return managedBy;
		}
		public void setManagedBy(String managedBy) {
			this.managedBy = managedBy;
		}
		public String getRemark() {
			return remark;
		}
		public void setRemark(String remark) {
			this.remark = remark;
		}
		public String getSerialNumber() {
			return serialNumber;
		}
		public void setSerialNumber(String serialNumber) {
			this.serialNumber = serialNumber;
		}
		public Status getStatus() {
			return status;
		}
		public void setStatus(Status status) {
			this.status = status;
		}
		public OperationalStatus getOperationalStatus() {
			return operationalStatus;
		}
		public void setOperationalStatus(OperationalStatus operationalStatus) {
			this.operationalStatus = operationalStatus;
		}
		public AssetType getAssetType() {
			return assetType;
		}
		public void setAssetType(AssetType assetType) {
			this.assetType = assetType;
		}
		public Category getCategory() {
			return category;
		}
		public void setCategory(Category category) {
			this.category = category;
		}
		public double getCost() {
			return cost;
		}
		public void setCost(double cost) {
			this.cost = cost;
		}
		public String getBrand() {
			return brand;
		}
		public void setBrand(String brand) {
			this.brand = brand;
		}
		public String getModelNumber() {
			return modelNumber;
		}
		public void setModelNumber(String modelNumber) {
			this.modelNumber = modelNumber;
		}
	    
	    
}
