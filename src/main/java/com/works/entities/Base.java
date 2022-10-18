package com.works.entities;

import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Data
public class Base {

    @CreatedBy
    private String createdBy;

    @CreatedDate
    private long createdDate;

    @LastModifiedBy
    private String lastModifiedBy;

    @LastModifiedDate
    private long lastModifiedDate;

}
