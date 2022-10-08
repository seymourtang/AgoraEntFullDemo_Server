package com.md.service.common;

public interface CommonKey {

    /**
     * 加入rtm状态
     */
    String login_status = "login_status_";

    /**
     * 加入合唱锁
     */
    String on_chorus_lock = "on_chorus_lock_";

    /**
     * 上麦锁
     */
    String on_seat_lock = "on_seat_lock_";

    /**
     * 下麦锁
     */
    String out_seat_lock = "on_seat_lock_";

    /**
     * 验证码发送次数
     */
    String verificationSendCodeTimes = "verification_send_code_times:";

    /**
     * 验证码验证次数
     */
    String verificationCheckCodeTimes = "verification_check_code_times:";

    /**
     * 5分钟验证锁
     */
    String verificationCheckCodeTimesLock = "verification_check_code_times_lock:";

}
