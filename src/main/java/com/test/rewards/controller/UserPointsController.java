package com.test.rewards.controller;

import com.test.rewards.exceptions.BusinessException;
import com.test.rewards.model.UserPayment;
import com.test.rewards.model.UserPoints;
import com.test.rewards.service.UserPointsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/points")
public class UserPointsController {

    @Autowired
    private UserPointsService userPointsService;

    /*@GetMapping("/{userId}")
    public ResponseEntity<UserPoints> fetchUserPoints(@PathVariable("userId") String userId) {
        if (StringUtils.isEmpty(userId)) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        UserPoints userPoints = null;
        try {
             userPoints = userPointsService.calculateReward(userId);
        } catch (BusinessException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(userPoints, HttpStatus.OK);
    }*/

    @GetMapping("/{userId}/last/{months}")
    public ResponseEntity<UserPoints> fetchUserPointsForLastNMonths(@PathVariable("userId") String userId,
                                                                    @PathVariable("months") Integer months) {
        if (StringUtils.isEmpty(userId)) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        UserPoints userPoints = null;
        try {
            userPoints = userPointsService.calculateReward(userId, months);
        } catch (BusinessException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(userPoints, HttpStatus.OK);
    }

    @GetMapping("/{userId}/startDate/{startDate}/endDate/{endDate}")
    public ResponseEntity<UserPoints> fetchUserPointsForGivenDates(@PathVariable("userId") String userId,
                                                                    @PathVariable("startDate") String startDate,
                                                                   @PathVariable("endDate") String endDate
                                                                   ) {
        if (StringUtils.isEmpty(userId)) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        UserPoints userPoints = null;
        try {
            userPoints = userPointsService.calculateReward(userId, startDate, endDate);
        } catch (BusinessException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(userPoints, HttpStatus.OK);
    }


    @PostMapping()
    public ResponseEntity<UserPayment> addUserTransaction(@RequestBody UserPayment userPayment) {
        // not adding validations intentionally here.
        userPointsService.addUserTransaction(userPayment);
        return new ResponseEntity<UserPayment>(userPayment, HttpStatus.OK);
    }
}
