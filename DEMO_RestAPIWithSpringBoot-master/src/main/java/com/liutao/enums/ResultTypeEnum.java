package com.liutao.enums;

/**
 * 响应类型
 * @author: LIUTAO
 * @Description:
 * @Date: Created in 14:23 2018/6/6
 * @Modified By:
 */
public enum ResultTypeEnum {
    SUCCESS {
        public int getStatus() {
            return 0;
        }

        public String getMessage() {
            return "操作成功";
        }
    },
    FAIL {
        public int getStatus() {
            return 1;
        }
    },
    OTHER {
        public int getStatus() {
            return 2;
        }
    };

    public abstract int getStatus();

    public String getMessage() {
        return "";
    }
}