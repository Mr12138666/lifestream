package com.sunrisejay.lifestream.user.relation.biz.service;

import com.sunrisejay.framework.common.response.Response;
import com.sunrisejay.lifestream.user.relation.biz.model.vo.FollowUserReqVO;

public interface RelationService {

    /**
     * 关注用户
     * @param followUserReqVO
     * @return
     */
    Response<?> follow(FollowUserReqVO followUserReqVO);

}