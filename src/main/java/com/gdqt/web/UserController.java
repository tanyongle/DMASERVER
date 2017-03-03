package com.gdqt.web;


import com.alibaba.fastjson.JSONObject;
//import com.avos.avoscloud.AVOSCloud;
import com.gdqt.entity.User;
import com.gdqt.service.impl.UserServiceImpl;
import com.gdqt.turing.TuringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@Controller
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    private UserServiceImpl service;


    @RequestMapping(value = "/helpme")
    public String helpme() {
//        User user = service.findUserByEmail("terrence.tan@vgcs.me");
//        System.out.println(user.getUsername() + "," + user.getEmail());
        return "/helpme";
    }

    //@CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/getUserByEmail")
    public User getUser(@RequestParam String email) {
        System.err.println("传的参数是:" + "" + email);
        return service.findUserByEmail(email);
    }

    @CrossOrigin(origins = "http://localhost:63343")
    @ResponseBody
    @RequestMapping(value = "/getUserByName")
    public User getUserByCName(@RequestParam String cname) {
        return service.findUserByName(cname);
    }

    @CrossOrigin(origins = "http://localhost:63343")
    @ResponseBody
    @RequestMapping(value = "/callRobot")
    public JSONObject callRobot(@RequestParam String cmd) {
        return TuringUtil.AskRobot(cmd);
    }

    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/checkUser")
    public Map<String, Object> checkUser(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> map = new HashMap<String, Object>();
        System.out.println(request.getParameterMap());
        map.put("result", TuringUtil.AskRobot("你叫什么名字？"));
        return map;
    }


    /**
     * 登陆验证
     *
     * @param username
     * @param password
     * @param code
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/login")
    public Map<String, Object> login(@RequestParam String username, @RequestParam String password, @RequestParam String code,
                                     HttpServletRequest request, HttpServletResponse response) {
        String clientIp = getIpAddress(request);
        System.err.println("登录IP:" + clientIp);
        return null;
    }

    /**
     * 获取验证码
     *
     * @return code:1234
     */
    @ResponseBody
    @RequestMapping(value = "/getCode")
    public Map<String, Object> getCode() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("code", 1234);
        //AVOSCloudMain.initCloud();
        return map;
    }


    public static String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
            if (ip.equals("127.0.0.1")) {
                //根据网卡取本机配置的IP
                InetAddress inet = null;
                try {
                    inet = InetAddress.getLocalHost();
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }
                ip = inet.getHostAddress();
            }
        }
        // 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
        if (ip != null && ip.length() > 15) {
            if (ip.indexOf(",") > 0) {
                ip = ip.substring(0, ip.indexOf(","));
            }
        }
        return ip;
    }

    @ResponseBody
    @RequestMapping(value = "getSession")
    public Map<String, Object> getSession(HttpSession session) {


        System.out.println(session);
        return null;
    }


    @ResponseBody
    @RequestMapping(value = "getData")
    public Map<String, Object> getData() {
        Map<String, Object> result = new HashMap<String, Object>();

//        Map<String, Object> map = new HashMap<String, Object>();
//        map.put("name", "Jean Luc");
//        map.put("email", "worf.moghsson@enterprise.com");
//        map.put("phone", "555-111-1111");
//
//        Map<String, Object> map1 = new HashMap<String, Object>();
//        map1.put("name", "Worf1111");
//        map1.put("email", "worf.moghsson@enterprise.com");
//        map1.put("phone", "555-222-2222");
//
//        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
//        list.add(map);
//        list.add(map1);
//        result.put("items", list);
//        /*数据库查询语句*/
//        select t1.id,t1.username,t2.name,t2.address,t1.status from tbl_user t1,tbl_check_point t2 where t1.cpid = t2.id;
        result.put("items", service.getUser());
        System.out.println("run");
        return result;
    }


}
