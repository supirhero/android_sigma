package com.sigma.prouds.model;

/**
 * Created by 1414 on 7/8/2017.
 */

public class UserModel
{
    private String userId;
    private String userName;
    private String buId;
    private String userTypeId;
    private String SupId;
    private String ProfId;
    private String lastLogin;
    private String loggedIn;

    public String getUserId()
    {
        return userId;
    }

    public void setUserId(String userId)
    {
        this.userId = userId;
    }

    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public String getBuId()
    {
        return buId;
    }

    public void setBuId(String buId)
    {
        this.buId = buId;
    }

    public String getUserTypeId()
    {
        return userTypeId;
    }

    public void setUserTypeId(String userTypeId)
    {
        this.userTypeId = userTypeId;
    }

    public String getSupId()
    {
        return SupId;
    }

    public void setSupId(String supId)
    {
        SupId = supId;
    }

    public String getProfId()
    {
        return ProfId;
    }

    public void setProfId(String profId)
    {
        ProfId = profId;
    }

    public String getLastLogin()
    {
        return lastLogin;
    }

    public void setLastLogin(String lastLogin)
    {
        this.lastLogin = lastLogin;
    }

    public String getLoggedIn()
    {
        return loggedIn;
    }

    public void setLoggedIn(String loggedIn)
    {
        this.loggedIn = loggedIn;
    }
}
