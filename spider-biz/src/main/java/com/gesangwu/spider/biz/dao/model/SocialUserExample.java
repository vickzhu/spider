package com.gesangwu.spider.biz.dao.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SocialUserExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected int offset = -1;

    protected int rows = -1;

    public SocialUserExample() {
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

        public Criteria andSocialMediaIsNull() {
            addCriterion("social_media is null");
            return (Criteria) this;
        }

        public Criteria andSocialMediaIsNotNull() {
            addCriterion("social_media is not null");
            return (Criteria) this;
        }

        public Criteria andSocialMediaEqualTo(String value) {
            addCriterion("social_media =", value, "socialMedia");
            return (Criteria) this;
        }

        public Criteria andSocialMediaNotEqualTo(String value) {
            addCriterion("social_media <>", value, "socialMedia");
            return (Criteria) this;
        }

        public Criteria andSocialMediaGreaterThan(String value) {
            addCriterion("social_media >", value, "socialMedia");
            return (Criteria) this;
        }

        public Criteria andSocialMediaGreaterThanOrEqualTo(String value) {
            addCriterion("social_media >=", value, "socialMedia");
            return (Criteria) this;
        }

        public Criteria andSocialMediaLessThan(String value) {
            addCriterion("social_media <", value, "socialMedia");
            return (Criteria) this;
        }

        public Criteria andSocialMediaLessThanOrEqualTo(String value) {
            addCriterion("social_media <=", value, "socialMedia");
            return (Criteria) this;
        }

        public Criteria andSocialMediaLike(String value) {
            addCriterion("social_media like", value, "socialMedia");
            return (Criteria) this;
        }

        public Criteria andSocialMediaNotLike(String value) {
            addCriterion("social_media not like", value, "socialMedia");
            return (Criteria) this;
        }

        public Criteria andSocialMediaIn(List<String> values) {
            addCriterion("social_media in", values, "socialMedia");
            return (Criteria) this;
        }

        public Criteria andSocialMediaNotIn(List<String> values) {
            addCriterion("social_media not in", values, "socialMedia");
            return (Criteria) this;
        }

        public Criteria andSocialMediaBetween(String value1, String value2) {
            addCriterion("social_media between", value1, value2, "socialMedia");
            return (Criteria) this;
        }

        public Criteria andSocialMediaNotBetween(String value1, String value2) {
            addCriterion("social_media not between", value1, value2, "socialMedia");
            return (Criteria) this;
        }

        public Criteria andUsernameIsNull() {
            addCriterion("username is null");
            return (Criteria) this;
        }

        public Criteria andUsernameIsNotNull() {
            addCriterion("username is not null");
            return (Criteria) this;
        }

        public Criteria andUsernameEqualTo(String value) {
            addCriterion("username =", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameNotEqualTo(String value) {
            addCriterion("username <>", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameGreaterThan(String value) {
            addCriterion("username >", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameGreaterThanOrEqualTo(String value) {
            addCriterion("username >=", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameLessThan(String value) {
            addCriterion("username <", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameLessThanOrEqualTo(String value) {
            addCriterion("username <=", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameLike(String value) {
            addCriterion("username like", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameNotLike(String value) {
            addCriterion("username not like", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameIn(List<String> values) {
            addCriterion("username in", values, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameNotIn(List<String> values) {
            addCriterion("username not in", values, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameBetween(String value1, String value2) {
            addCriterion("username between", value1, value2, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameNotBetween(String value1, String value2) {
            addCriterion("username not between", value1, value2, "username");
            return (Criteria) this;
        }

        public Criteria andHeaderImageIsNull() {
            addCriterion("header_image is null");
            return (Criteria) this;
        }

        public Criteria andHeaderImageIsNotNull() {
            addCriterion("header_image is not null");
            return (Criteria) this;
        }

        public Criteria andHeaderImageEqualTo(String value) {
            addCriterion("header_image =", value, "headerImage");
            return (Criteria) this;
        }

        public Criteria andHeaderImageNotEqualTo(String value) {
            addCriterion("header_image <>", value, "headerImage");
            return (Criteria) this;
        }

        public Criteria andHeaderImageGreaterThan(String value) {
            addCriterion("header_image >", value, "headerImage");
            return (Criteria) this;
        }

        public Criteria andHeaderImageGreaterThanOrEqualTo(String value) {
            addCriterion("header_image >=", value, "headerImage");
            return (Criteria) this;
        }

        public Criteria andHeaderImageLessThan(String value) {
            addCriterion("header_image <", value, "headerImage");
            return (Criteria) this;
        }

        public Criteria andHeaderImageLessThanOrEqualTo(String value) {
            addCriterion("header_image <=", value, "headerImage");
            return (Criteria) this;
        }

        public Criteria andHeaderImageLike(String value) {
            addCriterion("header_image like", value, "headerImage");
            return (Criteria) this;
        }

        public Criteria andHeaderImageNotLike(String value) {
            addCriterion("header_image not like", value, "headerImage");
            return (Criteria) this;
        }

        public Criteria andHeaderImageIn(List<String> values) {
            addCriterion("header_image in", values, "headerImage");
            return (Criteria) this;
        }

        public Criteria andHeaderImageNotIn(List<String> values) {
            addCriterion("header_image not in", values, "headerImage");
            return (Criteria) this;
        }

        public Criteria andHeaderImageBetween(String value1, String value2) {
            addCriterion("header_image between", value1, value2, "headerImage");
            return (Criteria) this;
        }

        public Criteria andHeaderImageNotBetween(String value1, String value2) {
            addCriterion("header_image not between", value1, value2, "headerImage");
            return (Criteria) this;
        }

        public Criteria andRmUsernameIsNull() {
            addCriterion("rm_username is null");
            return (Criteria) this;
        }

        public Criteria andRmUsernameIsNotNull() {
            addCriterion("rm_username is not null");
            return (Criteria) this;
        }

        public Criteria andRmUsernameEqualTo(String value) {
            addCriterion("rm_username =", value, "rmUsername");
            return (Criteria) this;
        }

        public Criteria andRmUsernameNotEqualTo(String value) {
            addCriterion("rm_username <>", value, "rmUsername");
            return (Criteria) this;
        }

        public Criteria andRmUsernameGreaterThan(String value) {
            addCriterion("rm_username >", value, "rmUsername");
            return (Criteria) this;
        }

        public Criteria andRmUsernameGreaterThanOrEqualTo(String value) {
            addCriterion("rm_username >=", value, "rmUsername");
            return (Criteria) this;
        }

        public Criteria andRmUsernameLessThan(String value) {
            addCriterion("rm_username <", value, "rmUsername");
            return (Criteria) this;
        }

        public Criteria andRmUsernameLessThanOrEqualTo(String value) {
            addCriterion("rm_username <=", value, "rmUsername");
            return (Criteria) this;
        }

        public Criteria andRmUsernameLike(String value) {
            addCriterion("rm_username like", value, "rmUsername");
            return (Criteria) this;
        }

        public Criteria andRmUsernameNotLike(String value) {
            addCriterion("rm_username not like", value, "rmUsername");
            return (Criteria) this;
        }

        public Criteria andRmUsernameIn(List<String> values) {
            addCriterion("rm_username in", values, "rmUsername");
            return (Criteria) this;
        }

        public Criteria andRmUsernameNotIn(List<String> values) {
            addCriterion("rm_username not in", values, "rmUsername");
            return (Criteria) this;
        }

        public Criteria andRmUsernameBetween(String value1, String value2) {
            addCriterion("rm_username between", value1, value2, "rmUsername");
            return (Criteria) this;
        }

        public Criteria andRmUsernameNotBetween(String value1, String value2) {
            addCriterion("rm_username not between", value1, value2, "rmUsername");
            return (Criteria) this;
        }

        public Criteria andCreateDateIsNull() {
            addCriterion("create_date is null");
            return (Criteria) this;
        }

        public Criteria andCreateDateIsNotNull() {
            addCriterion("create_date is not null");
            return (Criteria) this;
        }

        public Criteria andCreateDateEqualTo(Date value) {
            addCriterion("create_date =", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotEqualTo(Date value) {
            addCriterion("create_date <>", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateGreaterThan(Date value) {
            addCriterion("create_date >", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateGreaterThanOrEqualTo(Date value) {
            addCriterion("create_date >=", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateLessThan(Date value) {
            addCriterion("create_date <", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateLessThanOrEqualTo(Date value) {
            addCriterion("create_date <=", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateIn(List<Date> values) {
            addCriterion("create_date in", values, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotIn(List<Date> values) {
            addCriterion("create_date not in", values, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateBetween(Date value1, Date value2) {
            addCriterion("create_date between", value1, value2, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotBetween(Date value1, Date value2) {
            addCriterion("create_date not between", value1, value2, "createDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateIsNull() {
            addCriterion("update_date is null");
            return (Criteria) this;
        }

        public Criteria andUpdateDateIsNotNull() {
            addCriterion("update_date is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateDateEqualTo(Date value) {
            addCriterion("update_date =", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateNotEqualTo(Date value) {
            addCriterion("update_date <>", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateGreaterThan(Date value) {
            addCriterion("update_date >", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateGreaterThanOrEqualTo(Date value) {
            addCriterion("update_date >=", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateLessThan(Date value) {
            addCriterion("update_date <", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateLessThanOrEqualTo(Date value) {
            addCriterion("update_date <=", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateIn(List<Date> values) {
            addCriterion("update_date in", values, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateNotIn(List<Date> values) {
            addCriterion("update_date not in", values, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateBetween(Date value1, Date value2) {
            addCriterion("update_date between", value1, value2, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateNotBetween(Date value1, Date value2) {
            addCriterion("update_date not between", value1, value2, "updateDate");
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