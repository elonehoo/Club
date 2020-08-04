package com.springboot.beans;

public class SignState {
    private String uuid;
    private String memberNumber;
    private String memberName;
    private String checkIs;

    public SignState() {
    }

    public SignState(String uuid, String memberNumber, String memberName, String checkIs) {
        this.uuid = uuid;
        this.memberNumber = memberNumber;
        this.memberName = memberName;
        this.checkIs = checkIs;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
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

    public String getCheckIs() {
        return checkIs;
    }

    public void setCheckIs(String checkIs) {
        this.checkIs = checkIs;
    }

    @Override
    public String toString() {
        return "SignState{" +
                "uuid='" + uuid + '\'' +
                ", memberNumber='" + memberNumber + '\'' +
                ", memberName='" + memberName + '\'' +
                ", checkIs='" + checkIs + '\'' +
                '}';
    }
}
