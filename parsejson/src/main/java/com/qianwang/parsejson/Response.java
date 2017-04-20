package com.qianwang.parsejson;

import java.util.List;

/**
 * Created by sky on 2017/4/18.
 */

public class Response {

    private Response_head response_head;
    private Response_body response_body;

    public Response_head getResponse_head() {
        return response_head;
    }

    public void setResponse_head(Response_head response_head) {
        this.response_head = response_head;
    }

    public Response_body getResponse_body() {
        return response_body;
    }

    public void setResponse_body(Response_body response_body) {
        this.response_body = response_body;
    }

    public static class Response_head {

        private String respmenu;

        public String getRespmenu() {
            return respmenu;
        }

        public void setRespmenu(String respmenu) {
            this.respmenu = respmenu;
        }

        public String getResptime() {
            return resptime;
        }

        public void setResptime(String resptime) {
            this.resptime = resptime;
        }

        public Respinfo getRespinfo() {
            return respinfo;
        }

        public void setRespinfo(Respinfo respinfo) {
            this.respinfo = respinfo;
        }

        private String resptime;
        private Respinfo respinfo;

        public static  class Respinfo {

            private String respcode;

            public String getRespcode() {
                return respcode;
            }

            public void setRespcode(String respcode) {
                this.respcode = respcode;
            }

            public String getRespdes() {
                return respdes;
            }

            public void setRespdes(String respdes) {
                this.respdes = respdes;
            }

            private String respdes;
        }

    }

    public static class Response_body {

        private List<Merchant> crset;

        public List<Merchant> getCrset() {
            return crset;
        }

        public void setCrset(List<Merchant> crset) {
            this.crset = crset;
        }

        public  static class Merchant {

            private String merchantname;
            private List<Menu> menu;

            public String getMerchantname() {
                return merchantname;
            }

            public void setMerchantname(String merchantname) {
                this.merchantname = merchantname;
            }

            public List<Menu> getMenu() {
                return menu;
            }

            public void setMenu(List<Menu> menu) {
                this.menu = menu;
            }

            public  static class Menu {

                private String menuid;
                private String menuname;

                public String getMenuid() {
                    return menuid;
                }

                public void setMenuid(String menuid) {
                    this.menuid = menuid;
                }

                public String getMenuname() {
                    return menuname;
                }

                public void setMenuname(String menuname) {
                    this.menuname = menuname;
                }
            }
        }
    }
}
