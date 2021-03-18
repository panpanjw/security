package com.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

/**
 * @author panjw
 * @date 2021/3/15 22:34
 */

@Data
@Entity
@Table(name = "roles")
public class RoleEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;

    @Column(name = "roleName")
    private String roleName;


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "roles_permission",
            joinColumns = @JoinColumn(name = "roleId"),
            inverseJoinColumns = @JoinColumn(name = "permisionId"))
    private List<PermissionEntity> permissionEntityList;
}
