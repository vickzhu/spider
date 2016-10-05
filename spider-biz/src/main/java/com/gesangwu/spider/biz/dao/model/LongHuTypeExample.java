package com.gesangwu.spider.biz.dao.model;

import java.util.ArrayList;
import java.util.List;

public class LongHuTypeExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected int offset = -1;

    protected int rows = -1;

    public LongHuTypeExample() {
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

        public Criteria andLhTypeIsNull() {
            addCriterion("lh_type is null");
            return (Criteria) this;
        }

        public Criteria andLhTypeIsNotNull() {
            addCriterion("lh_type is not null");
            return (Criteria) this;
        }

        public Criteria andLhTypeEqualTo(String value) {
            addCriterion("lh_type =", value, "lhType");
            return (Criteria) this;
        }

        public Criteria andLhTypeNotEqualTo(String value) {
            addCriterion("lh_type <>", value, "lhType");
            return (Criteria) this;
        }

        public Criteria andLhTypeGreaterThan(String value) {
            addCriterion("lh_type >", value, "lhType");
            return (Criteria) this;
        }

        public Criteria andLhTypeGreaterThanOrEqualTo(String value) {
            addCriterion("lh_type >=", value, "lhType");
            return (Criteria) this;
        }

        public Criteria andLhTypeLessThan(String value) {
            addCriterion("lh_type <", value, "lhType");
            return (Criteria) this;
        }

        public Criteria andLhTypeLessThanOrEqualTo(String value) {
            addCriterion("lh_type <=", value, "lhType");
            return (Criteria) this;
        }

        public Criteria andLhTypeLike(String value) {
            addCriterion("lh_type like", value, "lhType");
            return (Criteria) this;
        }

        public Criteria andLhTypeNotLike(String value) {
            addCriterion("lh_type not like", value, "lhType");
            return (Criteria) this;
        }

        public Criteria andLhTypeIn(List<String> values) {
            addCriterion("lh_type in", values, "lhType");
            return (Criteria) this;
        }

        public Criteria andLhTypeNotIn(List<String> values) {
            addCriterion("lh_type not in", values, "lhType");
            return (Criteria) this;
        }

        public Criteria andLhTypeBetween(String value1, String value2) {
            addCriterion("lh_type between", value1, value2, "lhType");
            return (Criteria) this;
        }

        public Criteria andLhTypeNotBetween(String value1, String value2) {
            addCriterion("lh_type not between", value1, value2, "lhType");
            return (Criteria) this;
        }

        public Criteria andLhDescIsNull() {
            addCriterion("lh_desc is null");
            return (Criteria) this;
        }

        public Criteria andLhDescIsNotNull() {
            addCriterion("lh_desc is not null");
            return (Criteria) this;
        }

        public Criteria andLhDescEqualTo(String value) {
            addCriterion("lh_desc =", value, "lhDesc");
            return (Criteria) this;
        }

        public Criteria andLhDescNotEqualTo(String value) {
            addCriterion("lh_desc <>", value, "lhDesc");
            return (Criteria) this;
        }

        public Criteria andLhDescGreaterThan(String value) {
            addCriterion("lh_desc >", value, "lhDesc");
            return (Criteria) this;
        }

        public Criteria andLhDescGreaterThanOrEqualTo(String value) {
            addCriterion("lh_desc >=", value, "lhDesc");
            return (Criteria) this;
        }

        public Criteria andLhDescLessThan(String value) {
            addCriterion("lh_desc <", value, "lhDesc");
            return (Criteria) this;
        }

        public Criteria andLhDescLessThanOrEqualTo(String value) {
            addCriterion("lh_desc <=", value, "lhDesc");
            return (Criteria) this;
        }

        public Criteria andLhDescLike(String value) {
            addCriterion("lh_desc like", value, "lhDesc");
            return (Criteria) this;
        }

        public Criteria andLhDescNotLike(String value) {
            addCriterion("lh_desc not like", value, "lhDesc");
            return (Criteria) this;
        }

        public Criteria andLhDescIn(List<String> values) {
            addCriterion("lh_desc in", values, "lhDesc");
            return (Criteria) this;
        }

        public Criteria andLhDescNotIn(List<String> values) {
            addCriterion("lh_desc not in", values, "lhDesc");
            return (Criteria) this;
        }

        public Criteria andLhDescBetween(String value1, String value2) {
            addCriterion("lh_desc between", value1, value2, "lhDesc");
            return (Criteria) this;
        }

        public Criteria andLhDescNotBetween(String value1, String value2) {
            addCriterion("lh_desc not between", value1, value2, "lhDesc");
            return (Criteria) this;
        }

        public Criteria andDateTypeIsNull() {
            addCriterion("date_type is null");
            return (Criteria) this;
        }

        public Criteria andDateTypeIsNotNull() {
            addCriterion("date_type is not null");
            return (Criteria) this;
        }

        public Criteria andDateTypeEqualTo(Integer value) {
            addCriterion("date_type =", value, "dateType");
            return (Criteria) this;
        }

        public Criteria andDateTypeNotEqualTo(Integer value) {
            addCriterion("date_type <>", value, "dateType");
            return (Criteria) this;
        }

        public Criteria andDateTypeGreaterThan(Integer value) {
            addCriterion("date_type >", value, "dateType");
            return (Criteria) this;
        }

        public Criteria andDateTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("date_type >=", value, "dateType");
            return (Criteria) this;
        }

        public Criteria andDateTypeLessThan(Integer value) {
            addCriterion("date_type <", value, "dateType");
            return (Criteria) this;
        }

        public Criteria andDateTypeLessThanOrEqualTo(Integer value) {
            addCriterion("date_type <=", value, "dateType");
            return (Criteria) this;
        }

        public Criteria andDateTypeIn(List<Integer> values) {
            addCriterion("date_type in", values, "dateType");
            return (Criteria) this;
        }

        public Criteria andDateTypeNotIn(List<Integer> values) {
            addCriterion("date_type not in", values, "dateType");
            return (Criteria) this;
        }

        public Criteria andDateTypeBetween(Integer value1, Integer value2) {
            addCriterion("date_type between", value1, value2, "dateType");
            return (Criteria) this;
        }

        public Criteria andDateTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("date_type not between", value1, value2, "dateType");
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