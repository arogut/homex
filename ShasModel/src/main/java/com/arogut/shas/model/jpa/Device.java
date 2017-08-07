package com.arogut.shas.model.jpa;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "DEVICE")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "DEVICE_TYPE")
@Getter
@Setter
public abstract class Device {
    @Id
    @NotEmpty
    private String id;
    @Column
    private String name;
    @Column
    private boolean isConnected;
    @Column
    private String host;
    @Column
    private Instant lastConnection;
}
