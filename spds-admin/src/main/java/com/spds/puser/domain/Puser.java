package com.spds.puser.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.spds.common.annotation.Excel;
import com.spds.common.core.domain.BaseEntity;

/**
 * 用户管理对象 puser
 * 
 * @author hlp
 * @date 2024-12-25
 */
public class Puser extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 头像 */
    @Excel(name = "头像")
    private String image;

    /** 用户名 */
    @Excel(name = "用户名")
    private String username;

    /** 年龄 */
    @Excel(name = "年龄")
    private Long age;

    /** 性别 */
    @Excel(name = "性别")
    private Long sex;

    /** 手机号码 */
    @Excel(name = "手机号码")
    private String phone;

    /** 地址 */
    @Excel(name = "地址")
    private String address;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setImage(String image) 
    {
        this.image = image;
    }

    public String getImage() 
    {
        return image;
    }
    public void setUsername(String username) 
    {
        this.username = username;
    }

    public String getUsername() 
    {
        return username;
    }
    public void setAge(Long age) 
    {
        this.age = age;
    }

    public Long getAge() 
    {
        return age;
    }
    public void setSex(Long sex) 
    {
        this.sex = sex;
    }

    public Long getSex() 
    {
        return sex;
    }
    public void setPhone(String phone) 
    {
        this.phone = phone;
    }

    public String getPhone() 
    {
        return phone;
    }
    public void setAddress(String address) 
    {
        this.address = address;
    }

    public String getAddress() 
    {
        return address;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("image", getImage())
            .append("username", getUsername())
            .append("age", getAge())
            .append("sex", getSex())
            .append("phone", getPhone())
            .append("address", getAddress())
            .toString();
    }
}
