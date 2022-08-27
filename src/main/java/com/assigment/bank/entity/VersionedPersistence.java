package com.assigment.bank.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;

public abstract class VersionedPersistence {

    @Getter
    @Setter
    @UpdateTimestamp
    private Timestamp dateUpdate;
}
