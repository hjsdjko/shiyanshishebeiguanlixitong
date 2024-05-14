package com.entity.vo;

import com.entity.ShebeiJieyongEntity;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * 设备借用
 * 手机端接口返回实体辅助类
 * （主要作用去除一些不必要的字段）
 */
@TableName("shebei_jieyong")
public class ShebeiJieyongVO implements Serializable {
    private static final long serialVersionUID = 1L;


    /**
     * 主键
     */

    @TableField(value = "id")
    private Integer id;


    /**
     * 学生
     */

    @TableField(value = "xuesheng_id")
    private Integer xueshengId;


    /**
     * 设备
     */

    @TableField(value = "shebei_id")
    private Integer shebeiId;


    /**
     * 申请时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "insert_time")
    private Date insertTime;


    /**
     * 借用数量
     */

    @TableField(value = "shebei_jieyong_number")
    private Integer shebeiJieyongNumber;


    /**
     * 借用原因
     */

    @TableField(value = "beizhu_content")
    private String beizhuContent;


    /**
     * 是否转交
     */

    @TableField(value = "zhuanjiao_types")
    private Integer zhuanjiaoTypes;


    /**
     * 借用状态
     */

    @TableField(value = "shebei_jieyong_yesno_types")
    private Integer shebeiJieyongYesnoTypes;


    /**
     * 审核意见
     */

    @TableField(value = "shebei_jieyong_yesno_text")
    private String shebeiJieyongYesnoText;


    /**
     * 审核时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "shebei_jieyong_shenhe_time")
    private Date shebeiJieyongShenheTime;


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
	 * 设置：学生
	 */
    public Integer getXueshengId() {
        return xueshengId;
    }


    /**
	 * 获取：学生
	 */

    public void setXueshengId(Integer xueshengId) {
        this.xueshengId = xueshengId;
    }
    /**
	 * 设置：设备
	 */
    public Integer getShebeiId() {
        return shebeiId;
    }


    /**
	 * 获取：设备
	 */

    public void setShebeiId(Integer shebeiId) {
        this.shebeiId = shebeiId;
    }
    /**
	 * 设置：申请时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }


    /**
	 * 获取：申请时间
	 */

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    /**
	 * 设置：借用数量
	 */
    public Integer getShebeiJieyongNumber() {
        return shebeiJieyongNumber;
    }


    /**
	 * 获取：借用数量
	 */

    public void setShebeiJieyongNumber(Integer shebeiJieyongNumber) {
        this.shebeiJieyongNumber = shebeiJieyongNumber;
    }
    /**
	 * 设置：借用原因
	 */
    public String getBeizhuContent() {
        return beizhuContent;
    }


    /**
	 * 获取：借用原因
	 */

    public void setBeizhuContent(String beizhuContent) {
        this.beizhuContent = beizhuContent;
    }
    /**
	 * 设置：是否转交
	 */
    public Integer getZhuanjiaoTypes() {
        return zhuanjiaoTypes;
    }


    /**
	 * 获取：是否转交
	 */

    public void setZhuanjiaoTypes(Integer zhuanjiaoTypes) {
        this.zhuanjiaoTypes = zhuanjiaoTypes;
    }
    /**
	 * 设置：借用状态
	 */
    public Integer getShebeiJieyongYesnoTypes() {
        return shebeiJieyongYesnoTypes;
    }


    /**
	 * 获取：借用状态
	 */

    public void setShebeiJieyongYesnoTypes(Integer shebeiJieyongYesnoTypes) {
        this.shebeiJieyongYesnoTypes = shebeiJieyongYesnoTypes;
    }
    /**
	 * 设置：审核意见
	 */
    public String getShebeiJieyongYesnoText() {
        return shebeiJieyongYesnoText;
    }


    /**
	 * 获取：审核意见
	 */

    public void setShebeiJieyongYesnoText(String shebeiJieyongYesnoText) {
        this.shebeiJieyongYesnoText = shebeiJieyongYesnoText;
    }
    /**
	 * 设置：审核时间
	 */
    public Date getShebeiJieyongShenheTime() {
        return shebeiJieyongShenheTime;
    }


    /**
	 * 获取：审核时间
	 */

    public void setShebeiJieyongShenheTime(Date shebeiJieyongShenheTime) {
        this.shebeiJieyongShenheTime = shebeiJieyongShenheTime;
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
