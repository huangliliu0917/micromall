package com.micromall.datacenter.service.agent;

import com.micromall.datacenter.bean.agent.MallGroupBean;

import java.util.List;

/**
 * Created by allan on 2015/7/21.
 */
public interface MallGroupService {
    /**
     * 保存修改实体
     *
     * @param groupBean
     * @return
     */
    MallGroupBean save(MallGroupBean groupBean);

    /**
     * 删除一个分组
     *
     * @param groupId
     */
    void delete(int groupId);

    /**
     * 获取分组列表
     *
     * @param customerId
     * @return
     */
    List<MallGroupBean> findAll(int customerId);

    /**
     * 根据主键id得到实体
     *
     * @param groupId
     * @return
     */
    MallGroupBean findByGroupId(int groupId);

    /**
     * 是否可以删除
     *
     * @param groupId
     * @return
     */
    boolean deletable(int groupId);
}
