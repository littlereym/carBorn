package com.example.demo.model;
/*
 * 後台帳號列表
 *權限等級目前分三種
 *超級管理員 可以修改管理員密碼-"admin"
 *編輯_可以新增刪除停權協作人員帳號-"editor"
 *協作人員-只可以瀏覽-"contributor"
 * */
public class BackstageAccount {
	//後臺帳號流水號
	private Integer id;
	//後台帳號名稱
    private String accountName;
    //後台帳號密碼
    private String accountPassword;
    //後台權限等級
    private String accountPermissionLevel;
    //後台帳號狀態_1=正常，2=停權
    private Integer accountStatus;
    //後台帳號最後登入時間
    private String accountLastLoginTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName == null ? null : accountName.trim();
    }

    public String getAccountPassword() {
        return accountPassword;
    }

    public void setAccountPassword(String accountPassword) {
        this.accountPassword = accountPassword == null ? null : accountPassword.trim();
    }

    public String getAccountPermissionLevel() {
        return accountPermissionLevel;
    }

    public void setAccountPermissionLevel(String accountPermissionLevel) {
        this.accountPermissionLevel = accountPermissionLevel == null ? null : accountPermissionLevel.trim();
    }

    public Integer getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(Integer accountStatus) {
        this.accountStatus = accountStatus;
    }

    public String getAccountLastLoginTime() {
        return accountLastLoginTime;
    }

    public void setAccountLastLoginTime(String accountLastLoginTime) {
        this.accountLastLoginTime = accountLastLoginTime == null ? null : accountLastLoginTime.trim();
    }
}