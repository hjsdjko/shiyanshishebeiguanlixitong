
package com.controller;

import java.io.File;
import java.math.BigDecimal;
import java.net.URL;
import java.text.SimpleDateFormat;
import com.alibaba.fastjson.JSONObject;
import java.util.*;
import org.springframework.beans.BeanUtils;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.context.ContextLoader;
import javax.servlet.ServletContext;
import com.service.TokenService;
import com.utils.*;
import java.lang.reflect.InvocationTargetException;

import com.service.DictionaryService;
import org.apache.commons.lang3.StringUtils;
import com.annotation.IgnoreAuth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.entity.*;
import com.entity.view.*;
import com.service.*;
import com.utils.PageUtils;
import com.utils.R;
import com.alibaba.fastjson.*;

/**
 * 设备借用
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/shebeiJieyong")
public class ShebeiJieyongController {
    private static final Logger logger = LoggerFactory.getLogger(ShebeiJieyongController.class);

    @Autowired
    private ShebeiJieyongService shebeiJieyongService;


    @Autowired
    private TokenService tokenService;
    @Autowired
    private DictionaryService dictionaryService;

    //级联表service
    @Autowired
    private ShebeiService shebeiService;
    @Autowired
    private XueshengService xueshengService;

    @Autowired
    private XiaofangService xiaofangService;


    /**
    * 后端列表
    */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("page方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));
        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永不会进入");
        else if("校方".equals(role)){
            params.put("zhuanjiaoTypes",2);
            params.put("xiaofangId",request.getSession().getAttribute("userId"));
        }
        else if("学生".equals(role))
            params.put("xueshengId",request.getSession().getAttribute("userId"));
        if(params.get("orderBy")==null || params.get("orderBy")==""){
            params.put("orderBy","id");
        }
        PageUtils page = shebeiJieyongService.queryPage(params);

        //字典表数据转换
        List<ShebeiJieyongView> list =(List<ShebeiJieyongView>)page.getList();
        for(ShebeiJieyongView c:list){
            //修改对应字典表字段
            dictionaryService.dictionaryConvert(c, request);
        }
        return R.ok().put("data", page);
    }

    /**
    * 后端详情
    */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("info方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        ShebeiJieyongEntity shebeiJieyong = shebeiJieyongService.selectById(id);
        if(shebeiJieyong !=null){
            //entity转view
            ShebeiJieyongView view = new ShebeiJieyongView();
            BeanUtils.copyProperties( shebeiJieyong , view );//把实体数据重构到view中

                //级联表
                ShebeiEntity shebei = shebeiService.selectById(shebeiJieyong.getShebeiId());
                if(shebei != null){
                    BeanUtils.copyProperties( shebei , view ,new String[]{ "id", "createTime", "insertTime", "updateTime"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setShebeiId(shebei.getId());
                }
                //级联表
                XueshengEntity xuesheng = xueshengService.selectById(shebeiJieyong.getXueshengId());
                if(xuesheng != null){
                    BeanUtils.copyProperties( xuesheng , view ,new String[]{ "id", "createTime", "insertTime", "updateTime"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setXueshengId(xuesheng.getId());
                }
            //修改对应字典表字段
            dictionaryService.dictionaryConvert(view, request);
            return R.ok().put("data", view);
        }else {
            return R.error(511,"查不到数据");
        }

    }

    /**
    * 后端保存
    */
    @RequestMapping("/save")
    public R save(@RequestBody ShebeiJieyongEntity shebeiJieyong, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,shebeiJieyong:{}",this.getClass().getName(),shebeiJieyong.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");
        else if("学生".equals(role))
            shebeiJieyong.setXueshengId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));

        Wrapper<ShebeiJieyongEntity> queryWrapper = new EntityWrapper<ShebeiJieyongEntity>()
            .eq("xuesheng_id", shebeiJieyong.getXueshengId())
            .eq("shebei_id", shebeiJieyong.getShebeiId())
            .eq("shebei_jieyong_number", shebeiJieyong.getShebeiJieyongNumber())
            .eq("zhuanjiao_types", shebeiJieyong.getZhuanjiaoTypes())
            .eq("shebei_jieyong_yesno_types", shebeiJieyong.getShebeiJieyongYesnoTypes())
            .eq("shebei_jieyong_yesno_text", shebeiJieyong.getShebeiJieyongYesnoText())
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        ShebeiJieyongEntity shebeiJieyongEntity = shebeiJieyongService.selectOne(queryWrapper);
        if(shebeiJieyongEntity==null){

            ShebeiEntity shebeiEntity = shebeiService.selectById(shebeiJieyong.getShebeiId());
            if(shebeiEntity == null)
                return R.error("查不到设备");
            else if((shebeiEntity.getShebeiNumber()-shebeiJieyong.getShebeiJieyongNumber())<0)
                return R.error("目前库存不足以借用这么多数量的设备");

            shebeiJieyong.setInsertTime(new Date());
            shebeiJieyong.setShebeiJieyongYesnoTypes(1);
            shebeiJieyong.setZhuanjiaoTypes(1);
            shebeiJieyong.setCreateTime(new Date());
            shebeiJieyongService.insert(shebeiJieyong);
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody ShebeiJieyongEntity shebeiJieyong, HttpServletRequest request){
        logger.debug("update方法:,,Controller:{},,shebeiJieyong:{}",this.getClass().getName(),shebeiJieyong.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");
//        else if("学生".equals(role))
//            shebeiJieyong.setXueshengId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));
        //根据字段查询是否有相同数据
        Wrapper<ShebeiJieyongEntity> queryWrapper = new EntityWrapper<ShebeiJieyongEntity>()
            .notIn("id",shebeiJieyong.getId())
            .andNew()
            .eq("xuesheng_id", shebeiJieyong.getXueshengId())
            .eq("shebei_id", shebeiJieyong.getShebeiId())
            .eq("insert_time", shebeiJieyong.getInsertTime())
            .eq("shebei_jieyong_number", shebeiJieyong.getShebeiJieyongNumber())
            .eq("zhuanjiao_types", shebeiJieyong.getZhuanjiaoTypes())
            .eq("shebei_jieyong_yesno_types", shebeiJieyong.getShebeiJieyongYesnoTypes())
            .eq("shebei_jieyong_yesno_text", shebeiJieyong.getShebeiJieyongYesnoText())
            .eq("shebei_jieyong_shenhe_time", shebeiJieyong.getShebeiJieyongShenheTime())
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        ShebeiJieyongEntity shebeiJieyongEntity = shebeiJieyongService.selectOne(queryWrapper);
        if(shebeiJieyongEntity==null){
            shebeiJieyongService.updateById(shebeiJieyong);//根据id更新
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }


    /**
    * 审核
    */
    @RequestMapping("/shenhe")
    public R shenhe(@RequestBody ShebeiJieyongEntity shebeiJieyongEntity, HttpServletRequest request){
        logger.debug("shenhe方法:,,Controller:{},,shebeiJieyongEntity:{}",this.getClass().getName(),shebeiJieyongEntity.toString());

        if(shebeiJieyongEntity.getShebeiJieyongYesnoTypes() == 2){//通过

            ShebeiJieyongEntity shebeiJieyongEntity1 = shebeiJieyongService.selectById(shebeiJieyongEntity.getId());
            if(shebeiJieyongEntity1 == null)
                return R.error("查不到借用订单");
            ShebeiEntity shebeiEntity = shebeiService.selectById(shebeiJieyongEntity1.getShebeiId());
            if(shebeiEntity == null)
                return R.error("查不到设备");

            int balance = shebeiEntity.getShebeiNumber() - shebeiJieyongEntity1.getShebeiJieyongNumber();
            if(balance<0)
                return R.error("当前库存不够借出去这么多设备");
            shebeiEntity.setShebeiNumber(balance);
            shebeiService.updateById(shebeiEntity);
        }else if(shebeiJieyongEntity.getShebeiJieyongYesnoTypes() == 3){//拒绝
        }
        shebeiJieyongEntity.setShebeiJieyongShenheTime(new Date());//审核时间
        shebeiJieyongService.updateById(shebeiJieyongEntity);//审核
        return R.ok();
    }

    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        shebeiJieyongService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }


    /**
    * 设备归还
    */
    @RequestMapping("guihuanshebei")
    public R guihuanshebei(@RequestParam("id") Integer id){
        logger.debug("delete:,,Controller:{},,id:{}",this.getClass().getName(),id.toString());
        ShebeiJieyongEntity shebeiJieyongEntity = shebeiJieyongService.selectById(id);
        if(shebeiJieyongEntity == null)
            return R.error("查不到设备借用记录");
        ShebeiEntity shebeiEntity = shebeiService.selectById(shebeiJieyongEntity.getShebeiId());
        if(shebeiEntity == null)
            return R.error("查不到设备");
        shebeiEntity.setShebeiNumber(shebeiEntity.getShebeiNumber()+shebeiJieyongEntity.getShebeiJieyongNumber());
        shebeiService.updateById(shebeiEntity);
        shebeiJieyongEntity.setShebeiJieyongYesnoTypes(4);
        shebeiJieyongService.updateById(shebeiJieyongEntity);

        return R.ok();
    }


    /**
     * 批量上传
     */
    @RequestMapping("/batchInsert")
    public R save( String fileName, HttpServletRequest request){
        logger.debug("batchInsert方法:,,Controller:{},,fileName:{}",this.getClass().getName(),fileName);
        Integer yonghuId = Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId")));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            List<ShebeiJieyongEntity> shebeiJieyongList = new ArrayList<>();//上传的东西
            Map<String, List<String>> seachFields= new HashMap<>();//要查询的字段
            Date date = new Date();
            int lastIndexOf = fileName.lastIndexOf(".");
            if(lastIndexOf == -1){
                return R.error(511,"该文件没有后缀");
            }else{
                String suffix = fileName.substring(lastIndexOf);
                if(!".xls".equals(suffix)){
                    return R.error(511,"只支持后缀为xls的excel文件");
                }else{
                    URL resource = this.getClass().getClassLoader().getResource("../../upload/" + fileName);//获取文件路径
                    File file = new File(resource.getFile());
                    if(!file.exists()){
                        return R.error(511,"找不到上传文件，请联系管理员");
                    }else{
                        List<List<String>> dataList = PoiUtil.poiImport(file.getPath());//读取xls文件
                        dataList.remove(0);//删除第一行，因为第一行是提示
                        for(List<String> data:dataList){
                            //循环
                            ShebeiJieyongEntity shebeiJieyongEntity = new ShebeiJieyongEntity();
//                            shebeiJieyongEntity.setXueshengId(Integer.valueOf(data.get(0)));   //学生 要改的
//                            shebeiJieyongEntity.setShebeiId(Integer.valueOf(data.get(0)));   //设备 要改的
//                            shebeiJieyongEntity.setInsertTime(date);//时间
//                            shebeiJieyongEntity.setShebeiJieyongNumber(Integer.valueOf(data.get(0)));   //借用数量 要改的
//                            shebeiJieyongEntity.setBeizhuContent("");//详情和图片
//                            shebeiJieyongEntity.setZhuanjiaoTypes(Integer.valueOf(data.get(0)));   //是否转交 要改的
//                            shebeiJieyongEntity.setShebeiJieyongYesnoTypes(Integer.valueOf(data.get(0)));   //借用状态 要改的
//                            shebeiJieyongEntity.setShebeiJieyongYesnoText(data.get(0));                    //审核意见 要改的
//                            shebeiJieyongEntity.setShebeiJieyongShenheTime(sdf.parse(data.get(0)));          //审核时间 要改的
//                            shebeiJieyongEntity.setCreateTime(date);//时间
                            shebeiJieyongList.add(shebeiJieyongEntity);


                            //把要查询是否重复的字段放入map中
                        }

                        //查询是否重复
                        shebeiJieyongService.insertBatch(shebeiJieyongList);
                        return R.ok();
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            return R.error(511,"批量插入数据异常，请联系管理员");
        }
    }






}
