package com.itsean.days_matter.Service;

import com.itsean.days_matter.pojo.Matter;
import com.itsean.days_matter.pojo.MatterRequest;

/**
 * matter功能服务接口
 */

public interface MatterService {


    /**
     * 新建matter事件并且存入数据库
     * @param request 前端请求参数（date + isRepeat + name）
     * @return 计算结果（存入数据库）
     */
    Matter initNewMatter(MatterRequest request);


    /**
     * 调出存储的matter事件并且计算与访问日期的间隔天数
     * @return 计算结果（PeriodOut）
     */
    Matter calculateMatter();




}
