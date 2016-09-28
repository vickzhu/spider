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

        public Criteria andDrLhTypeIsNull() {
            addCriterion("dr_lh_type is null");
            return (Criteria) this;
        }

        public Criteria andDrLhTypeIsNotNull() {
            addCriterion("dr_lh_type is not null");
            return (Criteria) this;
        }

        public Criteria andDrLhTypeEqualTo(String value) {
            addCriterion("dr_lh_type =", value, "drLhType");
            return (Criteria) this;
        }

        public Criteria andDrLhTypeNotEqualTo(String value) {
            addCriterion("dr_lh_type <>", value, "drLhType");
            return (Criteria) this;
        }

        public Criteria andDrLhTypeGreaterThan(String value) {
            addCriterion("dr_lh_type >", value, "drLhType");
            return (Criteria) this;
        }

        public Criteria andDrLhTypeGreaterThanOrEqualTo(String value) {
            addCriterion("dr_lh_type >=", value, "drLhType");
            return (Criteria) this;
        }

        public Criteria andDrLhTypeLessThan(String value) {
            addCriterion("dr_lh_type <", value, "drLhType");
            return (Criteria) this;
        }

        public Criteria andDrLhTypeLessThanOrEqualTo(String value) {
            addCriterion("dr_lh_type <=", value, "drLhType");
            return (Criteria) this;
        }

        public Criteria andDrLhTypeLike(String value) {
            addCriterion("dr_lh_type like", value, "drLhType");
            return (Criteria) this;
        }

        public Criteria andDrLhTypeNotLike(String value) {
            addCriterion("dr_lh_type not like", value, "drLhType");
            return (Criteria) this;
        }

        public Criteria andDrLhTypeIn(List<String> values) {
            addCriterion("dr_lh_type in", values, "drLhType");
            return (Criteria) this;
        }

        public Criteria andDrLhTypeNotIn(List<String> values) {
            addCriterion("dr_lh_type not in", values, "drLhType");
            return (Criteria) this;
        }

        public Criteria andDrLhTypeBetween(String value1, String value2) {
            addCriterion("dr_lh_type between", value1, value2, "drLhType");
            return (Criteria) this;
        }

        public Criteria andDrLhTypeNotBetween(String value1, String value2) {
            addCriterion("dr_lh_type not between", value1, value2, "drLhType");
            return (Criteria) this;
        }

        public Criteria andDrBuyAmtIsNull() {
            addCriterion("dr_buy_amt is null");
            return (Criteria) this;
        }

        public Criteria andDrBuyAmtIsNotNull() {
            addCriterion("dr_buy_amt is not null");
            return (Criteria) this;
        }

        public Criteria andDrBuyAmtEqualTo(Double value) {
            addCriterion("dr_buy_amt =", value, "drBuyAmt");
            return (Criteria) this;
        }

        public Criteria andDrBuyAmtNotEqualTo(Double value) {
            addCriterion("dr_buy_amt <>", value, "drBuyAmt");
            return (Criteria) this;
        }

        public Criteria andDrBuyAmtGreaterThan(Double value) {
            addCriterion("dr_buy_amt >", value, "drBuyAmt");
            return (Criteria) this;
        }

        public Criteria andDrBuyAmtGreaterThanOrEqualTo(Double value) {
            addCriterion("dr_buy_amt >=", value, "drBuyAmt");
            return (Criteria) this;
        }

        public Criteria andDrBuyAmtLessThan(Double value) {
            addCriterion("dr_buy_amt <", value, "drBuyAmt");
            return (Criteria) this;
        }

        public Criteria andDrBuyAmtLessThanOrEqualTo(Double value) {
            addCriterion("dr_buy_amt <=", value, "drBuyAmt");
            return (Criteria) this;
        }

        public Criteria andDrBuyAmtIn(List<Double> values) {
            addCriterion("dr_buy_amt in", values, "drBuyAmt");
            return (Criteria) this;
        }

        public Criteria andDrBuyAmtNotIn(List<Double> values) {
            addCriterion("dr_buy_amt not in", values, "drBuyAmt");
            return (Criteria) this;
        }

        public Criteria andDrBuyAmtBetween(Double value1, Double value2) {
            addCriterion("dr_buy_amt between", value1, value2, "drBuyAmt");
            return (Criteria) this;
        }

        public Criteria andDrBuyAmtNotBetween(Double value1, Double value2) {
            addCriterion("dr_buy_amt not between", value1, value2, "drBuyAmt");
            return (Criteria) this;
        }

        public Criteria andDrSellAmtIsNull() {
            addCriterion("dr_sell_amt is null");
            return (Criteria) this;
        }

        public Criteria andDrSellAmtIsNotNull() {
            addCriterion("dr_sell_amt is not null");
            return (Criteria) this;
        }

        public Criteria andDrSellAmtEqualTo(Double value) {
            addCriterion("dr_sell_amt =", value, "drSellAmt");
            return (Criteria) this;
        }

        public Criteria andDrSellAmtNotEqualTo(Double value) {
            addCriterion("dr_sell_amt <>", value, "drSellAmt");
            return (Criteria) this;
        }

        public Criteria andDrSellAmtGreaterThan(Double value) {
            addCriterion("dr_sell_amt >", value, "drSellAmt");
            return (Criteria) this;
        }

        public Criteria andDrSellAmtGreaterThanOrEqualTo(Double value) {
            addCriterion("dr_sell_amt >=", value, "drSellAmt");
            return (Criteria) this;
        }

        public Criteria andDrSellAmtLessThan(Double value) {
            addCriterion("dr_sell_amt <", value, "drSellAmt");
            return (Criteria) this;
        }

        public Criteria andDrSellAmtLessThanOrEqualTo(Double value) {
            addCriterion("dr_sell_amt <=", value, "drSellAmt");
            return (Criteria) this;
        }

        public Criteria andDrSellAmtIn(List<Double> values) {
            addCriterion("dr_sell_amt in", values, "drSellAmt");
            return (Criteria) this;
        }

        public Criteria andDrSellAmtNotIn(List<Double> values) {
            addCriterion("dr_sell_amt not in", values, "drSellAmt");
            return (Criteria) this;
        }

        public Criteria andDrSellAmtBetween(Double value1, Double value2) {
            addCriterion("dr_sell_amt between", value1, value2, "drSellAmt");
            return (Criteria) this;
        }

        public Criteria andDrSellAmtNotBetween(Double value1, Double value2) {
            addCriterion("dr_sell_amt not between", value1, value2, "drSellAmt");
            return (Criteria) this;
        }

        public Criteria andDrNetBuyIsNull() {
            addCriterion("dr_net_buy is null");
            return (Criteria) this;
        }

        public Criteria andDrNetBuyIsNotNull() {
            addCriterion("dr_net_buy is not null");
            return (Criteria) this;
        }

        public Criteria andDrNetBuyEqualTo(Double value) {
            addCriterion("dr_net_buy =", value, "drNetBuy");
            return (Criteria) this;
        }

        public Criteria andDrNetBuyNotEqualTo(Double value) {
            addCriterion("dr_net_buy <>", value, "drNetBuy");
            return (Criteria) this;
        }

        public Criteria andDrNetBuyGreaterThan(Double value) {
            addCriterion("dr_net_buy >", value, "drNetBuy");
            return (Criteria) this;
        }

        public Criteria andDrNetBuyGreaterThanOrEqualTo(Double value) {
            addCriterion("dr_net_buy >=", value, "drNetBuy");
            return (Criteria) this;
        }

        public Criteria andDrNetBuyLessThan(Double value) {
            addCriterion("dr_net_buy <", value, "drNetBuy");
            return (Criteria) this;
        }

        public Criteria andDrNetBuyLessThanOrEqualTo(Double value) {
            addCriterion("dr_net_buy <=", value, "drNetBuy");
            return (Criteria) this;
        }

        public Criteria andDrNetBuyIn(List<Double> values) {
            addCriterion("dr_net_buy in", values, "drNetBuy");
            return (Criteria) this;
        }

        public Criteria andDrNetBuyNotIn(List<Double> values) {
            addCriterion("dr_net_buy not in", values, "drNetBuy");
            return (Criteria) this;
        }

        public Criteria andDrNetBuyBetween(Double value1, Double value2) {
            addCriterion("dr_net_buy between", value1, value2, "drNetBuy");
            return (Criteria) this;
        }

        public Criteria andDrNetBuyNotBetween(Double value1, Double value2) {
            addCriterion("dr_net_buy not between", value1, value2, "drNetBuy");
            return (Criteria) this;
        }

        public Criteria andSrLhTypeIsNull() {
            addCriterion("sr_lh_type is null");
            return (Criteria) this;
        }

        public Criteria andSrLhTypeIsNotNull() {
            addCriterion("sr_lh_type is not null");
            return (Criteria) this;
        }

        public Criteria andSrLhTypeEqualTo(String value) {
            addCriterion("sr_lh_type =", value, "srLhType");
            return (Criteria) this;
        }

        public Criteria andSrLhTypeNotEqualTo(String value) {
            addCriterion("sr_lh_type <>", value, "srLhType");
            return (Criteria) this;
        }

        public Criteria andSrLhTypeGreaterThan(String value) {
            addCriterion("sr_lh_type >", value, "srLhType");
            return (Criteria) this;
        }

        public Criteria andSrLhTypeGreaterThanOrEqualTo(String value) {
            addCriterion("sr_lh_type >=", value, "srLhType");
            return (Criteria) this;
        }

        public Criteria andSrLhTypeLessThan(String value) {
            addCriterion("sr_lh_type <", value, "srLhType");
            return (Criteria) this;
        }

        public Criteria andSrLhTypeLessThanOrEqualTo(String value) {
            addCriterion("sr_lh_type <=", value, "srLhType");
            return (Criteria) this;
        }

        public Criteria andSrLhTypeLike(String value) {
            addCriterion("sr_lh_type like", value, "srLhType");
            return (Criteria) this;
        }

        public Criteria andSrLhTypeNotLike(String value) {
            addCriterion("sr_lh_type not like", value, "srLhType");
            return (Criteria) this;
        }

        public Criteria andSrLhTypeIn(List<String> values) {
            addCriterion("sr_lh_type in", values, "srLhType");
            return (Criteria) this;
        }

        public Criteria andSrLhTypeNotIn(List<String> values) {
            addCriterion("sr_lh_type not in", values, "srLhType");
            return (Criteria) this;
        }

        public Criteria andSrLhTypeBetween(String value1, String value2) {
            addCriterion("sr_lh_type between", value1, value2, "srLhType");
            return (Criteria) this;
        }

        public Criteria andSrLhTypeNotBetween(String value1, String value2) {
            addCriterion("sr_lh_type not between", value1, value2, "srLhType");
            return (Criteria) this;
        }

        public Criteria andSrBuyAmtIsNull() {
            addCriterion("sr_buy_amt is null");
            return (Criteria) this;
        }

        public Criteria andSrBuyAmtIsNotNull() {
            addCriterion("sr_buy_amt is not null");
            return (Criteria) this;
        }

        public Criteria andSrBuyAmtEqualTo(Double value) {
            addCriterion("sr_buy_amt =", value, "srBuyAmt");
            return (Criteria) this;
        }

        public Criteria andSrBuyAmtNotEqualTo(Double value) {
            addCriterion("sr_buy_amt <>", value, "srBuyAmt");
            return (Criteria) this;
        }

        public Criteria andSrBuyAmtGreaterThan(Double value) {
            addCriterion("sr_buy_amt >", value, "srBuyAmt");
            return (Criteria) this;
        }

        public Criteria andSrBuyAmtGreaterThanOrEqualTo(Double value) {
            addCriterion("sr_buy_amt >=", value, "srBuyAmt");
            return (Criteria) this;
        }

        public Criteria andSrBuyAmtLessThan(Double value) {
            addCriterion("sr_buy_amt <", value, "srBuyAmt");
            return (Criteria) this;
        }

        public Criteria andSrBuyAmtLessThanOrEqualTo(Double value) {
            addCriterion("sr_buy_amt <=", value, "srBuyAmt");
            return (Criteria) this;
        }

        public Criteria andSrBuyAmtIn(List<Double> values) {
            addCriterion("sr_buy_amt in", values, "srBuyAmt");
            return (Criteria) this;
        }

        public Criteria andSrBuyAmtNotIn(List<Double> values) {
            addCriterion("sr_buy_amt not in", values, "srBuyAmt");
            return (Criteria) this;
        }

        public Criteria andSrBuyAmtBetween(Double value1, Double value2) {
            addCriterion("sr_buy_amt between", value1, value2, "srBuyAmt");
            return (Criteria) this;
        }

        public Criteria andSrBuyAmtNotBetween(Double value1, Double value2) {
            addCriterion("sr_buy_amt not between", value1, value2, "srBuyAmt");
            return (Criteria) this;
        }

        public Criteria andSrSellAmtIsNull() {
            addCriterion("sr_sell_amt is null");
            return (Criteria) this;
        }

        public Criteria andSrSellAmtIsNotNull() {
            addCriterion("sr_sell_amt is not null");
            return (Criteria) this;
        }

        public Criteria andSrSellAmtEqualTo(Double value) {
            addCriterion("sr_sell_amt =", value, "srSellAmt");
            return (Criteria) this;
        }

        public Criteria andSrSellAmtNotEqualTo(Double value) {
            addCriterion("sr_sell_amt <>", value, "srSellAmt");
            return (Criteria) this;
        }

        public Criteria andSrSellAmtGreaterThan(Double value) {
            addCriterion("sr_sell_amt >", value, "srSellAmt");
            return (Criteria) this;
        }

        public Criteria andSrSellAmtGreaterThanOrEqualTo(Double value) {
            addCriterion("sr_sell_amt >=", value, "srSellAmt");
            return (Criteria) this;
        }

        public Criteria andSrSellAmtLessThan(Double value) {
            addCriterion("sr_sell_amt <", value, "srSellAmt");
            return (Criteria) this;
        }

        public Criteria andSrSellAmtLessThanOrEqualTo(Double value) {
            addCriterion("sr_sell_amt <=", value, "srSellAmt");
            return (Criteria) this;
        }

        public Criteria andSrSellAmtIn(List<Double> values) {
            addCriterion("sr_sell_amt in", values, "srSellAmt");
            return (Criteria) this;
        }

        public Criteria andSrSellAmtNotIn(List<Double> values) {
            addCriterion("sr_sell_amt not in", values, "srSellAmt");
            return (Criteria) this;
        }

        public Criteria andSrSellAmtBetween(Double value1, Double value2) {
            addCriterion("sr_sell_amt between", value1, value2, "srSellAmt");
            return (Criteria) this;
        }

        public Criteria andSrSellAmtNotBetween(Double value1, Double value2) {
            addCriterion("sr_sell_amt not between", value1, value2, "srSellAmt");
            return (Criteria) this;
        }

        public Criteria andSrNetBuyIsNull() {
            addCriterion("sr_net_buy is null");
            return (Criteria) this;
        }

        public Criteria andSrNetBuyIsNotNull() {
            addCriterion("sr_net_buy is not null");
            return (Criteria) this;
        }

        public Criteria andSrNetBuyEqualTo(Double value) {
            addCriterion("sr_net_buy =", value, "srNetBuy");
            return (Criteria) this;
        }

        public Criteria andSrNetBuyNotEqualTo(Double value) {
            addCriterion("sr_net_buy <>", value, "srNetBuy");
            return (Criteria) this;
        }

        public Criteria andSrNetBuyGreaterThan(Double value) {
            addCriterion("sr_net_buy >", value, "srNetBuy");
            return (Criteria) this;
        }

        public Criteria andSrNetBuyGreaterThanOrEqualTo(Double value) {
            addCriterion("sr_net_buy >=", value, "srNetBuy");
            return (Criteria) this;
        }

        public Criteria andSrNetBuyLessThan(Double value) {
            addCriterion("sr_net_buy <", value, "srNetBuy");
            return (Criteria) this;
        }

        public Criteria andSrNetBuyLessThanOrEqualTo(Double value) {
            addCriterion("sr_net_buy <=", value, "srNetBuy");
            return (Criteria) this;
        }

        public Criteria andSrNetBuyIn(List<Double> values) {
            addCriterion("sr_net_buy in", values, "srNetBuy");
            return (Criteria) this;
        }

        public Criteria andSrNetBuyNotIn(List<Double> values) {
            addCriterion("sr_net_buy not in", values, "srNetBuy");
            return (Criteria) this;
        }

        public Criteria andSrNetBuyBetween(Double value1, Double value2) {
            addCriterion("sr_net_buy between", value1, value2, "srNetBuy");
            return (Criteria) this;
        }

        public Criteria andSrNetBuyNotBetween(Double value1, Double value2) {
            addCriterion("sr_net_buy not between", value1, value2, "srNetBuy");
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

        public Criteria andCliqueIdIsNull() {
            addCriterion("clique_id is null");
            return (Criteria) this;
        }

        public Criteria andCliqueIdIsNotNull() {
            addCriterion("clique_id is not null");
            return (Criteria) this;
        }

        public Criteria andCliqueIdEqualTo(Long value) {
            addCriterion("clique_id =", value, "cliqueId");
            return (Criteria) this;
        }

        public Criteria andCliqueIdNotEqualTo(Long value) {
            addCriterion("clique_id <>", value, "cliqueId");
            return (Criteria) this;
        }

        public Criteria andCliqueIdGreaterThan(Long value) {
            addCriterion("clique_id >", value, "cliqueId");
            return (Criteria) this;
        }

        public Criteria andCliqueIdGreaterThanOrEqualTo(Long value) {
            addCriterion("clique_id >=", value, "cliqueId");
            return (Criteria) this;
        }

        public Criteria andCliqueIdLessThan(Long value) {
            addCriterion("clique_id <", value, "cliqueId");
            return (Criteria) this;
        }

        public Criteria andCliqueIdLessThanOrEqualTo(Long value) {
            addCriterion("clique_id <=", value, "cliqueId");
            return (Criteria) this;
        }

        public Criteria andCliqueIdIn(List<Long> values) {
            addCriterion("clique_id in", values, "cliqueId");
            return (Criteria) this;
        }

        public Criteria andCliqueIdNotIn(List<Long> values) {
            addCriterion("clique_id not in", values, "cliqueId");
            return (Criteria) this;
        }

        public Criteria andCliqueIdBetween(Long value1, Long value2) {
            addCriterion("clique_id between", value1, value2, "cliqueId");
            return (Criteria) this;
        }

        public Criteria andCliqueIdNotBetween(Long value1, Long value2) {
            addCriterion("clique_id not between", value1, value2, "cliqueId");
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