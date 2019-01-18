package cn.blog.utils.gaode;


/**
 * Created by xiaotao on 2018/2/1.
 */
public class GaoDeResponse {
    private String info;
    private Integer status;
    private Integer infocode;
    private Regeocode regeocode;

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getInfocode() {
        return infocode;
    }

    public void setInfocode(Integer infocode) {
        this.infocode = infocode;
    }

    public Regeocode getRegeocode() {
        return regeocode;
    }

    public void setRegeocode(Regeocode regeocode) {
        this.regeocode = regeocode;
    }



    public static class Regeocode{
        private String formatted_address;
        private AddressComponent addressComponent;

        public String getFormatted_address() {
            return formatted_address;
        }

        public void setFormatted_address(String formatted_address) {
            this.formatted_address = formatted_address;
        }

        public AddressComponent getAddressComponent() {
            return addressComponent;
        }

        public void setAddressComponent(AddressComponent addressComponent) {
            this.addressComponent = addressComponent;
        }

        public static class AddressComponent{
            private String country;
            private String province;
            private String city;
            private String towncode;
            private String citycode;
            private String district;
            private String adcode;
            private String township;

            public String getCountry() {
                return country;
            }

            public void setCountry(String country) {
                this.country = country;
            }

            public String getProvince() {
                return province;
            }

            public void setProvince(String province) {
                this.province = province;
            }

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public String getTowncode() {
                return towncode;
            }

            public void setTowncode(String towncode) {
                this.towncode = towncode;
            }

            public String getCitycode() {
                return citycode;
            }

            public void setCitycode(String citycode) {
                this.citycode = citycode;
            }

            public String getDistrict() {
                return district;
            }

            public void setDistrict(String district) {
                this.district = district;
            }

            public String getAdcode() {
                return adcode;
            }

            public void setAdcode(String adcode) {
                this.adcode = adcode;
            }

            public String getTownship() {
                return township;
            }

            public void setTownship(String township) {
                this.township = township;
            }
        }
    }
}

