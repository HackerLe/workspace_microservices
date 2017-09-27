package com.baowei;

import java.util.List;

import redis.clients.jedis.Jedis;

public class TestString {
	public static void main(String[] args) {
		// ��ȡ���ݿ������
		Jedis jedis = new Jedis("192.168.2.116", 6379);

		// �������
		jedis.set("age", "18");
		jedis.set("name", "zhang");

		// һ�λ�ȡһ������
		String age = jedis.get("age");
		System.out.println(age);

		// һ�λ�ȡ�������
		List<String> lists = jedis.mget("name", "age");
		for (int i = 0; i < lists.size(); i++) {
			String data = lists.get(i);
			System.out.println(data);
		}

		// ��redis���ݿ�ļ�ֵ�����м�1�Ĳ���
		jedis.incr("age");
		String inc_age = jedis.get("age");
		System.out.println(inc_age);

		// ɾ��ĳ����
		jedis.del("age");
		// ��Ϊ����ɾ�������Է�����ֵΪnull
		String del_age = jedis.get("age");
		System.out.println(del_age);

		// ƴ��
		jedis.append("name", " bao bao ");
		String app_name = jedis.get("name");
		System.out.println(app_name);

	}
}
