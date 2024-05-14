package com.entity.model;

import com.entity.XiaofangEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;


/**
 * 校方
 * 接收传参的实体类
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了）
 * 取自ModelAndView 的model名称
 */
public class XiaofangModel implements Serializable {
    private static final long serialVersionUID = 1L;




    /**
     * 主键
     */
    private Integer id;


    /**
     * 账户
     */
    private String username;


    /**
     * 密码
     */
    private String password;


    /**
     * 名称
     */
    private String xiaofangName;


    /**
     * 头像
     */
    private String xiaofangPhoto;


    /**
     * 校方联系邮箱
     */
    private String xiaofangEmail;


    /**
     * 创建时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date createTime;


    /**
	 * 获取：主键
	 */
    public Integer getId() {
        return id;
    }


    /**
	 * 设置：主键
	 */
    public void setId(Integer id) {
        this.id = id;
    }
    /**
	 * 获取：账户
	 */
    public String getUsername() {
        return username;
    }


    /**
	 * 设置：账户
	 */
    public void setUsername(String username) {
        this.username = username;
    }
    /**
	 * 获取：密码
	 */
    public String getPassword() {
        return password;
    }


    /**
	 * 设置：密码
	 */
    public void setPassword(String password) {
        this.password = password;
    }
    /**
	 * 获取：名称
	 */
    public String getXiaofangName() {
        return xiaofangName;
    }


    /**
	 * 设置：名称
	 */
    public void setXiaofangName(String xiaofangName) {
        this.xiaofangName = xiaofangName;
    }
    /**
	 * 获取：头像
	 */
    public String getXiaofangPhoto() {
        return xiaofangPhoto;
    }


    /**
	 * 设置：头像
	 */
    public void setXiaofangPhoto(String xiaofangPhoto) {
        this.xiaofangPhoto = xiaofangPhoto;
    }
    /**
	 * 获取：校方联系邮箱
	 */
    public String getXiaofangEmail() {
        return xiaofangEmail;
    }


    /**
	 * 设置：校方联系邮箱
	 */
    public void setXiaofangEmail(String xiaofangEmail) {
        this.xiaofangEmail = xiaofangEmail;
    }
    /**
	 * 获取：创建时间
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 设置：创建时间
	 */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    }
