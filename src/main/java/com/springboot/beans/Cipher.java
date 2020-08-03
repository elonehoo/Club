package com.springboot.beans;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName(value = "cipher")
public class Cipher {
    @TableId(value = "memberUUID")
    private String memberUUID;
    @TableField(value = "memberNumber")
    private String memberNumber;
    @TableField(value = "memberCipher")
    private String memberCipher;

    public Cipher() {
    }

    public Cipher(String memberUUID, String memberNumber, String memberCipher) {
        this.memberUUID = memberUUID;
        this.memberNumber = memberNumber;
        this.memberCipher = memberCipher;
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

    public String getMemberCipher() {
        return memberCipher;
    }

    public void setMemberCipher(String memberCipher) {
        this.memberCipher = memberCipher;
    }

    @Override
    public String toString() {
        return "Cipher{" +
                "memberUUID='" + memberUUID + '\'' +
                ", memberNumber='" + memberNumber + '\'' +
                ", memberCipher='" + memberCipher + '\'' +
                '}';
    }
}
