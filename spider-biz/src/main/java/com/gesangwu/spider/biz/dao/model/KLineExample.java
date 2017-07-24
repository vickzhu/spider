package com.gesangwu.spider.biz.dao.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class KLineExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected int offset = -1;

    protected int rows = -1;

    public KLineExample() {
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

        public Criteria andVolumeIsNull() {
            addCriterion("volume is null");
            return (Criteria) this;
        }

        public Criteria andVolumeIsNotNull() {
            addCriterion("volume is not null");
            return (Criteria) this;
        }

        public Criteria andVolumeEqualTo(Long value) {
            addCriterion("volume =", value, "volume");
            return (Criteria) this;
        }

        public Criteria andVolumeNotEqualTo(Long value) {
            addCriterion("volume <>", value, "volume");
            return (Criteria) this;
        }

        public Criteria andVolumeGreaterThan(Long value) {
            addCriterion("volume >", value, "volume");
            return (Criteria) this;
        }

        public Criteria andVolumeGreaterThanOrEqualTo(Long value) {
            addCriterion("volume >=", value, "volume");
            return (Criteria) this;
        }

        public Criteria andVolumeLessThan(Long value) {
            addCriterion("volume <", value, "volume");
            return (Criteria) this;
        }

        public Criteria andVolumeLessThanOrEqualTo(Long value) {
            addCriterion("volume <=", value, "volume");
            return (Criteria) this;
        }

        public Criteria andVolumeIn(List<Long> values) {
            addCriterion("volume in", values, "volume");
            return (Criteria) this;
        }

        public Criteria andVolumeNotIn(List<Long> values) {
            addCriterion("volume not in", values, "volume");
            return (Criteria) this;
        }

        public Criteria andVolumeBetween(Long value1, Long value2) {
            addCriterion("volume between", value1, value2, "volume");
            return (Criteria) this;
        }

        public Criteria andVolumeNotBetween(Long value1, Long value2) {
            addCriterion("volume not between", value1, value2, "volume");
            return (Criteria) this;
        }

        public Criteria andOpenIsNull() {
            addCriterion("open is null");
            return (Criteria) this;
        }

        public Criteria andOpenIsNotNull() {
            addCriterion("open is not null");
            return (Criteria) this;
        }

        public Criteria andOpenEqualTo(Double value) {
            addCriterion("open =", value, "open");
            return (Criteria) this;
        }

        public Criteria andOpenNotEqualTo(Double value) {
            addCriterion("open <>", value, "open");
            return (Criteria) this;
        }

        public Criteria andOpenGreaterThan(Double value) {
            addCriterion("open >", value, "open");
            return (Criteria) this;
        }

        public Criteria andOpenGreaterThanOrEqualTo(Double value) {
            addCriterion("open >=", value, "open");
            return (Criteria) this;
        }

        public Criteria andOpenLessThan(Double value) {
            addCriterion("open <", value, "open");
            return (Criteria) this;
        }

        public Criteria andOpenLessThanOrEqualTo(Double value) {
            addCriterion("open <=", value, "open");
            return (Criteria) this;
        }

        public Criteria andOpenIn(List<Double> values) {
            addCriterion("open in", values, "open");
            return (Criteria) this;
        }

        public Criteria andOpenNotIn(List<Double> values) {
            addCriterion("open not in", values, "open");
            return (Criteria) this;
        }

        public Criteria andOpenBetween(Double value1, Double value2) {
            addCriterion("open between", value1, value2, "open");
            return (Criteria) this;
        }

        public Criteria andOpenNotBetween(Double value1, Double value2) {
            addCriterion("open not between", value1, value2, "open");
            return (Criteria) this;
        }

        public Criteria andCloseIsNull() {
            addCriterion("close is null");
            return (Criteria) this;
        }

        public Criteria andCloseIsNotNull() {
            addCriterion("close is not null");
            return (Criteria) this;
        }

        public Criteria andCloseEqualTo(Double value) {
            addCriterion("close =", value, "close");
            return (Criteria) this;
        }

        public Criteria andCloseNotEqualTo(Double value) {
            addCriterion("close <>", value, "close");
            return (Criteria) this;
        }

        public Criteria andCloseGreaterThan(Double value) {
            addCriterion("close >", value, "close");
            return (Criteria) this;
        }

        public Criteria andCloseGreaterThanOrEqualTo(Double value) {
            addCriterion("close >=", value, "close");
            return (Criteria) this;
        }

        public Criteria andCloseLessThan(Double value) {
            addCriterion("close <", value, "close");
            return (Criteria) this;
        }

        public Criteria andCloseLessThanOrEqualTo(Double value) {
            addCriterion("close <=", value, "close");
            return (Criteria) this;
        }

        public Criteria andCloseIn(List<Double> values) {
            addCriterion("close in", values, "close");
            return (Criteria) this;
        }

        public Criteria andCloseNotIn(List<Double> values) {
            addCriterion("close not in", values, "close");
            return (Criteria) this;
        }

        public Criteria andCloseBetween(Double value1, Double value2) {
            addCriterion("close between", value1, value2, "close");
            return (Criteria) this;
        }

        public Criteria andCloseNotBetween(Double value1, Double value2) {
            addCriterion("close not between", value1, value2, "close");
            return (Criteria) this;
        }

        public Criteria andHighIsNull() {
            addCriterion("high is null");
            return (Criteria) this;
        }

        public Criteria andHighIsNotNull() {
            addCriterion("high is not null");
            return (Criteria) this;
        }

        public Criteria andHighEqualTo(Double value) {
            addCriterion("high =", value, "high");
            return (Criteria) this;
        }

        public Criteria andHighNotEqualTo(Double value) {
            addCriterion("high <>", value, "high");
            return (Criteria) this;
        }

        public Criteria andHighGreaterThan(Double value) {
            addCriterion("high >", value, "high");
            return (Criteria) this;
        }

        public Criteria andHighGreaterThanOrEqualTo(Double value) {
            addCriterion("high >=", value, "high");
            return (Criteria) this;
        }

        public Criteria andHighLessThan(Double value) {
            addCriterion("high <", value, "high");
            return (Criteria) this;
        }

        public Criteria andHighLessThanOrEqualTo(Double value) {
            addCriterion("high <=", value, "high");
            return (Criteria) this;
        }

        public Criteria andHighIn(List<Double> values) {
            addCriterion("high in", values, "high");
            return (Criteria) this;
        }

        public Criteria andHighNotIn(List<Double> values) {
            addCriterion("high not in", values, "high");
            return (Criteria) this;
        }

        public Criteria andHighBetween(Double value1, Double value2) {
            addCriterion("high between", value1, value2, "high");
            return (Criteria) this;
        }

        public Criteria andHighNotBetween(Double value1, Double value2) {
            addCriterion("high not between", value1, value2, "high");
            return (Criteria) this;
        }

        public Criteria andLowIsNull() {
            addCriterion("low is null");
            return (Criteria) this;
        }

        public Criteria andLowIsNotNull() {
            addCriterion("low is not null");
            return (Criteria) this;
        }

        public Criteria andLowEqualTo(Double value) {
            addCriterion("low =", value, "low");
            return (Criteria) this;
        }

        public Criteria andLowNotEqualTo(Double value) {
            addCriterion("low <>", value, "low");
            return (Criteria) this;
        }

        public Criteria andLowGreaterThan(Double value) {
            addCriterion("low >", value, "low");
            return (Criteria) this;
        }

        public Criteria andLowGreaterThanOrEqualTo(Double value) {
            addCriterion("low >=", value, "low");
            return (Criteria) this;
        }

        public Criteria andLowLessThan(Double value) {
            addCriterion("low <", value, "low");
            return (Criteria) this;
        }

        public Criteria andLowLessThanOrEqualTo(Double value) {
            addCriterion("low <=", value, "low");
            return (Criteria) this;
        }

        public Criteria andLowIn(List<Double> values) {
            addCriterion("low in", values, "low");
            return (Criteria) this;
        }

        public Criteria andLowNotIn(List<Double> values) {
            addCriterion("low not in", values, "low");
            return (Criteria) this;
        }

        public Criteria andLowBetween(Double value1, Double value2) {
            addCriterion("low between", value1, value2, "low");
            return (Criteria) this;
        }

        public Criteria andLowNotBetween(Double value1, Double value2) {
            addCriterion("low not between", value1, value2, "low");
            return (Criteria) this;
        }

        public Criteria andChangeAmountIsNull() {
            addCriterion("change_amount is null");
            return (Criteria) this;
        }

        public Criteria andChangeAmountIsNotNull() {
            addCriterion("change_amount is not null");
            return (Criteria) this;
        }

        public Criteria andChangeAmountEqualTo(Double value) {
            addCriterion("change_amount =", value, "changeAmount");
            return (Criteria) this;
        }

        public Criteria andChangeAmountNotEqualTo(Double value) {
            addCriterion("change_amount <>", value, "changeAmount");
            return (Criteria) this;
        }

        public Criteria andChangeAmountGreaterThan(Double value) {
            addCriterion("change_amount >", value, "changeAmount");
            return (Criteria) this;
        }

        public Criteria andChangeAmountGreaterThanOrEqualTo(Double value) {
            addCriterion("change_amount >=", value, "changeAmount");
            return (Criteria) this;
        }

        public Criteria andChangeAmountLessThan(Double value) {
            addCriterion("change_amount <", value, "changeAmount");
            return (Criteria) this;
        }

        public Criteria andChangeAmountLessThanOrEqualTo(Double value) {
            addCriterion("change_amount <=", value, "changeAmount");
            return (Criteria) this;
        }

        public Criteria andChangeAmountIn(List<Double> values) {
            addCriterion("change_amount in", values, "changeAmount");
            return (Criteria) this;
        }

        public Criteria andChangeAmountNotIn(List<Double> values) {
            addCriterion("change_amount not in", values, "changeAmount");
            return (Criteria) this;
        }

        public Criteria andChangeAmountBetween(Double value1, Double value2) {
            addCriterion("change_amount between", value1, value2, "changeAmount");
            return (Criteria) this;
        }

        public Criteria andChangeAmountNotBetween(Double value1, Double value2) {
            addCriterion("change_amount not between", value1, value2, "changeAmount");
            return (Criteria) this;
        }

        public Criteria andPercentIsNull() {
            addCriterion("percent is null");
            return (Criteria) this;
        }

        public Criteria andPercentIsNotNull() {
            addCriterion("percent is not null");
            return (Criteria) this;
        }

        public Criteria andPercentEqualTo(Double value) {
            addCriterion("percent =", value, "percent");
            return (Criteria) this;
        }

        public Criteria andPercentNotEqualTo(Double value) {
            addCriterion("percent <>", value, "percent");
            return (Criteria) this;
        }

        public Criteria andPercentGreaterThan(Double value) {
            addCriterion("percent >", value, "percent");
            return (Criteria) this;
        }

        public Criteria andPercentGreaterThanOrEqualTo(Double value) {
            addCriterion("percent >=", value, "percent");
            return (Criteria) this;
        }

        public Criteria andPercentLessThan(Double value) {
            addCriterion("percent <", value, "percent");
            return (Criteria) this;
        }

        public Criteria andPercentLessThanOrEqualTo(Double value) {
            addCriterion("percent <=", value, "percent");
            return (Criteria) this;
        }

        public Criteria andPercentIn(List<Double> values) {
            addCriterion("percent in", values, "percent");
            return (Criteria) this;
        }

        public Criteria andPercentNotIn(List<Double> values) {
            addCriterion("percent not in", values, "percent");
            return (Criteria) this;
        }

        public Criteria andPercentBetween(Double value1, Double value2) {
            addCriterion("percent between", value1, value2, "percent");
            return (Criteria) this;
        }

        public Criteria andPercentNotBetween(Double value1, Double value2) {
            addCriterion("percent not between", value1, value2, "percent");
            return (Criteria) this;
        }

        public Criteria andTurnrateIsNull() {
            addCriterion("turnrate is null");
            return (Criteria) this;
        }

        public Criteria andTurnrateIsNotNull() {
            addCriterion("turnrate is not null");
            return (Criteria) this;
        }

        public Criteria andTurnrateEqualTo(Double value) {
            addCriterion("turnrate =", value, "turnrate");
            return (Criteria) this;
        }

        public Criteria andTurnrateNotEqualTo(Double value) {
            addCriterion("turnrate <>", value, "turnrate");
            return (Criteria) this;
        }

        public Criteria andTurnrateGreaterThan(Double value) {
            addCriterion("turnrate >", value, "turnrate");
            return (Criteria) this;
        }

        public Criteria andTurnrateGreaterThanOrEqualTo(Double value) {
            addCriterion("turnrate >=", value, "turnrate");
            return (Criteria) this;
        }

        public Criteria andTurnrateLessThan(Double value) {
            addCriterion("turnrate <", value, "turnrate");
            return (Criteria) this;
        }

        public Criteria andTurnrateLessThanOrEqualTo(Double value) {
            addCriterion("turnrate <=", value, "turnrate");
            return (Criteria) this;
        }

        public Criteria andTurnrateIn(List<Double> values) {
            addCriterion("turnrate in", values, "turnrate");
            return (Criteria) this;
        }

        public Criteria andTurnrateNotIn(List<Double> values) {
            addCriterion("turnrate not in", values, "turnrate");
            return (Criteria) this;
        }

        public Criteria andTurnrateBetween(Double value1, Double value2) {
            addCriterion("turnrate between", value1, value2, "turnrate");
            return (Criteria) this;
        }

        public Criteria andTurnrateNotBetween(Double value1, Double value2) {
            addCriterion("turnrate not between", value1, value2, "turnrate");
            return (Criteria) this;
        }

        public Criteria andMa5IsNull() {
            addCriterion("ma5 is null");
            return (Criteria) this;
        }

        public Criteria andMa5IsNotNull() {
            addCriterion("ma5 is not null");
            return (Criteria) this;
        }

        public Criteria andMa5EqualTo(Double value) {
            addCriterion("ma5 =", value, "ma5");
            return (Criteria) this;
        }

        public Criteria andMa5NotEqualTo(Double value) {
            addCriterion("ma5 <>", value, "ma5");
            return (Criteria) this;
        }

        public Criteria andMa5GreaterThan(Double value) {
            addCriterion("ma5 >", value, "ma5");
            return (Criteria) this;
        }

        public Criteria andMa5GreaterThanOrEqualTo(Double value) {
            addCriterion("ma5 >=", value, "ma5");
            return (Criteria) this;
        }

        public Criteria andMa5LessThan(Double value) {
            addCriterion("ma5 <", value, "ma5");
            return (Criteria) this;
        }

        public Criteria andMa5LessThanOrEqualTo(Double value) {
            addCriterion("ma5 <=", value, "ma5");
            return (Criteria) this;
        }

        public Criteria andMa5In(List<Double> values) {
            addCriterion("ma5 in", values, "ma5");
            return (Criteria) this;
        }

        public Criteria andMa5NotIn(List<Double> values) {
            addCriterion("ma5 not in", values, "ma5");
            return (Criteria) this;
        }

        public Criteria andMa5Between(Double value1, Double value2) {
            addCriterion("ma5 between", value1, value2, "ma5");
            return (Criteria) this;
        }

        public Criteria andMa5NotBetween(Double value1, Double value2) {
            addCriterion("ma5 not between", value1, value2, "ma5");
            return (Criteria) this;
        }

        public Criteria andMa10IsNull() {
            addCriterion("ma10 is null");
            return (Criteria) this;
        }

        public Criteria andMa10IsNotNull() {
            addCriterion("ma10 is not null");
            return (Criteria) this;
        }

        public Criteria andMa10EqualTo(Double value) {
            addCriterion("ma10 =", value, "ma10");
            return (Criteria) this;
        }

        public Criteria andMa10NotEqualTo(Double value) {
            addCriterion("ma10 <>", value, "ma10");
            return (Criteria) this;
        }

        public Criteria andMa10GreaterThan(Double value) {
            addCriterion("ma10 >", value, "ma10");
            return (Criteria) this;
        }

        public Criteria andMa10GreaterThanOrEqualTo(Double value) {
            addCriterion("ma10 >=", value, "ma10");
            return (Criteria) this;
        }

        public Criteria andMa10LessThan(Double value) {
            addCriterion("ma10 <", value, "ma10");
            return (Criteria) this;
        }

        public Criteria andMa10LessThanOrEqualTo(Double value) {
            addCriterion("ma10 <=", value, "ma10");
            return (Criteria) this;
        }

        public Criteria andMa10In(List<Double> values) {
            addCriterion("ma10 in", values, "ma10");
            return (Criteria) this;
        }

        public Criteria andMa10NotIn(List<Double> values) {
            addCriterion("ma10 not in", values, "ma10");
            return (Criteria) this;
        }

        public Criteria andMa10Between(Double value1, Double value2) {
            addCriterion("ma10 between", value1, value2, "ma10");
            return (Criteria) this;
        }

        public Criteria andMa10NotBetween(Double value1, Double value2) {
            addCriterion("ma10 not between", value1, value2, "ma10");
            return (Criteria) this;
        }

        public Criteria andMa20IsNull() {
            addCriterion("ma20 is null");
            return (Criteria) this;
        }

        public Criteria andMa20IsNotNull() {
            addCriterion("ma20 is not null");
            return (Criteria) this;
        }

        public Criteria andMa20EqualTo(Double value) {
            addCriterion("ma20 =", value, "ma20");
            return (Criteria) this;
        }

        public Criteria andMa20NotEqualTo(Double value) {
            addCriterion("ma20 <>", value, "ma20");
            return (Criteria) this;
        }

        public Criteria andMa20GreaterThan(Double value) {
            addCriterion("ma20 >", value, "ma20");
            return (Criteria) this;
        }

        public Criteria andMa20GreaterThanOrEqualTo(Double value) {
            addCriterion("ma20 >=", value, "ma20");
            return (Criteria) this;
        }

        public Criteria andMa20LessThan(Double value) {
            addCriterion("ma20 <", value, "ma20");
            return (Criteria) this;
        }

        public Criteria andMa20LessThanOrEqualTo(Double value) {
            addCriterion("ma20 <=", value, "ma20");
            return (Criteria) this;
        }

        public Criteria andMa20In(List<Double> values) {
            addCriterion("ma20 in", values, "ma20");
            return (Criteria) this;
        }

        public Criteria andMa20NotIn(List<Double> values) {
            addCriterion("ma20 not in", values, "ma20");
            return (Criteria) this;
        }

        public Criteria andMa20Between(Double value1, Double value2) {
            addCriterion("ma20 between", value1, value2, "ma20");
            return (Criteria) this;
        }

        public Criteria andMa20NotBetween(Double value1, Double value2) {
            addCriterion("ma20 not between", value1, value2, "ma20");
            return (Criteria) this;
        }

        public Criteria andMa30IsNull() {
            addCriterion("ma30 is null");
            return (Criteria) this;
        }

        public Criteria andMa30IsNotNull() {
            addCriterion("ma30 is not null");
            return (Criteria) this;
        }

        public Criteria andMa30EqualTo(Double value) {
            addCriterion("ma30 =", value, "ma30");
            return (Criteria) this;
        }

        public Criteria andMa30NotEqualTo(Double value) {
            addCriterion("ma30 <>", value, "ma30");
            return (Criteria) this;
        }

        public Criteria andMa30GreaterThan(Double value) {
            addCriterion("ma30 >", value, "ma30");
            return (Criteria) this;
        }

        public Criteria andMa30GreaterThanOrEqualTo(Double value) {
            addCriterion("ma30 >=", value, "ma30");
            return (Criteria) this;
        }

        public Criteria andMa30LessThan(Double value) {
            addCriterion("ma30 <", value, "ma30");
            return (Criteria) this;
        }

        public Criteria andMa30LessThanOrEqualTo(Double value) {
            addCriterion("ma30 <=", value, "ma30");
            return (Criteria) this;
        }

        public Criteria andMa30In(List<Double> values) {
            addCriterion("ma30 in", values, "ma30");
            return (Criteria) this;
        }

        public Criteria andMa30NotIn(List<Double> values) {
            addCriterion("ma30 not in", values, "ma30");
            return (Criteria) this;
        }

        public Criteria andMa30Between(Double value1, Double value2) {
            addCriterion("ma30 between", value1, value2, "ma30");
            return (Criteria) this;
        }

        public Criteria andMa30NotBetween(Double value1, Double value2) {
            addCriterion("ma30 not between", value1, value2, "ma30");
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

        public Criteria andAmountIsNull() {
            addCriterion("amount is null");
            return (Criteria) this;
        }

        public Criteria andAmountIsNotNull() {
            addCriterion("amount is not null");
            return (Criteria) this;
        }

        public Criteria andAmountEqualTo(Double value) {
            addCriterion("amount =", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountNotEqualTo(Double value) {
            addCriterion("amount <>", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountGreaterThan(Double value) {
            addCriterion("amount >", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountGreaterThanOrEqualTo(Double value) {
            addCriterion("amount >=", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountLessThan(Double value) {
            addCriterion("amount <", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountLessThanOrEqualTo(Double value) {
            addCriterion("amount <=", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountIn(List<Double> values) {
            addCriterion("amount in", values, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountNotIn(List<Double> values) {
            addCriterion("amount not in", values, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountBetween(Double value1, Double value2) {
            addCriterion("amount between", value1, value2, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountNotBetween(Double value1, Double value2) {
            addCriterion("amount not between", value1, value2, "amount");
            return (Criteria) this;
        }

        public Criteria andYesterdayCloseIsNull() {
            addCriterion("yesterday_close is null");
            return (Criteria) this;
        }

        public Criteria andYesterdayCloseIsNotNull() {
            addCriterion("yesterday_close is not null");
            return (Criteria) this;
        }

        public Criteria andYesterdayCloseEqualTo(Double value) {
            addCriterion("yesterday_close =", value, "yesterdayClose");
            return (Criteria) this;
        }

        public Criteria andYesterdayCloseNotEqualTo(Double value) {
            addCriterion("yesterday_close <>", value, "yesterdayClose");
            return (Criteria) this;
        }

        public Criteria andYesterdayCloseGreaterThan(Double value) {
            addCriterion("yesterday_close >", value, "yesterdayClose");
            return (Criteria) this;
        }

        public Criteria andYesterdayCloseGreaterThanOrEqualTo(Double value) {
            addCriterion("yesterday_close >=", value, "yesterdayClose");
            return (Criteria) this;
        }

        public Criteria andYesterdayCloseLessThan(Double value) {
            addCriterion("yesterday_close <", value, "yesterdayClose");
            return (Criteria) this;
        }

        public Criteria andYesterdayCloseLessThanOrEqualTo(Double value) {
            addCriterion("yesterday_close <=", value, "yesterdayClose");
            return (Criteria) this;
        }

        public Criteria andYesterdayCloseIn(List<Double> values) {
            addCriterion("yesterday_close in", values, "yesterdayClose");
            return (Criteria) this;
        }

        public Criteria andYesterdayCloseNotIn(List<Double> values) {
            addCriterion("yesterday_close not in", values, "yesterdayClose");
            return (Criteria) this;
        }

        public Criteria andYesterdayCloseBetween(Double value1, Double value2) {
            addCriterion("yesterday_close between", value1, value2, "yesterdayClose");
            return (Criteria) this;
        }

        public Criteria andYesterdayCloseNotBetween(Double value1, Double value2) {
            addCriterion("yesterday_close not between", value1, value2, "yesterdayClose");
            return (Criteria) this;
        }

        public Criteria andShapeIsNull() {
            addCriterion("shape is null");
            return (Criteria) this;
        }

        public Criteria andShapeIsNotNull() {
            addCriterion("shape is not null");
            return (Criteria) this;
        }

        public Criteria andShapeEqualTo(Integer value) {
            addCriterion("shape =", value, "shape");
            return (Criteria) this;
        }

        public Criteria andShapeNotEqualTo(Integer value) {
            addCriterion("shape <>", value, "shape");
            return (Criteria) this;
        }

        public Criteria andShapeGreaterThan(Integer value) {
            addCriterion("shape >", value, "shape");
            return (Criteria) this;
        }

        public Criteria andShapeGreaterThanOrEqualTo(Integer value) {
            addCriterion("shape >=", value, "shape");
            return (Criteria) this;
        }

        public Criteria andShapeLessThan(Integer value) {
            addCriterion("shape <", value, "shape");
            return (Criteria) this;
        }

        public Criteria andShapeLessThanOrEqualTo(Integer value) {
            addCriterion("shape <=", value, "shape");
            return (Criteria) this;
        }

        public Criteria andShapeIn(List<Integer> values) {
            addCriterion("shape in", values, "shape");
            return (Criteria) this;
        }

        public Criteria andShapeNotIn(List<Integer> values) {
            addCriterion("shape not in", values, "shape");
            return (Criteria) this;
        }

        public Criteria andShapeBetween(Integer value1, Integer value2) {
            addCriterion("shape between", value1, value2, "shape");
            return (Criteria) this;
        }

        public Criteria andShapeNotBetween(Integer value1, Integer value2) {
            addCriterion("shape not between", value1, value2, "shape");
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