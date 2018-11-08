package com.ititon.datacraw.model;

import java.util.HashMap;
import java.util.Map;

public enum SearchField {
    NAME("发明名称"),
    APPLICATION_NUM1("申请号"),
    APPLICATION_NUM("申请号\u00AD"),
    APPLICATION_DATE("申请日"),
    PUBLICATION_NUM("公开（公告）号\u00AD"),
    PUBLICATION_DATE("公开（公告）日"),
    APPLICANT("申请（专利权）人"),
    HOLDER("发明人"),
    LA_NUM("外观设计洛迦诺分类号"),
    AGENT("代理人"),
    AGENCY("代理机构"),
    PRIORITY_NUM("优先权号"),
    PRIORITY_DATE("优先权日"),
    APPLICANT_ADDRESS("申请人地址"),
    APPLICANT_INDEX("申请人邮编"),
    APPLICANT_PROVINCE("申请人所在国（省）"),
    CPC_NUM("CPC分类号"),
    LEGAL_STATUS("法律状态"),
    SOURCE_OF("引证"),
    PATENT_TYPE("同族"),
    IPC_NUM("IPC分类号");


    private static final Map<String, SearchField> fields = new HashMap<>();

    static {
        for (SearchField field : values()) {
            fields.put(field.getTranslation(), field);
        }
    }

    private String translation;

    SearchField(String translation) {
        this.translation = translation;
    }

    public String getTranslation() {
        return translation;
    }

    public static SearchField findByValue(final String value) {
        return fields.get(value);
    }
}
