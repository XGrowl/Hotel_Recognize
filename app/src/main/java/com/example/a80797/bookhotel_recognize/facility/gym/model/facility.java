package com.example.a80797.bookhotel_recognize.facility.gym.model;

public class facility {
    String facilityName;
    int facilityReserve;
    int facilityUser;

    public facility(String facilityName, int facilityReserve, int facilityUser) {
        this.facilityName = facilityName;
        this.facilityReserve = facilityReserve;
        this.facilityUser = facilityUser;
    }

    public String getFacilityName() {
        return facilityName;
    }

    public void setFacilityName(String facilityName) {
        this.facilityName = facilityName;
    }

    public int getFacilityReserve() {
        return facilityReserve;
    }

    public void setFacilityReserve(int facilityReserve) {
        this.facilityReserve = facilityReserve;
    }

    public int getFacilityUser() {
        return facilityUser;
    }

    public void setFacilityUser(int facilityUser) {
        this.facilityUser = facilityUser;
    }
}
