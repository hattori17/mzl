package com.mzl.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mzl.po.UserInfo;

@Controller
public class testAction extends BaseController
{
    private static final Log LOGGER = LogFactory.getLog(testAction.class);

    @RequestMapping(value = "test", produces = "application/json; charset=utf-8")
    public @ResponseBody String updateUser(
    		@RequestParam(value = "userId") String userId, 
    		//@RequestBody UserInfo userInfo,
    		HttpServletRequest request)
    {
    	LOGGER.debug(String.format("enter function, %s", userId));

        return buildSuccessResultInfo(userId);
    }

}
