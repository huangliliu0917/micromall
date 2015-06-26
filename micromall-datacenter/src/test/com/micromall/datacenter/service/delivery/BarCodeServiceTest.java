package com.micromall.datacenter.service.delivery;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

/**
 * Created by Administrator on 2015/6/26.
 */
@ActiveProfiles("test")
@ContextConfiguration(locations = {"/applicationContext-datacenter.xml", "/produconfig.xml"})
//@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class BarCodeServiceTest {
    @Autowired
    private BarCodeService codeService;

    private int mockCustomerId = 5;

    @Test
    public void testCreateBarCode() throws Exception {
        //codeService.createBarCode(mockCustomerId, 5, 10, 1);
    }

    @Test
    public void testBatchDelete() throws Exception {

    }

    @Test
    public void testFindById() throws Exception {

    }

    @Test
    public void testFindAll() throws Exception {

    }
}