package com.vr.miniauth.common.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@MappedSuperclass
public class BaseModel implements Serializable{

    private static final long serialVersionUID = 1313653041303822071L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "id")
    public Long id;
}
