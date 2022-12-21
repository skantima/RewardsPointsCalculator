package com.test.rewards.service;

import com.test.rewards.exceptions.BusinessException;
import com.test.rewards.model.UserPayment;
import com.test.rewards.model.UserPoints;

public interface UserPointsService {

    public UserPoints calculateReward(String userId) throws BusinessException;

    public UserPoints calculateReward(String userId, int months) throws BusinessException;

    public UserPoints calculateReward(String userId, String startDate, String endDate) throws BusinessException;

    public UserPayment addUserTransaction(UserPayment userPayment);
}
