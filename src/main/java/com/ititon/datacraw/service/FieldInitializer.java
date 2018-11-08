package com.ititon.datacraw.service;

import com.ititon.datacraw.model.Patent;

import java.io.Serializable;

public interface FieldInitializer<Type extends Serializable> {

    void setValue(final Patent patent, final Type initValue);
}
