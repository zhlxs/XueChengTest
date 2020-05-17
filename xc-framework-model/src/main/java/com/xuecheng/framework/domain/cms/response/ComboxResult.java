package com.xuecheng.framework.domain.cms.response;

import com.xuecheng.framework.model.response.ResponseResult;
import com.xuecheng.framework.model.response.ResultCode;
import lombok.Data;

import java.util.List;
@Data
public class ComboxResult extends ResponseResult {

    List<Combox> comboxList;

    //操作代码
    int code = SUCCESS_CODE;

    //提示信息
    String message;

    public ComboxResult(){

    }

    public ComboxResult(int code, String message){
        super(code,message);
    }

    public ComboxResult(ResultCode resultCode, List<Combox> comboxList) {
        super(resultCode);
        this.comboxList = comboxList;
    }
}
