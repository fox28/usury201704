package cn.kkk.usury.model.bean;

/**
 * Created by apple on 2017/5/9.
 */

public class User {

    private String mac_uuid;
    private String phoneNum;

    public String getMac_uuid() {
        return mac_uuid;
    }

    public void setMac_uuid(String mac_uuid) {
        this.mac_uuid = mac_uuid;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof User)) return false;

        User user = (User) obj;
        if (!phoneNum.equals(user.getPhoneNum())) {
            return false;
        }
//        return super.equals(obj);
        return this == obj;
    }

    @Override
    public String toString() {
//        return super.toString();
        return "Uer{"+
                "mac_uuid = \'" + mac_uuid + '\'' +
                " ,phoneNum + \'" + phoneNum + "\'" +
                "}";
    }
}
