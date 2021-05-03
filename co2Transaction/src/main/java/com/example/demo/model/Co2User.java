package com.example.demo.model;

public class Co2User {
    private String userAddress;

    private Integer id;

    private String userName;

    private Float userCo2Points;

    private Float userTreePoints;

    private String userAddressPlatformKey;

    private String userAddressPrivateKey;

    private String userAccount;

    private String userShareLink;

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress == null ? null : userAddress.trim();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public Float getUserCo2Points() {
        return userCo2Points;
    }

    public void setUserCo2Points(Float userCo2Points) {
        this.userCo2Points = userCo2Points;
    }

    public Float getUserTreePoints() {
        return userTreePoints;
    }

    public void setUserTreePoints(Float userTreePoints) {
        this.userTreePoints = userTreePoints;
    }

    public String getUserAddressPlatformKey() {
        return userAddressPlatformKey;
    }

    public void setUserAddressPlatformKey(String userAddressPlatformKey) {
        this.userAddressPlatformKey = userAddressPlatformKey == null ? null : userAddressPlatformKey.trim();
    }

    public String getUserAddressPrivateKey() {
        return userAddressPrivateKey;
    }

    public void setUserAddressPrivateKey(String userAddressPrivateKey) {
        this.userAddressPrivateKey = userAddressPrivateKey == null ? null : userAddressPrivateKey.trim();
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount == null ? null : userAccount.trim();
    }

	public String getUserShareLink() {
		return userShareLink;
	}

	public void setUserShareLink(String userShareLink) {
		this.userShareLink = userShareLink;
	}
    
    
}