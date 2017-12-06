package us.mifeng.addanddelete.bean;

import java.util.ArrayList;

/**
 * Created by 黑夜之火 on 2017/8/22.
 */

public class Custom {
    private ArrayList<D>d;
    private ArrayList<ArrayList<M>>m;

    public ArrayList<D> getD() {
        return d;
    }

    public void setD(ArrayList<D> d) {
        this.d = d;
    }

    public ArrayList<ArrayList<M>> getM() {
        return m;
    }

    public void setM(ArrayList<ArrayList<M>> m) {
        this.m = m;
    }

    @Override
    public String toString() {
        return "Custom{" +
                "d=" + d +
                ", m=" + m +
                '}';
    }

    public class D{
        private String name;
        private String id;
        private String type;
        private String original;
        private String img;

        public D(String name) {
            this.name = name;
            this.id = id;
            this.type = type;
            this.original = original;
            this.img = img;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getOriginal() {
            return original;
        }

        public void setOriginal(String original) {
            this.original = original;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        @Override
        public String toString() {
            return "D{" +
                    "name='" + name + '\'' +
                    ", id='" + id + '\'' +
                    ", type='" + type + '\'' +
                    ", original='" + original + '\'' +
                    ", img='" + img + '\'' +
                    '}';
        }
    }
    public class M{
        private String name;
        private String id;
        private String type;
        private String l_type;
        private boolean isAdd;

        public boolean isAdd() {
            return isAdd;
        }

        public void setAdd(boolean add) {
            isAdd = add;
        }

        public M(String name) {
            this.name = name;
            this.id = id;
            this.type = type;
            this.l_type = l_type;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getL_type() {
            return l_type;
        }

        public void setL_type(String l_type) {
            this.l_type = l_type;
        }

        @Override
        public String toString() {
            return "M{" +
                    "name='" + name + '\'' +
                    ", id='" + id + '\'' +
                    ", type='" + type + '\'' +
                    ", l_type='" + l_type + '\'' +
                    '}';
        }
    }

}
