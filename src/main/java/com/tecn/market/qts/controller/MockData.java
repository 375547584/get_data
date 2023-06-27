package com.tecn.market.qts.controller;

import com.google.gson.reflect.TypeToken;
import com.tecn.market.qts.entity.*;
import com.tecn.market.qts.util.JSONUtils;

import java.util.List;

public class MockData {
    public static List<StockStatic> buioldStockStatics() {
        String s = "[{\"firstday\":\"20210813\",\"buyVolumeUnit\":1,\"sellVolumeUnit\":1,\"declareVolumeFloor\":200,\"declareVolumeCeiling\":100000,\"code\":\"688787\",\"block\":\"SZ\",\"stockJaneSpell\":\"\",\"fallStopPrice\":126.4,\"riseStopPrice\":189.6,\"pe\":0.0,\"folweQuity\":29176147,\"totaleValue\":0.0,\"securityStatus\":\"D  F  N\",\"upDownLimitType\":\"N\"},{\"firstday\":\"20201022\",\"buyVolumeUnit\":1,\"sellVolumeUnit\":1,\"declareVolumeFloor\":200,\"declareVolumeCeiling\":100000,\"code\":\"688788\",\"block\":\"SZ\",\"stockJaneSpell\":\"\",\"fallStopPrice\":34.34,\"riseStopPrice\":51.5,\"pe\":0.0,\"folweQuity\":65634184,\"totaleValue\":0.0,\"securityStatus\":\"D  F  N\",\"upDownLimitType\":\"N\"},{\"firstday\":\"20210708\",\"buyVolumeUnit\":1,\"sellVolumeUnit\":1,\"declareVolumeFloor\":200,\"declareVolumeCeiling\":100000,\"code\":\"688789\",\"block\":\"SZ\",\"stockJaneSpell\":\"\",\"fallStopPrice\":146.82,\"riseStopPrice\":220.22,\"pe\":0.0,\"folweQuity\":41963756,\"totaleValue\":0.0,\"securityStatus\":\"D  F  N\",\"upDownLimitType\":\"N\"},{\"firstday\":\"20210715\",\"buyVolumeUnit\":1,\"sellVolumeUnit\":1,\"declareVolumeFloor\":200,\"declareVolumeCeiling\":100000,\"code\":\"688793\",\"block\":\"SZ\",\"stockJaneSpell\":\"\",\"fallStopPrice\":39.94,\"riseStopPrice\":59.92,\"pe\":0.0,\"folweQuity\":23184443,\"totaleValue\":0.0,\"securityStatus\":\"D  F  N\",\"upDownLimitType\":\"N\"},{\"firstday\":\"20210816\",\"buyVolumeUnit\":1,\"sellVolumeUnit\":1,\"declareVolumeFloor\":200,\"declareVolumeCeiling\":100000,\"code\":\"688798\",\"block\":\"SZ\",\"stockJaneSpell\":\"\",\"fallStopPrice\":94.24,\"riseStopPrice\":141.36,\"pe\":0.0,\"folweQuity\":96437203,\"totaleValue\":0.0,\"securityStatus\":\"D  F  N\",\"upDownLimitType\":\"N\"},{\"firstday\":\"20210713\",\"buyVolumeUnit\":1,\"sellVolumeUnit\":1,\"declareVolumeFloor\":200,\"declareVolumeCeiling\":100000,\"code\":\"688799\",\"block\":\"SZ\",\"stockJaneSpell\":\"\",\"fallStopPrice\":27.76,\"riseStopPrice\":41.64,\"pe\":0.0,\"folweQuity\":55453400,\"totaleValue\":0.0,\"securityStatus\":\"D  F  N\",\"upDownLimitType\":\"N\"},{\"firstday\":\"20210722\",\"buyVolumeUnit\":1,\"sellVolumeUnit\":1,\"declareVolumeFloor\":200,\"declareVolumeCeiling\":100000,\"code\":\"688800\",\"block\":\"SZ\",\"stockJaneSpell\":\"\",\"fallStopPrice\":90.56,\"riseStopPrice\":135.84,\"pe\":0.0,\"folweQuity\":70178000,\"totaleValue\":0.0,\"securityStatus\":\"D  F  N\",\"upDownLimitType\":\"N\"},{\"firstday\":\"20210118\",\"buyVolumeUnit\":1,\"sellVolumeUnit\":1,\"declareVolumeFloor\":200,\"declareVolumeCeiling\":100000,\"code\":\"688819\",\"block\":\"SZ\",\"stockJaneSpell\":\"\",\"fallStopPrice\":30.54,\"riseStopPrice\":45.8,\"pe\":0.0,\"folweQuity\":130900000,\"totaleValue\":0.0,\"securityStatus\":\"D  F  N\",\"upDownLimitType\":\"N\"},{\"firstday\":\"20200716\",\"buyVolumeUnit\":1,\"sellVolumeUnit\":1,\"declareVolumeFloor\":200,\"declareVolumeCeiling\":100000,\"code\":\"688981\",\"block\":\"SZ\",\"stockJaneSpell\":\"\",\"fallStopPrice\":34.54,\"riseStopPrice\":51.82,\"pe\":0.0,\"folweQuity\":1957402720,\"totaleValue\":0.0,\"securityStatus\":\"D  F  N\",\"upDownLimitType\":\"N\"},{\"firstday\":\"20201029\",\"buyVolumeUnit\":1,\"sellVolumeUnit\":1,\"declareVolumeFloor\":200,\"declareVolumeCeiling\":100000,\"code\":\"689009\",\"block\":\"SZ\",\"stockJaneSpell\":\"\",\"fallStopPrice\":28.72,\"riseStopPrice\":43.08,\"pe\":0.0,\"folweQuity\":511700430,\"totaleValue\":0.0,\"securityStatus\":\"DY F WN\",\"upDownLimitType\":\"N\"}]";
        return JSONUtils.fromJson(s, new TypeToken<List<StockStatic>>(){}.getType());
    }

