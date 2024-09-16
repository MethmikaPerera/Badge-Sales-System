/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author mamet
 */
public class BadgeItem {
    private int badgeId;
    private String badgeNo;
    private String badgeName;
    private int sectionId;
    private String sectionName;

    /**
     * @return the badgeId
     */
    public int getBadgeId() {
        return badgeId;
    }

    /**
     * @param badgeId the badgeId to set
     */
    public void setBadgeId(int badgeId) {
        this.badgeId = badgeId;
    }

    /**
     * @return the badgeNo
     */
    public String getBadgeNo() {
        return badgeNo;
    }

    /**
     * @param badgeNo the badgeNo to set
     */
    public void setBadgeNo(String badgeNo) {
        this.badgeNo = badgeNo;
    }

    /**
     * @return the badgeName
     */
    public String getBadgeName() {
        return badgeName;
    }

    /**
     * @param badgeName the badgeName to set
     */
    public void setBadgeName(String badgeName) {
        this.badgeName = badgeName;
    }

    /**
     * @return the sectionId
     */
    public int getSectionId() {
        return sectionId;
    }

    /**
     * @param sectionId the sectionId to set
     */
    public void setSectionId(int sectionId) {
        this.sectionId = sectionId;
    }

    /**
     * @return the sectionName
     */
    public String getSectionName() {
        return sectionName;
    }

    /**
     * @param sectionName the sectionName to set
     */
    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }
}
