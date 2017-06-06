package com.gesangwu.spider.biz.dao.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
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

        public Criteria andLongHuIdIsNull() {
            addCriterion("long_hu_id is null");
            return (Criteria) this;
        }

        public Criteria andLongHuIdIsNotNull() {
            addCriterion("long_hu_id is not null");
            return (Criteria) this;
        }

        public Criteria andLongHuIdEqualTo(Long value) {
            addCriterion("long_hu_id =", value, "longHuId");
            return (Criteria) this;
        }

        public Criteria andLongHuIdNotEqualTo(Long value) {
            addCriterion("long_hu_id <>", value, "longHuId");
            return (Criteria) this;
        }

        public Criteria andLongHuIdGreaterThan(Long value) {
            addCriterion("long_hu_id >", value, "longHuId");
            return (Criteria) this;
        }

        public Criteria andLongHuIdGreaterThanOrEqualTo(Long value) {
            addCriterion("long_hu_id >=", value, "longHuId");
            return (Criteria) this;
        }

        public Criteria andLongHuIdLessThan(Long value) {
            addCriterion("long_hu_id <", value, "longHuId");
            return (Criteria) this;
        }

        public Criteria andLongHuIdLessThanOrEqualTo(Long value) {
            addCriterion("long_hu_id <=", value, "longHuId");
            return (Criteria) this;
        }

        public Criteria andLongHuIdIn(List<Long> values) {
            addCriterion("long_hu_id in", values, "longHuId");
            return (Criteria) this;
        }

        public Criteria andLongHuIdNotIn(List<Long> values) {
            addCriterion("long_hu_id not in", values, "longHuId");
            return (Criteria) this;
        }

        public Criteria andLongHuIdBetween(Long value1, Long value2) {
            addCriterion("long_hu_id between", value1, value2, "longHuId");
            return (Criteria) this;
        }

        public Criteria andLongHuIdNotBetween(Long value1, Long value2) {
            addCriterion("long_hu_id not between", value1, value2, "longHuId");
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

        public Criteria andBuyAmtIsNull() {
            addCriterion("buy_amt is null");
            return (Criteria) this;
        }

        public Criteria andBuyAmtIsNotNull() {
            addCriterion("buy_amt is not null");
            return (Criteria) this;
        }

        public Criteria andBuyAmtEqualTo(BigDecimal value) {
            addCriterion("buy_amt =", value, "buyAmt");
            return (Criteria) this;
        }

        public Criteria andBuyAmtNotEqualTo(BigDecimal value) {
            addCriterion("buy_amt <>", value, "buyAmt");
            return (Criteria) this;
        }

        public Criteria andBuyAmtGreaterThan(BigDecimal value) {
            addCriterion("buy_amt >", value, "buyAmt");
            return (Criteria) this;
        }

        public Criteria andBuyAmtGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("buy_amt >=", value, "buyAmt");
            return (Criteria) this;
        }

        public Criteria andBuyAmtLessThan(BigDecimal value) {
            addCriterion("buy_amt <", value, "buyAmt");
            return (Criteria) this;
        }

        public Criteria andBuyAmtLessThanOrEqualTo(BigDecimal value) {
            addCriterion("buy_amt <=", value, "buyAmt");
            return (Criteria) this;
        }

        public Criteria andBuyAmtIn(List<BigDecimal> values) {
            addCriterion("buy_amt in", values, "buyAmt");
            return (Criteria) this;
        }

        public Criteria andBuyAmtNotIn(List<BigDecimal> values) {
            addCriterion("buy_amt not in", values, "buyAmt");
            return (Criteria) this;
        }

        public Criteria andBuyAmtBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("buy_amt between", value1, value2, "buyAmt");
            return (Criteria) this;
        }

        public Criteria andBuyAmtNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("buy_amt not between", value1, value2, "buyAmt");
            return (Criteria) this;
        }

        public Criteria andSellAmtIsNull() {
            addCriterion("sell_amt is null");
            return (Criteria) this;
        }

        public Criteria andSellAmtIsNotNull() {
            addCriterion("sell_amt is not null");
            return (Criteria) this;
        }

        public Criteria andSellAmtEqualTo(BigDecimal value) {
            addCriterion("sell_amt =", value, "sellAmt");
            return (Criteria) this;
        }

        public Criteria andSellAmtNotEqualTo(BigDecimal value) {
            addCriterion("sell_amt <>", value, "sellAmt");
            return (Criteria) this;
        }

        public Criteria andSellAmtGreaterThan(BigDecimal value) {
            addCriterion("sell_amt >", value, "sellAmt");
            return (Criteria) this;
        }

        public Criteria andSellAmtGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("sell_amt >=", value, "sellAmt");
            return (Criteria) this;
        }

        public Criteria andSellAmtLessThan(BigDecimal value) {
            addCriterion("sell_amt <", value, "sellAmt");
            return (Criteria) this;
        }

        public Criteria andSellAmtLessThanOrEqualTo(BigDecimal value) {
            addCriterion("sell_amt <=", value, "sellAmt");
            return (Criteria) this;
        }

        public Criteria andSellAmtIn(List<BigDecimal> values) {
            addCriterion("sell_amt in", values, "sellAmt");
            return (Criteria) this;
        }

        public Criteria andSellAmtNotIn(List<BigDecimal> values) {
            addCriterion("sell_amt not in", values, "sellAmt");
            return (Criteria) this;
        }

        public Criteria andSellAmtBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("sell_amt between", value1, value2, "sellAmt");
            return (Criteria) this;
        }

        public Criteria andSellAmtNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("sell_amt not between", value1, value2, "sellAmt");
            return (Criteria) this;
        }

        public Criteria andNetBuyIsNull() {
            addCriterion("net_buy is null");
            return (Criteria) this;
        }

        public Criteria andNetBuyIsNotNull() {
            addCriterion("net_buy is not null");
            return (Criteria) this;
        }

        public Criteria andNetBuyEqualTo(BigDecimal value) {
            addCriterion("net_buy =", value, "netBuy");
            return (Criteria) this;
        }

        public Criteria andNetBuyNotEqualTo(BigDecimal value) {
            addCriterion("net_buy <>", value, "netBuy");
            return (Criteria) this;
        }

        public Criteria andNetBuyGreaterThan(BigDecimal value) {
            addCriterion("net_buy >", value, "netBuy");
            return (Criteria) this;
        }

        public Criteria andNetBuyGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("net_buy >=", value, "netBuy");
            return (Criteria) this;
        }

        public Criteria andNetBuyLessThan(BigDecimal value) {
            addCriterion("net_buy <", value, "netBuy");
            return (Criteria) this;
        }

        public Criteria andNetBuyLessThanOrEqualTo(BigDecimal value) {
            addCriterion("net_buy <=", value, "netBuy");
            return (Criteria) this;
        }

        public Criteria andNetBuyIn(List<BigDecimal> values) {
            addCriterion("net_buy in", values, "netBuy");
            return (Criteria) this;
        }

        public Criteria andNetBuyNotIn(List<BigDecimal> values) {
            addCriterion("net_buy not in", values, "netBuy");
            return (Criteria) this;
        }

        public Criteria andNetBuyBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("net_buy between", value1, value2, "netBuy");
            return (Criteria) this;
        }

        public Criteria andNetBuyNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("net_buy not between", value1, value2, "netBuy");
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

        public Criteria andSecDeptCodeEqualTo(String value) {
            addCriterion("sec_dept_code =", value, "secDeptCode");
            return (Criteria) this;
        }

        public Criteria andSecDeptCodeNotEqualTo(String value) {
            addCriterion("sec_dept_code <>", value, "secDeptCode");
            return (Criteria) this;
        }

        public Criteria andSecDeptCodeGreaterThan(String value) {
            addCriterion("sec_dept_code >", value, "secDeptCode");
            return (Criteria) this;
        }

        public Criteria andSecDeptCodeGreaterThanOrEqualTo(String value) {
            addCriterion("sec_dept_code >=", value, "secDeptCode");
            return (Criteria) this;
        }

        public Criteria andSecDeptCodeLessThan(String value) {
            addCriterion("sec_dept_code <", value, "secDeptCode");
            return (Criteria) this;
        }

        public Criteria andSecDeptCodeLessThanOrEqualTo(String value) {
            addCriterion("sec_dept_code <=", value, "secDeptCode");
            return (Criteria) this;
        }

        public Criteria andSecDeptCodeLike(String value) {
            addCriterion("sec_dept_code like", value, "secDeptCode");
            return (Criteria) this;
        }

        public Criteria andSecDeptCodeNotLike(String value) {
            addCriterion("sec_dept_code not like", value, "secDeptCode");
            return (Criteria) this;
        }

        public Criteria andSecDeptCodeIn(List<String> values) {
            addCriterion("sec_dept_code in", values, "secDeptCode");
            return (Criteria) this;
        }

        public Criteria andSecDeptCodeNotIn(List<String> values) {
            addCriterion("sec_dept_code not in", values, "secDeptCode");
            return (Criteria) this;
        }

        public Criteria andSecDeptCodeBetween(String value1, String value2) {
            addCriterion("sec_dept_code between", value1, value2, "secDeptCode");
            return (Criteria) this;
        }

        public Criteria andSecDeptCodeNotBetween(String value1, String value2) {
            addCriterion("sec_dept_code not between", value1, value2, "secDeptCode");
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

        public Criteria andRelationIsNull() {
            addCriterion("relation is null");
            return (Criteria) this;
        }

        public Criteria andRelationIsNotNull() {
            addCriterion("relation is not null");
            return (Criteria) this;
        }

        public Criteria andRelationEqualTo(Integer value) {
            addCriterion("relation =", value, "relation");
            return (Criteria) this;
        }

        public Criteria andRelationNotEqualTo(Integer value) {
            addCriterion("relation <>", value, "relation");
            return (Criteria) this;
        }

        public Criteria andRelationGreaterThan(Integer value) {
            addCriterion("relation >", value, "relation");
            return (Criteria) this;
        }

        public Criteria andRelationGreaterThanOrEqualTo(Integer value) {
            addCriterion("relation >=", value, "relation");
            return (Criteria) this;
        }

        public Criteria andRelationLessThan(Integer value) {
            addCriterion("relation <", value, "relation");
            return (Criteria) this;
        }

        public Criteria andRelationLessThanOrEqualTo(Integer value) {
            addCriterion("relation <=", value, "relation");
            return (Criteria) this;
        }

        public Criteria andRelationIn(List<Integer> values) {
            addCriterion("relation in", values, "relation");
            return (Criteria) this;
        }

        public Criteria andRelationNotIn(List<Integer> values) {
            addCriterion("relation not in", values, "relation");
            return (Criteria) this;
        }

        public Criteria andRelationBetween(Integer value1, Integer value2) {
            addCriterion("relation between", value1, value2, "relation");
            return (Criteria) this;
        }

        public Criteria andRelationNotBetween(Integer value1, Integer value2) {
            addCriterion("relation not between", value1, value2, "relation");
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