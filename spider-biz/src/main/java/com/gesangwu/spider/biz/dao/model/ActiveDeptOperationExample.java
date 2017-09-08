package com.gesangwu.spider.biz.dao.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ActiveDeptOperationExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected int offset = -1;

    protected int rows = -1;

    public ActiveDeptOperationExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    public void setOffset(int offset) {
        this.offset=offset;
    }

    public int getOffset() {
        return offset;
    }

    public void setRows(int rows) {
        this.rows=rows;
    }

    public int getRows() {
        return rows;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andOperationIdIsNull() {
            addCriterion("operation_id is null");
            return (Criteria) this;
        }

        public Criteria andOperationIdIsNotNull() {
            addCriterion("operation_id is not null");
            return (Criteria) this;
        }

        public Criteria andOperationIdEqualTo(Long value) {
            addCriterion("operation_id =", value, "operationId");
            return (Criteria) this;
        }

        public Criteria andOperationIdNotEqualTo(Long value) {
            addCriterion("operation_id <>", value, "operationId");
            return (Criteria) this;
        }

        public Criteria andOperationIdGreaterThan(Long value) {
            addCriterion("operation_id >", value, "operationId");
            return (Criteria) this;
        }

        public Criteria andOperationIdGreaterThanOrEqualTo(Long value) {
            addCriterion("operation_id >=", value, "operationId");
            return (Criteria) this;
        }

        public Criteria andOperationIdLessThan(Long value) {
            addCriterion("operation_id <", value, "operationId");
            return (Criteria) this;
        }

        public Criteria andOperationIdLessThanOrEqualTo(Long value) {
            addCriterion("operation_id <=", value, "operationId");
            return (Criteria) this;
        }

        public Criteria andOperationIdIn(List<Long> values) {
            addCriterion("operation_id in", values, "operationId");
            return (Criteria) this;
        }

        public Criteria andOperationIdNotIn(List<Long> values) {
            addCriterion("operation_id not in", values, "operationId");
            return (Criteria) this;
        }

        public Criteria andOperationIdBetween(Long value1, Long value2) {
            addCriterion("operation_id between", value1, value2, "operationId");
            return (Criteria) this;
        }

        public Criteria andOperationIdNotBetween(Long value1, Long value2) {
            addCriterion("operation_id not between", value1, value2, "operationId");
            return (Criteria) this;
        }

        public Criteria andTradeDateIsNull() {
            addCriterion("trade_date is null");
            return (Criteria) this;
        }

        public Criteria andTradeDateIsNotNull() {
            addCriterion("trade_date is not null");
            return (Criteria) this;
        }

        public Criteria andTradeDateEqualTo(String value) {
            addCriterion("trade_date =", value, "tradeDate");
            return (Criteria) this;
        }

        public Criteria andTradeDateNotEqualTo(String value) {
            addCriterion("trade_date <>", value, "tradeDate");
            return (Criteria) this;
        }

        public Criteria andTradeDateGreaterThan(String value) {
            addCriterion("trade_date >", value, "tradeDate");
            return (Criteria) this;
        }

        public Criteria andTradeDateGreaterThanOrEqualTo(String value) {
            addCriterion("trade_date >=", value, "tradeDate");
            return (Criteria) this;
        }

        public Criteria andTradeDateLessThan(String value) {
            addCriterion("trade_date <", value, "tradeDate");
            return (Criteria) this;
        }

        public Criteria andTradeDateLessThanOrEqualTo(String value) {
            addCriterion("trade_date <=", value, "tradeDate");
            return (Criteria) this;
        }

        public Criteria andTradeDateLike(String value) {
            addCriterion("trade_date like", value, "tradeDate");
            return (Criteria) this;
        }

        public Criteria andTradeDateNotLike(String value) {
            addCriterion("trade_date not like", value, "tradeDate");
            return (Criteria) this;
        }

        public Criteria andTradeDateIn(List<String> values) {
            addCriterion("trade_date in", values, "tradeDate");
            return (Criteria) this;
        }

        public Criteria andTradeDateNotIn(List<String> values) {
            addCriterion("trade_date not in", values, "tradeDate");
            return (Criteria) this;
        }

        public Criteria andTradeDateBetween(String value1, String value2) {
            addCriterion("trade_date between", value1, value2, "tradeDate");
            return (Criteria) this;
        }

        public Criteria andTradeDateNotBetween(String value1, String value2) {
            addCriterion("trade_date not between", value1, value2, "tradeDate");
            return (Criteria) this;
        }

        public Criteria andBuyDeptIsNull() {
            addCriterion("buy_dept is null");
            return (Criteria) this;
        }

        public Criteria andBuyDeptIsNotNull() {
            addCriterion("buy_dept is not null");
            return (Criteria) this;
        }

        public Criteria andBuyDeptEqualTo(Integer value) {
            addCriterion("buy_dept =", value, "buyDept");
            return (Criteria) this;
        }

        public Criteria andBuyDeptNotEqualTo(Integer value) {
            addCriterion("buy_dept <>", value, "buyDept");
            return (Criteria) this;
        }

        public Criteria andBuyDeptGreaterThan(Integer value) {
            addCriterion("buy_dept >", value, "buyDept");
            return (Criteria) this;
        }

        public Criteria andBuyDeptGreaterThanOrEqualTo(Integer value) {
            addCriterion("buy_dept >=", value, "buyDept");
            return (Criteria) this;
        }

        public Criteria andBuyDeptLessThan(Integer value) {
            addCriterion("buy_dept <", value, "buyDept");
            return (Criteria) this;
        }

        public Criteria andBuyDeptLessThanOrEqualTo(Integer value) {
            addCriterion("buy_dept <=", value, "buyDept");
            return (Criteria) this;
        }

        public Criteria andBuyDeptIn(List<Integer> values) {
            addCriterion("buy_dept in", values, "buyDept");
            return (Criteria) this;
        }

        public Criteria andBuyDeptNotIn(List<Integer> values) {
            addCriterion("buy_dept not in", values, "buyDept");
            return (Criteria) this;
        }

        public Criteria andBuyDeptBetween(Integer value1, Integer value2) {
            addCriterion("buy_dept between", value1, value2, "buyDept");
            return (Criteria) this;
        }

        public Criteria andBuyDeptNotBetween(Integer value1, Integer value2) {
            addCriterion("buy_dept not between", value1, value2, "buyDept");
            return (Criteria) this;
        }

        public Criteria andSellDeptIsNull() {
            addCriterion("sell_dept is null");
            return (Criteria) this;
        }

        public Criteria andSellDeptIsNotNull() {
            addCriterion("sell_dept is not null");
            return (Criteria) this;
        }

        public Criteria andSellDeptEqualTo(Integer value) {
            addCriterion("sell_dept =", value, "sellDept");
            return (Criteria) this;
        }

        public Criteria andSellDeptNotEqualTo(Integer value) {
            addCriterion("sell_dept <>", value, "sellDept");
            return (Criteria) this;
        }

        public Criteria andSellDeptGreaterThan(Integer value) {
            addCriterion("sell_dept >", value, "sellDept");
            return (Criteria) this;
        }

        public Criteria andSellDeptGreaterThanOrEqualTo(Integer value) {
            addCriterion("sell_dept >=", value, "sellDept");
            return (Criteria) this;
        }

        public Criteria andSellDeptLessThan(Integer value) {
            addCriterion("sell_dept <", value, "sellDept");
            return (Criteria) this;
        }

        public Criteria andSellDeptLessThanOrEqualTo(Integer value) {
            addCriterion("sell_dept <=", value, "sellDept");
            return (Criteria) this;
        }

        public Criteria andSellDeptIn(List<Integer> values) {
            addCriterion("sell_dept in", values, "sellDept");
            return (Criteria) this;
        }

        public Criteria andSellDeptNotIn(List<Integer> values) {
            addCriterion("sell_dept not in", values, "sellDept");
            return (Criteria) this;
        }

        public Criteria andSellDeptBetween(Integer value1, Integer value2) {
            addCriterion("sell_dept between", value1, value2, "sellDept");
            return (Criteria) this;
        }

        public Criteria andSellDeptNotBetween(Integer value1, Integer value2) {
            addCriterion("sell_dept not between", value1, value2, "sellDept");
            return (Criteria) this;
        }

        public Criteria andTotalBuyAmountIsNull() {
            addCriterion("total_buy_amount is null");
            return (Criteria) this;
        }

        public Criteria andTotalBuyAmountIsNotNull() {
            addCriterion("total_buy_amount is not null");
            return (Criteria) this;
        }

        public Criteria andTotalBuyAmountEqualTo(Double value) {
            addCriterion("total_buy_amount =", value, "totalBuyAmount");
            return (Criteria) this;
        }

        public Criteria andTotalBuyAmountNotEqualTo(Double value) {
            addCriterion("total_buy_amount <>", value, "totalBuyAmount");
            return (Criteria) this;
        }

        public Criteria andTotalBuyAmountGreaterThan(Double value) {
            addCriterion("total_buy_amount >", value, "totalBuyAmount");
            return (Criteria) this;
        }

        public Criteria andTotalBuyAmountGreaterThanOrEqualTo(Double value) {
            addCriterion("total_buy_amount >=", value, "totalBuyAmount");
            return (Criteria) this;
        }

        public Criteria andTotalBuyAmountLessThan(Double value) {
            addCriterion("total_buy_amount <", value, "totalBuyAmount");
            return (Criteria) this;
        }

        public Criteria andTotalBuyAmountLessThanOrEqualTo(Double value) {
            addCriterion("total_buy_amount <=", value, "totalBuyAmount");
            return (Criteria) this;
        }

        public Criteria andTotalBuyAmountIn(List<Double> values) {
            addCriterion("total_buy_amount in", values, "totalBuyAmount");
            return (Criteria) this;
        }

        public Criteria andTotalBuyAmountNotIn(List<Double> values) {
            addCriterion("total_buy_amount not in", values, "totalBuyAmount");
            return (Criteria) this;
        }

        public Criteria andTotalBuyAmountBetween(Double value1, Double value2) {
            addCriterion("total_buy_amount between", value1, value2, "totalBuyAmount");
            return (Criteria) this;
        }

        public Criteria andTotalBuyAmountNotBetween(Double value1, Double value2) {
            addCriterion("total_buy_amount not between", value1, value2, "totalBuyAmount");
            return (Criteria) this;
        }

        public Criteria andTotalBuyStockIsNull() {
            addCriterion("total_buy_stock is null");
            return (Criteria) this;
        }

        public Criteria andTotalBuyStockIsNotNull() {
            addCriterion("total_buy_stock is not null");
            return (Criteria) this;
        }

        public Criteria andTotalBuyStockEqualTo(Integer value) {
            addCriterion("total_buy_stock =", value, "totalBuyStock");
            return (Criteria) this;
        }

        public Criteria andTotalBuyStockNotEqualTo(Integer value) {
            addCriterion("total_buy_stock <>", value, "totalBuyStock");
            return (Criteria) this;
        }

        public Criteria andTotalBuyStockGreaterThan(Integer value) {
            addCriterion("total_buy_stock >", value, "totalBuyStock");
            return (Criteria) this;
        }

        public Criteria andTotalBuyStockGreaterThanOrEqualTo(Integer value) {
            addCriterion("total_buy_stock >=", value, "totalBuyStock");
            return (Criteria) this;
        }

        public Criteria andTotalBuyStockLessThan(Integer value) {
            addCriterion("total_buy_stock <", value, "totalBuyStock");
            return (Criteria) this;
        }

        public Criteria andTotalBuyStockLessThanOrEqualTo(Integer value) {
            addCriterion("total_buy_stock <=", value, "totalBuyStock");
            return (Criteria) this;
        }

        public Criteria andTotalBuyStockIn(List<Integer> values) {
            addCriterion("total_buy_stock in", values, "totalBuyStock");
            return (Criteria) this;
        }

        public Criteria andTotalBuyStockNotIn(List<Integer> values) {
            addCriterion("total_buy_stock not in", values, "totalBuyStock");
            return (Criteria) this;
        }

        public Criteria andTotalBuyStockBetween(Integer value1, Integer value2) {
            addCriterion("total_buy_stock between", value1, value2, "totalBuyStock");
            return (Criteria) this;
        }

        public Criteria andTotalBuyStockNotBetween(Integer value1, Integer value2) {
            addCriterion("total_buy_stock not between", value1, value2, "totalBuyStock");
            return (Criteria) this;
        }

        public Criteria andTotalSellAmountIsNull() {
            addCriterion("total_sell_amount is null");
            return (Criteria) this;
        }

        public Criteria andTotalSellAmountIsNotNull() {
            addCriterion("total_sell_amount is not null");
            return (Criteria) this;
        }

        public Criteria andTotalSellAmountEqualTo(Double value) {
            addCriterion("total_sell_amount =", value, "totalSellAmount");
            return (Criteria) this;
        }

        public Criteria andTotalSellAmountNotEqualTo(Double value) {
            addCriterion("total_sell_amount <>", value, "totalSellAmount");
            return (Criteria) this;
        }

        public Criteria andTotalSellAmountGreaterThan(Double value) {
            addCriterion("total_sell_amount >", value, "totalSellAmount");
            return (Criteria) this;
        }

        public Criteria andTotalSellAmountGreaterThanOrEqualTo(Double value) {
            addCriterion("total_sell_amount >=", value, "totalSellAmount");
            return (Criteria) this;
        }

        public Criteria andTotalSellAmountLessThan(Double value) {
            addCriterion("total_sell_amount <", value, "totalSellAmount");
            return (Criteria) this;
        }

        public Criteria andTotalSellAmountLessThanOrEqualTo(Double value) {
            addCriterion("total_sell_amount <=", value, "totalSellAmount");
            return (Criteria) this;
        }

        public Criteria andTotalSellAmountIn(List<Double> values) {
            addCriterion("total_sell_amount in", values, "totalSellAmount");
            return (Criteria) this;
        }

        public Criteria andTotalSellAmountNotIn(List<Double> values) {
            addCriterion("total_sell_amount not in", values, "totalSellAmount");
            return (Criteria) this;
        }

        public Criteria andTotalSellAmountBetween(Double value1, Double value2) {
            addCriterion("total_sell_amount between", value1, value2, "totalSellAmount");
            return (Criteria) this;
        }

        public Criteria andTotalSellAmountNotBetween(Double value1, Double value2) {
            addCriterion("total_sell_amount not between", value1, value2, "totalSellAmount");
            return (Criteria) this;
        }

        public Criteria andTotalSellStockIsNull() {
            addCriterion("total_sell_stock is null");
            return (Criteria) this;
        }

        public Criteria andTotalSellStockIsNotNull() {
            addCriterion("total_sell_stock is not null");
            return (Criteria) this;
        }

        public Criteria andTotalSellStockEqualTo(Integer value) {
            addCriterion("total_sell_stock =", value, "totalSellStock");
            return (Criteria) this;
        }

        public Criteria andTotalSellStockNotEqualTo(Integer value) {
            addCriterion("total_sell_stock <>", value, "totalSellStock");
            return (Criteria) this;
        }

        public Criteria andTotalSellStockGreaterThan(Integer value) {
            addCriterion("total_sell_stock >", value, "totalSellStock");
            return (Criteria) this;
        }

        public Criteria andTotalSellStockGreaterThanOrEqualTo(Integer value) {
            addCriterion("total_sell_stock >=", value, "totalSellStock");
            return (Criteria) this;
        }

        public Criteria andTotalSellStockLessThan(Integer value) {
            addCriterion("total_sell_stock <", value, "totalSellStock");
            return (Criteria) this;
        }

        public Criteria andTotalSellStockLessThanOrEqualTo(Integer value) {
            addCriterion("total_sell_stock <=", value, "totalSellStock");
            return (Criteria) this;
        }

        public Criteria andTotalSellStockIn(List<Integer> values) {
            addCriterion("total_sell_stock in", values, "totalSellStock");
            return (Criteria) this;
        }

        public Criteria andTotalSellStockNotIn(List<Integer> values) {
            addCriterion("total_sell_stock not in", values, "totalSellStock");
            return (Criteria) this;
        }

        public Criteria andTotalSellStockBetween(Integer value1, Integer value2) {
            addCriterion("total_sell_stock between", value1, value2, "totalSellStock");
            return (Criteria) this;
        }

        public Criteria andTotalSellStockNotBetween(Integer value1, Integer value2) {
            addCriterion("total_sell_stock not between", value1, value2, "totalSellStock");
            return (Criteria) this;
        }

        public Criteria andNetIsNull() {
            addCriterion("net is null");
            return (Criteria) this;
        }

        public Criteria andNetIsNotNull() {
            addCriterion("net is not null");
            return (Criteria) this;
        }

        public Criteria andNetEqualTo(Double value) {
            addCriterion("net =", value, "net");
            return (Criteria) this;
        }

        public Criteria andNetNotEqualTo(Double value) {
            addCriterion("net <>", value, "net");
            return (Criteria) this;
        }

        public Criteria andNetGreaterThan(Double value) {
            addCriterion("net >", value, "net");
            return (Criteria) this;
        }

        public Criteria andNetGreaterThanOrEqualTo(Double value) {
            addCriterion("net >=", value, "net");
            return (Criteria) this;
        }

        public Criteria andNetLessThan(Double value) {
            addCriterion("net <", value, "net");
            return (Criteria) this;
        }

        public Criteria andNetLessThanOrEqualTo(Double value) {
            addCriterion("net <=", value, "net");
            return (Criteria) this;
        }

        public Criteria andNetIn(List<Double> values) {
            addCriterion("net in", values, "net");
            return (Criteria) this;
        }

        public Criteria andNetNotIn(List<Double> values) {
            addCriterion("net not in", values, "net");
            return (Criteria) this;
        }

        public Criteria andNetBetween(Double value1, Double value2) {
            addCriterion("net between", value1, value2, "net");
            return (Criteria) this;
        }

        public Criteria andNetNotBetween(Double value1, Double value2) {
            addCriterion("net not between", value1, value2, "net");
            return (Criteria) this;
        }

        public Criteria andGmtCreateIsNull() {
            addCriterion("gmt_create is null");
            return (Criteria) this;
        }

        public Criteria andGmtCreateIsNotNull() {
            addCriterion("gmt_create is not null");
            return (Criteria) this;
        }

        public Criteria andGmtCreateEqualTo(Date value) {
            addCriterion("gmt_create =", value, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateNotEqualTo(Date value) {
            addCriterion("gmt_create <>", value, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateGreaterThan(Date value) {
            addCriterion("gmt_create >", value, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateGreaterThanOrEqualTo(Date value) {
            addCriterion("gmt_create >=", value, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateLessThan(Date value) {
            addCriterion("gmt_create <", value, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateLessThanOrEqualTo(Date value) {
            addCriterion("gmt_create <=", value, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateIn(List<Date> values) {
            addCriterion("gmt_create in", values, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateNotIn(List<Date> values) {
            addCriterion("gmt_create not in", values, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateBetween(Date value1, Date value2) {
            addCriterion("gmt_create between", value1, value2, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateNotBetween(Date value1, Date value2) {
            addCriterion("gmt_create not between", value1, value2, "gmtCreate");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}