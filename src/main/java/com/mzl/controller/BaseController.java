/**
 * 
 */
package com.mzl.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 * 项目名称：forum
 * 
 * @description: 基础controller
 * 
 * @author hattori17
 * 
 * @create_time：2014年10月8日 下午4:08:06
 * 
 * @version V1.0.0
 * 
 */
public class BaseController
{
    private static final Log LOGGER = LogFactory.getLog(BaseController.class);

    SerializerFeature[] feature =
    { SerializerFeature.DisableCircularReferenceDetect, SerializerFeature.WriteNullListAsEmpty,
            SerializerFeature.WriteNullStringAsEmpty, SerializerFeature.WriteNullBooleanAsFalse,
            SerializerFeature.WriteMapNullValue };

    /**
     * @description: 构造返回结果
     * @author: hattori17
     * @param resultData
     *            : 需要返回的数据，可选
     * @return
     */
    protected String buildResultInfo(Object resultData)
    {
        LOGGER.debug(String.format("enter function, %s", resultData));
        return JSON.toJSONString(resultData, feature);
    }


}
