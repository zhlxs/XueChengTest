package com.xuecheng.framework.exception;

import com.google.common.collect.ImmutableMap;
import com.xuecheng.framework.model.response.CommonCode;
import com.xuecheng.framework.model.response.ResponseResult;
import com.xuecheng.framework.model.response.ResultCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice//控制器增强
public class ExceptionCatch
{
	private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionCatch.class);

	private static ImmutableMap<Class<? extends Throwable>, ResultCode> exceptions;
	private static ImmutableMap.Builder<Class<? extends Throwable>, ResultCode> builder = ImmutableMap.builder();

	//捕获此类异常
	@ExceptionHandler(CustomException.class)
	@ResponseBody//返回json格式的数据
	public ResponseResult customException(CustomException customException)
	{
		//记录日志
		LOGGER.error("Catch CustomException:{}", customException.getMessage());
		ResultCode resultCode = customException.getResultCode();
		return new ResponseResult(resultCode);
	}

	/**
	 * 不可预知的异常
	 *
	 * @param ex
	 * @return
	 */
	@ExceptionHandler(Exception.class)
	@ResponseBody//返回json格式的数据
	public ResponseResult exception(Exception ex)
	{
		//记录日志
		LOGGER.error("Catch Exception:{}", ex.getMessage());
		if (exceptions == null)
		{
			exceptions = builder.build();
		}
		ResultCode resultCode = exceptions.get(ex.getClass());
		if (resultCode != null)
		{
			return new ResponseResult(resultCode);
		}
		return new ResponseResult(CommonCode.SERVER_ERROR);
	}

	static
	{
		//定义异常类型所对应的错误代码
		builder.put(HttpMessageNotReadableException.class, CommonCode.INVALID_PARAM);
	}

}
