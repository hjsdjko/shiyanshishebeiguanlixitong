package com.entity.view;

import com.entity.ShebeiEntity;
import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;

/**
 * 设备
 * 后端返回视图实体辅助类
 * （通常后端关联的表或者自定义的字段需要返回使用）
 */
@TableName("shebei")
public class ShebeiView extends ShebeiEntity implements Serializable {
    private static final long serialVersionUID = 1L;

		/**
		* 设备分类的值
		*/
		private String shebeiValue;



		//级联表 shiyanshi
			/**
			* 实验室名称
			*/
			private String shiyanshiName;
			/**
			* 实验室类型
			*/
			private Integer shiyanshiTypes;
				/**
				* 实验室类型的值
				*/
				private String shiyanshiValue;
			/**
			* 实验室位置
			*/
			private String shiyanshiAddress;
			/**
			* 实验室详情
			*/
			private String shiyanshiContent;

	public ShebeiView() {

	}

	public ShebeiView(ShebeiEntity shebeiEntity) {
		try {
			BeanUtils.copyProperties(this, shebeiEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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












				//级联表的get和set shiyanshi

					/**
					* 获取： 实验室名称
					*/
					public String getShiyanshiName() {
						return shiyanshiName;
					}
					/**
					* 设置： 实验室名称
					*/
					public void setShiyanshiName(String shiyanshiName) {
						this.shiyanshiName = shiyanshiName;
					}

					/**
					* 获取： 实验室类型
					*/
					public Integer getShiyanshiTypes() {
						return shiyanshiTypes;
					}
					/**
					* 设置： 实验室类型
					*/
					public void setShiyanshiTypes(Integer shiyanshiTypes) {
						this.shiyanshiTypes = shiyanshiTypes;
					}


						/**
						* 获取： 实验室类型的值
						*/
						public String getShiyanshiValue() {
							return shiyanshiValue;
						}
						/**
						* 设置： 实验室类型的值
						*/
						public void setShiyanshiValue(String shiyanshiValue) {
							this.shiyanshiValue = shiyanshiValue;
						}

					/**
					* 获取： 实验室位置
					*/
					public String getShiyanshiAddress() {
						return shiyanshiAddress;
					}
					/**
					* 设置： 实验室位置
					*/
					public void setShiyanshiAddress(String shiyanshiAddress) {
						this.shiyanshiAddress = shiyanshiAddress;
					}

					/**
					* 获取： 实验室详情
					*/
					public String getShiyanshiContent() {
						return shiyanshiContent;
					}
					/**
					* 设置： 实验室详情
					*/
					public void setShiyanshiContent(String shiyanshiContent) {
						this.shiyanshiContent = shiyanshiContent;
					}






}
