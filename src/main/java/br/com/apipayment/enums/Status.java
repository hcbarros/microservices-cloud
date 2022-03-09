package br.com.apipayment.enums;


import com.fasterxml.jackson.annotation.JsonCreator;

public enum Status {

    AWAITING_PAYMENT,
    CANCELED,
    PAID_OFF;

    @JsonCreator
    public static Status fromString(String name) {
        for(Status status : Status.values()) {
            if(status.name().equalsIgnoreCase(name)) {
                return status;
            }
        }
        return null;
    }

}