    public static List<IndexQuotation> buioldIndexQuotations() {
        String s ="[{\"code\":\"000001\",\"block\":\"SZ\",\"price\":3232.1063,\"sendTime\":\"2023-02-08T15:59:59\",\"tradeTime\":\"2023-02-08T15:59:57\",\"increase\":-0.4900,\"risefall\":-15.9877,\"openPrice\":3251.5006,\"highPrice\":3257.114,\"lowPrice\":3257.114,\"vol\":23453048100,\"amount\":293332736125.8,\"yclose\":3248.094}]";
        return JSONUtils.fromJson(s, new TypeToken<List<IndexQuotation>>(){}.getType());
    }

    public static List<StockQuotation> buioldStockQuotations() {
        String s ="[{\"sendTime\":\"2023-02-08T15:59:57\",\"tradeTime\":\"2023-02-08T15:59:56\",\"code\":\"688800\",\"block\":\"SH\",\"price\":0.0,\"increase\":0,\"risefall\":0.0,\"highPrice\":0.0,\"lowPrice\":0.0,\"openPrice\":0.0,\"fallStopPrice\":0.0,\"riseStopPrice\":0.0,\"vol\":0,\"amount\":0.0,\"buypx1\":0.0,\"buypx2\":0.0,\"buypx3\":0.0,\"buypx4\":0.0,\"buypx5\":0.0,\"buypx6\":0.0,\"buypx7\":0.0,\"buypx8\":0.0,\"buypx9\":0.0,\"buypx10\":0.0,\"buyvol1\":497,\"buyvol2\":0,\"buyvol3\":0,\"buyvol4\":0,\"buyvol5\":0,\"buyvol6\":0,\"buyvol7\":0,\"buyvol8\":0,\"buyvol9\":0,\"buyvol10\":0,\"sellpx1\":0.0,\"sellpx2\":0.0,\"sellpx3\":0.0,\"sellpx4\":0.0,\"sellpx5\":0.0,\"sellpx6\":0.0,\"sellpx7\":0.0,\"sellpx8\":0.0,\"sellpx9\":0.0,\"sellpx10\":0.0,\"sellvol1\":0,\"sellvol2\":0,\"sellvol3\":0,\"sellvol4\":0,\"sellvol5\":0,\"sellvol6\":0,\"sellvol7\":0,\"sellvol8\":0,\"sellvol9\":0,\"sellvol10\":0,\"yclose\":0.0},{\"sendTime\":\"2023-02-08T15:59:57\",\"tradeTime\":\"2023-02-08T15:59:56\",\"code\":\"688819\",\"block\":\"SH\",\"price\":0.0,\"increase\":0,\"risefall\":0.0,\"highPrice\":0.0,\"lowPrice\":0.0,\"openPrice\":0.0,\"fallStopPrice\":0.0,\"riseStopPrice\":0.0,\"vol\":0,\"amount\":0.0,\"buypx1\":0.0,\"buypx2\":0.0,\"buypx3\":0.0,\"buypx4\":0.0,\"buypx5\":0.0,\"buypx6\":0.0,\"buypx7\":0.0,\"buypx8\":0.0,\"buypx9\":0.0,\"buypx10\":0.0,\"buyvol1\":0,\"buyvol2\":0,\"buyvol3\":0,\"buyvol4\":0,\"buyvol5\":0,\"buyvol6\":0,\"buyvol7\":0,\"buyvol8\":0,\"buyvol9\":0,\"buyvol10\":0,\"sellpx1\":0.0,\"sellpx2\":0.0,\"sellpx3\":0.0,\"sellpx4\":0.0,\"sellpx5\":0.0,\"sellpx6\":0.0,\"sellpx7\":0.0,\"sellpx8\":0.0,\"sellpx9\":0.0,\"sellpx10\":0.0,\"sellvol1\":0,\"sellvol2\":0,\"sellvol3\":0,\"sellvol4\":0,\"sellvol5\":0,\"sellvol6\":0,\"sellvol7\":0,\"sellvol8\":0,\"sellvol9\":0,\"sellvol10\":0,\"yclose\":0.0},{\"sendTime\":\"2023-02-08T15:59:57\",\"tradeTime\":\"2023-02-08T15:59:56\",\"code\":\"688981\",\"block\":\"SH\",\"price\":0.0,\"increase\":0,\"risefall\":0.0,\"highPrice\":0.0,\"lowPrice\":0.0,\"openPrice\":0.0,\"fallStopPrice\":0.0,\"riseStopPrice\":0.0,\"vol\":0,\"amount\":0.0,\"buypx1\":0.0,\"buypx2\":0.0,\"buypx3\":0.0,\"buypx4\":0.0,\"buypx5\":0.0,\"buypx6\":0.0,\"buypx7\":0.0,\"buypx8\":0.0,\"buypx9\":0.0,\"buypx10\":0.0,\"buyvol1\":300,\"buyvol2\":0,\"buyvol3\":0,\"buyvol4\":0,\"buyvol5\":0,\"buyvol6\":0,\"buyvol7\":0,\"buyvol8\":0,\"buyvol9\":0,\"buyvol10\":0,\"sellpx1\":0.0,\"sellpx2\":0.0,\"sellpx3\":0.0,\"sellpx4\":0.0,\"sellpx5\":0.0,\"sellpx6\":0.0,\"sellpx7\":0.0,\"sellpx8\":0.0,\"sellpx9\":0.0,\"sellpx10\":0.0,\"sellvol1\":0,\"sellvol2\":0,\"sellvol3\":0,\"sellvol4\":0,\"sellvol5\":0,\"sellvol6\":0,\"sellvol7\":0,\"sellvol8\":0,\"sellvol9\":0,\"sellvol10\":0,\"yclose\":0.0},{\"sendTime\":\"2023-02-08T15:59:57\",\"tradeTime\":\"2023-02-08T15:59:56\",\"code\":\"689009\",\"block\":\"SH\",\"price\":0.0,\"increase\":0,\"risefall\":0.0,\"highPrice\":0.0,\"lowPrice\":0.0,\"openPrice\":0.0,\"fallStopPrice\":0.0,\"riseStopPrice\":0.0,\"vol\":0,\"amount\":0.0,\"buypx1\":0.0,\"buypx2\":0.0,\"buypx3\":0.0,\"buypx4\":0.0,\"buypx5\":0.0,\"buypx6\":0.0,\"buypx7\":0.0,\"buypx8\":0.0,\"buypx9\":0.0,\"buypx10\":0.0,\"buyvol1\":0,\"buyvol2\":0,\"buyvol3\":0,\"buyvol4\":0,\"buyvol5\":0,\"buyvol6\":0,\"buyvol7\":0,\"buyvol8\":0,\"buyvol9\":0,\"buyvol10\":0,\"sellpx1\":0.0,\"sellpx2\":0.0,\"sellpx3\":0.0,\"sellpx4\":0.0,\"sellpx5\":0.0,\"sellpx6\":0.0,\"sellpx7\":0.0,\"sellpx8\":0.0,\"sellpx9\":0.0,\"sellpx10\":0.0,\"sellvol1\":0,\"sellvol2\":0,\"sellvol3\":0,\"sellvol4\":0,\"sellvol5\":0,\"sellvol6\":0,\"sellvol7\":0,\"sellvol8\":0,\"sellvol9\":0,\"sellvol10\":0,\"yclose\":0.0}]";
        return JSONUtils.fromJson(s, new TypeToken<List<StockQuotation>>(){}.getType());
    }

