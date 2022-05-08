package com.learn.caching;

import org.ehcache.event.CacheEvent;
import org.ehcache.event.CacheEventListener;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class CacheEventLogger implements CacheEventListener<Object, Object> {
	@Override
	public void onEvent(CacheEvent<? extends Object, ? extends Object> cacheEvent) {
		log.info("Cache event {} for item with key {}. Old value = {}, New value = {}",
				StringUtils.capitalize(cacheEvent.getType().toString()),
				cacheEvent.getKey(), cacheEvent.getOldValue(), cacheEvent.getNewValue());
	}
}
