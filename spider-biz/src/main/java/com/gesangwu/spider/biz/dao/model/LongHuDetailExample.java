package com.gesangwu.spider.biz.dao.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class LongHuDetailExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected int offset = -1;

    protected int rows = -1;

    public LongHuDetailExample() {
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

        protected void addCriterionForJDBCDate(String condition, Date value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value.getTime()), property);
        }

        protected void addCriterionForJDBCDate(String condition, List<Date> values, String property) {
            if (values == null || values.size() == 0) {
                throw new RuntimeException("Value list for " + property + " cannot be null or empty");
            }
            List<java.sql.Date> dateList = new ArrayList<java.sql.Date>();
            Iterator<Date> iter = values.iterator();
            while (iter.hasNext()) {
                dateList.add(new java.sql.Date(iter.next().getTime()));
            }
            addCriterion(condition, dateList, property);
        }

        protected void addCriterionForJDBCDate(String condition, Date value1, Date value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value1.getTime()), new java.sql.Date(value2.getTime()), property);
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

        public Criteria andSymbolIsNull() {
            addCriterion("symbol is null");
            return (Criteria) this;
        }

        public Criteria andSymbolIsNotNull() {
            addCriterion("symbol is not null");
            return (Criteria) this;
        }

        public Criteria andSymbolEqualTo(String value) {
            addCriterion("symbol =", value, "symbol");
            return (Criteria) this;
        }

        public Criteria andSymbolNotEqualTo(String value) {
            addCriterion("symbol <>", value, "symbol");
            return (Criteria) this;
        }

        public Criteria andSymbolGreaterThan(String value) {
            addCriterion("symbol >", value, "symbol");
            return (Criteria) this;
        }

        public Criteria andSymbolGreaterThanOrEqualTo(String value) {
            addCriterion("symbol >=", value, "symbol");
            return (Criteria) this;
        }

        public Criteria andSymbolLessThan(String value) {
            addCriterion("symbol <", value, "symbol");
            return (Criteria) this;
        }

        public Criteria andSymbolLessThanOrEqualTo(String value) {
            addCriterion("symbol <=", value, "symbol");
            return (Criteria) this;
        }

        public Criteria andSymbolLike(String value) {
            addCriterion("symbol like", value, "symbol");
            return (Criteria) this;
        }

        public Criteria andSymbolNotLike(String value) {
            addCriterion("symbol not like", value, "symbol");
            return (Criteria) this;
        }

        public Criteria andSymbolIn(List<String> values) {
            addCriterion("symbol in", values, "symbol");
            return (Criteria) this;
        }

        public Criteria andSymbolNotIn(List<String> values) {
            addCriterion("symbol not in", values, "symbol");
            return (Criteria) this;
        }

        public Criteria andSymbolBetween(String value1, String value2) {
            addCriterion("symbol between", value1, value2, "symbol");
            return (Criteria) this;
        }

        public Criteria andSymbolNotBetween(String value1, String value2) {
            addCriterion("symbol not between", value1, value2, "symbol");
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

        public Criteria andTradeDateEqualTo(Date value) {
            addCriterionForJDBCDate("trade_date =", value, "tradeDate");
            return (Criteria) this;
        }

        public Criteria andTradeDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("trade_date <>", value, "tradeDate");
            return (Criteria) this;
        }

        public Criteria andTradeDateGreaterThan(Date value) {
            addCriterionForJDBCDate("trade_date >", value, "tradeDate");
            return (Criteria) this;
        }

        public Criteria andTradeDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("trade_date >=", value, "tradeDate");
            return (Criteria) this;
        }

        public Criteria andTradeDateLessThan(Date value) {
            addCriterionForJDBCDate("trade_date <", value, "tradeDate");
            return (Criteria) this;
        }

        public Criteria andTradeDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("trade_date <=", value, "tradeDate");
            return (Criteria) this;
        }

        public Criteria andTradeDateIn(List<Date> values) {
            addCriterionForJDBCDate("trade_date in", values, "tradeDate");
            return (Criteria) this;
        }

        public Criteria andTradeDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("trade_date not in", values, "tradeDate");
            return (Criteria) this;
        }

        public Criteria andTradeDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("trade_date between", value1, value2, "tradeDate");
            return (Criteria) this;
        }

        public Criteria andTradeDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("trade_date not between", value1, value2, "tradeDate");
            return (Criteria) this;
        }

        public Criteria andIsSrIsNull() {
            addCriterion("is_sr is null");
            return (Criteria) this;
        }

        public Criteria andIsSrIsNotNull() {
            addCriterion("is_sr is not null");
            return (Criteria) this;
        }

        public Criteria andIsSrEqualTo(Integer value) {
            addCriterion("is_sr =", value, "isSr");
            return (Criteria) this;
        }

        public Criteria andIsSrNotEqualTo(Integer value) {
            addCriterion("is_sr <>", value, "isSr");
            return (Criteria) this;
        }

        public Criteria andIsSrGreaterThan(Integer value) {
            addCriterion("is_sr >", value, "isSr");
            return (Criteria) this;
        }

        public Criteria andIsSrGreaterThanOrEqualTo(Integer value) {
            addCriterion("is_sr >=", value, "isSr");
            return (Criteria) this;
        }

        public Criteria andIsSrLessThan(Integer value) {
            addCriterion("is_sr <", value, "isSr");
            return (Criteria) this;
        }

        public Criteria andIsSrLessThanOrEqualTo(Integer value) {
            addCriterion("is_sr <=", value, "isSr");
            return (Criteria) this;
        }

        public Criteria andIsSrIn(List<Integer> values) {
            addCriterion("is_sr in", values, "isSr");
            return (Criteria) this;
        }

        public Criteria andIsSrNotIn(List<Integer> values) {
            addCriterion("is_sr not in", values, "isSr");
            return (Criteria) this;
        }

        public Criteria andIsSrBetween(Integer value1, Integer value2) {
            addCriterion("is_sr between", value1, value2, "isSr");
            return (Criteria) this;
        }

        public Criteria andIsSrNotBetween(Integer value1, Integer value2) {
            addCriterion("is_sr not between", value1, value2, "isSr");
            return (Criteria) this;
        }

        public Criteria andSecDeptCodeIsNull() {
            addCriterion("sec_dept_code is null");
            return (Criteria) this;
        }

        public Criteria andSecDeptCodeIsNotNull() {
            addCriterion("sec_dept_code is not null");
            return (Criteria) this;
        }

        public Criteria andSecDeptCodeEqualTo(Integer value) {
            addCriterion("sec_dept_code =", value, "secDeptCode");
            return (Criteria) this;
        }

        public Criteria andSecDeptCodeNotEqualTo(Integer value) {
            addCriterion("sec_dept_code <>", value, "secDeptCode");
            return (Criteria) this;
        }

        public Criteria andSecDeptCodeGreaterThan(Integer value) {
            addCriterion("sec_dept_code >", value, "secDeptCode");
            return (Criteria) this;
        }

        public Criteria andSecDeptCodeGreaterThanOrEqualTo(Integer value) {
            addCriterion("sec_dept_code >=", value, "secDeptCode");
            return (Criteria) this;
        }

        public Criteria andSecDeptCodeLessThan(Integer value) {
            addCriterion("sec_dept_code <", value, "secDeptCode");
            return (Criteria) this;
        }

        public Criteria andSecDeptCodeLessThanOrEqualTo(Integer value) {
            addCriterion("sec_dept_code <=", value, "secDeptCode");
            return (Criteria) this;
        }

        public Criteria andSecDeptCodeIn(List<Integer> values) {
            addCriterion("sec_dept_code in", values, "secDeptCode");
            return (Criteria) this;
        }

        public Criteria andSecDeptCodeNotIn(List<Integer> values) {
            addCriterion("sec_dept_code not in", values, "secDeptCode");
            return (Criteria) this;
        }

        public Criteria andSecDeptCodeBetween(Integer value1, Integer value2) {
            addCriterion("sec_dept_code between", value1, value2, "secDeptCode");
            return (Criteria) this;
        }

        public Criteria andSecDeptCodeNotBetween(Integer value1, Integer value2) {
            addCriterion("sec_dept_code not between", value1, value2, "secDeptCode");
            return (Criteria) this;
        }

        public Criteria andSecDeptAddrIsNull() {
            addCriterion("sec_dept_addr is null");
            return (Criteria) this;
        }

        public Criteria andSecDeptAddrIsNotNull() {
            addCriterion("sec_dept_addr is not null");
            return (Criteria) this;
        }

        public Criteria andSecDeptAddrEqualTo(String value) {
            addCriterion("sec_dept_addr =", value, "secDeptAddr");
            return (Criteria) this;
        }

        public Criteria andSecDeptAddrNotEqualTo(String value) {
            addCriterion("sec_dept_addr <>", value, "secDeptAddr");
            return (Criteria) this;
        }

        public Criteria andSecDeptAddrGreaterThan(String value) {
            addCriterion("sec_dept_addr >", value, "secDeptAddr");
            return (Criteria) this;
        }

        public Criteria andSecDeptAddrGreaterThanOrEqualTo(String value) {
            addCriterion("sec_dept_addr >=", value, "secDeptAddr");
            return (Criteria) this;
        }

        public Criteria andSecDeptAddrLessThan(String value) {
            addCriterion("sec_dept_addr <", value, "secDeptAddr");
            return (Criteria) this;
        }

        public Criteria andSecDeptAddrLessThanOrEqualTo(String value) {
            addCriterion("sec_dept_addr <=", value, "secDeptAddr");
            return (Criteria) this;
        }

        public Criteria andSecDeptAddrLike(String value) {
            addCriterion("sec_dept_addr like", value, "secDeptAddr");
            return (Criteria) this;
        }

        public Criteria andSecDeptAddrNotLike(String value) {
            addCriterion("sec_dept_addr not like", value, "secDeptAddr");
            return (Criteria) this;
        }

        public Criteria andSecDeptAddrIn(List<String> values) {
            addCriterion("sec_dept_addr in", values, "secDeptAddr");
            return (Criteria) this;
        }

        public Criteria andSecDeptAddrNotIn(List<String> values) {
            addCriterion("sec_dept_addr not in", values, "secDeptAddr");
            return (Criteria) this;
        }

        public Criteria andSecDeptAddrBetween(String value1, String value2) {
            addCriterion("sec_dept_addr between", value1, value2, "secDeptAddr");
            return (Criteria) this;
        }

        public Criteria andSecDeptAddrNotBetween(String value1, String value2) {
            addCriterion("sec_dept_addr not between", value1, value2, "secDeptAddr");
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

        public Criteria andGmtUpdateIsNull() {
            addCriterion("gmt_update is null");
            return (Criteria) this;
        }

        public Criteria andGmtUpdateIsNotNull() {
            addCriterion("gmt_update is not null");
            return (Criteria) this;
        }

        public Criteria andGmtUpdateEqualTo(Date value) {
            addCriterion("gmt_update =", value, "gmtUpdate");
            return (Criteria) this;
        }

        public Criteria andGmtUpdateNotEqualTo(Date value) {
            addCriterion("gmt_update <>", value, "gmtUpdate");
            return (Criteria) this;
        }

        public Criteria andGmtUpdateGreaterThan(Date value) {
            addCriterion("gmt_update >", value, "gmtUpdate");
            return (Criteria) this;
        }

        public Criteria andGmtUpdateGreaterThanOrEqualTo(Date value) {
            addCriterion("gmt_update >=", value, "gmtUpdate");
            return (Criteria) this;
        }

        public Criteria andGmtUpdateLessThan(Date value) {
            addCriterion("gmt_update <", value, "gmtUpdate");
            return (Criteria) this;
        }

        public Criteria andGmtUpdateLessThanOrEqualTo(Date value) {
            addCriterion("gmt_update <=", value, "gmtUpdate");
            return (Criteria) this;
        }

        public Criteria andGmtUpdateIn(List<Date> values) {
            addCriterion("gmt_update in", values, "gmtUpdate");
            return (Criteria) this;
        }

        public Criteria andGmtUpdateNotIn(List<Date> values) {
            addCriterion("gmt_update not in", values, "gmtUpdate");
            return (Criteria) this;
        }

        public Criteria andGmtUpdateBetween(Date value1, Date value2) {
            addCriterion("gmt_update between", value1, value2, "gmtUpdate");
            return (Criteria) this;
        }

        public Criteria andGmtUpdateNotBetween(Date value1, Date value2) {
            addCriterion("gmt_update not between", value1, value2, "gmtUpdate");
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