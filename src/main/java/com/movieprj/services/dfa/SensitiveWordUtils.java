package com.movieprj.services.dfa;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.movieprj.beans.Keyword;
import com.movieprj.mapper.SensitiveWordMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 敏感词过滤工具类
 *
 * @author AlanLee
 */
@Service
public class SensitiveWordUtils {

    /**
     * 敏感词库
     */


    public static Map sensitiveWordMap;

    public static Map getSensitiveWordMap() {
        return sensitiveWordMap;
    }

    @Resource
    public SensitiveWordMapper sensitiveWordMapper;

    @Autowired
    public void setSensitiveWordMap() {//在此处初始化sensitiveWordMap
        SensitiveWordInit sensitiveWordInit = new SensitiveWordInit();
        sensitiveWordMap = sensitiveWordInit.initKeyWord(sensitiveWordMapper.getKeyword());
    }

    /**
     * 只过滤最小敏感词
     */
    public static int minMatchTYpe = 1;

    /**
     * 过滤所有敏感词
     */
    public static int maxMatchType = 2;


    /**
     * 敏感词库敏感词数量
     *
     * @return
     */

    public static int getWordSize() {
        if (SensitiveWordUtils.sensitiveWordMap == null) {
            return 0;
        }
        return SensitiveWordUtils.sensitiveWordMap.size();
    }

    /**
     * 是否包含敏感词
     */
    public static boolean isContaintSensitiveWord(String txt, int matchType) {
        boolean flag = false;
        for (int i = 0; i < txt.length(); i++) {
            int matchFlag = checkSensitiveWord(txt, i, matchType);
            if (matchFlag > 0) {
                flag = true;
            }
        }
        return flag;
    }

    /**
     * 获取敏感词内容
     *
     * @param txt
     * @param matchType
     * @return 敏感词内容
     */
    public static Set<String> getSensitiveWord(String txt, int matchType) {
        Set<String> sensitiveWordList = new HashSet<String>();

        for (int i = 0; i < txt.length(); i++) {
            int length = checkSensitiveWord(txt, i, matchType);
            if (length > 0) {
                // 将检测出的敏感词保存到集合中
                sensitiveWordList.add(txt.substring(i, i + length));
                i = i + length - 1;
            }
        }

        return sensitiveWordList;
    }

    /**
     * 替换敏感词
     */
    public static String replaceSensitiveWord(String txt, int matchType, String replaceChar) {
        String resultTxt = txt;
        Set<String> set = getSensitiveWord(txt, matchType);
        Iterator<String> iterator = set.iterator();
        String word = null;
        String replaceString = null;
        while (iterator.hasNext()) {
            word = iterator.next();
            replaceString = getReplaceChars(replaceChar, word.length());
            resultTxt = resultTxt.replaceAll(word, replaceString);
        }

        return resultTxt;
    }

    /**
     * 替换敏感词内容
     */
    private static String getReplaceChars(String replaceChar, int length) {
        String resultReplace = replaceChar;
        for (int i = 1; i < length; i++) {
            resultReplace += replaceChar;
        }

        return resultReplace;
    }

    /**
     * 检查敏感词数量
     */
    public static int checkSensitiveWord(String txt, int beginIndex, int matchType) {
        boolean flag = false;
        // 记录敏感词数量
        int matchFlag = 0;
        char word = 0;
        Map nowMap = SensitiveWordUtils.sensitiveWordMap;
        for (int i = beginIndex; i < txt.length(); i++) {
            word = txt.charAt(i);
            // 判断该字是否存在于敏感词库中
            nowMap = (Map) nowMap.get(word);
            if (nowMap != null) {
                matchFlag++;
                // 判断是否是敏感词的结尾字，如果是结尾字则判断是否继续检测
                if ("1".equals(nowMap.get("isEnd"))) {
                    flag = true;
                    // 判断过滤类型，如果是小过滤则跳出循环，否则继续循环
                    if (SensitiveWordUtils.minMatchTYpe == matchType) {
                        break;
                    }
                }
            } else {
                break;
            }
        }
        if (!flag) {
            matchFlag = 0;
        }
        return matchFlag;
    }

    /**
     * 敏感词汇对应个数
     * 返回 "关键字"="关键字个数"
     */
    public static Map getSensitiveWordSum(String txt, int matchType) {
        Map<String, Integer> map = new HashMap<String, Integer>();
        for (int i = 0; i < txt.length(); i++) {
            int length = checkSensitiveWord(txt, i, matchType);
            if (length > 0) {
                // 将检测出的敏感词保存到集合中
                String str = txt.substring(i, i + length);
                if (map.containsKey(str)) {
                    map.put(str, map.get(str).intValue() + 1);
                } else {
                    map.put(str, new Integer(1));
                }
                //System.out.println(txt.substring(i, i + length));
                i = i + length - 1;
            }
        }
        return map;
    }

    /**
     * 对map数组value排序，并取前10
     * this method will always sort the map;
     * isCondition is true condition can be used otherwise invalid
     *
     * @param unsortMap
     * @return
     */
    public static Map<String, Integer> sortByValue(Map<String, Integer> unsortMap, int condition, boolean isCondition) {

        // 1. Convert Map to List of Map
        List<Map.Entry<String, Integer>> list =
                new LinkedList<Map.Entry<String, Integer>>(unsortMap.entrySet());

        // 2. Sort list with Collections.sort(), provide a custom Comparator
        //    Try switch the o1 o2 position for a different order
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            public int compare(Map.Entry<String, Integer> o1,
                               Map.Entry<String, Integer> o2) {
                return (o2.getValue()).compareTo(o1.getValue());
            }
        });

        // 3. Loop the sorted list and put it into a new insertion order Map LinkedHashMap
        Map<String, Integer> sortedMap = new LinkedHashMap<String, Integer>();
        if (isCondition) {
            for (int i = 0; i < list.size(); i++) {
                if (i < condition) {
                    sortedMap.put(list.get(i).getKey(), list.get(i).getValue());
                }
            }
        } else {
            for (int i = 0; i < list.size(); i++) {
                sortedMap.put(list.get(i).getKey(), list.get(i).getValue());
            }
        }
        return sortedMap;
    }

}
