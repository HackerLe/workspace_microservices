package com.baowei;

import java.util.Set;

import redis.clients.jedis.Jedis;

public class TestSet {
	public static void main(String[] args) {
		// ��ȡ���ݿ������
		Jedis jedis = new Jedis("192.168.2.116", 6379);

		// �����ڣ���ɾ�����key���������
		jedis.del("testset");

		// ��Redis��Set�����������
		jedis.sadd("testset", "zhangsan");
		jedis.sadd("testset", "lisi");
		jedis.sadd("testset", "wangwu");
		jedis.sadd("testset", "zhaoliu");

		// ��Redis��Set�����Ƴ�����
		jedis.srem("testset", "zhangsan");

		// ����Redis��Set����ز���
		// ��ȡ���м����Value
		Set<String> set = jedis.smembers("testset");
		for (String str : set) {
			System.out.println(str);
		}
		// �ж�who�ǲ���testlist���ϵ�Ԫ��
		Boolean sismember = jedis.sismember("testset", "who");
		System.out.println(sismember);
		// ����testlist���ϵĸ���
		Long scard = jedis.scard("testset");
		System.out.println(scard);

	}
}
