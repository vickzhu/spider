package com.gesangwu.spider.biz.dao.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LongHuExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected int offset = -1;

    protected int rows = -1;

    public LongHuExample() {
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

        public Criteria andStockNameIsNull() {
            addCriterion("stock_name is null");
            return (Criteria) this;
        }

        public Criteria andStockNameIsNotNull() {
            addCriterion("stock_name is not null");
            return (Criteria) this;
        }

        public Criteria andStockNameEqualTo(String value) {
            addCriterion("stock_name =", value, "stockName");
            return (Criteria) this;
        }

        public Criteria andStockNameNotEqualTo(String value) {
            addCriterion("stock_name <>", value, "stockName");
            return (Criteria) this;
        }

        public Criteria andStockNameGreaterThan(String value) {
            addCriterion("stock_name >", value, "stockName");
            return (Criteria) this;
        }

        public Criteria andStockNameGreaterThanOrEqualTo(String value) {
            addCriterion("stock_name >=", value, "stockName");
            return (Criteria) this;
        }

        public Criteria andStockNameLessThan(String value) {
            addCriterion("stock_name <", value, "stockName");
            return (Criteria) this;
        }

        public Criteria andStockNameLessThanOrEqualTo(String value) {
            addCriterion("stock_name <=", value, "stockName");
            return (Criteria) this;
        }

        public Criteria andStockNameLike(String value) {
            addCriterion("stock_name like", value, "stockName");
            return (Criteria) this;
        }

        public Criteria andStockNameNotLike(String value) {
            addCriterion("stock_name not like", value, "stockName");
            return (Criteria) this;
        }

        public Criteria andStockNameIn(List<String> values) {
            addCriterion("stock_name in", values, "stockName");
            return (Criteria) this;
        }

        public Criteria andStockNameNotIn(List<String> values) {
            addCriterion("stock_name not in", values, "stockName");
            return (Criteria) this;
        }

        public Criteria andStockNameBetween(String value1, String value2) {
            addCriterion("stock_name between", value1, value2, "stockName");
            return (Criteria) this;
        }

        public Criteria andStockNameNotBetween(String value1, String value2) {
            addCriterion("stock_name not between", value1, value2, "stockName");
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

        public Criteria andPriceIsNull() {
            addCriterion("price is null");
            return (Criteria) this;
        }

        public Criteria andPriceIsNotNull() {
            addCriterion("price is not null");
            return (Criteria) this;
        }

        public Criteria andPriceEqualTo(Double value) {
            addCriterion("price =", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceNotEqualTo(Double value) {
            addCriterion("price <>", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceGreaterThan(Double value) {
            addCriterion("price >", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceGreaterThanOrEqualTo(Double value) {
            addCriterion("price >=", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceLessThan(Double value) {
            addCriterion("price <", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceLessThanOrEqualTo(Double value) {
            addCriterion("price <=", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceIn(List<Double> values) {
            addCriterion("price in", values, "price");
            return (Criteria) this;
        }

        public Criteria andPriceNotIn(List<Double> values) {
            addCriterion("price not in", values, "price");
            return (Criteria) this;
        }

        public Criteria andPriceBetween(Double value1, Double value2) {
            addCriterion("price between", value1, value2, "price");
            return (Criteria) this;
        }

        public Criteria andPriceNotBetween(Double value1, Double value2) {
            addCriterion("price not between", value1, value2, "price");
            return (Criteria) this;
        }

        public Criteria andChgIsNull() {
            addCriterion("chg is null");
            return (Criteria) this;
        }

        public Criteria andChgIsNotNull() {
            addCriterion("chg is not null");
            return (Criteria) this;
        }

        public Criteria andChgEqualTo(Double value) {
            addCriterion("chg =", value, "chg");
            return (Criteria) this;
        }

        public Criteria andChgNotEqualTo(Double value) {
            addCriterion("chg <>", value, "chg");
            return (Criteria) this;
        }

        public Criteria andChgGreaterThan(Double value) {
            addCriterion("chg >", value, "chg");
            return (Criteria) this;
        }

        public Criteria andChgGreaterThanOrEqualTo(Double value) {
            addCriterion("chg >=", value, "chg");
            return (Criteria) this;
        }

        public Criteria andChgLessThan(Double value) {
            addCriterion("chg <", value, "chg");
            return (Criteria) this;
        }

        public Criteria andChgLessThanOrEqualTo(Double value) {
            addCriterion("chg <=", value, "chg");
            return (Criteria) this;
        }

        public Criteria andChgIn(List<Double> values) {
            addCriterion("chg in", values, "chg");
            return (Criteria) this;
        }

        public Criteria andChgNotIn(List<Double> values) {
            addCriterion("chg not in", values, "chg");
            return (Criteria) this;
        }

        public Criteria andChgBetween(Double value1, Double value2) {
            addCriterion("chg between", value1, value2, "chg");
            return (Criteria) this;
        }

        public Criteria andChgNotBetween(Double value1, Double value2) {
            addCriterion("chg not between", value1, value2, "chg");
            return (Criteria) this;
        }

        public Criteria andChgPercentIsNull() {
            addCriterion("chg_percent is null");
            return (Criteria) this;
        }

        public Criteria andChgPercentIsNotNull() {
            addCriterion("chg_percent is not null");
            return (Criteria) this;
        }

        public Criteria andChgPercentEqualTo(Double value) {
            addCriterion("chg_percent =", value, "chgPercent");
            return (Criteria) this;
        }

        public Criteria andChgPercentNotEqualTo(Double value) {
            addCriterion("chg_percent <>", value, "chgPercent");
            return (Criteria) this;
        }

        public Criteria andChgPercentGreaterThan(Double value) {
            addCriterion("chg_percent >", value, "chgPercent");
            return (Criteria) this;
        }

        public Criteria andChgPercentGreaterThanOrEqualTo(Double value) {
            addCriterion("chg_percent >=", value, "chgPercent");
            return (Criteria) this;
        }

        public Criteria andChgPercentLessThan(Double value) {
            addCriterion("chg_percent <", value, "chgPercent");
            return (Criteria) this;
        }

        public Criteria andChgPercentLessThanOrEqualTo(Double value) {
            addCriterion("chg_percent <=", value, "chgPercent");
            return (Criteria) this;
        }

        public Criteria andChgPercentIn(List<Double> values) {
            addCriterion("chg_percent in", values, "chgPercent");
            return (Criteria) this;
        }

        public Criteria andChgPercentNotIn(List<Double> values) {
            addCriterion("chg_percent not in", values, "chgPercent");
            return (Criteria) this;
        }

        public Criteria andChgPercentBetween(Double value1, Double value2) {
            addCriterion("chg_percent between", value1, value2, "chgPercent");
            return (Criteria) this;
        }

        public Criteria andChgPercentNotBetween(Double value1, Double value2) {
            addCriterion("chg_percent not between", value1, value2, "chgPercent");
            return (Criteria) this;
        }

        public Criteria andTurnoverIsNull() {
            addCriterion("turnover is null");
            return (Criteria) this;
        }

        public Criteria andTurnoverIsNotNull() {
            addCriterion("turnover is not null");
            return (Criteria) this;
        }

        public Criteria andTurnoverEqualTo(Double value) {
            addCriterion("turnover =", value, "turnover");
            return (Criteria) this;
        }

        public Criteria andTurnoverNotEqualTo(Double value) {
            addCriterion("turnover <>", value, "turnover");
            return (Criteria) this;
        }

        public Criteria andTurnoverGreaterThan(Double value) {
            addCriterion("turnover >", value, "turnover");
            return (Criteria) this;
        }

        public Criteria andTurnoverGreaterThanOrEqualTo(Double value) {
            addCriterion("turnover >=", value, "turnover");
            return (Criteria) this;
        }

        public Criteria andTurnoverLessThan(Double value) {
            addCriterion("turnover <", value, "turnover");
            return (Criteria) this;
        }

        public Criteria andTurnoverLessThanOrEqualTo(Double value) {
            addCriterion("turnover <=", value, "turnover");
            return (Criteria) this;
        }

        public Criteria andTurnoverIn(List<Double> values) {
            addCriterion("turnover in", values, "turnover");
            return (Criteria) this;
        }

        public Criteria andTurnoverNotIn(List<Double> values) {
            addCriterion("turnover not in", values, "turnover");
            return (Criteria) this;
        }

        public Criteria andTurnoverBetween(Double value1, Double value2) {
            addCriterion("turnover between", value1, value2, "turnover");
            return (Criteria) this;
        }

        public Criteria andTurnoverNotBetween(Double value1, Double value2) {
            addCriterion("turnover not between", value1, value2, "turnover");
            return (Criteria) this;
        }

        public Criteria andAmplitudeIsNull() {
            addCriterion("amplitude is null");
            return (Criteria) this;
        }

        public Criteria andAmplitudeIsNotNull() {
            addCriterion("amplitude is not null");
            return (Criteria) this;
        }

        public Criteria andAmplitudeEqualTo(Double value) {
            addCriterion("amplitude =", value, "amplitude");
            return (Criteria) this;
        }

        public Criteria andAmplitudeNotEqualTo(Double value) {
            addCriterion("amplitude <>", value, "amplitude");
            return (Criteria) this;
        }

        public Criteria andAmplitudeGreaterThan(Double value) {
            addCriterion("amplitude >", value, "amplitude");
            return (Criteria) this;
        }

        public Criteria andAmplitudeGreaterThanOrEqualTo(Double value) {
            addCriterion("amplitude >=", value, "amplitude");
            return (Criteria) this;
        }

        public Criteria andAmplitudeLessThan(Double value) {
            addCriterion("amplitude <", value, "amplitude");
            return (Criteria) this;
        }

        public Criteria andAmplitudeLessThanOrEqualTo(Double value) {
            addCriterion("amplitude <=", value, "amplitude");
            return (Criteria) this;
        }

        public Criteria andAmplitudeIn(List<Double> values) {
            addCriterion("amplitude in", values, "amplitude");
            return (Criteria) this;
        }

        public Criteria andAmplitudeNotIn(List<Double> values) {
            addCriterion("amplitude not in", values, "amplitude");
            return (Criteria) this;
        }

        public Criteria andAmplitudeBetween(Double value1, Double value2) {
            addCriterion("amplitude between", value1, value2, "amplitude");
            return (Criteria) this;
        }

        public Criteria andAmplitudeNotBetween(Double value1, Double value2) {
            addCriterion("amplitude not between", value1, value2, "amplitude");
            return (Criteria) this;
        }

        public Criteria andTotMktValIsNull() {
            addCriterion("tot_mkt_val is null");
            return (Criteria) this;
        }

        public Criteria andTotMktValIsNotNull() {
            addCriterion("tot_mkt_val is not null");
            return (Criteria) this;
        }

        public Criteria andTotMktValEqualTo(Double value) {
            addCriterion("tot_mkt_val =", value, "totMktVal");
            return (Criteria) this;
        }

        public Criteria andTotMktValNotEqualTo(Double value) {
            addCriterion("tot_mkt_val <>", value, "totMktVal");
            return (Criteria) this;
        }

        public Criteria andTotMktValGreaterThan(Double value) {
            addCriterion("tot_mkt_val >", value, "totMktVal");
            return (Criteria) this;
        }

        public Criteria andTotMktValGreaterThanOrEqualTo(Double value) {
            addCriterion("tot_mkt_val >=", value, "totMktVal");
            return (Criteria) this;
        }

        public Criteria andTotMktValLessThan(Double value) {
            addCriterion("tot_mkt_val <", value, "totMktVal");
            return (Criteria) this;
        }

        public Criteria andTotMktValLessThanOrEqualTo(Double value) {
            addCriterion("tot_mkt_val <=", value, "totMktVal");
            return (Criteria) this;
        }

        public Criteria andTotMktValIn(List<Double> values) {
            addCriterion("tot_mkt_val in", values, "totMktVal");
            return (Criteria) this;
        }

        public Criteria andTotMktValNotIn(List<Double> values) {
            addCriterion("tot_mkt_val not in", values, "totMktVal");
            return (Criteria) this;
        }

        public Criteria andTotMktValBetween(Double value1, Double value2) {
            addCriterion("tot_mkt_val between", value1, value2, "totMktVal");
            return (Criteria) this;
        }

        public Criteria andTotMktValNotBetween(Double value1, Double value2) {
            addCriterion("tot_mkt_val not between", value1, value2, "totMktVal");
            return (Criteria) this;
        }

        public Criteria andNegMktValIsNull() {
            addCriterion("neg_mkt_val is null");
            return (Criteria) this;
        }

        public Criteria andNegMktValIsNotNull() {
            addCriterion("neg_mkt_val is not null");
            return (Criteria) this;
        }

        public Criteria andNegMktValEqualTo(Double value) {
            addCriterion("neg_mkt_val =", value, "negMktVal");
            return (Criteria) this;
        }

        public Criteria andNegMktValNotEqualTo(Double value) {
            addCriterion("neg_mkt_val <>", value, "negMktVal");
            return (Criteria) this;
        }

        public Criteria andNegMktValGreaterThan(Double value) {
            addCriterion("neg_mkt_val >", value, "negMktVal");
            return (Criteria) this;
        }

        public Criteria andNegMktValGreaterThanOrEqualTo(Double value) {
            addCriterion("neg_mkt_val >=", value, "negMktVal");
            return (Criteria) this;
        }

        public Criteria andNegMktValLessThan(Double value) {
            addCriterion("neg_mkt_val <", value, "negMktVal");
            return (Criteria) this;
        }

        public Criteria andNegMktValLessThanOrEqualTo(Double value) {
            addCriterion("neg_mkt_val <=", value, "negMktVal");
            return (Criteria) this;
        }

        public Criteria andNegMktValIn(List<Double> values) {
            addCriterion("neg_mkt_val in", values, "negMktVal");
            return (Criteria) this;
        }

        public Criteria andNegMktValNotIn(List<Double> values) {
            addCriterion("neg_mkt_val not in", values, "negMktVal");
            return (Criteria) this;
        }

        public Criteria andNegMktValBetween(Double value1, Double value2) {
            addCriterion("neg_mkt_val between", value1, value2, "negMktVal");
            return (Criteria) this;
        }

        public Criteria andNegMktValNotBetween(Double value1, Double value2) {
            addCriterion("neg_mkt_val not between", value1, value2, "negMktVal");
            return (Criteria) this;
        }

        public Criteria andSecDeptRelationIsNull() {
            addCriterion("sec_dept_relation is null");
            return (Criteria) this;
        }

        public Criteria andSecDeptRelationIsNotNull() {
            addCriterion("sec_dept_relation is not null");
            return (Criteria) this;
        }

        public Criteria andSecDeptRelationEqualTo(Integer value) {
            addCriterion("sec_dept_relation =", value, "secDeptRelation");
            return (Criteria) this;
        }

        public Criteria andSecDeptRelationNotEqualTo(Integer value) {
            addCriterion("sec_dept_relation <>", value, "secDeptRelation");
            return (Criteria) this;
        }

        public Criteria andSecDeptRelationGreaterThan(Integer value) {
            addCriterion("sec_dept_relation >", value, "secDeptRelation");
            return (Criteria) this;
        }

        public Criteria andSecDeptRelationGreaterThanOrEqualTo(Integer value) {
            addCriterion("sec_dept_relation >=", value, "secDeptRelation");
            return (Criteria) this;
        }

        public Criteria andSecDeptRelationLessThan(Integer value) {
            addCriterion("sec_dept_relation <", value, "secDeptRelation");
            return (Criteria) this;
        }

        public Criteria andSecDeptRelationLessThanOrEqualTo(Integer value) {
            addCriterion("sec_dept_relation <=", value, "secDeptRelation");
            return (Criteria) this;
        }

        public Criteria andSecDeptRelationIn(List<Integer> values) {
            addCriterion("sec_dept_relation in", values, "secDeptRelation");
            return (Criteria) this;
        }

        public Criteria andSecDeptRelationNotIn(List<Integer> values) {
            addCriterion("sec_dept_relation not in", values, "secDeptRelation");
            return (Criteria) this;
        }

        public Criteria andSecDeptRelationBetween(Integer value1, Integer value2) {
            addCriterion("sec_dept_relation between", value1, value2, "secDeptRelation");
            return (Criteria) this;
        }

        public Criteria andSecDeptRelationNotBetween(Integer value1, Integer value2) {
            addCriterion("sec_dept_relation not between", value1, value2, "secDeptRelation");
            return (Criteria) this;
        }

        public Criteria andOperateCliqueIsNull() {
            addCriterion("operate_clique is null");
            return (Criteria) this;
        }

        public Criteria andOperateCliqueIsNotNull() {
            addCriterion("operate_clique is not null");
            return (Criteria) this;
        }

        public Criteria andOperateCliqueEqualTo(Long value) {
            addCriterion("operate_clique =", value, "operateClique");
            return (Criteria) this;
        }

        public Criteria andOperateCliqueNotEqualTo(Long value) {
            addCriterion("operate_clique <>", value, "operateClique");
            return (Criteria) this;
        }

        public Criteria andOperateCliqueGreaterThan(Long value) {
            addCriterion("operate_clique >", value, "operateClique");
            return (Criteria) this;
        }

        public Criteria andOperateCliqueGreaterThanOrEqualTo(Long value) {
            addCriterion("operate_clique >=", value, "operateClique");
            return (Criteria) this;
        }

        public Criteria andOperateCliqueLessThan(Long value) {
            addCriterion("operate_clique <", value, "operateClique");
            return (Criteria) this;
        }

        public Criteria andOperateCliqueLessThanOrEqualTo(Long value) {
            addCriterion("operate_clique <=", value, "operateClique");
            return (Criteria) this;
        }

        public Criteria andOperateCliqueIn(List<Long> values) {
            addCriterion("operate_clique in", values, "operateClique");
            return (Criteria) this;
        }

        public Criteria andOperateCliqueNotIn(List<Long> values) {
            addCriterion("operate_clique not in", values, "operateClique");
            return (Criteria) this;
        }

        public Criteria andOperateCliqueBetween(Long value1, Long value2) {
            addCriterion("operate_clique between", value1, value2, "operateClique");
            return (Criteria) this;
        }

        public Criteria andOperateCliqueNotBetween(Long value1, Long value2) {
            addCriterion("operate_clique not between", value1, value2, "operateClique");
            return (Criteria) this;
        }

        public Criteria andYrTypeIsNull() {
            addCriterion("yr_type is null");
            return (Criteria) this;
        }

        public Criteria andYrTypeIsNotNull() {
            addCriterion("yr_type is not null");
            return (Criteria) this;
        }

        public Criteria andYrTypeEqualTo(String value) {
            addCriterion("yr_type =", value, "yrType");
            return (Criteria) this;
        }

        public Criteria andYrTypeNotEqualTo(String value) {
            addCriterion("yr_type <>", value, "yrType");
            return (Criteria) this;
        }

        public Criteria andYrTypeGreaterThan(String value) {
            addCriterion("yr_type >", value, "yrType");
            return (Criteria) this;
        }

        public Criteria andYrTypeGreaterThanOrEqualTo(String value) {
            addCriterion("yr_type >=", value, "yrType");
            return (Criteria) this;
        }

        public Criteria andYrTypeLessThan(String value) {
            addCriterion("yr_type <", value, "yrType");
            return (Criteria) this;
        }

        public Criteria andYrTypeLessThanOrEqualTo(String value) {
            addCriterion("yr_type <=", value, "yrType");
            return (Criteria) this;
        }

        public Criteria andYrTypeLike(String value) {
            addCriterion("yr_type like", value, "yrType");
            return (Criteria) this;
        }

        public Criteria andYrTypeNotLike(String value) {
            addCriterion("yr_type not like", value, "yrType");
            return (Criteria) this;
        }

        public Criteria andYrTypeIn(List<String> values) {
            addCriterion("yr_type in", values, "yrType");
            return (Criteria) this;
        }

        public Criteria andYrTypeNotIn(List<String> values) {
            addCriterion("yr_type not in", values, "yrType");
            return (Criteria) this;
        }

        public Criteria andYrTypeBetween(String value1, String value2) {
            addCriterion("yr_type between", value1, value2, "yrType");
            return (Criteria) this;
        }

        public Criteria andYrTypeNotBetween(String value1, String value2) {
            addCriterion("yr_type not between", value1, value2, "yrType");
            return (Criteria) this;
        }

        public Criteria andYrAmtIsNull() {
            addCriterion("yr_amt is null");
            return (Criteria) this;
        }

        public Criteria andYrAmtIsNotNull() {
            addCriterion("yr_amt is not null");
            return (Criteria) this;
        }

        public Criteria andYrAmtEqualTo(String value) {
            addCriterion("yr_amt =", value, "yrAmt");
            return (Criteria) this;
        }

        public Criteria andYrAmtNotEqualTo(String value) {
            addCriterion("yr_amt <>", value, "yrAmt");
            return (Criteria) this;
        }

        public Criteria andYrAmtGreaterThan(String value) {
            addCriterion("yr_amt >", value, "yrAmt");
            return (Criteria) this;
        }

        public Criteria andYrAmtGreaterThanOrEqualTo(String value) {
            addCriterion("yr_amt >=", value, "yrAmt");
            return (Criteria) this;
        }

        public Criteria andYrAmtLessThan(String value) {
            addCriterion("yr_amt <", value, "yrAmt");
            return (Criteria) this;
        }

        public Criteria andYrAmtLessThanOrEqualTo(String value) {
            addCriterion("yr_amt <=", value, "yrAmt");
            return (Criteria) this;
        }

        public Criteria andYrAmtLike(String value) {
            addCriterion("yr_amt like", value, "yrAmt");
            return (Criteria) this;
        }

        public Criteria andYrAmtNotLike(String value) {
            addCriterion("yr_amt not like", value, "yrAmt");
            return (Criteria) this;
        }

        public Criteria andYrAmtIn(List<String> values) {
            addCriterion("yr_amt in", values, "yrAmt");
            return (Criteria) this;
        }

        public Criteria andYrAmtNotIn(List<String> values) {
            addCriterion("yr_amt not in", values, "yrAmt");
            return (Criteria) this;
        }

        public Criteria andYrAmtBetween(String value1, String value2) {
            addCriterion("yr_amt between", value1, value2, "yrAmt");
            return (Criteria) this;
        }

        public Criteria andYrAmtNotBetween(String value1, String value2) {
            addCriterion("yr_amt not between", value1, value2, "yrAmt");
            return (Criteria) this;
        }

        public Criteria andErTypeIsNull() {
            addCriterion("er_type is null");
            return (Criteria) this;
        }

        public Criteria andErTypeIsNotNull() {
            addCriterion("er_type is not null");
            return (Criteria) this;
        }

        public Criteria andErTypeEqualTo(String value) {
            addCriterion("er_type =", value, "erType");
            return (Criteria) this;
        }

        public Criteria andErTypeNotEqualTo(String value) {
            addCriterion("er_type <>", value, "erType");
            return (Criteria) this;
        }

        public Criteria andErTypeGreaterThan(String value) {
            addCriterion("er_type >", value, "erType");
            return (Criteria) this;
        }

        public Criteria andErTypeGreaterThanOrEqualTo(String value) {
            addCriterion("er_type >=", value, "erType");
            return (Criteria) this;
        }

        public Criteria andErTypeLessThan(String value) {
            addCriterion("er_type <", value, "erType");
            return (Criteria) this;
        }

        public Criteria andErTypeLessThanOrEqualTo(String value) {
            addCriterion("er_type <=", value, "erType");
            return (Criteria) this;
        }

        public Criteria andErTypeLike(String value) {
            addCriterion("er_type like", value, "erType");
            return (Criteria) this;
        }

        public Criteria andErTypeNotLike(String value) {
            addCriterion("er_type not like", value, "erType");
            return (Criteria) this;
        }

        public Criteria andErTypeIn(List<String> values) {
            addCriterion("er_type in", values, "erType");
            return (Criteria) this;
        }

        public Criteria andErTypeNotIn(List<String> values) {
            addCriterion("er_type not in", values, "erType");
            return (Criteria) this;
        }

        public Criteria andErTypeBetween(String value1, String value2) {
            addCriterion("er_type between", value1, value2, "erType");
            return (Criteria) this;
        }

        public Criteria andErTypeNotBetween(String value1, String value2) {
            addCriterion("er_type not between", value1, value2, "erType");
            return (Criteria) this;
        }

        public Criteria andErAmtIsNull() {
            addCriterion("er_amt is null");
            return (Criteria) this;
        }

        public Criteria andErAmtIsNotNull() {
            addCriterion("er_amt is not null");
            return (Criteria) this;
        }

        public Criteria andErAmtEqualTo(String value) {
            addCriterion("er_amt =", value, "erAmt");
            return (Criteria) this;
        }

        public Criteria andErAmtNotEqualTo(String value) {
            addCriterion("er_amt <>", value, "erAmt");
            return (Criteria) this;
        }

        public Criteria andErAmtGreaterThan(String value) {
            addCriterion("er_amt >", value, "erAmt");
            return (Criteria) this;
        }

        public Criteria andErAmtGreaterThanOrEqualTo(String value) {
            addCriterion("er_amt >=", value, "erAmt");
            return (Criteria) this;
        }

        public Criteria andErAmtLessThan(String value) {
            addCriterion("er_amt <", value, "erAmt");
            return (Criteria) this;
        }

        public Criteria andErAmtLessThanOrEqualTo(String value) {
            addCriterion("er_amt <=", value, "erAmt");
            return (Criteria) this;
        }

        public Criteria andErAmtLike(String value) {
            addCriterion("er_amt like", value, "erAmt");
            return (Criteria) this;
        }

        public Criteria andErAmtNotLike(String value) {
            addCriterion("er_amt not like", value, "erAmt");
            return (Criteria) this;
        }

        public Criteria andErAmtIn(List<String> values) {
            addCriterion("er_amt in", values, "erAmt");
            return (Criteria) this;
        }

        public Criteria andErAmtNotIn(List<String> values) {
            addCriterion("er_amt not in", values, "erAmt");
            return (Criteria) this;
        }

        public Criteria andErAmtBetween(String value1, String value2) {
            addCriterion("er_amt between", value1, value2, "erAmt");
            return (Criteria) this;
        }

        public Criteria andErAmtNotBetween(String value1, String value2) {
            addCriterion("er_amt not between", value1, value2, "erAmt");
            return (Criteria) this;
        }

        public Criteria andSrTypeIsNull() {
            addCriterion("sr_type is null");
            return (Criteria) this;
        }

        public Criteria andSrTypeIsNotNull() {
            addCriterion("sr_type is not null");
            return (Criteria) this;
        }

        public Criteria andSrTypeEqualTo(String value) {
            addCriterion("sr_type =", value, "srType");
            return (Criteria) this;
        }

        public Criteria andSrTypeNotEqualTo(String value) {
            addCriterion("sr_type <>", value, "srType");
            return (Criteria) this;
        }

        public Criteria andSrTypeGreaterThan(String value) {
            addCriterion("sr_type >", value, "srType");
            return (Criteria) this;
        }

        public Criteria andSrTypeGreaterThanOrEqualTo(String value) {
            addCriterion("sr_type >=", value, "srType");
            return (Criteria) this;
        }

        public Criteria andSrTypeLessThan(String value) {
            addCriterion("sr_type <", value, "srType");
            return (Criteria) this;
        }

        public Criteria andSrTypeLessThanOrEqualTo(String value) {
            addCriterion("sr_type <=", value, "srType");
            return (Criteria) this;
        }

        public Criteria andSrTypeLike(String value) {
            addCriterion("sr_type like", value, "srType");
            return (Criteria) this;
        }

        public Criteria andSrTypeNotLike(String value) {
            addCriterion("sr_type not like", value, "srType");
            return (Criteria) this;
        }

        public Criteria andSrTypeIn(List<String> values) {
            addCriterion("sr_type in", values, "srType");
            return (Criteria) this;
        }

        public Criteria andSrTypeNotIn(List<String> values) {
            addCriterion("sr_type not in", values, "srType");
            return (Criteria) this;
        }

        public Criteria andSrTypeBetween(String value1, String value2) {
            addCriterion("sr_type between", value1, value2, "srType");
            return (Criteria) this;
        }

        public Criteria andSrTypeNotBetween(String value1, String value2) {
            addCriterion("sr_type not between", value1, value2, "srType");
            return (Criteria) this;
        }

        public Criteria andSrAmtIsNull() {
            addCriterion("sr_amt is null");
            return (Criteria) this;
        }

        public Criteria andSrAmtIsNotNull() {
            addCriterion("sr_amt is not null");
            return (Criteria) this;
        }

        public Criteria andSrAmtEqualTo(String value) {
            addCriterion("sr_amt =", value, "srAmt");
            return (Criteria) this;
        }

        public Criteria andSrAmtNotEqualTo(String value) {
            addCriterion("sr_amt <>", value, "srAmt");
            return (Criteria) this;
        }

        public Criteria andSrAmtGreaterThan(String value) {
            addCriterion("sr_amt >", value, "srAmt");
            return (Criteria) this;
        }

        public Criteria andSrAmtGreaterThanOrEqualTo(String value) {
            addCriterion("sr_amt >=", value, "srAmt");
            return (Criteria) this;
        }

        public Criteria andSrAmtLessThan(String value) {
            addCriterion("sr_amt <", value, "srAmt");
            return (Criteria) this;
        }

        public Criteria andSrAmtLessThanOrEqualTo(String value) {
            addCriterion("sr_amt <=", value, "srAmt");
            return (Criteria) this;
        }

        public Criteria andSrAmtLike(String value) {
            addCriterion("sr_amt like", value, "srAmt");
            return (Criteria) this;
        }

        public Criteria andSrAmtNotLike(String value) {
            addCriterion("sr_amt not like", value, "srAmt");
            return (Criteria) this;
        }

        public Criteria andSrAmtIn(List<String> values) {
            addCriterion("sr_amt in", values, "srAmt");
            return (Criteria) this;
        }

        public Criteria andSrAmtNotIn(List<String> values) {
            addCriterion("sr_amt not in", values, "srAmt");
            return (Criteria) this;
        }

        public Criteria andSrAmtBetween(String value1, String value2) {
            addCriterion("sr_amt between", value1, value2, "srAmt");
            return (Criteria) this;
        }

        public Criteria andSrAmtNotBetween(String value1, String value2) {
            addCriterion("sr_amt not between", value1, value2, "srAmt");
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