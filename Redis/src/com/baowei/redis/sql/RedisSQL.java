package com.baowei.redis.sql;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import redis.clients.jedis.Jedis;

import com.baowei.entity.User;
import com.baowei.json.util.GsonUtil;

public class RedisSQL {
	public static void main(String[] args) {

		// ��User������ݷŵ�Redis����,����Ϊkey
		// ��User�������field:id value: json ����һ����¼
		final String SYS_USER_TABLE = "SYS_USER_TABLE";
		// ���ڱ����Ա�Ϊ���Ե�User�û���Redis��Set
		final String SYS_USER_TABLE_SEX_MAN = "SYS_USER_TABLE_SEX_MAN";
		// ���ڱ����Ա�ΪŮ�Ե��û���Redis��Set
		final String SYS_USER_TABLE_SEX_FEMAN = "SYS_USER_TABLE_SEX_FEMAN";
		// ���ڱ���ageΪ25����û���Redis��Set
		final String SYS_USER_TABLE_AGE_25 = "SYS_USER_TABLE_AGE_25";
		// ��ȡ���ݿ������
		Jedis jedis = new Jedis("localhost", 6379);

		// ģ�����ݿ�ı�User������
		List<User> users = new ArrayList<User>();
		for (int i = 0; i < 5; i++) {
			User user = new User();
			user.setId(i);
			user.setName("zhang" + i);
			user.setSex("man");
			user.setAge(20 + i);
			users.add(user);
		}
		for (int i = 5; i < 10; i++) {
			User user = new User();
			user.setId(i);
			user.setName("zhang" + i);
			user.setSex("feman");
			user.setAge(20 + i);
			users.add(user);
		}

		// ͨ��Hash���
		Map<String, String> map = new HashMap<String, String>();
		for (int i = 0; i < users.size(); i++) {
			map.put(users.get(i).getId() + "",
					GsonUtil.object2Json(users.get(i)));

			// ���Ա�Ϊ���Ե�User�û�
			// ���뵽Redis��keyΪSYS_USER_TABLE_SEX_MAN��Set��������
			// ֻ��Ҫ����User�û���id�Ϳ�����
			if ("man".equals(users.get(i).getSex())) {
				jedis.sadd(SYS_USER_TABLE_SEX_MAN, users.get(i).getId() + "");
			}
			// ���Ա�ΪŮ�Ե�User�û�
			// ���뵽Redis��keyΪSYS_USER_TABLE_SEX_FEMAN��Set��������
			// ֻ��Ҫ����User�û���id�Ϳ�����
			if ("feman".equals(users.get(i).getSex())) {
				jedis.sadd(SYS_USER_TABLE_SEX_FEMAN, users.get(i).getId() + "");
			}
			// �����age,birthday�ȣ�ʹ�����Ƶķ���
			// ��Redis���棬�½���һ��Set����������������������User��id��Ϣ
			if ("25".equals(users.get(i).getAge() + "")) {
				jedis.sadd(SYS_USER_TABLE_AGE_25, users.get(i).getId() + "");
			}
		}
		// �鿴ת�����
		// Set<String> keySet = map.keySet();
		// for (String key : keySet) {
		// System.out.println(map.get(key));
		// }

		// ����User������ݵ�Redis��
		// keyΪSYS_USER_TABLE
		// ÿ����¼һ feild :id value:json ����ʽ����
		jedis.hmset(SYS_USER_TABLE, map);

		// ===============================
		// ģ��SQL��where��������ѯ
		// ��ѯ�Ա�ΪŮ��user�û�
		Set<String> sinter = jedis.sinter(SYS_USER_TABLE_SEX_FEMAN);
		for (String key : sinter) {
			// ����id��Redis�е�SYS_USER_TABLE,��ѯ����������user
			String hkey = jedis.hget(SYS_USER_TABLE, key);
			System.out.println(hkey);
			// ����ѯ����json����,ת��ΪUser�û�
			User user = GsonUtil.json2Object(hkey, User.class);
			System.out.println(user);

		}

		// ��ѯ����Ϊ25�꣬�Ա�ΪŮ��user�û�
		Set<String> sinter2 = jedis.sinter(SYS_USER_TABLE_SEX_FEMAN,
				SYS_USER_TABLE_AGE_25);
		for (String key : sinter2) {
			// ����id��Redis�е�SYS_USER_TABLE,��ѯ����������user
			String hkey = jedis.hget(SYS_USER_TABLE, key);
			System.out.println(hkey);
			// ����ѯ����json����,ת��ΪUser�û�
			User user = GsonUtil.json2Object(hkey, User.class);
			System.out.println(user);
		}
	}
}
