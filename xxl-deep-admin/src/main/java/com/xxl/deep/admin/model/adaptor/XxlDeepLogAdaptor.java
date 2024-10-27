package com.xxl.deep.admin.model.adaptor;

import com.xxl.deep.admin.model.dto.XxlDeepLogDTO;
import com.xxl.deep.admin.model.entity.XxlDeepLog;
import com.xxl.deep.admin.util.Ip2regionUtil;
import com.xxl.tool.core.DateTool;

import java.util.ArrayList;
import java.util.List;

public class XxlDeepLogAdaptor {


    public static List<XxlDeepLogDTO> adaptor(List<XxlDeepLog> pageList) {
        List<XxlDeepLogDTO> dtoList = new ArrayList<XxlDeepLogDTO>();
        for (XxlDeepLog xxlDeepLog : pageList) {
            // adaptor
            XxlDeepLogDTO dto = new XxlDeepLogDTO();
            dto.setId(xxlDeepLog.getId());
            dto.setType(xxlDeepLog.getType());
            dto.setModule(xxlDeepLog.getModule());
            dto.setTitle(xxlDeepLog.getTitle());
            dto.setContent(xxlDeepLog.getContent());
            dto.setOperator(xxlDeepLog.getOperator());
            dto.setIp(xxlDeepLog.getIp());
            dto.setAddTime(DateTool.formatDateTime(xxlDeepLog.getAddTime()));
            // ip
            Ip2regionUtil.CityInfo cityInfo = Ip2regionUtil.getCityInfo(xxlDeepLog.getIp());
            if (cityInfo != null) {
                String temp = String.format("%s%s%s", cityInfo.getCountry(), cityInfo.getProvince(), cityInfo.getCity());
                dto.setIpAddress(temp);
            }

            // collect
            dtoList.add(dto);
        }
        return dtoList;
    }

}
