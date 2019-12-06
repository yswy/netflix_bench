package com.zuoer.netflix.bench.core.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zuoer.netflix.bench.base.utils.ListUtils;
import com.zuoer.netflix.bench.base.utils.StringUtils;
import com.zuoer.netflix.bench.core.model.enums.ErrorCodeEnum;
import com.zuoer.netflix.bench.core.model.request.AdminUserPasswordModifyRequest;
import com.zuoer.netflix.bench.core.model.result.AdminUserOperateResult;
import com.zuoer.netflix.bench.core.service.AdminUserManageComponent;
import com.zuoer.netflix.bench.dal.entity.AdminUserDO;
import com.zuoer.netflix.bench.dal.mapper.AdminUserMapper;

/**
 * @author zuoer
 * @version $Id: AdminUserManageComponentImpl.java, v 0.1 2019/12/2 11:28 zuoer Exp $$
 */
@Component
public class AdminUserManageComponentImpl implements AdminUserManageComponent {
	
	@Autowired
	private AdminUserMapper adminUserMapper;

    @Override
    public AdminUserOperateResult deleteByIds(List<Integer> ids) {
    	AdminUserOperateResult result=new AdminUserOperateResult();
    	result.setSuccess(true);
    	if(ListUtils.isEmpty(ids)) {
    		return result;
    	}
    	adminUserMapper.deleteByIds(ids);
        return result;
    }

    @Override
    public AdminUserOperateResult modifyPassword(AdminUserPasswordModifyRequest request) {
    	AdminUserOperateResult result=new AdminUserOperateResult();
    	result.setSuccess(true);
    	//先校验原密码是否正确
    	AdminUserDO adminUserDO=adminUserMapper.selectById(request.getId());
    	if(adminUserDO==null) {
    		result.setSuccess(false);
    		result.setError(ErrorCodeEnum.ADMIN_USER_NOT_FOUND.errorCode());
    		return result;
    	}
    	if(StringUtils.notEquals(request.getOriPwd(), adminUserDO.getPassword())) {
    		result.setSuccess(false);
    		result.setError(ErrorCodeEnum.PASSWORD_ERROR.errorCode());
    		return result;
    	}
    	
    	if(StringUtils.notEquals(request.getNewPwd(), request.getConfirmPwd())) {
    		result.setSuccess(false);
    		result.setError(ErrorCodeEnum.NEW_PASSWORD_NOT_SAME.errorCode());
    		return result;
    	}
    	adminUserDO.setPassword(request.getNewPwd());
    	adminUserMapper.updateById(adminUserDO);
        return null;
    }
}
