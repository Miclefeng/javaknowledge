package com.javase.base.mybatis.entity;

import java.io.Serializable;

/**
 * @author miclefengzss
 * 2021/4/22 下午9:49
 */
public class PassAuth implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    /**
     * id
     */
    private String passAuthId;

    /**
     * 项目id
     */
    private String projectId;

    /**
     * 合集id: 访客登记或者邀约记录id，人员员工的同user_id一样
     */
    private String collectionId;

    /**
     * 账号id
     */
    private String accountId;

    /**
     * 用户Id
     */
    private String userId;

    /**
     * 用户类型: 1 员工,2 办公邀约访客,3 办公登记访客,20 住户,21 社区邀约访客,22 社区登记访客
     */
    private Integer userType;

    /**
     * 点位ID
     */
    private String locationId;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 卡号
     */
    private String cardNumber;

    /**
     * 授权生效时间
     */
    private Integer startTime;

    /**
     * 授权失效时间
     */
    private Long endTime;

    /**
     * 授权类型： 1 人脸，2 卡，3 二维码；（多个 , 拼接）
     */
    private String authType;

    private Integer updateTime;

    /**
     * 操作人Id
     */
    private String createUser;

    private Integer createTime;

    private Integer isDelete;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPass_auth_id() {
        return passAuthId;
    }

    public void setPass_auth_id(String pass_auth_id) {
        this.passAuthId = pass_auth_id;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId == null ? null : projectId.trim();
    }

    public String getCollectionId() {
        return collectionId;
    }

    public void setCollectionId(String collectionId) {
        this.collectionId = collectionId == null ? null : collectionId.trim();
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId == null ? null : accountId.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public String getLocationId() {
        return locationId;
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId == null ? null : locationId.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar == null ? null : avatar.trim();
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber == null ? null : cardNumber.trim();
    }

    public Integer getStartTime() {
        return startTime;
    }

    public void setStartTime(Integer startTime) {
        this.startTime = startTime;
    }

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

    public String getAuthType() {
        return authType;
    }

    public void setAuthType(String authType) {
        this.authType = authType == null ? null : authType.trim();
    }

    public Integer getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Integer updateTime) {
        this.updateTime = updateTime;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser == null ? null : createUser.trim();
    }

    public Integer getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Integer createTime) {
        this.createTime = createTime;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    @Override
    public String toString() {
        return "PassAuth{" +
                "id=" + id +
                ", passAuthId=" + passAuthId +
                ", projectId=" + projectId +
                ", collectionId=" + collectionId +
                ", accountId=" + accountId +
                ", userId=" + userId +
                ", userType=" + userType +
                ", locationId=" + locationId +
                ", phone=" + phone +
                ", avatar=" + avatar +
                ", cardNumber=" + cardNumber +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", authType=" + authType +
                ", updateTime=" + updateTime +
                ", createUser=" + createUser +
                ", createTime=" + createTime +
                ", isDelete=" + isDelete +
                "}";
    }
}

