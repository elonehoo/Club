package com.springboot.beans;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName(value = "message")
public class Message {
    @TableId(value = "memberUUID")
    private String memberUUID;
    @TableField(value = "memberNumber")
    private String memberNumber;
    @TableField(value = "memberName")
    private String memberName;
    @TableField(value = "memberPhone")
    private String memberPhone;
    @TableField(value = "memberClazz")
    private String memberClazz;

    public Message() {
    }

    public Message(String memberUUID, String memberNumber, String memberName, String memberPhone, String memberClazz) {
        this.memberUUID = memberUUID;
        this.memberNumber = memberNumber;
        this.memberName = memberName;
        this.memberPhone = memberPhone;
        this.memberClazz = memberClazz;
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

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getMemberPhone() {
        return memberPhone;
    }

    public void setMemberPhone(String memberPhone) {
        this.memberPhone = memberPhone;
    }

    public String getMemberClazz() {
        return memberClazz;
    }

    public void setMemberClazz(String memberClazz) {
        this.memberClazz = memberClazz;
    }

    @Override
    public String toString() {
        return "Message{" +
                "memberUUID='" + memberUUID + '\'' +
                ", memberNumber='" + memberNumber + '\'' +
                ", memberName='" + memberName + '\'' +
                ", memberPhone='" + memberPhone + '\'' +
                ", memberClazz='" + memberClazz + '\'' +
                '}';
    }
}
