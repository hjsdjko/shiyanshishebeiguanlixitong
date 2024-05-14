package com.entity.model;

import com.entity.ShebeiJieyongEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;


/**
 * 设备借用
 * 接收传参的实体类
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了）
 * 取自ModelAndView 的model名称
 */
public class ShebeiJieyongModel implements Serializable {
    private static final long serialVersionUID = 1L;




    /**
     * 主键
     */
    private Integer id;


    /**
     * 学生
     */
    private Integer xueshengId;


    /**
     * 设备
     */
    private Integer shebeiId;


    /**
     * 申请时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date insertTime;


    /**
     * 借用数量
     */
    private Integer shebeiJieyongNumber;


    /**
     * 借用原因
     */
    private String beizhuContent;


    /**
     * 是否转交
     */
    private Integer zhuanjiaoTypes;


    /**
     * 借用状态
     */
    private Integer shebeiJieyongYesnoTypes;


    /**
     * 审核意见
     */
    private String shebeiJieyongYesnoText;


    /**
     * 审核时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date shebeiJieyongShenheTime;


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
	 * 获取：学生
	 */
    public Integer getXueshengId() {
        return xueshengId;
    }


    /**
	 * 设置：学生
	 */
    public void setXueshengId(Integer xueshengId) {
        this.xueshengId = xueshengId;
    }
    /**
	 * 获取：设备
	 */
    public Integer getShebeiId() {
        return shebeiId;
    }


    /**
	 * 设置：设备
	 */
    public void setShebeiId(Integer shebeiId) {
        this.shebeiId = shebeiId;
    }
    /**
	 * 获取：申请时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }


    /**
	 * 设置：申请时间
	 */
    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    /**
	 * 获取：借用数量
	 */
    public Integer getShebeiJieyongNumber() {
        return shebeiJieyongNumber;
    }


    /**
	 * 设置：借用数量
	 */
    public void setShebeiJieyongNumber(Integer shebeiJieyongNumber) {
        this.shebeiJieyongNumber = shebeiJieyongNumber;
    }
    /**
	 * 获取：借用原因
	 */
    public String getBeizhuContent() {
        return beizhuContent;
    }


    /**
	 * 设置：借用原因
	 */
    public void setBeizhuContent(String beizhuContent) {
        this.beizhuContent = beizhuContent;
    }
    /**
	 * 获取：是否转交
	 */
    public Integer getZhuanjiaoTypes() {
        return zhuanjiaoTypes;
    }


    /**
	 * 设置：是否转交
	 */
    public void setZhuanjiaoTypes(Integer zhuanjiaoTypes) {
        this.zhuanjiaoTypes = zhuanjiaoTypes;
    }
    /**
	 * 获取：借用状态
	 */
    public Integer getShebeiJieyongYesnoTypes() {
        return shebeiJieyongYesnoTypes;
    }


    /**
	 * 设置：借用状态
	 */
    public void setShebeiJieyongYesnoTypes(Integer shebeiJieyongYesnoTypes) {
        this.shebeiJieyongYesnoTypes = shebeiJieyongYesnoTypes;
    }
    /**
	 * 获取：审核意见
	 */
    public String getShebeiJieyongYesnoText() {
        return shebeiJieyongYesnoText;
    }


    /**
	 * 设置：审核意见
	 */
    public void setShebeiJieyongYesnoText(String shebeiJieyongYesnoText) {
        this.shebeiJieyongYesnoText = shebeiJieyongYesnoText;
    }
    /**
	 * 获取：审核时间
	 */
    public Date getShebeiJieyongShenheTime() {
        return shebeiJieyongShenheTime;
    }


    /**
	 * 设置：审核时间
	 */
    public void setShebeiJieyongShenheTime(Date shebeiJieyongShenheTime) {
        this.shebeiJieyongShenheTime = shebeiJieyongShenheTime;
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
