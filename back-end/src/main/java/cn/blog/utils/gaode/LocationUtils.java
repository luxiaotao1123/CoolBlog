package cn.blog.utils.gaode;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by xiaotao on 2018/2/1.
 */
public class LocationUtils {

    private static Logger logger = LoggerFactory.getLogger(LocationUtils.class);

    /**
     * 输入地点查询
     *
     * @param address 地点的中文名称
     * @return
     */
    public static LocationQueryResult query(String address, QueryMethod queryMethod) {
        LocationQueryResult locationQueryResult = new LocationQueryResult();
        locationQueryResult.setSuccess(Boolean.FALSE);

        HttpURLConnection connection = null;
        BufferedInputStream inputStream = null;
        ByteArrayOutputStream outputStream = null;
        try {

            if (StringUtils.isEmpty(address)) {
                logger.warn("查询地址输入为空");
                return locationQueryResult;
            }
            String url;
            switch (queryMethod) {
                case BAI_DU:
                    url = "http://api.map.baidu.com/geocoder/v2/?address=%s&&city=杭州&output=json&ak=MclkIs6Tv09tXD4LDPHVTMg5coXEphDz";
                    break;
                case GAO_DE:
                    url = "http://restapi.amap.com/v3/geocode/geo?address=%s&&city=杭州&key=2f52b814f431a00001d4af0f59b183c0";
                    break;
                default:
                    logger.warn("暂时不支持其他类型");
                    return locationQueryResult;
            }

            connection = (HttpURLConnection) new URL(String.format(url, address)).openConnection();

            connection.connect();


            inputStream = new BufferedInputStream(connection.getInputStream());

            outputStream = new ByteArrayOutputStream();


            byte[] bytes = new byte[1024];

            int len = 0;
            while ((len = inputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, len);
            }

            JSONObject jsonObject = JSONObject.parseObject(new String(outputStream.toByteArray()));

            Integer result = jsonObject.getInteger("status");
            switch (queryMethod) {
                case BAI_DU:

                    if (result != null && result.equals(QueryMethod.BAI_DU.toSuccessCode())) {
                        JSONObject resultJson = jsonObject.getJSONObject("result");
                        if (!resultJson.isEmpty()) {
                            JSONObject location = resultJson.getJSONObject("location");
                            locationQueryResult.setSuccess(Boolean.TRUE);
                            locationQueryResult.setLatitude(location.getDouble("lat"));
                            locationQueryResult.setLongitude(location.getDouble("lng"));

                        }
                    }
                    break;
                case GAO_DE:
                    if (result != null && result.equals(QueryMethod.GAO_DE.toSuccessCode())) {
                        JSONArray resultArray = jsonObject.getJSONArray("geocodes");
                        if (!resultArray.isEmpty()) {
                            String location = resultArray.getJSONObject(0).getString("location");
                            locationQueryResult.setSuccess(Boolean.TRUE);
                            locationQueryResult.setLatitude(Double.parseDouble(location.split(",")[1]));
                            locationQueryResult.setLongitude(Double.parseDouble(location.split(",")[0]));

                        }
                    }
                    break;
                default:
            }


        } catch (Exception e) {
            logger.error("解析地址={} 出错", address, e);
        } finally {
            if (connection != null) {
                connection.disconnect();
            }

            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException ignore) {

                }

            }

            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException ignore) {

                }

            }


        }
        return locationQueryResult;

    }

    public static GaoDeResponse GaoDeAddress(Double longitude,Double latitude){
        String url = "http://restapi.amap.com/v3/geocode/regeo?output=json&location=%a,%a&key=2f52b814f431a00001d4af0f59b183c0&radius=1000&extensions=all";

        HttpURLConnection connection = null;

        BufferedInputStream inputStream  = null;
        try {

            connection = (HttpURLConnection) new URL(String.format(url,longitude,latitude)).openConnection();
            if (connection == null) {
                return null;
            }

            connection.connect();

            inputStream = new BufferedInputStream(connection.getInputStream());

        } catch (IOException e) {
            e.printStackTrace();
        }


        if (inputStream == null){
            return null;
        }
        ByteArrayOutputStream  outputStream = new ByteArrayOutputStream();
        byte[] bytes = new byte[1024];

        int len = 0;
        try {
            while ((len = inputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        JSONObject jsonObject = JSONObject.parseObject( new String(outputStream.toByteArray()));
        return jsonObject.toJavaObject(GaoDeResponse.class);
    }

    public enum QueryMethod {
        BAI_DU(0), GAO_DE(1);
        private int success;

        QueryMethod(int success) {
            this.success = success;
        }

        public int toSuccessCode() {
            return success;
        }
    }


    public static class LocationQueryResult {
        /**
         * 是否成功 true成功 false不成功
         */
        private Boolean isSuccess;
        /**
         * 经度
         */
        private Double longitude;
        /**
         * 纬度
         */
        private Double latitude;


        public Boolean getSuccess() {
            return isSuccess;
        }

        public void setSuccess(Boolean success) {
            isSuccess = success;
        }

        public Double getLongitude() {
            return longitude;
        }

        public void setLongitude(Double longitude) {


            this.longitude = keepPoint(longitude);
        }

        public Double getLatitude() {
            return latitude;
        }

        public void setLatitude(Double latitude) {
            this.latitude = keepPoint(latitude);
        }

        @Override
        public String toString() {
            return "LocationQueryResult{" +
                    "isSuccess=" + isSuccess +
                    ", longitude=" + longitude +
                    ", latitude=" + latitude +
                    '}';
        }
    }


    /**
     * 保留小数点
     *
     * @param source 需要保留的数据
     * @return
     */
    private static double keepPoint(double source) {
        return new BigDecimal(source).setScale(6, BigDecimal.ROUND_HALF_UP).doubleValue();

    }

    public static void main(String[] args) throws IOException {
//        GaoDeResponse gaoDeResponse = LocationUtils.GaoDeAddress(120.094491,30.150197);
        GaoDeResponse gaoDeResponse = LocationUtils.GaoDeAddress(      121.2959, 31.1423);
        String city = gaoDeResponse.getRegeocode().getAddressComponent().getCity();
        String district = gaoDeResponse.getRegeocode().getAddressComponent().getDistrict();
        if ("[]".equals(city)){
            city = gaoDeResponse.getRegeocode().getAddressComponent().getProvince();
        }
        System.out.println(city + "-" + district);


        LocationQueryResult result = query("西湖", QueryMethod.GAO_DE);
        System.out.println(result);
    }


}
