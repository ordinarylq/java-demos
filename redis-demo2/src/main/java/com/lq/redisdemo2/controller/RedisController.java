package com.lq.redisdemo2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisZSetCommands;
import org.springframework.data.redis.core.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;

import java.util.*;

/**
 * @author qili
 * @create 2022-07-04-20:37
 */
@RestController
public class RedisController {
    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @GetMapping("/string-hash")
    public Map<String, Object> testStringAndHash() {
        redisTemplate.opsForValue().set("key1", "value1");
        redisTemplate.opsForValue().set("int_key", "1");

        stringRedisTemplate.opsForValue().set("int", "1");
        // 1.使用运算
        stringRedisTemplate.opsForValue().increment("int", 3L);
        stringRedisTemplate.opsForValue().decrement("int", 4L);

        // 2.获取底层jedis连接
        Jedis jedis = (Jedis) stringRedisTemplate.getConnectionFactory().
                getConnection().getNativeConnection();

        // 减一操作，该命令RedisTemplate不支持
        jedis.decr("int");

        // 3.存入散列数据
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("field1", "value1");
        hashMap.put("field2", "value2");
        // 存入散列数据类型
        stringRedisTemplate.opsForHash().putAll("hash", hashMap);

        // 4. 绑定散列操作的key, 这样可以连续对同一个散列数据进行操作
        BoundHashOperations<String, Object, Object> hashOps = stringRedisTemplate.boundHashOps("hash");
        // 删除两个字段
        hashOps.delete("field1", "field2");
        hashOps.put("field3", "value3");


        HashMap<String, Object> map = new HashMap<>();
        map.put("success", true);
        return map;
    }

    @GetMapping("/list")
    public Map<String, Object> testList() {
        // 列表底层是双向链表
        // 插入两个列表
        // 列表左到右：v3 v2 v1
        stringRedisTemplate.opsForList().leftPushAll("list1", "v1", "v2", "v3");
        // 列表左到右：v1 v2 v3
        stringRedisTemplate.opsForList().rightPushAll("list2", "v1", "v2", "v3");

        BoundListOperations<String, String> listOps = stringRedisTemplate.boundListOps("list2");
        String result1 = listOps.rightPop(); // v3
        System.out.println("result1=" + result1);
        // 获取索引为1的元素，redis列表索引从0开始
        String result2 = listOps.index(1L); // v2
        System.out.println("result2=" + result2);

        listOps.leftPush("v0");
        Long size = listOps.size(); // 3 v0 v1 v2

        List<String> strList = listOps.range(0, size - 2); // list范围是[0, size-1],此时取的是[0, size-2]即v0 v1
        System.out.println("strList=" + strList);

        HashMap<String, Object> map = new HashMap<>();
        map.put("success", true);
        return map;
    }

    @GetMapping("/set")
    public Map<String, Object> testSet() {
        stringRedisTemplate.opsForSet().add("set1", "v1", "v2", "v3", "v4", "v4");
        stringRedisTemplate.opsForSet().add("set2", "v2", "v4", "v6", "v8");

        BoundSetOperations<String, String> setOps = stringRedisTemplate.boundSetOps("set1");

        setOps.add("v6", "v7");
        setOps.remove("v1", "v7");
        Set<String> set1 = setOps.members();
        System.out.println("set1=" + set1); // v2 v3 v4 v6

        Long size = setOps.size();
        System.out.println("size=" + size); // 4

        // set1= v2 v3 v4 v6
        // set2= v2 v4 v6 v8
        setOps.intersectAndStore("set2", "inter"); // v2 v4 v6
        setOps.diffAndStore("set2", "diff"); // 在set1而不在set2中 v3
        setOps.unionAndStore("set2", "union"); // v2 v3 v4 v6 v8

        HashMap<String, Object> map = new HashMap<>();
        map.put("success", true);
        return map;
    }

    @GetMapping("/zset")
    public Map<String, Object> testZset() {
        // Spring提供了TypedTuple接口，其默认实现DefaultTypedTuple
        HashSet<ZSetOperations.TypedTuple<String>> typedTupleSet = new HashSet<>();

        for (int i = 0; i < 10; i++) {
            double score = i * 0.1;
            DefaultTypedTuple<String> typedTuple = new DefaultTypedTuple<>("value" + i, score);
            typedTupleSet.add(typedTuple);
        }

        // 向有序集合插入元素
        stringRedisTemplate.opsForZSet().add("zset1", typedTupleSet);

        // 绑定zset1操作
        BoundZSetOperations<String, String> zsetOps = stringRedisTemplate.boundZSetOps("zset1");


        // 增加一个元素
        zsetOps.add("value10", 0.26);

        // 获取[1,6]范围内的集合
        Set<String> setRange = zsetOps.range(1, 6); // 下标在[1,6]
        System.out.println("range[1,6]=" + setRange); // value1, value2, value10, value3, value4, value5

        Set<String> setScore = zsetOps.rangeByScore(0.2, 0.6);
        System.out.println("score[0.2, 0.6]=" + setScore); // value2, value10, value3, value4, value5 [, value6] value6不包括在内！原因在于double的精度

        RedisZSetCommands.Range range = new RedisZSetCommands.Range();
        range.gt("value3");
        range.lt("value8");
        Set<String> rangeSet = zsetOps.rangeByLex(range);
        System.out.println("rangeSet[value3, value8]=" + rangeSet); // value4~value7

        // 删除元素
        zsetOps.remove("value9", "value2");

        Double value8 = zsetOps.score("value8");
        System.out.println("value8=" + value8); //0.8

        Set<ZSetOperations.TypedTuple<String>> rangeSet2 = zsetOps.rangeWithScores(1, 6);
        System.out.println("rangeSet2=" + rangeSet2);

        Set<ZSetOperations.TypedTuple<String>> scoreSet = zsetOps.rangeByScoreWithScores(1, 6);
        System.out.println("scoreSet=" + scoreSet);

        Set<String> reverseSet = zsetOps.reverseRange(2, 8);
        System.out.println("reverseSet=" + reverseSet);

        HashMap<String, Object> map = new HashMap<>();
        map.put("success", true);
        return map;

    }

}
