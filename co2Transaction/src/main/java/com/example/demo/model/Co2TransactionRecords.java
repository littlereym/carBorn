package com.example.demo.model;

import java.util.Date;

public class Co2TransactionRecords {
    private Integer id;

    
    private String tokenName;//代幣類型樹幣或是碳權幣
    
    private String tokenType;//買，每日獎勵，分享獎勵，捐贈

    private String tokenCountry;//代幣購買國家

    private String tokenBuyAddress;//買入代幣的地址

    private String tokenSaleAddress;//賣出代幣的地址

    private Float tokenRate;//代幣的價格

    private Date tokenTradingTime;//交易時間

    private String tokenCertificate;//是否發過證書

    private Float tokenAmout;//購買總額

    private String tokenTxhash;//交易紀錄地址

    private String bookNumber;//證書編號
    
    private Float tokenQuantity;//交易幣數

    private Float rank;//排行排行

    private String userName;//證書編號
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTokenName() {
        return tokenName;
    }

    public void setTokenName(String tokenName) {
        this.tokenName = tokenName == null ? null : tokenName.trim();
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType == null ? null : tokenType.trim();
    }

    public String getTokenCountry() {
        return tokenCountry;
    }

    public void setTokenCountry(String tokenCountry) {
        this.tokenCountry = tokenCountry == null ? null : tokenCountry.trim();
    }

    public String getTokenBuyAddress() {
        return tokenBuyAddress;
    }

    public void setTokenBuyAddress(String tokenBuyAddress) {
        this.tokenBuyAddress = tokenBuyAddress == null ? null : tokenBuyAddress.trim();
    }

    public String getTokenSaleAddress() {
        return tokenSaleAddress;
    }

    public void setTokenSaleAddress(String tokenSaleAddress) {
        this.tokenSaleAddress = tokenSaleAddress == null ? null : tokenSaleAddress.trim();
    }

    public Float getTokenRate() {
        return tokenRate;
    }

    public void setTokenRate(Float tokenRate) {
        this.tokenRate = tokenRate;
    }

    public Date getTokenTradingTime() {
        return tokenTradingTime;
    }

    public void setTokenTradingTime(Date tokenTradingTime) {
        this.tokenTradingTime = tokenTradingTime;
    }

    public String getTokenCertificate() {
        return tokenCertificate;
    }

    public void setTokenCertificate(String tokenCertificate) {
        this.tokenCertificate = tokenCertificate == null ? null : tokenCertificate.trim();
    }

    public Float getTokenAmout() {
        return tokenAmout;
    }

    public void setTokenAmout(Float tokenAmout) {
        this.tokenAmout = tokenAmout;
    }

    public String getTokenTxhash() {
        return tokenTxhash;
    }

    public void setTokenTxhash(String tokenTxhash) {
        this.tokenTxhash = tokenTxhash == null ? null : tokenTxhash.trim();
    }

	public String getBookNumber() {
		return bookNumber;
	}

	public void setBookNumber(String bookNumber) {
		this.bookNumber = bookNumber;
	}

	public Float getTokenQuantity() {
		return tokenQuantity;
	}

	public void setTokenQuantity(Float tokenQuantity) {
		this.tokenQuantity = tokenQuantity;
	}

    public Float getRank() {
        return rank;
    }

    public void setRank(Float rank) {
        this.rank = rank;
    }

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
    
    
}