    public static List<EntrustOrder> buioldEntrustOrders() {
        String s ="[{\"sendTime\":\"2023-02-21T14:59:59\",\"code\":\"000001\",\"price\":14.11,\"vol\":200,\"entrustType\":1,\"entrustTime\":145959510},{\"sendTime\":\"2023-02-21T14:59:59\",\"code\":\"000002\",\"price\":17.77,\"vol\":200,\"entrustType\":2,\"entrustTime\":145959780},{\"sendTime\":\"2023-02-21T14:59:59\",\"code\":\"000004\",\"price\":9.67,\"vol\":800,\"entrustType\":2,\"entrustTime\":145959080},{\"sendTime\":\"2023-02-21T14:59:54\",\"code\":\"000005\",\"price\":1.76,\"vol\":500,\"entrustType\":2,\"entrustTime\":145954230},{\"sendTime\":\"2023-02-21T14:59:59\",\"code\":\"000006\",\"price\":5.82,\"vol\":100,\"entrustType\":1,\"entrustTime\":145959480},{\"sendTime\":\"2023-02-21T14:59:58\",\"code\":\"000007\",\"price\":7.64,\"vol\":1900,\"entrustType\":1,\"entrustTime\":145958950},{\"sendTime\":\"2023-02-21T14:59:59\",\"code\":\"000008\",\"price\":2.48,\"vol\":100,\"entrustType\":1,\"entrustTime\":145959050},{\"sendTime\":\"2023-02-21T14:59:58\",\"code\":\"000009\",\"price\":12.6,\"vol\":800,\"entrustType\":2,\"entrustTime\":145958950},{\"sendTime\":\"2023-02-21T14:59:59\",\"code\":\"000010\",\"price\":3.39,\"vol\":100,\"entrustType\":2,\"entrustTime\":145959320},{\"sendTime\":\"2023-02-21T14:59:59\",\"code\":\"000011\",\"price\":11.55,\"vol\":100,\"entrustType\":2,\"entrustTime\":145959730},{\"sendTime\":\"2023-02-21T14:59:58\",\"code\":\"000012\",\"price\":7.12,\"vol\":3000,\"entrustType\":2,\"entrustTime\":145958110},{\"sendTime\":\"2023-02-21T14:59:59\",\"code\":\"000014\",\"price\":10.32,\"vol\":3000,\"entrustType\":2,\"entrustTime\":145959160},{\"sendTime\":\"2023-02-21T14:59:59\",\"code\":\"000016\",\"price\":4.8,\"vol\":2100,\"entrustType\":2,\"entrustTime\":145959550},{\"sendTime\":\"2023-02-21T14:59:59\",\"code\":\"000017\",\"price\":5.02,\"vol\":300,\"entrustType\":2,\"entrustTime\":145959770},{\"sendTime\":\"2023-02-21T14:59:59\",\"code\":\"000019\",\"price\":7.85,\"vol\":200,\"entrustType\":2,\"entrustTime\":145959430},{\"sendTime\":\"2023-02-21T14:59:59\",\"code\":\"000020\",\"price\":10.31,\"vol\":300,\"entrustType\":1,\"entrustTime\":145959410},{\"sendTime\":\"2023-02-21T14:59:59\",\"code\":\"000021\",\"price\":12.39,\"vol\":300,\"entrustType\":2,\"entrustTime\":145959340},{\"sendTime\":\"2023-02-21T14:59:59\",\"code\":\"000023\",\"price\":11.35,\"vol\":200,\"entrustType\":2,\"entrustTime\":145959350},{\"sendTime\":\"2023-02-21T14:59:59\",\"code\":\"000025\",\"price\":19.41,\"vol\":3500,\"entrustType\":1,\"entrustTime\":145959180},{\"sendTime\":\"2023-02-21T14:59:59\",\"code\":\"000026\",\"price\":11.61,\"vol\":1300,\"entrustType\":2,\"entrustTime\":145959340},{\"sendTime\":\"2023-02-21T14:59:59\",\"code\":\"000027\",\"price\":6.25,\"vol\":200,\"entrustType\":2,\"entrustTime\":145959880},{\"sendTime\":\"2023-02-21T14:59:57\",\"code\":\"000028\",\"price\":36.59,\"vol\":100,\"entrustType\":2,\"entrustTime\":145957490},{\"sendTime\":\"2023-02-21T14:59:59\",\"code\":\"000029\",\"price\":12.39,\"vol\":100,\"entrustType\":1,\"entrustTime\":145959740},{\"sendTime\":\"2023-02-21T14:59:59\",\"code\":\"000030\",\"price\":5.11,\"vol\":300,\"entrustType\":1,\"entrustTime\":145959910},{\"sendTime\":\"2023-02-21T14:59:59\",\"code\":\"000031\",\"price\":3.76,\"vol\":400,\"entrustType\":1,\"entrustTime\":145959910},{\"sendTime\":\"2023-02-21T14:59:59\",\"code\":\"000032\",\"price\":26.51,\"vol\":800,\"entrustType\":2,\"entrustTime\":145959550},{\"sendTime\":\"2023-02-21T14:59:59\",\"code\":\"000034\",\"price\":26.8,\"vol\":300,\"entrustType\":1,\"entrustTime\":145959650},{\"sendTime\":\"2023-02-21T14:59:59\",\"code\":\"000035\",\"price\":5.52,\"vol\":400,\"entrustType\":1,\"entrustTime\":145959770},{\"sendTime\":\"2023-02-21T14:59:59\",\"code\":\"000036\",\"price\":4.01,\"vol\":100,\"entrustType\":2,\"entrustTime\":145959420},{\"sendTime\":\"2023-02-21T14:59:59\",\"code\":\"000037\",\"price\":8.45,\"vol\":100,\"entrustType\":2,\"entrustTime\":145959140},{\"sendTime\":\"2023-02-21T14:59:59\",\"code\":\"000038\",\"price\":4.01,\"vol\":4500,\"entrustType\":2,\"entrustTime\":145959110},{\"sendTime\":\"2023-02-21T14:59:59\",\"code\":\"000039\",\"price\":7.75,\"vol\":100,\"entrustType\":1,\"entrustTime\":145959960},{\"sendTime\":\"2023-02-21T14:59:59\",\"code\":\"000040\",\"price\":4.33,\"vol\":9600,\"entrustType\":2,\"entrustTime\":145959410},{\"sendTime\":\"2023-02-21T14:59:59\",\"code\":\"000042\",\"price\":9.1,\"vol\":1300,\"entrustType\":1,\"entrustTime\":145959280}]";
        return JSONUtils.fromJson(s, new TypeToken<List<EntrustOrder>>(){}.getType());
    }

