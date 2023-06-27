package com.tecn.market.qts.util;

import com.google.gson.*;
import com.google.gson.internal.LinkedTreeMap;
import com.google.gson.reflect.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 包含操作 {@code JSON} 数据的常用方法的工具类。
 * <p/>
 * 该工具类使用的 {@code JSON} 转换引擎是 <a href="http://code.google.com/p/google-gson/" mce_href="http://code.google.com/p/google-gson/"
 * target="_blank"> {@code Google Gson}</a>。 下面是工具类的使用案例：
 *
 * <pre>
 * public class User {
 *     @SerializedName("pwd")
 *     private String password;
 *     @Expose
 *     @SerializedName("uname")
 *     private String username;
 *     @Expose
 *     @Since(1.1)
 *     private String gender;
 *     @Expose
 *     @Since(1.0)
 *     private String sex;
 *
 *     public User() {}
 *     public User(String username, String password, String gender) {
 *         // user constructor code... ... ...
 *     }
 *
 *     public String getUsername()
 *     ... ... ...
 * }
 * List<User> userList = new LinkedList<User>();
 * User jack = new User("Jack", "123456", "Male");
 * User marry = new User("Marry", "888888", "Female");
 * userList.add(jack);
 * userList.add(marry);
 * Type targetType = new TypeToken<List<User>>(){}.getType();
 * String sUserList1 = JSONUtils.toJson(userList, targetType);
 * sUserList1 ----> [{"uname":"jack","gender":"Male","sex":"Male"},{"uname":"marry","gender":"Female","sex":"Female"}]
 * String sUserList2 = JSONUtils.toJson(userList, targetType, false);
 * sUserList2 ----> [{"uname":"jack","pwd":"123456","gender":"Male","sex":"Male"},{"uname":"marry","pwd":"888888","gender":"Female","sex":"Female"}]
 * String sUserList3 = JSONUtils.toJson(userList, targetType, 1.0d, true);
 * sUserList3 ----> [{"uname":"jack","sex":"Male"},{"uname":"marry","sex":"Female"}]
 * </pre>
 *
 * @author Fuchun
 * @version 1.1.0
 * @since ay-commons-lang 1.0
 */
public class JSONUtils {
     private static final Logger LOGGER = LoggerFactory.getLogger(JSONUtils.class);

    public static Type MAP_STR_OBJ_TYPE = new TypeToken<Map<String, Object>>() {}.getType();
    public static Gson GSON = new GsonBuilder()
            .registerTypeAdapter(MAP_STR_OBJ_TYPE, new MapDeserializerDoubleAsIntFix())
            .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
            .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
            .create();

    public static Gson GSONNoExclude = new GsonBuilder()
            .excludeFieldsWithoutExposeAnnotation() // 默认排除指定字段
            .registerTypeAdapter(MAP_STR_OBJ_TYPE, new MapDeserializerDoubleAsIntFix())
            .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
            .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
            .create();


    /**
     * 空的 {@code JSON} 数据 - <code>"{}"</code>。
     */
    public static final String EMPTY_JSON = "{}";
    /**
     * 空的 {@code JSON} 数组(集合)数据 - {@code "[]"}。
     */
    public static final String EMPTY_JSON_ARRAY = "[]";
    /**
     * 默认的 {@code JSON} 日期/时间字段的格式化模式。
     */
    public static final String DEFAULT_DATE_PATTERN = "yyyy-MM-dd HH:mm:ss SSS";

    /**
     * <p>
     * <code>JSONUtils</code> instances should NOT be constructed in standard
     * programming. Instead, the class should be used as
     * <code>JSONUtils.fromJson("foo");</code>.
     * </p>
     * <p>
     * This constructor is public to permit tools that require a JavaBean instance to operate.
     * </p>
     */
    public JSONUtils() {
        super();
    }


    /**
     * 将对象转为json不忽略字段
     *
     * @param bean the bean
     * @return the string
     */
    public static String toJson(Object bean) {
        if(bean == null) {
            return null;
        }
        return GSON.toJson(bean);
    }

    public static byte[] toJsonBytes(Object value) {
        return toJson(value).getBytes(StandardCharsets.UTF_8);
    }

    /**
     * 将对象转为json排除指定字段
     * @param target
     * @return
     */
    public static String toExcJson(Object target) {
        return GSONNoExclude.toJson(target);
    }

    public static byte[] toExcJsonBytes(Object value) {
        return toExcJson(value).getBytes(StandardCharsets.UTF_8);
    }






    public static <T> T fromJson(String json, Class<T> clz) {
        if (StringUtils.isEmpty(json)) {
            return null;
        }
        return GSON.fromJson(json, clz);
    }

    public static <T> T fromJson(String json, Type type) {
        if (StringUtils.isEmpty(json)) {
            return null;
        }
        return GSON.fromJson(json, type);
    }

