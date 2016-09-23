package com.gesangwu.spider.biz.dao.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
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

        public Criteria andLongHuTypeIsNull() {
            addCriterion("long_hu_type is null");
            return (Criteria) this;
        }

        public Criteria andLongHuTypeIsNotNull() {
            addCriterion("long_hu_type is not null");
            return (Criteria) this;
        }

        public Criteria andLongHuTypeEqualTo(String value) {
            addCriterion("long_hu_type =", value, "longHuType");
            return (Criteria) this;
        }

        public Criteria andLongHuTypeNotEqualTo(String value) {
            addCriterion("long_hu_type <>", value, "longHuType");
            return (Criteria) this;
        }

        public Criteria andLongHuTypeGreaterThan(String value) {
            addCriterion("long_hu_type >", value, "longHuType");
            return (Criteria) this;
        }

        public Criteria andLongHuTypeGreaterThanOrEqualTo(String value) {
            addCriterion("long_hu_type >=", value, "longHuType");
            return (Criteria) this;
        }

        public Criteria andLongHuTypeLessThan(String value) {
            addCriterion("long_hu_type <", value, "longHuType");
            return (Criteria) this;
        }

        public Criteria andLongHuTypeLessThanOrEqualTo(String value) {
            addCriterion("long_hu_type <=", value, "longHuType");
            return (Criteria) this;
        }

        public Criteria andLongHuTypeLike(String value) {
            addCriterion("long_hu_type like", value, "longHuType");
            return (Criteria) this;
        }

        public Criteria andLongHuTypeNotLike(String value) {
            addCriterion("long_hu_type not like", value, "longHuType");
            return (Criteria) this;
        }

        public Criteria andLongHuTypeIn(List<String> values) {
            addCriterion("long_hu_type in", values, "longHuType");
            return (Criteria) this;
        }

        public Criteria andLongHuTypeNotIn(List<String> values) {
            addCriterion("long_hu_type not in", values, "longHuType");
            return (Criteria) this;
        }

        public Criteria andLongHuTypeBetween(String value1, String value2) {
            addCriterion("long_hu_type between", value1, value2, "longHuType");
            return (Criteria) this;
        }

        public Criteria andLongHuTypeNotBetween(String value1, String value2) {
            addCriterion("long_hu_type not between", value1, value2, "longHuType");
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

        public Criteria andTotalBuyIsNull() {
            addCriterion("total_buy is null");
            return (Criteria) this;
        }

        public Criteria andTotalBuyIsNotNull() {
            addCriterion("total_buy is not null");
            return (Criteria) this;
        }

        public Criteria andTotalBuyEqualTo(Double value) {
            addCriterion("total_buy =", value, "totalBuy");
            return (Criteria) this;
        }

        public Criteria andTotalBuyNotEqualTo(Double value) {
            addCriterion("total_buy <>", value, "totalBuy");
            return (Criteria) this;
        }

        public Criteria andTotalBuyGreaterThan(Double value) {
            addCriterion("total_buy >", value, "totalBuy");
            return (Criteria) this;
        }

        public Criteria andTotalBuyGreaterThanOrEqualTo(Double value) {
            addCriterion("total_buy >=", value, "totalBuy");
            return (Criteria) this;
        }

        public Criteria andTotalBuyLessThan(Double value) {
            addCriterion("total_buy <", value, "totalBuy");
            return (Criteria) this;
        }

        public Criteria andTotalBuyLessThanOrEqualTo(Double value) {
            addCriterion("total_buy <=", value, "totalBuy");
            return (Criteria) this;
        }

        public Criteria andTotalBuyIn(List<Double> values) {
            addCriterion("total_buy in", values, "totalBuy");
            return (Criteria) this;
        }

        public Criteria andTotalBuyNotIn(List<Double> values) {
            addCriterion("total_buy not in", values, "totalBuy");
            return (Criteria) this;
        }

        public Criteria andTotalBuyBetween(Double value1, Double value2) {
            addCriterion("total_buy between", value1, value2, "totalBuy");
            return (Criteria) this;
        }

        public Criteria andTotalBuyNotBetween(Double value1, Double value2) {
            addCriterion("total_buy not between", value1, value2, "totalBuy");
            return (Criteria) this;
        }

        public Criteria andTotalSellIsNull() {
            addCriterion("total_sell is null");
            return (Criteria) this;
        }

        public Criteria andTotalSellIsNotNull() {
            addCriterion("total_sell is not null");
            return (Criteria) this;
        }

        public Criteria andTotalSellEqualTo(Double value) {
            addCriterion("total_sell =", value, "totalSell");
            return (Criteria) this;
        }

        public Criteria andTotalSellNotEqualTo(Double value) {
            addCriterion("total_sell <>", value, "totalSell");
            return (Criteria) this;
        }

        public Criteria andTotalSellGreaterThan(Double value) {
            addCriterion("total_sell >", value, "totalSell");
            return (Criteria) this;
        }

        public Criteria andTotalSellGreaterThanOrEqualTo(Double value) {
            addCriterion("total_sell >=", value, "totalSell");
            return (Criteria) this;
        }

        public Criteria andTotalSellLessThan(Double value) {
            addCriterion("total_sell <", value, "totalSell");
            return (Criteria) this;
        }

        public Criteria andTotalSellLessThanOrEqualTo(Double value) {
            addCriterion("total_sell <=", value, "totalSell");
            return (Criteria) this;
        }

        public Criteria andTotalSellIn(List<Double> values) {
            addCriterion("total_sell in", values, "totalSell");
            return (Criteria) this;
        }

        public Criteria andTotalSellNotIn(List<Double> values) {
            addCriterion("total_sell not in", values, "totalSell");
            return (Criteria) this;
        }

        public Criteria andTotalSellBetween(Double value1, Double value2) {
            addCriterion("total_sell between", value1, value2, "totalSell");
            return (Criteria) this;
        }

        public Criteria andTotalSellNotBetween(Double value1, Double value2) {
            addCriterion("total_sell not between", value1, value2, "totalSell");
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