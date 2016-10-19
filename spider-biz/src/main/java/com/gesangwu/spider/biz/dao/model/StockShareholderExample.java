package com.gesangwu.spider.biz.dao.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class StockShareholderExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected int offset = -1;

    protected int rows = -1;

    public StockShareholderExample() {
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

        public Criteria andShareholderIsNull() {
            addCriterion("shareholder is null");
            return (Criteria) this;
        }

        public Criteria andShareholderIsNotNull() {
            addCriterion("shareholder is not null");
            return (Criteria) this;
        }

        public Criteria andShareholderEqualTo(Long value) {
            addCriterion("shareholder =", value, "shareholder");
            return (Criteria) this;
        }

        public Criteria andShareholderNotEqualTo(Long value) {
            addCriterion("shareholder <>", value, "shareholder");
            return (Criteria) this;
        }

        public Criteria andShareholderGreaterThan(Long value) {
            addCriterion("shareholder >", value, "shareholder");
            return (Criteria) this;
        }

        public Criteria andShareholderGreaterThanOrEqualTo(Long value) {
            addCriterion("shareholder >=", value, "shareholder");
            return (Criteria) this;
        }

        public Criteria andShareholderLessThan(Long value) {
            addCriterion("shareholder <", value, "shareholder");
            return (Criteria) this;
        }

        public Criteria andShareholderLessThanOrEqualTo(Long value) {
            addCriterion("shareholder <=", value, "shareholder");
            return (Criteria) this;
        }

        public Criteria andShareholderIn(List<Long> values) {
            addCriterion("shareholder in", values, "shareholder");
            return (Criteria) this;
        }

        public Criteria andShareholderNotIn(List<Long> values) {
            addCriterion("shareholder not in", values, "shareholder");
            return (Criteria) this;
        }

        public Criteria andShareholderBetween(Long value1, Long value2) {
            addCriterion("shareholder between", value1, value2, "shareholder");
            return (Criteria) this;
        }

        public Criteria andShareholderNotBetween(Long value1, Long value2) {
            addCriterion("shareholder not between", value1, value2, "shareholder");
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

        public Criteria andEndDateEqualTo(Date value) {
            addCriterion("end_date =", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateNotEqualTo(Date value) {
            addCriterion("end_date <>", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateGreaterThan(Date value) {
            addCriterion("end_date >", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateGreaterThanOrEqualTo(Date value) {
            addCriterion("end_date >=", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateLessThan(Date value) {
            addCriterion("end_date <", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateLessThanOrEqualTo(Date value) {
            addCriterion("end_date <=", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateIn(List<Date> values) {
            addCriterion("end_date in", values, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateNotIn(List<Date> values) {
            addCriterion("end_date not in", values, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateBetween(Date value1, Date value2) {
            addCriterion("end_date between", value1, value2, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateNotBetween(Date value1, Date value2) {
            addCriterion("end_date not between", value1, value2, "endDate");
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

        public Criteria andStockCountIsNull() {
            addCriterion("stock_count is null");
            return (Criteria) this;
        }

        public Criteria andStockCountIsNotNull() {
            addCriterion("stock_count is not null");
            return (Criteria) this;
        }

        public Criteria andStockCountEqualTo(Integer value) {
            addCriterion("stock_count =", value, "stockCount");
            return (Criteria) this;
        }

        public Criteria andStockCountNotEqualTo(Integer value) {
            addCriterion("stock_count <>", value, "stockCount");
            return (Criteria) this;
        }

        public Criteria andStockCountGreaterThan(Integer value) {
            addCriterion("stock_count >", value, "stockCount");
            return (Criteria) this;
        }

        public Criteria andStockCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("stock_count >=", value, "stockCount");
            return (Criteria) this;
        }

        public Criteria andStockCountLessThan(Integer value) {
            addCriterion("stock_count <", value, "stockCount");
            return (Criteria) this;
        }

        public Criteria andStockCountLessThanOrEqualTo(Integer value) {
            addCriterion("stock_count <=", value, "stockCount");
            return (Criteria) this;
        }

        public Criteria andStockCountIn(List<Integer> values) {
            addCriterion("stock_count in", values, "stockCount");
            return (Criteria) this;
        }

        public Criteria andStockCountNotIn(List<Integer> values) {
            addCriterion("stock_count not in", values, "stockCount");
            return (Criteria) this;
        }

        public Criteria andStockCountBetween(Integer value1, Integer value2) {
            addCriterion("stock_count between", value1, value2, "stockCount");
            return (Criteria) this;
        }

        public Criteria andStockCountNotBetween(Integer value1, Integer value2) {
            addCriterion("stock_count not between", value1, value2, "stockCount");
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

        public Criteria andIsNewHolderIsNull() {
            addCriterion("is_new_holder is null");
            return (Criteria) this;
        }

        public Criteria andIsNewHolderIsNotNull() {
            addCriterion("is_new_holder is not null");
            return (Criteria) this;
        }

        public Criteria andIsNewHolderEqualTo(Integer value) {
            addCriterion("is_new_holder =", value, "isNewHolder");
            return (Criteria) this;
        }

        public Criteria andIsNewHolderNotEqualTo(Integer value) {
            addCriterion("is_new_holder <>", value, "isNewHolder");
            return (Criteria) this;
        }

        public Criteria andIsNewHolderGreaterThan(Integer value) {
            addCriterion("is_new_holder >", value, "isNewHolder");
            return (Criteria) this;
        }

        public Criteria andIsNewHolderGreaterThanOrEqualTo(Integer value) {
            addCriterion("is_new_holder >=", value, "isNewHolder");
            return (Criteria) this;
        }

        public Criteria andIsNewHolderLessThan(Integer value) {
            addCriterion("is_new_holder <", value, "isNewHolder");
            return (Criteria) this;
        }

        public Criteria andIsNewHolderLessThanOrEqualTo(Integer value) {
            addCriterion("is_new_holder <=", value, "isNewHolder");
            return (Criteria) this;
        }

        public Criteria andIsNewHolderIn(List<Integer> values) {
            addCriterion("is_new_holder in", values, "isNewHolder");
            return (Criteria) this;
        }

        public Criteria andIsNewHolderNotIn(List<Integer> values) {
            addCriterion("is_new_holder not in", values, "isNewHolder");
            return (Criteria) this;
        }

        public Criteria andIsNewHolderBetween(Integer value1, Integer value2) {
            addCriterion("is_new_holder between", value1, value2, "isNewHolder");
            return (Criteria) this;
        }

        public Criteria andIsNewHolderNotBetween(Integer value1, Integer value2) {
            addCriterion("is_new_holder not between", value1, value2, "isNewHolder");
            return (Criteria) this;
        }

        public Criteria andChgCountIsNull() {
            addCriterion("chg_count is null");
            return (Criteria) this;
        }

        public Criteria andChgCountIsNotNull() {
            addCriterion("chg_count is not null");
            return (Criteria) this;
        }

        public Criteria andChgCountEqualTo(Integer value) {
            addCriterion("chg_count =", value, "chgCount");
            return (Criteria) this;
        }

        public Criteria andChgCountNotEqualTo(Integer value) {
            addCriterion("chg_count <>", value, "chgCount");
            return (Criteria) this;
        }

        public Criteria andChgCountGreaterThan(Integer value) {
            addCriterion("chg_count >", value, "chgCount");
            return (Criteria) this;
        }

        public Criteria andChgCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("chg_count >=", value, "chgCount");
            return (Criteria) this;
        }

        public Criteria andChgCountLessThan(Integer value) {
            addCriterion("chg_count <", value, "chgCount");
            return (Criteria) this;
        }

        public Criteria andChgCountLessThanOrEqualTo(Integer value) {
            addCriterion("chg_count <=", value, "chgCount");
            return (Criteria) this;
        }

        public Criteria andChgCountIn(List<Integer> values) {
            addCriterion("chg_count in", values, "chgCount");
            return (Criteria) this;
        }

        public Criteria andChgCountNotIn(List<Integer> values) {
            addCriterion("chg_count not in", values, "chgCount");
            return (Criteria) this;
        }

        public Criteria andChgCountBetween(Integer value1, Integer value2) {
            addCriterion("chg_count between", value1, value2, "chgCount");
            return (Criteria) this;
        }

        public Criteria andChgCountNotBetween(Integer value1, Integer value2) {
            addCriterion("chg_count not between", value1, value2, "chgCount");
            return (Criteria) this;
        }

        public Criteria andStockTypeIsNull() {
            addCriterion("stock_type is null");
            return (Criteria) this;
        }

        public Criteria andStockTypeIsNotNull() {
            addCriterion("stock_type is not null");
            return (Criteria) this;
        }

        public Criteria andStockTypeEqualTo(Integer value) {
            addCriterion("stock_type =", value, "stockType");
            return (Criteria) this;
        }

        public Criteria andStockTypeNotEqualTo(Integer value) {
            addCriterion("stock_type <>", value, "stockType");
            return (Criteria) this;
        }

        public Criteria andStockTypeGreaterThan(Integer value) {
            addCriterion("stock_type >", value, "stockType");
            return (Criteria) this;
        }

        public Criteria andStockTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("stock_type >=", value, "stockType");
            return (Criteria) this;
        }

        public Criteria andStockTypeLessThan(Integer value) {
            addCriterion("stock_type <", value, "stockType");
            return (Criteria) this;
        }

        public Criteria andStockTypeLessThanOrEqualTo(Integer value) {
            addCriterion("stock_type <=", value, "stockType");
            return (Criteria) this;
        }

        public Criteria andStockTypeIn(List<Integer> values) {
            addCriterion("stock_type in", values, "stockType");
            return (Criteria) this;
        }

        public Criteria andStockTypeNotIn(List<Integer> values) {
            addCriterion("stock_type not in", values, "stockType");
            return (Criteria) this;
        }

        public Criteria andStockTypeBetween(Integer value1, Integer value2) {
            addCriterion("stock_type between", value1, value2, "stockType");
            return (Criteria) this;
        }

        public Criteria andStockTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("stock_type not between", value1, value2, "stockType");
            return (Criteria) this;
        }

        public Criteria andPublishDateIsNull() {
            addCriterion("publish_date is null");
            return (Criteria) this;
        }

        public Criteria andPublishDateIsNotNull() {
            addCriterion("publish_date is not null");
            return (Criteria) this;
        }

        public Criteria andPublishDateEqualTo(String value) {
            addCriterion("publish_date =", value, "publishDate");
            return (Criteria) this;
        }

        public Criteria andPublishDateNotEqualTo(String value) {
            addCriterion("publish_date <>", value, "publishDate");
            return (Criteria) this;
        }

        public Criteria andPublishDateGreaterThan(String value) {
            addCriterion("publish_date >", value, "publishDate");
            return (Criteria) this;
        }

        public Criteria andPublishDateGreaterThanOrEqualTo(String value) {
            addCriterion("publish_date >=", value, "publishDate");
            return (Criteria) this;
        }

        public Criteria andPublishDateLessThan(String value) {
            addCriterion("publish_date <", value, "publishDate");
            return (Criteria) this;
        }

        public Criteria andPublishDateLessThanOrEqualTo(String value) {
            addCriterion("publish_date <=", value, "publishDate");
            return (Criteria) this;
        }

        public Criteria andPublishDateLike(String value) {
            addCriterion("publish_date like", value, "publishDate");
            return (Criteria) this;
        }

        public Criteria andPublishDateNotLike(String value) {
            addCriterion("publish_date not like", value, "publishDate");
            return (Criteria) this;
        }

        public Criteria andPublishDateIn(List<String> values) {
            addCriterion("publish_date in", values, "publishDate");
            return (Criteria) this;
        }

        public Criteria andPublishDateNotIn(List<String> values) {
            addCriterion("publish_date not in", values, "publishDate");
            return (Criteria) this;
        }

        public Criteria andPublishDateBetween(String value1, String value2) {
            addCriterion("publish_date between", value1, value2, "publishDate");
            return (Criteria) this;
        }

        public Criteria andPublishDateNotBetween(String value1, String value2) {
            addCriterion("publish_date not between", value1, value2, "publishDate");
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