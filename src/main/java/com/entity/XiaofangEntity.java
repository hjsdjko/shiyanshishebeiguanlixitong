package com.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.lang.reflect.InvocationTargetException;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.beanutils.BeanUtils;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;

/**
 * 校方
 *
 * @author 
 * @email
 */
@TableName("xiaofang")
public class XiaofangEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;


	public XiaofangEntity() {

	}

	public XiaofangEntity(T t) {
		try {
			BeanUtils.copyProperties(this, t);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    @TableField(value = "id")

    private Integer id;


    /**
     * 账户
     */
    @TableField(value = "username")

    private String username;


    /**
     * 密码
     */
    @TableField(value = "password")

    private String password;


    /**
     * 名称
     */
    @TableField(value = "xiaofang_name")

    private String xiaofangName;


    /**
     * 头像
     */
    @TableField(value = "xiaofang_photo")

    private String xiaofangPhoto;


    /**
     * 校方联系邮箱
     */
    @TableField(value = "xiaofang_email")

    private String xiaofangEmail;


    /**
     * 创建时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @TableField(value = "create_time",fill = FieldFill.INSERT)

    private Date createTime;


    /**
	 * 设置：主键
	 */
    public Integer getId() {
        return id;
    }
    /**
	 * 获取：主键
	 */

    public void setId(Integer id) {
        this.id = id;
    }
    /**
	 * 设置：账户
	 */
    public String getUsername() {
        return username;
    }
    /**
	 * 获取：账户
	 */

    public void setUsername(String username) {
        this.username = username;
    }
    /**
	 * 设置：密码
	 */
    public String getPassword() {
        return password;
    }
    /**
	 * 获取：密码
	 */

    public void setPassword(String password) {
        this.password = password;
    }
    /**
	 * 设置：名称
	 */
    public String getXiaofangName() {
        return xiaofangName;
    }
    /**
	 * 获取：名称
	 */

    public void setXiaofangName(String xiaofangName) {
        this.xiaofangName = xiaofangName;
    }
    /**
	 * 设置：头像
	 */
    public String getXiaofangPhoto() {
        return xiaofangPhoto;
    }
    /**
	 * 获取：头像
	 */

    public void setXiaofangPhoto(String xiaofangPhoto) {
        this.xiaofangPhoto = xiaofangPhoto;
    }
    /**
	 * 设置：校方联系邮箱
	 */
    public String getXiaofangEmail() {
        return xiaofangEmail;
    }
    /**
	 * 获取：校方联系邮箱
	 */

    public void setXiaofangEmail(String xiaofangEmail) {
        this.xiaofangEmail = xiaofangEmail;
    }
    /**
	 * 设置：创建时间
	 */
    public Date getCreateTime() {
        return createTime;
    }
    /**
	 * 获取：创建时间
	 */

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Xiaofang{" +
            "id=" + id +
            ", username=" + username +
            ", password=" + password +
            ", xiaofangName=" + xiaofangName +
            ", xiaofangPhoto=" + xiaofangPhoto +
            ", xiaofangEmail=" + xiaofangEmail +
            ", createTime=" + createTime +
        "}";
    }
}
