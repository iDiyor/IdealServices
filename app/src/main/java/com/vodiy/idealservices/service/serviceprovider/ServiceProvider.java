package com.vodiy.idealservices.service.serviceprovider;

/**
 * Created by idiyor on 03/01/2016.
 */
public class ServiceProvider {
    private String mProfilePicture;
    private String mName;
    private String mSkills;
    private String mPhone;

    public ServiceProvider() {

    }

    public ServiceProvider(String name, String skills, String phone, String profilePicture) {
        mName = name;
        mSkills = skills;
        mPhone = phone;
        mProfilePicture = profilePicture;
    }


    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmSkills() {
        return mSkills;
    }

    public void setmSkills(String mSkills) {
        this.mSkills = mSkills;
    }

    public String getmPhone() {
        return mPhone;
    }

    public void setmPhone(String mPhone) {
        this.mPhone = mPhone;
    }

    public String getmProfilePicture() {
        return mProfilePicture;
    }

    public void setmProfilePicture(String mProfilePicture) {
        this.mProfilePicture = mProfilePicture;
    }
}
