package com.boco.njjx.commconfig;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@Data
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement(name = "devece")
@XmlAccessorType(XmlAccessType.FIELD)
public class DevcommInfo {
    @XmlAttribute(name = "commid")   //设备通讯编码
    private String commid;
    @XmlAttribute(name = "devid")   //设备编码
    private String devid;
    @XmlAttribute(name = "devip") //设备通讯ip信息
    private String devip;
    @XmlAttribute(name = "devport") //设备通讯ip信息
    private String devport;
    @XmlAttribute(name = "devaddr") //设备地址
    private String devaddr;
}