    public static <T> T fromJson(JsonElement jsonElement, Type type) {
        if (jsonElement == null) {
            return null;
        }
        return GSON.fromJson(jsonElement, type);
    }

    public static <T> T fromJson(String json, TypeToken<T> token) {
        if (StringUtils.isEmpty(json)) {
            return null;
        }
        return GSON.fromJson(json, token.getType());
    }

    public static <T> T fromJson(JsonElement jsonElement, Class<T> clazz) {
        if (jsonElement == null) {
            return null;
        }
        return GSON.fromJson(jsonElement, clazz);
    }



    /**
     * Created by tao.zeng on 2020/6/4.
     * <p>
     * 处理LocalDate的序列化与反序列化
     */
    public final static class LocalDateAdapter implements JsonSerializer<LocalDate>, JsonDeserializer<LocalDate> {

        @Override
        public JsonElement serialize(LocalDate date, Type typeOfSrc, JsonSerializationContext context) {
            return new JsonPrimitive(date.format(DateTimeFormatter.ISO_LOCAL_DATE));
        }

        @Override
        public LocalDate deserialize(JsonElement element, Type type, JsonDeserializationContext context) throws JsonParseException {
            String timestamp = element.getAsJsonPrimitive().getAsString();
            return LocalDate.parse(timestamp, DateTimeFormatter.ISO_LOCAL_DATE);
        }
    }

    /**
     * Created by tao.zeng on 2020/6/4.
     * <p>
     * 处理LocalDateTime序列化与反序列化
     */
    public final static class LocalDateTimeAdapter implements JsonSerializer<LocalDateTime>, JsonDeserializer<LocalDateTime> {

        @Override
        public JsonElement serialize(LocalDateTime date, Type typeOfSrc, JsonSerializationContext context) {
            return new JsonPrimitive(date.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        }

        @Override
        public LocalDateTime deserialize(JsonElement element, Type type, JsonDeserializationContext context) throws JsonParseException {
            String timestamp = element.getAsJsonPrimitive().getAsString();
            return LocalDateTime.parse(timestamp, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        }
    }

    /**
     * Created by tao.zeng on 2020/6/4.
     * <p>
     * https://gist.github.com/xingstarx/5ddc14ff6ca68ba4097815c90d1c47cc
     * <p>
     * https://stackoverflow.com/questions/36508323/how-can-i-prevent-gson-from-converting-integers-to-doubles/36529534#36529534
     * <p>
     * <p>
     * 解决json数据转换为map结构的时候，会出现int变成double的问题
     */
    public final static class MapDeserializerDoubleAsIntFix implements JsonDeserializer<Map<String, Object>> {
        @SuppressWarnings("unchecked")
        @Override
        public Map<String, Object> deserialize(JsonElement element, Type type, JsonDeserializationContext context) throws JsonParseException {
            return (Map<String, Object>) read(element);
        }

        private Object read(JsonElement in) {
            if (in.isJsonArray()) {
                List<Object> list = new ArrayList<>();
                JsonArray arr = in.getAsJsonArray();
                for (JsonElement anArr : arr) {
                    list.add(read(anArr));
                }
                return list;
            } else if (in.isJsonObject()) {
                Map<String, Object> map = new LinkedTreeMap<>();
                JsonObject obj = in.getAsJsonObject();
                Set<Map.Entry<String, JsonElement>> entitySet = obj.entrySet();
                for (Map.Entry<String, JsonElement> entry : entitySet) {
                    map.put(entry.getKey(), read(entry.getValue()));
                }
                return map;
            } else if (in.isJsonPrimitive()) {
                JsonPrimitive prim = in.getAsJsonPrimitive();
                if (prim.isBoolean()) {
                    return prim.getAsBoolean();
                } else if (prim.isString()) {
                    return prim.getAsString();
                } else if (prim.isNumber()) {
                    Number num = prim.getAsNumber();
                    // here you can handle double int/long values
                    // and return any type you want
                    // this solution will transform 3.0 float to long values
                    if (Math.ceil(num.doubleValue()) == num.longValue())
                        return num.longValue();
                    else {
                        return num.doubleValue();
                    }
                }
            }
            return null;
        }
    }










    public static void main(String[] args) {
        String jsons = "region:[[440000,广州市,799],[440300,珠海市,390]]";
        region:
        jsons = jsons.substring("region:".length());
        jsons = jsons.replaceAll("\\[", "");
        jsons = jsons.replaceAll("]", "");
        String[] lists = jsons.split(",");
        String js = null;
        for (int i = 1; i <= lists.length; i++) {
            js = js+lists[i-1];
            if(i%3 == 0) {
                System.out.println(js);
                js = null;
            }
        }
    }
}