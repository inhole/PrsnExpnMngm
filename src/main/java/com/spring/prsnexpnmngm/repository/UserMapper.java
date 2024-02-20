package com.spring.prsnexpnmngm.repository;

import com.spring.prsnexpnmngm.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

    @Select("SELECT * FROM USER WHERE ID = '${id}'")
    User selectUserId(@Param("id") String id);

    @Insert(
        "INSERT INTO USER (" +
            "ID" +
            ", PW" +
            ", NAME" +
            ", EMAIL" +
        ") VALUES (" +
            "'${id}'" +
            ", '${pw}'" +
            ", '${name}'" +
            ", '${email}'" +
        ")"
    )
    int insertUserId(User user);
}
