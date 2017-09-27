package com.baowei;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import redis.clients.jedis.Jedis;

public class TestHashMap {
	public static void main(String[] args) {

		// ��ȡ���ݿ������
		Jedis jedis = new Jedis("192.168.2.116", 6379);

		Map<String, String> map = new HashMap<String, String>();
		map.put("name", "zhangsan");
		map.put("age", "18");
		map.put("qq", "123456");

		// ������ݵ�Redis��Hash
		jedis.hmset("user", map);

		// ��ȡmap�ֶε�ĳ�����Ե�����
		String name = jedis.hget("user", "name");
		System.out.println(name);

		// ��ȡmap�Ķ�����Ե�����
		List<String> user = jedis.hmget("user", "name", "age");
		System.out.println(user);

		// ��ȡHash�ļ�ֵ�����е������Լ�����
		Iterator<String> iterator = jedis.hkeys("user").iterator();
		while (iterator.hasNext()) {
			String key = iterator.next();
			System.out.println(key + ":" + jedis.hget("user", key));
		}

		// �ж�һ��Hash�Ƿ����һ�� field ������
		boolean contains = jedis.hkeys("user").contains("name");
		System.out.println(contains);
	}
}
