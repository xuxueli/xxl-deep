package com.xxl.deep.admin.model.adaptor;

import com.xxl.deep.admin.model.dto.XxlDeepLogDTO;
import com.xxl.deep.admin.model.dto.XxlDeepMessageDTO;
import com.xxl.deep.admin.model.entity.XxlDeepLog;
import com.xxl.deep.admin.model.entity.XxlDeepMessage;
import com.xxl.deep.admin.util.Ip2regionUtil;
import com.xxl.tool.core.CollectionTool;
import com.xxl.tool.core.DateTool;
import com.xxl.tool.response.PageModel;

import java.util.ArrayList;
import java.util.List;

public class XxlDeepMesssageAdaptor {


    public static List<XxlDeepMessageDTO> adaptor(List<XxlDeepMessage> entityList) {

        if (CollectionTool.isEmpty(entityList)) {
            return new ArrayList<>();
        }

        List<XxlDeepMessageDTO> dtoList = new ArrayList<>();
        for (XxlDeepMessage entity : entityList) {
            XxlDeepMessageDTO dto = new XxlDeepMessageDTO();
            dto.setId(entity.getId());
            dto.setCategory(entity.getCategory());
            dto.setTitle(entity.getTitle());
            dto.setContent(entity.getContent());
            dto.setSender(entity.getSender());
            dto.setStatus(entity.getStatus());
            dto.setAddTime(DateTool.formatDateTime(entity.getAddTime()));
            dto.setUpdateTime(DateTool.formatDateTime(entity.getUpdateTime()));

            dtoList.add(dto);
        }

        return dtoList;
    }

}
