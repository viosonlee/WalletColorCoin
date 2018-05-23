package com.a7.wallet.models;

import java.util.List;

import lee.vioson.network.core.BaseResponse;

/**
 * Created by viosonlee
 * on 2018/5/23.
 * for
 */
public class PayLogResponse extends BaseResponse {

    /**
     * pageQuery : {"firstPage":true,"lastPage":true,"list":[{"content":"转账","createTime":1526972569000,"id":11,"inOut":0,"moneyType":3,"nick":"e123","nick1":"b","num":-10000,"orderId":"ex00065290925038895104","typ":1,"userid":123,"userid1":124},{"content":"转账","createTime":1526972640000,"id":13,"inOut":0,"moneyType":3,"nick":"e123","nick1":"b","num":-10000.123456789011,"orderId":"ex00065291221639102464","typ":1,"userid":123,"userid1":124}],"pageNumber":1,"pageSize":10,"paras":{"moneyType":3,"typ":1,"userid":123},"totalPage":1,"totalRow":2}
     */

    private Bean pageQuery;

    public Bean getPageQuery() {
        return pageQuery;
    }

    public void setPageQuery(Bean pageQuery) {
        this.pageQuery = pageQuery;
    }

    public static class Bean {
        /**
         * firstPage : true
         * lastPage : true
         * list : [{"content":"转账","createTime":1526972569000,"id":11,"inOut":0,"moneyType":3,"nick":"e123","nick1":"b","num":-10000,"orderId":"ex00065290925038895104","typ":1,"userid":123,"userid1":124},{"content":"转账","createTime":1526972640000,"id":13,"inOut":0,"moneyType":3,"nick":"e123","nick1":"b","num":-10000.123456789011,"orderId":"ex00065291221639102464","typ":1,"userid":123,"userid1":124}]
         * pageNumber : 1
         * pageSize : 10
         * paras : {"moneyType":3,"typ":1,"userid":123}
         * totalPage : 1
         * totalRow : 2
         */

        private boolean firstPage;
        private boolean lastPage;
        private int pageNumber;
        private int pageSize;
        private ParasBean paras;
        private int totalPage;
        private int totalRow;
        private List<ListBean> list;

        public boolean isFirstPage() {
            return firstPage;
        }

        public void setFirstPage(boolean firstPage) {
            this.firstPage = firstPage;
        }

        public boolean isLastPage() {
            return lastPage;
        }

        public void setLastPage(boolean lastPage) {
            this.lastPage = lastPage;
        }

        public int getPageNumber() {
            return pageNumber;
        }

        public void setPageNumber(int pageNumber) {
            this.pageNumber = pageNumber;
        }

        public int getPageSize() {
            return pageSize;
        }

        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
        }

        public ParasBean getParas() {
            return paras;
        }

        public void setParas(ParasBean paras) {
            this.paras = paras;
        }

        public int getTotalPage() {
            return totalPage;
        }

        public void setTotalPage(int totalPage) {
            this.totalPage = totalPage;
        }

        public int getTotalRow() {
            return totalRow;
        }

        public void setTotalRow(int totalRow) {
            this.totalRow = totalRow;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ParasBean {
            /**
             * moneyType : 3
             * typ : 1
             * userid : 123
             */

            private int moneyType;
            private int typ;
            private int userid;

            public int getMoneyType() {
                return moneyType;
            }

            public void setMoneyType(int moneyType) {
                this.moneyType = moneyType;
            }

            public int getTyp() {
                return typ;
            }

            public void setTyp(int typ) {
                this.typ = typ;
            }

            public int getUserid() {
                return userid;
            }

            public void setUserid(int userid) {
                this.userid = userid;
            }
        }

        public static class ListBean {
            /**
             * content : 转账
             * createTime : 1526972569000
             * id : 11
             * inOut : 0
             * moneyType : 3
             * nick : e123
             * nick1 : b
             * num : -10000.0
             * orderId : ex00065290925038895104
             * typ : 1
             * userid : 123
             * userid1 : 124
             */

            private String content;
            private long createTime;
            private int id;
            private int inOut;
            private int moneyType;
            private String nick;
            private String nick1;
            private double num;
            private String orderId;
            private int typ;
            private int userid;
            private int userid1;

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public long getCreateTime() {
                return createTime;
            }

            public void setCreateTime(long createTime) {
                this.createTime = createTime;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getInOut() {
                return inOut;
            }

            public void setInOut(int inOut) {
                this.inOut = inOut;
            }

            public int getMoneyType() {
                return moneyType;
            }

            public void setMoneyType(int moneyType) {
                this.moneyType = moneyType;
            }

            public String getNick() {
                return nick;
            }

            public void setNick(String nick) {
                this.nick = nick;
            }

            public String getNick1() {
                return nick1;
            }

            public void setNick1(String nick1) {
                this.nick1 = nick1;
            }

            public double getNum() {
                return num;
            }

            public void setNum(double num) {
                this.num = num;
            }

            public String getOrderId() {
                return orderId;
            }

            public void setOrderId(String orderId) {
                this.orderId = orderId;
            }

            public int getTyp() {
                return typ;
            }

            public void setTyp(int typ) {
                this.typ = typ;
            }

            public int getUserid() {
                return userid;
            }

            public void setUserid(int userid) {
                this.userid = userid;
            }

            public int getUserid1() {
                return userid1;
            }

            public void setUserid1(int userid1) {
                this.userid1 = userid1;
            }
        }
    }
}
