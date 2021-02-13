package com.demo.featureswitches.featureswitches.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "UserFeatures")
public class UserFeatures implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    private String featureName;
    private String email;
    private Boolean enable;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate = new Date();

    @Temporal(TemporalType.TIMESTAMP)
    private Date updateDate;

}
