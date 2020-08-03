package com.springboot.beans;

import java.io.Serializable;

public class Student implements Serializable {
    private String memberUUID;
    private String memberNumber;
    private String memberName;

    public Student() {
    }

    public Student(String memberUUID, String memberNumber, String memberName) {
        this.memberUUID = memberUUID;
        this.memberNumber = memberNumber;
        this.memberName = memberName;
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

    @Override
    public String toString() {
        return "Login{" +
                "memberUUID='" + memberUUID + '\'' +
                ", memberNumber='" + memberNumber + '\'' +
                ", memberName='" + memberName + '\'' +
                '}';
    }
}
