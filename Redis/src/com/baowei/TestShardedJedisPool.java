package com.baowei;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedisPool;

/**
 * ����һ���Թ�ϣ�㷨��ʵ��Redis�ļ�Ⱥ
 */
public class TestShardedJedisPool {
	public static void main(String[] args) {

		// ���� �ڱ� ʹ�� share
		// ����һ���Թ�ϣ�㷨��ʵ��Redis�ļ�Ⱥ
		// ��Ⱥ����Ϣ,һ�������̨��Ϣ
		List<JedisShardInfo> shards = Arrays.asList(new JedisShardInfo(
				"192.168.2.116", 6379));
		// ���ӳ���Ϣ
		GenericObjectPoolConfig goConfig = new GenericObjectPoolConfig();
		goConfig.setMaxTotal(100);
		goConfig.setMaxIdle(20);
		goConfig.setMaxWaitMillis(-1);
		goConfig.setTestOnBorrow(true);

		// ����ShardedJedisPool
		ShardedJedisPool pool = new ShardedJedisPool(goConfig, shards);

		// ʹ��ShardedJedisPool��������
		pool.getResource().set("haha", "haha");

		// �ͷ���Դ
		pool.close();

	}
}
