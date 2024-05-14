
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
 * 校方
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/xiaofang")
public class XiaofangController {
    private static final Logger logger = LoggerFactory.getLogger(XiaofangController.class);

    @Autowired
    private XiaofangService xiaofangService;


    @Autowired
    private TokenService tokenService;
    @Autowired
    private DictionaryService dictionaryService;

    //级联表service

    @Autowired
    private XueshengService xueshengService;


    /**
    * 后端列表
    */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("page方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));
        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永不会进入");
        else if("校方".equals(role))
            params.put("xiaofangId",request.getSession().getAttribute("userId"));
        else if("学生".equals(role))
            params.put("xueshengId",request.getSession().getAttribute("userId"));
        if(params.get("orderBy")==null || params.get("orderBy")==""){
            params.put("orderBy","id");
        }
        PageUtils page = xiaofangService.queryPage(params);

        //字典表数据转换
        List<XiaofangView> list =(List<XiaofangView>)page.getList();
        for(XiaofangView c:list){
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
        XiaofangEntity xiaofang = xiaofangService.selectById(id);
        if(xiaofang !=null){
            //entity转view
            XiaofangView view = new XiaofangView();
            BeanUtils.copyProperties( xiaofang , view );//把实体数据重构到view中

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
    public R save(@RequestBody XiaofangEntity xiaofang, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,xiaofang:{}",this.getClass().getName(),xiaofang.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");

        Wrapper<XiaofangEntity> queryWrapper = new EntityWrapper<XiaofangEntity>()
            .eq("username", xiaofang.getUsername())
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        XiaofangEntity xiaofangEntity = xiaofangService.selectOne(queryWrapper);
        if(xiaofangEntity==null){
            xiaofang.setCreateTime(new Date());
            xiaofang.setPassword("123456");
            xiaofangService.insert(xiaofang);
            return R.ok();
        }else {
            return R.error(511,"账户已经被使用");
        }
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody XiaofangEntity xiaofang, HttpServletRequest request){
        logger.debug("update方法:,,Controller:{},,xiaofang:{}",this.getClass().getName(),xiaofang.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");
        //根据字段查询是否有相同数据
        Wrapper<XiaofangEntity> queryWrapper = new EntityWrapper<XiaofangEntity>()
            .notIn("id",xiaofang.getId())
            .andNew()
            .eq("username", xiaofang.getUsername())
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        XiaofangEntity xiaofangEntity = xiaofangService.selectOne(queryWrapper);
        if("".equals(xiaofang.getXiaofangPhoto()) || "null".equals(xiaofang.getXiaofangPhoto())){
                xiaofang.setXiaofangPhoto(null);
        }
        if(xiaofangEntity==null){
            xiaofangService.updateById(xiaofang);//根据id更新
            return R.ok();
        }else {
            return R.error(511,"账户已经被使用");
        }
    }



    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        xiaofangService.deleteBatchIds(Arrays.asList(ids));
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
            List<XiaofangEntity> xiaofangList = new ArrayList<>();//上传的东西
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
                            XiaofangEntity xiaofangEntity = new XiaofangEntity();
//                            xiaofangEntity.setUsername(data.get(0));                    //账户 要改的
//                            //xiaofangEntity.setPassword("123456");//密码
//                            xiaofangEntity.setXiaofangName(data.get(0));                    //名称 要改的
//                            xiaofangEntity.setXiaofangPhoto("");//详情和图片
//                            xiaofangEntity.setXiaofangEmail(data.get(0));                    //校方联系邮箱 要改的
//                            xiaofangEntity.setCreateTime(date);//时间
                            xiaofangList.add(xiaofangEntity);


                            //把要查询是否重复的字段放入map中
                                //账户
                                if(seachFields.containsKey("username")){
                                    List<String> username = seachFields.get("username");
                                    username.add(data.get(0));//要改的
                                }else{
                                    List<String> username = new ArrayList<>();
                                    username.add(data.get(0));//要改的
                                    seachFields.put("username",username);
                                }
                        }

                        //查询是否重复
                         //账户
                        List<XiaofangEntity> xiaofangEntities_username = xiaofangService.selectList(new EntityWrapper<XiaofangEntity>().in("username", seachFields.get("username")));
                        if(xiaofangEntities_username.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(XiaofangEntity s:xiaofangEntities_username){
                                repeatFields.add(s.getUsername());
                            }
                            return R.error(511,"数据库的该表中的 [账户] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                        xiaofangService.insertBatch(xiaofangList);
                        return R.ok();
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            return R.error(511,"批量插入数据异常，请联系管理员");
        }
    }


    /**
    * 登录
    */
    @IgnoreAuth
    @RequestMapping(value = "/login")
    public R login(String username, String password, String captcha, HttpServletRequest request) {
        XiaofangEntity xiaofang = xiaofangService.selectOne(new EntityWrapper<XiaofangEntity>().eq("username", username));
        if(xiaofang==null || !xiaofang.getPassword().equals(password))
            return R.error("账号或密码不正确");
        //  // 获取监听器中的字典表
        // ServletContext servletContext = ContextLoader.getCurrentWebApplicationContext().getServletContext();
        // Map<String, Map<Integer, String>> dictionaryMap= (Map<String, Map<Integer, String>>) servletContext.getAttribute("dictionaryMap");
        // Map<Integer, String> role_types = dictionaryMap.get("role_types");
        // role_types.get(.getRoleTypes());
        String token = tokenService.generateToken(xiaofang.getId(),username, "xiaofang", "校方");
        R r = R.ok();
        r.put("token", token);
        r.put("role","校方");
        r.put("username",xiaofang.getXiaofangName());
        r.put("tableName","xiaofang");
        r.put("userId",xiaofang.getId());
        return r;
    }

    /**
    * 注册
    */
    @IgnoreAuth
    @PostMapping(value = "/register")
    public R register(@RequestBody XiaofangEntity xiaofang){
//    	ValidatorUtils.validateEntity(user);
        Wrapper<XiaofangEntity> queryWrapper = new EntityWrapper<XiaofangEntity>()
            .eq("username", xiaofang.getUsername())
            ;
        XiaofangEntity xiaofangEntity = xiaofangService.selectOne(queryWrapper);
        if(xiaofangEntity != null)
            return R.error("账户已经被使用");
        xiaofang.setCreateTime(new Date());
        xiaofangService.insert(xiaofang);
        return R.ok();
    }

    /**
     * 重置密码
     */
    @GetMapping(value = "/resetPassword")
    public R resetPassword(Integer  id){
        XiaofangEntity xiaofang = new XiaofangEntity();
        xiaofang.setPassword("123456");
        xiaofang.setId(id);
        xiaofangService.updateById(xiaofang);
        return R.ok();
    }


    /**
     * 忘记密码
     */
    @IgnoreAuth
    @RequestMapping(value = "/resetPass")
    public R resetPass(String username, HttpServletRequest request) {
        XiaofangEntity xiaofang = xiaofangService.selectOne(new EntityWrapper<XiaofangEntity>().eq("username", username));
        if(xiaofang!=null){
            xiaofang.setPassword("123456");
            boolean b = xiaofangService.updateById(xiaofang);
            if(!b){
               return R.error();
            }
        }else{
           return R.error("账号不存在");
        }
        return R.ok();
    }


    /**
    * 获取用户的session用户信息
    */
    @RequestMapping("/session")
    public R getCurrXiaofang(HttpServletRequest request){
        Integer id = (Integer)request.getSession().getAttribute("userId");
        XiaofangEntity xiaofang = xiaofangService.selectById(id);
        if(xiaofang !=null){
            //entity转view
            XiaofangView view = new XiaofangView();
            BeanUtils.copyProperties( xiaofang , view );//把实体数据重构到view中

            //修改对应字典表字段
            dictionaryService.dictionaryConvert(view, request);
            return R.ok().put("data", view);
        }else {
            return R.error(511,"查不到数据");
        }
    }


    /**
    * 退出
    */
    @GetMapping(value = "logout")
    public R logout(HttpServletRequest request) {
        request.getSession().invalidate();
        return R.ok("退出成功");
    }





}