    public static List<StepTransaction> buioldStepTransactions() {
        String s ="[{\"id\":\"s6_r2166761\",\"code\":\"501000\",\"time\":\"10:27\",\"timeMillis\":102706270,\"price\":0.826,\"vol\":50,\"volStr\":\"0.5000\",\"amount\":41.300,\"bs\":\"B\"},{\"id\":\"s6_r4891115\",\"code\":\"501001\",\"time\":\"14:20\",\"timeMillis\":142046840,\"price\":1.888,\"vol\":500,\"volStr\":\"5.0000\",\"amount\":944.000,\"bs\":\"B\"},{\"id\":\"s1_r6072656\",\"code\":\"501005\",\"time\":\"14:56\",\"timeMillis\":145623500,\"price\":1.122,\"vol\":2,\"volStr\":\"0.0200\",\"amount\":2.244,\"bs\":\"S\"},{\"id\":\"s3_r5300260\",\"code\":\"501007\",\"time\":\"14:59\",\"timeMillis\":145941070,\"price\":1.134,\"vol\":29,\"volStr\":\"0.2900\",\"amount\":32.886,\"bs\":\"B\"},{\"id\":\"s5_r4828594\",\"code\":\"501008\",\"time\":\"14:34\",\"timeMillis\":143435700,\"price\":1.107,\"vol\":8100,\"volStr\":\"81.0000\",\"amount\":8966.700,\"bs\":\"S\"},{\"id\":\"s6_r5677117\",\"code\":\"501009\",\"time\":\"14:57\",\"timeMillis\":145745220,\"price\":1.634,\"vol\":5100,\"volStr\":\"51.0000\",\"amount\":8333.400,\"bs\":\"B\"},{\"id\":\"s1_r5647610\",\"code\":\"501010\",\"time\":\"14:40\",\"timeMillis\":144054270,\"price\":1.617,\"vol\":200,\"volStr\":\"2.0000\",\"amount\":323.400,\"bs\":\"B\"},{\"id\":\"s2_r5811879\",\"code\":\"501011\",\"time\":\"14:59\",\"timeMillis\":145938980,\"price\":1.302,\"vol\":200,\"volStr\":\"2.0000\",\"amount\":260.400,\"bs\":\"S\"},{\"id\":\"s3_r5300417\",\"code\":\"501012\",\"time\":\"14:59\",\"timeMillis\":145950450,\"price\":1.279,\"vol\":500,\"volStr\":\"5.0000\",\"amount\":639.500,\"bs\":\"B\"},{\"id\":\"s3_r5299985\",\"code\":\"501015\",\"time\":\"14:59\",\"timeMillis\":145924950,\"price\":1.255,\"vol\":100,\"volStr\":\"1.0000\",\"amount\":125.500,\"bs\":\"B\"},{\"id\":\"s6_r5677374\",\"code\":\"501016\",\"time\":\"14:57\",\"timeMillis\":145758380,\"price\":1.02,\"vol\":7100,\"volStr\":\"71.0000\",\"amount\":7242.00,\"bs\":\"B\"},{\"id\":\"s1_r5437586\",\"code\":\"501017\",\"time\":\"14:30\",\"timeMillis\":143042880,\"price\":1.149,\"vol\":300,\"volStr\":\"3.0000\",\"amount\":344.700,\"bs\":\"S\"},{\"id\":\"s2_r5812434\",\"code\":\"501018\",\"time\":\"14:59\",\"timeMillis\":145957860,\"price\":1.135,\"vol\":100,\"volStr\":\"1.0000\",\"amount\":113.500,\"bs\":\"B\"},{\"id\":\"s3_r5298752\",\"code\":\"501019\",\"time\":\"14:58\",\"timeMillis\":145806000,\"price\":1.247,\"vol\":100,\"volStr\":\"1.0000\",\"amount\":124.700,\"bs\":\"B\"},{\"id\":\"s3_r5299999\",\"code\":\"501021\",\"time\":\"14:59\",\"timeMillis\":145926000,\"price\":1.303,\"vol\":100,\"volStr\":\"1.0000\",\"amount\":130.300,\"bs\":\"B\"},{\"id\":\"s3_r5298442\",\"code\":\"501022\",\"time\":\"14:57\",\"timeMillis\":145738900,\"price\":2.421,\"vol\":203,\"volStr\":\"2.0300\",\"amount\":491.463,\"bs\":\"B\"},{\"id\":\"s2_r5751511\",\"code\":\"501025\",\"time\":\"14:55\",\"timeMillis\":145526830,\"price\":1.013,\"vol\":27300,\"volStr\":\"273.0000\",\"amount\":27654.900,\"bs\":\"B\"},{\"id\":\"s5_r3234997\",\"code\":\"501026\",\"time\":\"11:19\",\"timeMillis\":111951160,\"price\":0.877,\"vol\":1000,\"volStr\":\"10.0000\",\"amount\":877.000,\"bs\":\"S\"},{\"id\":\"s5_r1883458\",\"code\":\"501027\",\"time\":\"10:17\",\"timeMillis\":101719800,\"price\":1.166,\"vol\":100,\"volStr\":\"1.0000\",\"amount\":116.600,\"bs\":\"S\"},{\"id\":\"s1_r4738139\",\"code\":\"501028\",\"time\":\"13:47\",\"timeMillis\":134700130,\"price\":1.339,\"vol\":9900,\"volStr\":\"99.0000\",\"amount\":13256.100,\"bs\":\"S\"}]";
        return JSONUtils.fromJson(s, new TypeToken<List<StepTransaction>>(){}.getType());
    }
}
