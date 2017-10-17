package com.arogut.shas.model.jpa.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Transient;
import java.util.Map;

@Entity
@Getter
@Setter
public class SourceDevice extends Device {
    @Transient
    private Map<String, Object> measurements;
}
