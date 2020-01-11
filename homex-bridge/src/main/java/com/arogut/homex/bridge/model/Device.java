package com.arogut.homex.bridge.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "device")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "deviceType")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Device {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    @Column
    @NotEmpty
    private String name;
    @Column(name = "deviceType", insertable = false, updatable = false)
    @NotNull
    private DeviceType deviceType;
    @Column
    @JsonProperty
    private boolean isConnected;
    @Column
    @NotEmpty
    private String host;
    @Column
    @Max(99999)
    private int port;
}
