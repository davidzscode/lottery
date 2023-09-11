package com.qihang.common.util.area;

import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.model.CityResponse;
import com.maxmind.geoip2.record.City;
import com.maxmind.geoip2.record.Country;
import com.maxmind.geoip2.record.Subdivision;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.io.File;
import java.net.InetAddress;

/**
 * @author: qihang
 * @description: 地区
 * @time: 2023-04-12 11:36
 */
@Component
public class AreaUtil {
    public String getIp() {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        String ip = servletRequestAttributes.getRequest().getHeader("x-access-ip");
        return ip;
    }

    public String getAreaByIp(String ip) {
        try {
            // 创建 GeoLite2 数据库
            File database = new File(this.getClass().getClassLoader().getResource("GeoLite2-City.mmdb").getPath());
            // 读取数据库内容
            DatabaseReader reader = new DatabaseReader.Builder(database).build();
            InetAddress ipAddress = InetAddress.getByName(ip);

            // 获取查询结果
            CityResponse response = reader.city(ipAddress);

            // 获取国家信息
            Country country = response.getCountry();

            // 获取省份
            Subdivision subdivision = response.getMostSpecificSubdivision();
            // 获取城市
            City city = response.getCity();
            return country.getNames().get("zh-CN") + " " + subdivision.getNames().get("zh-CN") + " " + city.getNames().get("zh-CN");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
