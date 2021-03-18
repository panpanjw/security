package com.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * @author panjw
 * @date 2021/3/18 14:30
 */

@Data
@Entity
@Table(name = "permission")
public class PermissionEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;

    @Column(name = "permissionName")
    private String permissionName;

    @Column(name = "url")
    private String url;
}
