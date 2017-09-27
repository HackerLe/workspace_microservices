package com.baowei;

import java.util.List;

import redis.clients.jedis.Jedis;

public class TestList {
	public static void main(String[] args) {
		// ��ȡ���ݿ������
		Jedis jedis = new Jedis("192.168.2.116", 6379);
		// �����ڣ���ɾ�����key���������
		jedis.del("testlist");
		// ��keyΪtestlist��Redis��List����,�������
		// ע��lpush �� rpush������
		// ���Ե�����ʹ��
		// lpush
		jedis.lpush("testlist", "zhangsan");
		jedis.lpush("testlist", "lisi");
		jedis.lpush("testlist", "wangwu");
		// rpush
		jedis.rpush("testlist", "zhaoliu");

		// ��ȡkeyΪtestlist�ĳ���
		System.out.println(jedis.llen("testlist"));
		// ��ȡkeyΪtestlist������
		List<String> lists = jedis.lrange("testlist", 0, -1);
		for (int i = 0; i < lists.size(); i++) {
			System.out.println(lists.get(i));
		}
	}
}
