package com.gdqt.dao;

import com.gdqt.entity.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * 名称:数据访问层（接口）
 * 作用:
 * 与数据打交道，可以是数据库操作，也可以是文件读写操作，甚至是redis缓存操作，总之与数据操作有关的都放在这里，
 * 也有人叫做dal或者数据持久层都差不多意思。为什么没有daoImpl，因为我们用的是mybatis，所以可以直接在配置文
 * 件中实现接口的每个方法。
 */
public interface UserDao {
    User findUserByEmail(@Param("email") String email);


    @Select("SELECT * FROM tbl_user WHERE id = #{id}")
    User findUserById(int id);

    User findUserByName(@Param("username")String username);

    List<Map<String,Object>> getUser();
}
