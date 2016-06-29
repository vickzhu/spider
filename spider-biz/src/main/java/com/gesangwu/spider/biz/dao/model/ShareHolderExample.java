package com.gesangwu.spider.biz.dao.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ShareHolderExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected int offset = -1;

    protected int rows = -1;

    public ShareHolderExample() {
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

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andStockCodeIsNull() {
            addCriterion("stock_code is null");
            return (Criteria) this;
        }

        public Criteria andStockCodeIsNotNull() {
            addCriterion("stock_code is not null");
            return (Criteria) this;
        }

        public Criteria andStockCodeEqualTo(String value) {
            addCriterion("stock_code =", value, "stockCode");
            return (Criteria) this;
        }

        public Criteria andStockCodeNotEqualTo(String value) {
            addCriterion("stock_code <>", value, "stockCode");
            return (Criteria) this;
        }

        public Criteria andStockCodeGreaterThan(String value) {
            addCriterion("stock_code >", value, "stockCode");
            return (Criteria) this;
        }

        public Criteria andStockCodeGreaterThanOrEqualTo(String value) {
            addCriterion("stock_code >=", value, "stockCode");
            return (Criteria) this;
        }

        public Criteria andStockCodeLessThan(String value) {
            addCriterion("stock_code <", value, "stockCode");
            return (Criteria) this;
        }

        public Criteria andStockCodeLessThanOrEqualTo(String value) {
            addCriterion("stock_code <=", value, "stockCode");
            return (Criteria) this;
        }

        public Criteria andStockCodeLike(String value) {
            addCriterion("stock_code like", value, "stockCode");
            return (Criteria) this;
        }

        public Criteria andStockCodeNotLike(String value) {
            addCriterion("stock_code not like", value, "stockCode");
            return (Criteria) this;
        }

        public Criteria andStockCodeIn(List<String> values) {
            addCriterion("stock_code in", values, "stockCode");
            return (Criteria) this;
        }

        public Criteria andStockCodeNotIn(List<String> values) {
            addCriterion("stock_code not in", values, "stockCode");
            return (Criteria) this;
        }

        public Criteria andStockCodeBetween(String value1, String value2) {
            addCriterion("stock_code between", value1, value2, "stockCode");
            return (Criteria) this;
        }

        public Criteria andStockCodeNotBetween(String value1, String value2) {
            addCriterion("stock_code not between", value1, value2, "stockCode");
            return (Criteria) this;
        }

        public Criteria andEndDateIsNull() {
            addCriterion("end_date is null");
            return (Criteria) this;
        }

        public Criteria andEndDateIsNotNull() {
            addCriterion("end_date is not null");
            return (Criteria) this;
        }

        public Criteria andEndDateEqualTo(String value) {
            addCriterion("end_date =", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateNotEqualTo(String value) {
            addCriterion("end_date <>", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateGreaterThan(String value) {
            addCriterion("end_date >", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateGreaterThanOrEqualTo(String value) {
            addCriterion("end_date >=", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateLessThan(String value) {
            addCriterion("end_date <", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateLessThanOrEqualTo(String value) {
            addCriterion("end_date <=", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateLike(String value) {
            addCriterion("end_date like", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateNotLike(String value) {
            addCriterion("end_date not like", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateIn(List<String> values) {
            addCriterion("end_date in", values, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateNotIn(List<String> values) {
            addCriterion("end_date not in", values, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateBetween(String value1, String value2) {
            addCriterion("end_date between", value1, value2, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateNotBetween(String value1, String value2) {
            addCriterion("end_date not between", value1, value2, "endDate");
            return (Criteria) this;
        }

        public Criteria andHolderNameIsNull() {
            addCriterion("holder_name is null");
            return (Criteria) this;
        }

        public Criteria andHolderNameIsNotNull() {
            addCriterion("holder_name is not null");
            return (Criteria) this;
        }

        public Criteria andHolderNameEqualTo(String value) {
            addCriterion("holder_name =", value, "holderName");
            return (Criteria) this;
        }

        public Criteria andHolderNameNotEqualTo(String value) {
            addCriterion("holder_name <>", value, "holderName");
            return (Criteria) this;
        }

        public Criteria andHolderNameGreaterThan(String value) {
            addCriterion("holder_name >", value, "holderName");
            return (Criteria) this;
        }

        public Criteria andHolderNameGreaterThanOrEqualTo(String value) {
            addCriterion("holder_name >=", value, "holderName");
            return (Criteria) this;
        }

        public Criteria andHolderNameLessThan(String value) {
            addCriterion("holder_name <", value, "holderName");
            return (Criteria) this;
        }

        public Criteria andHolderNameLessThanOrEqualTo(String value) {
            addCriterion("holder_name <=", value, "holderName");
            return (Criteria) this;
        }

        public Criteria andHolderNameLike(String value) {
            addCriterion("holder_name like", value, "holderName");
            return (Criteria) this;
        }

        public Criteria andHolderNameNotLike(String value) {
            addCriterion("holder_name not like", value, "holderName");
            return (Criteria) this;
        }

        public Criteria andHolderNameIn(List<String> values) {
            addCriterion("holder_name in", values, "holderName");
            return (Criteria) this;
        }

        public Criteria andHolderNameNotIn(List<String> values) {
            addCriterion("holder_name not in", values, "holderName");
            return (Criteria) this;
        }

        public Criteria andHolderNameBetween(String value1, String value2) {
            addCriterion("holder_name between", value1, value2, "holderName");
            return (Criteria) this;
        }

        public Criteria andHolderNameNotBetween(String value1, String value2) {
            addCriterion("holder_name not between", value1, value2, "holderName");
            return (Criteria) this;
        }

        public Criteria andHolderCodeIsNull() {
            addCriterion("holder_code is null");
            return (Criteria) this;
        }

        public Criteria andHolderCodeIsNotNull() {
            addCriterion("holder_code is not null");
            return (Criteria) this;
        }

        public Criteria andHolderCodeEqualTo(String value) {
            addCriterion("holder_code =", value, "holderCode");
            return (Criteria) this;
        }

        public Criteria andHolderCodeNotEqualTo(String value) {
            addCriterion("holder_code <>", value, "holderCode");
            return (Criteria) this;
        }

        public Criteria andHolderCodeGreaterThan(String value) {
            addCriterion("holder_code >", value, "holderCode");
            return (Criteria) this;
        }

        public Criteria andHolderCodeGreaterThanOrEqualTo(String value) {
            addCriterion("holder_code >=", value, "holderCode");
            return (Criteria) this;
        }

        public Criteria andHolderCodeLessThan(String value) {
            addCriterion("holder_code <", value, "holderCode");
            return (Criteria) this;
        }

        public Criteria andHolderCodeLessThanOrEqualTo(String value) {
            addCriterion("holder_code <=", value, "holderCode");
            return (Criteria) this;
        }

        public Criteria andHolderCodeLike(String value) {
            addCriterion("holder_code like", value, "holderCode");
            return (Criteria) this;
        }

        public Criteria andHolderCodeNotLike(String value) {
            addCriterion("holder_code not like", value, "holderCode");
            return (Criteria) this;
        }

        public Criteria andHolderCodeIn(List<String> values) {
            addCriterion("holder_code in", values, "holderCode");
            return (Criteria) this;
        }

        public Criteria andHolderCodeNotIn(List<String> values) {
            addCriterion("holder_code not in", values, "holderCode");
            return (Criteria) this;
        }

        public Criteria andHolderCodeBetween(String value1, String value2) {
            addCriterion("holder_code between", value1, value2, "holderCode");
            return (Criteria) this;
        }

        public Criteria andHolderCodeNotBetween(String value1, String value2) {
            addCriterion("holder_code not between", value1, value2, "holderCode");
            return (Criteria) this;
        }

        public Criteria andRankingIsNull() {
            addCriterion("ranking is null");
            return (Criteria) this;
        }

        public Criteria andRankingIsNotNull() {
            addCriterion("ranking is not null");
            return (Criteria) this;
        }

        public Criteria andRankingEqualTo(Integer value) {
            addCriterion("ranking =", value, "ranking");
            return (Criteria) this;
        }

        public Criteria andRankingNotEqualTo(Integer value) {
            addCriterion("ranking <>", value, "ranking");
            return (Criteria) this;
        }

        public Criteria andRankingGreaterThan(Integer value) {
            addCriterion("ranking >", value, "ranking");
            return (Criteria) this;
        }

        public Criteria andRankingGreaterThanOrEqualTo(Integer value) {
            addCriterion("ranking >=", value, "ranking");
            return (Criteria) this;
        }

        public Criteria andRankingLessThan(Integer value) {
            addCriterion("ranking <", value, "ranking");
            return (Criteria) this;
        }

        public Criteria andRankingLessThanOrEqualTo(Integer value) {
            addCriterion("ranking <=", value, "ranking");
            return (Criteria) this;
        }

        public Criteria andRankingIn(List<Integer> values) {
            addCriterion("ranking in", values, "ranking");
            return (Criteria) this;
        }

        public Criteria andRankingNotIn(List<Integer> values) {
            addCriterion("ranking not in", values, "ranking");
            return (Criteria) this;
        }

        public Criteria andRankingBetween(Integer value1, Integer value2) {
            addCriterion("ranking between", value1, value2, "ranking");
            return (Criteria) this;
        }

        public Criteria andRankingNotBetween(Integer value1, Integer value2) {
            addCriterion("ranking not between", value1, value2, "ranking");
            return (Criteria) this;
        }

        public Criteria andHoldCountIsNull() {
            addCriterion("hold_count is null");
            return (Criteria) this;
        }

        public Criteria andHoldCountIsNotNull() {
            addCriterion("hold_count is not null");
            return (Criteria) this;
        }

        public Criteria andHoldCountEqualTo(Double value) {
            addCriterion("hold_count =", value, "holdCount");
            return (Criteria) this;
        }

        public Criteria andHoldCountNotEqualTo(Double value) {
            addCriterion("hold_count <>", value, "holdCount");
            return (Criteria) this;
        }

        public Criteria andHoldCountGreaterThan(Double value) {
            addCriterion("hold_count >", value, "holdCount");
            return (Criteria) this;
        }

        public Criteria andHoldCountGreaterThanOrEqualTo(Double value) {
            addCriterion("hold_count >=", value, "holdCount");
            return (Criteria) this;
        }

        public Criteria andHoldCountLessThan(Double value) {
            addCriterion("hold_count <", value, "holdCount");
            return (Criteria) this;
        }

        public Criteria andHoldCountLessThanOrEqualTo(Double value) {
            addCriterion("hold_count <=", value, "holdCount");
            return (Criteria) this;
        }

        public Criteria andHoldCountIn(List<Double> values) {
            addCriterion("hold_count in", values, "holdCount");
            return (Criteria) this;
        }

        public Criteria andHoldCountNotIn(List<Double> values) {
            addCriterion("hold_count not in", values, "holdCount");
            return (Criteria) this;
        }

        public Criteria andHoldCountBetween(Double value1, Double value2) {
            addCriterion("hold_count between", value1, value2, "holdCount");
            return (Criteria) this;
        }

        public Criteria andHoldCountNotBetween(Double value1, Double value2) {
            addCriterion("hold_count not between", value1, value2, "holdCount");
            return (Criteria) this;
        }

        public Criteria andHoldRateIsNull() {
            addCriterion("hold_rate is null");
            return (Criteria) this;
        }

        public Criteria andHoldRateIsNotNull() {
            addCriterion("hold_rate is not null");
            return (Criteria) this;
        }

        public Criteria andHoldRateEqualTo(Double value) {
            addCriterion("hold_rate =", value, "holdRate");
            return (Criteria) this;
        }

        public Criteria andHoldRateNotEqualTo(Double value) {
            addCriterion("hold_rate <>", value, "holdRate");
            return (Criteria) this;
        }

        public Criteria andHoldRateGreaterThan(Double value) {
            addCriterion("hold_rate >", value, "holdRate");
            return (Criteria) this;
        }

        public Criteria andHoldRateGreaterThanOrEqualTo(Double value) {
            addCriterion("hold_rate >=", value, "holdRate");
            return (Criteria) this;
        }

        public Criteria andHoldRateLessThan(Double value) {
            addCriterion("hold_rate <", value, "holdRate");
            return (Criteria) this;
        }

        public Criteria andHoldRateLessThanOrEqualTo(Double value) {
            addCriterion("hold_rate <=", value, "holdRate");
            return (Criteria) this;
        }

        public Criteria andHoldRateIn(List<Double> values) {
            addCriterion("hold_rate in", values, "holdRate");
            return (Criteria) this;
        }

        public Criteria andHoldRateNotIn(List<Double> values) {
            addCriterion("hold_rate not in", values, "holdRate");
            return (Criteria) this;
        }

        public Criteria andHoldRateBetween(Double value1, Double value2) {
            addCriterion("hold_rate between", value1, value2, "holdRate");
            return (Criteria) this;
        }

        public Criteria andHoldRateNotBetween(Double value1, Double value2) {
            addCriterion("hold_rate not between", value1, value2, "holdRate");
            return (Criteria) this;
        }

        public Criteria andHoldFloatRateIsNull() {
            addCriterion("hold_float_rate is null");
            return (Criteria) this;
        }

        public Criteria andHoldFloatRateIsNotNull() {
            addCriterion("hold_float_rate is not null");
            return (Criteria) this;
        }

        public Criteria andHoldFloatRateEqualTo(Double value) {
            addCriterion("hold_float_rate =", value, "holdFloatRate");
            return (Criteria) this;
        }

        public Criteria andHoldFloatRateNotEqualTo(Double value) {
            addCriterion("hold_float_rate <>", value, "holdFloatRate");
            return (Criteria) this;
        }

        public Criteria andHoldFloatRateGreaterThan(Double value) {
            addCriterion("hold_float_rate >", value, "holdFloatRate");
            return (Criteria) this;
        }

        public Criteria andHoldFloatRateGreaterThanOrEqualTo(Double value) {
            addCriterion("hold_float_rate >=", value, "holdFloatRate");
            return (Criteria) this;
        }

        public Criteria andHoldFloatRateLessThan(Double value) {
            addCriterion("hold_float_rate <", value, "holdFloatRate");
            return (Criteria) this;
        }

        public Criteria andHoldFloatRateLessThanOrEqualTo(Double value) {
            addCriterion("hold_float_rate <=", value, "holdFloatRate");
            return (Criteria) this;
        }

        public Criteria andHoldFloatRateIn(List<Double> values) {
            addCriterion("hold_float_rate in", values, "holdFloatRate");
            return (Criteria) this;
        }

        public Criteria andHoldFloatRateNotIn(List<Double> values) {
            addCriterion("hold_float_rate not in", values, "holdFloatRate");
            return (Criteria) this;
        }

        public Criteria andHoldFloatRateBetween(Double value1, Double value2) {
            addCriterion("hold_float_rate between", value1, value2, "holdFloatRate");
            return (Criteria) this;
        }

        public Criteria andHoldFloatRateNotBetween(Double value1, Double value2) {
            addCriterion("hold_float_rate not between", value1, value2, "holdFloatRate");
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