package com.sunrisejay.lifestream.auth.alarm.impl;

import com.sunrisejay.lifestream.auth.alarm.AlarmInterface;
import lombok.extern.slf4j.Slf4j;

/**
 * @description:
 * @author: Sunrise_Jay
 * @email: sunrise_jay@yeah.net
 * @date: 2026/3/24 11:36
 */
@Slf4j
public class ConsoleAlarmHelper implements AlarmInterface {
    @Override
    public boolean send(String message) {
        log.error("  ==>控制台告警："+message);
        return true;
    }
}
