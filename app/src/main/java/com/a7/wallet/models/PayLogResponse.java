package com.a7.wallet.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

import lee.vioson.network.core.BaseResponse;

/**
 * Created by viosonlee
 * on 2018/5/23.
 * for
 */
public class PayLogResponse extends BaseResponse {


    /**
     * pageQuery : {"firstPage":true,"lastPage":true,"list":[{"content":"转账","createTime":1526972569000,"id":11,"inOut":0,"moneyType":3,"nick":"e123","nick1":"b","num":-10000,"orderId":"ex00065290925038895104","typ":1,"userid":123,"userid1":124,"walletAddr":"0x16945d69f6e9448d8976cc1fabf236d1af60c818","walletAddr1":"0x26945d69f6e9448d8976cc1fabf236d1af60c818"},{"content":"转账","createTime":1526972640000,"id":13,"inOut":0,"moneyType":3,"nick":"e123","nick1":"b","num":-10000.123456789011,"orderId":"ex00065291221639102464","typ":1,"userid":123,"userid1":124}],"pageNumber":1,"pageSize":10,"paras":{"moneyType":3,"typ":1,"userid":123},"totalPage":1,"totalRow":2}
     */

    private PageQueryBean pageQuery;

    public PageQueryBean getPageQuery() {
        return pageQuery;
    }

    public void setPageQuery(PageQueryBean pageQuery) {
        this.pageQuery = pageQuery;
    }

    public static class PageQueryBean {
        /**
         * firstPage : true
         * lastPage : true
         * list : [{"content":"转账","createTime":1526972569000,"id":11,"inOut":0,"moneyType":3,"nick":"e123","nick1":"b","num":-10000,"orderId":"ex00065290925038895104","typ":1,"userid":123,"userid1":124,"walletAddr":"0x16945d69f6e9448d8976cc1fabf236d1af60c818","walletAddr1":"0x26945d69f6e9448d8976cc1fabf236d1af60c818"},{"content":"转账","createTime":1526972640000,"id":13,"inOut":0,"moneyType":3,"nick":"e123","nick1":"b","num":-10000.123456789011,"orderId":"ex00065291221639102464","typ":1,"userid":123,"userid1":124}]
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

        public static class ListBean  implements Parcelable{
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
             * walletAddr : 0x16945d69f6e9448d8976cc1fabf236d1af60c818
             * walletAddr1 : 0x26945d69f6e9448d8976cc1fabf236d1af60c818
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
            private String walletAddr;
            private String walletAddr1;

            protected ListBean(Parcel in) {
                content = in.readString();
                createTime = in.readLong();
                id = in.readInt();
                inOut = in.readInt();
                moneyType = in.readInt();
                nick = in.readString();
                nick1 = in.readString();
                num = in.readDouble();
                orderId = in.readString();
                typ = in.readInt();
                userid = in.readInt();
                userid1 = in.readInt();
                walletAddr = in.readString();
                walletAddr1 = in.readString();
            }

            public static final Creator<ListBean> CREATOR = new Creator<ListBean>() {
                @Override
                public ListBean createFromParcel(Parcel in) {
                    return new ListBean(in);
                }

                @Override
                public ListBean[] newArray(int size) {
                    return new ListBean[size];
                }
            };

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

            public String getWalletAddr() {
                return walletAddr;
            }

            public void setWalletAddr(String walletAddr) {
                this.walletAddr = walletAddr;
            }

            public String getWalletAddr1() {
                return walletAddr1;
            }

            public void setWalletAddr1(String walletAddr1) {
                this.walletAddr1 = walletAddr1;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(content);
                dest.writeLong(createTime);
                dest.writeInt(id);
                dest.writeInt(inOut);
                dest.writeInt(moneyType);
                dest.writeString(nick);
                dest.writeString(nick1);
                dest.writeDouble(num);
                dest.writeString(orderId);
                dest.writeInt(typ);
                dest.writeInt(userid);
                dest.writeInt(userid1);
                dest.writeString(walletAddr);
                dest.writeString(walletAddr1);
            }
        }
    }
}
