package com.entity.view;

import com.entity.ShebeiJieyongEntity;
import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;

/**
 * 设备借用
 * 后端返回视图实体辅助类
 * （通常后端关联的表或者自定义的字段需要返回使用）
 */
@TableName("shebei_jieyong")
public class ShebeiJieyongView extends ShebeiJieyongEntity implements Serializable {
    private static final long serialVersionUID = 1L;

		/**
		* 是否转交的值
		*/
		private String zhuanjiaoValue;
		/**
		* 借用状态的值
		*/
		private String shebeiJieyongYesnoValue;



		//级联表 shebei
			/**
			* 设备名称
			*/
			private String shebeiName;
			/**
			* 设备分类
			*/
			private Integer shebeiTypes;
				/**
				* 设备分类的值
				*/
				private String shebeiValue;
			/**
			* 设备数量
			*/
			private Integer shebeiNumber;
			/**
			* 设备详情
			*/
			private String shebeiContent;

		//级联表 xuesheng
			/**
			* 姓名
			*/
			private String xueshengName;
			/**
			* 手机号
			*/
			private String xueshengPhone;
			/**
			* 身份证号
			*/
			private String xueshengIdNumber;
			/**
			* 照片
			*/
			private String xueshengPhoto;
			/**
			* 电子邮箱
			*/
			private String xueshengEmail;

	public ShebeiJieyongView() {

	}

	public ShebeiJieyongView(ShebeiJieyongEntity shebeiJieyongEntity) {
		try {
			BeanUtils.copyProperties(this, shebeiJieyongEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



			/**
			* 获取： 是否转交的值
			*/
			public String getZhuanjiaoValue() {
				return zhuanjiaoValue;
			}
			/**
			* 设置： 是否转交的值
			*/
			public void setZhuanjiaoValue(String zhuanjiaoValue) {
				this.zhuanjiaoValue = zhuanjiaoValue;
			}
			/**
			* 获取： 借用状态的值
			*/
			public String getShebeiJieyongYesnoValue() {
				return shebeiJieyongYesnoValue;
			}
			/**
			* 设置： 借用状态的值
			*/
			public void setShebeiJieyongYesnoValue(String shebeiJieyongYesnoValue) {
				this.shebeiJieyongYesnoValue = shebeiJieyongYesnoValue;
			}











				//级联表的get和set shebei


					/**
					* 获取： 设备名称
					*/
					public String getShebeiName() {
						return shebeiName;
					}
					/**
					* 设置： 设备名称
					*/
					public void setShebeiName(String shebeiName) {
						this.shebeiName = shebeiName;
					}

					/**
					* 获取： 设备分类
					*/
					public Integer getShebeiTypes() {
						return shebeiTypes;
					}
					/**
					* 设置： 设备分类
					*/
					public void setShebeiTypes(Integer shebeiTypes) {
						this.shebeiTypes = shebeiTypes;
					}


						/**
						* 获取： 设备分类的值
						*/
						public String getShebeiValue() {
							return shebeiValue;
						}
						/**
						* 设置： 设备分类的值
						*/
						public void setShebeiValue(String shebeiValue) {
							this.shebeiValue = shebeiValue;
						}

					/**
					* 获取： 设备数量
					*/
					public Integer getShebeiNumber() {
						return shebeiNumber;
					}
					/**
					* 设置： 设备数量
					*/
					public void setShebeiNumber(Integer shebeiNumber) {
						this.shebeiNumber = shebeiNumber;
					}

					/**
					* 获取： 设备详情
					*/
					public String getShebeiContent() {
						return shebeiContent;
					}
					/**
					* 设置： 设备详情
					*/
					public void setShebeiContent(String shebeiContent) {
						this.shebeiContent = shebeiContent;
					}











				//级联表的get和set xuesheng

					/**
					* 获取： 姓名
					*/
					public String getXueshengName() {
						return xueshengName;
					}
					/**
					* 设置： 姓名
					*/
					public void setXueshengName(String xueshengName) {
						this.xueshengName = xueshengName;
					}

					/**
					* 获取： 手机号
					*/
					public String getXueshengPhone() {
						return xueshengPhone;
					}
					/**
					* 设置： 手机号
					*/
					public void setXueshengPhone(String xueshengPhone) {
						this.xueshengPhone = xueshengPhone;
					}

					/**
					* 获取： 身份证号
					*/
					public String getXueshengIdNumber() {
						return xueshengIdNumber;
					}
					/**
					* 设置： 身份证号
					*/
					public void setXueshengIdNumber(String xueshengIdNumber) {
						this.xueshengIdNumber = xueshengIdNumber;
					}

					/**
					* 获取： 照片
					*/
					public String getXueshengPhoto() {
						return xueshengPhoto;
					}
					/**
					* 设置： 照片
					*/
					public void setXueshengPhoto(String xueshengPhoto) {
						this.xueshengPhoto = xueshengPhoto;
					}

					/**
					* 获取： 电子邮箱
					*/
					public String getXueshengEmail() {
						return xueshengEmail;
					}
					/**
					* 设置： 电子邮箱
					*/
					public void setXueshengEmail(String xueshengEmail) {
						this.xueshengEmail = xueshengEmail;
					}




}
