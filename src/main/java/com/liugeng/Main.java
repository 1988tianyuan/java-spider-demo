package com.liugeng;

import static com.liugeng.common.Constants.*;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.google.common.base.Preconditions;
import com.liugeng.parser.ZhihuSearchImageParser;
import com.liugeng.utils.RegexUtils;
import com.liugeng.utils.ZhihuSpiderUtils;
import com.xuxueli.crawler.XxlCrawler;
import com.xuxueli.crawler.rundata.RunData;
import com.xuxueli.crawler.rundata.strategy.LocalRunData;
import com.xuxueli.crawler.util.FileUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Main {
	
	public static void main(String[] args) throws Exception {
		String fileBasePath = args[1];
		String searchWords = args[0];
		Preconditions.checkNotNull(searchWords, "searchWords should not be null!");
		Map<String, String> searchParams = ZhihuSpiderUtils.buildParams(args[0], 20, 0);
		RunData runData = new LocalRunData();
		ExecutorService threadPool = Executors.newWorkStealingPool(50);
		runData.addUrl(ZHIHU_API_BASE);
		XxlCrawler crawler = new XxlCrawler.Builder()
			.setRunData(runData)
			.setPageParser(new ZhihuSearchImageParser(runData, threadPool, fileBasePath))
			.setParamMap(searchParams)
			.build();
		crawler.start(true);
		threadPool.awaitTermination(Integer.MAX_VALUE, TimeUnit.MILLISECONDS);
		Runtime.getRuntime().addShutdownHook(new Thread(() -> {
			threadPool.shutdown();
			crawler.stop();
		}));
	}
}
