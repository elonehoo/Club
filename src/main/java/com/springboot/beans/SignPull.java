package com.springboot.beans;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName(value = "signPull")
public class SignPull {
    @TableId(value = "memberUUID")
    private String memberUUID;
    @TableField(value = "memberNumber")
    private String memberNumber;
    /**
     * false - 未签到
     * maybe - 签到但是没有同意
     * true - 正在签到
     */
    @TableField(value = "checkIS")
    private String checkIS;
    @TableField(value = "checkStart")
    private String checkStart;
    @TableField(value = "checkTerminate")
    private String checkTerminate;
    @TableField(value = "checkTime")
    private String checkTime;

    public SignPull() {
    }

    public SignPull(String memberUUID, String memberNumber, String checkIS, String checkStart, String checkTerminate, String checkTime) {
        this.memberUUID = memberUUID;
        this.memberNumber = memberNumber;
        this.checkIS = checkIS;
        this.checkStart = checkStart;
        this.checkTerminate = checkTerminate;
        this.checkTime = checkTime;
    }

    public String getMemberUUID() {
        return memberUUID;
    }

    public void setMemberUUID(String memberUUID) {
        this.memberUUID = memberUUID;
    }

    public String getMemberNumber() {
        return memberNumber;
    }

    public void setMemberNumber(String memberNumber) {
        this.memberNumber = memberNumber;
    }

    public String getCheckIS() {
        return checkIS;
    }

    public void setCheckIS(String checkIS) {
        this.checkIS = checkIS;
    }

    public String getCheckStart() {
        return checkStart;
    }

    public void setCheckStart(String checkStart) {
        this.checkStart = checkStart;
    }

    public String getCheckTerminate() {
        return checkTerminate;
    }

    public void setCheckTerminate(String checkTerminate) {
        this.checkTerminate = checkTerminate;
    }

    public String getCheckTime() {
        return checkTime;
    }

    public void setCheckTime(String checkTime) {
        this.checkTime = checkTime;
    }

    @Override
    public String toString() {
        return "Check{" +
                "memberUUID='" + memberUUID + '\'' +
                ", memberNumber='" + memberNumber + '\'' +
                ", checkIS='" + checkIS + '\'' +
                ", checkStart='" + checkStart + '\'' +
                ", checkTerminate='" + checkTerminate + '\'' +
                ", checkTime='" + checkTime + '\'' +
                '}';
    }
}
