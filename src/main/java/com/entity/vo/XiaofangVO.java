package com.entity.vo;

import com.entity.XiaofangEntity;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * 校方
 * 手机端接口返回实体辅助类
 * （主要作用去除一些不必要的字段）
 */
@TableName("xiaofang")
public class XiaofangVO implements Serializable {
    private static final long serialVersionUID = 1L;


    /**
     * 主键
     */

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

    @TableField(value = "create_time")
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

}
