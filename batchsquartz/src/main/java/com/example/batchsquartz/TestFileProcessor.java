package com.example.batchsquartz;

import com.example.batchsquartz.entity.TestReport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

/**
 * @Author: Kayla,Ye
 * @Description:
 * @Date:Created in 3:01 PM 7/26/2018
 */
public class TestFileProcessor implements ItemProcessor<TestReport, TestReport> {

private static final Logger log=LoggerFactory.getLogger(TestFileProcessor.class);

@Override
public TestReport process(final TestReport testReport) throws Exception {
        testReport.setTimeSection(CommonUtils.getTimeSection(0,0, 0));
        log.info("StatisticResult 【" +testReport + "】");
        return testReport;
        }

}
