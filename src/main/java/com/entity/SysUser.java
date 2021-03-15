//package com.entity;
//
//import io.swagger.annotations.ApiModelProperty;
//import lombok.Data;
//import org.hibernate.annotations.GenericGenerator;
//
//import javax.persistence.*;
//import java.util.Date;
//
///**
// * @author panjw
// * @date 2020/8/28 10:51
// */
//@Data
//@Entity
//@Table(name = "t_sys_user")
//public class SysUser{
//
//    @Id
//    @Column(
//            name = "id",
//            length = 36
//    )
//    @GeneratedValue(
//            strategy = GenerationType.IDENTITY,
//            generator = "BaseIdGenerator"
//    )
//    @GenericGenerator(
//            name = "BaseIdGenerator",
//            strategy = "com.mysd.core.base.BaseIdGenerator"
//    )
//    private String id;
//
//    @ApiModelProperty(value = "登录名")
//    @Column(name = "LOGIN")
//    private String login;
//
//    @ApiModelProperty(value = "用户名")
//    @Column(name = "NAME")
//    private String name;
//
//    @Column(name = "PASSWORD")
//    private String password;
//
//    @ApiModelProperty(value = "用户单位")
//    @Column(name = "USER_DEPT")
//    private String userDept;
//
//    @ApiModelProperty(value = "是否管理员")
//    @Column(name = "IS_MANAGER")
//    private Integer isManager;
//
//
//    @ApiModelProperty(value = "是否已删除")
//    @Column(name = "DELETED")
//    private Integer deleted;
//
//    @ApiModelProperty(value = "最近登录时间")
//    @Column(name = "LAST_LOGIN_TIME")
//    private Date lastLoginTime;
//
//    @ApiModelProperty(value = "系统风格")
//    @Column(name = "STYLE")
//    private String style;
//
//    @ApiModelProperty(value = "用于电子标识授权使用")
//    @Column(name = "IS_SETUP")
//    private Integer isSetup;
//
//    @ApiModelProperty(value = "同步至公正云后，在公正云当中的用户名称")
//    @Column(name = "CURNAR_USER_NAME")
//    private String curnarUserName;
//
//    @Column(name = "CURNAR_STS")
//    private Integer curnarSts;
//
//    @Column(name = "EXT1")
//    private String ext1;
//
//    @ApiModelProperty(value = "已开通存证账号各系统间同步状态(0未同步、1已同步)")
//    @Column(name = "SYNC_STATUS")
//    private Integer syncStatus;
//
//    @ApiModelProperty(value = "手机号")
//    @Column(name = "TELEPHONE")
//    private String telephone;
//
//}