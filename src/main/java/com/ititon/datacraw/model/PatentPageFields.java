package com.ititon.datacraw.model;

import java.util.HashMap;
import java.util.Map;

public enum PatentPageFields {

    NAME("titleHidden"),
    APPLICATION_NUM_FULL("idHidden"),
    APPLICATION_NUM_SHORT("vIdHidden"),
    APPLICATION_DATE("nrdAdpHidden"),
    PUBLICATION_NUM("nrdPnHidden"),
    PUBLICATION_DATE("nrdpdHidden"),
    APPLICANT("appNameHidden"),
    APPLICANT_ADDRESS("appAddrHidden"),
    APPLICANT_INDEX("appZipHidden"),
    APPLICANT_PROVINCE("appCountryHidden"),
    LEGAL_STATUS("docStatusHidden"),
    AGENT("agent"),
    AGENCY("agency"),
    LA_NUM("lanum"),
    IPC_NUM("ipc");


    private static final Map<String, PatentPageFields> fields = new HashMap<>();

    static {
        for (PatentPageFields field : values()) {
            fields.put(field.getValue(), field);
        }
    }

    private String value;

    PatentPageFields(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static PatentPageFields findByValue(final String value) {
        return fields.get(value);
    }
}
