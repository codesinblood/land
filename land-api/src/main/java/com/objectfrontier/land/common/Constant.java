package com.objectfrontier.land.common;

/**
 * @author karthik.n
 * @author gunasekaran.k
 * @since v1.0
 */

public interface Constant {

// Package details
    String BASE_PACKAGE                = "com.objectfrontier.land"   ;

// Database related details
    String SPRING_HIKARI               = "spring.datasource.hikari"  ;
    String ENTITY_MANAGER              = "entityManagerFactory"      ;
    String TRANSACTION_MANAGER         = "transactionManager"        ;

// Topic related details
    String TOPIC                       = "Topic"                     ;
    String TOPIC_TABLE                 = "topic"                     ;
    String ID                          = "id"                        ;
    String NAME                        = "name"                      ;
    String DESCRIPTION                 = "description"               ;
    String DISPLAY_ORDER               = "display_order"             ;
    String COURSE_ID                   = "course_id"                 ;
    String AUTHOR_ID                   = "author_id"                 ;
    String TOPIC_AUTHOR                = "topic_author"              ;

// Subtopic related details
    String SUBTOPIC                    = "Subtopic"                  ;
    String SUBTOPIC_TABLE              = "sub_topic"                 ;
    String FASTRACK_DURATION           = "fastrack_duration"         ;
    String FULLTRACK_DURATION          = "fulltrack_duration"        ;
    String TOPIC_ID                    = "topic_id"                  ;
    String RESOURCE_ID                 = "resource_id"               ;
    String USER_ID                     = "user_id"                   ;

// Test related details
    String TEST                        = "Test"                      ;
    String TEST_TABLE                  = "test"                      ;
    String TYPE                        = "type"                      ;
    String MAX_ATTEMPT                 = "max_attempt"               ;
    String PASS_CRITERIA               = "pass_criteria"             ;
    
// Reference related details
    String REFERENCE                   = "Reference"                 ;
    String REFERENCE_TABLE             = "reference"                 ;
    String LINK                        = "link"                      ;
    
// Exercise related details
    String EXERCISE                    = "Exercise"                  ;
    String EXERCISE_TABLE              = "exercise"                  ;
    String REVIEW_FULLTRACK_DURATION   = "review_fulltrack_duration" ;
    String REVIEW_FASTRACK_DURATION    = "review_fastrack_duration"  ;
    String RECAP_FULLTRACK             = "recap_fulltrack"           ;
    String RECAP_FASTRACK              = "recap_fastrack"            ;

// Evaluation related details
    String EVALUATION                  = "Evaluation"                ;
    String EVALUATION_TABLE            = "evaluation"                ;
    String EVALUATION_PARAM_TABLE      = "evaluation_param"          ;
    String EVALUATION_PARAM            = "EvaluationParam"           ;
    String EVALUATION_ID               = "evaluation_id"             ;
    String PARAM                       = "param"                     ;
    String EVALUATION_DESCRIPTION      = "description"               ;
    String EVALUATION_WEIGHTAGE        = "weightage"                 ;
    String MAX_MARK                    = "max_mark"                  ;
    String EVALUATION_MAX_ATTEMPT      = "max_attempt"               ;
    String EVALUATION_PASS_CRITERIA    = "pass_criteria"             ;
    String FULLTRACK_RECAP_DURATION    = "fulltrack_recap_duration"  ;
    String FULLTRACK_REVIEW_DURATION   = "fulltrack_review_duration" ;
    String FASTRACK_RECAP_DURATION     = "fastrack_recap_duration"   ;
    String FASTRACK_REVIEW_DURATION    = "fastrack_review_duration"  ;
    String EVALUATION_PARAM_LIST       = "evaluation_param_list"     ;

// Test Evaluation param details
    String TEST_EVALUATION_PARAM       = "TestEvaluationParam"       ;
    String TEST_EVALUATION_PARAM_TABLE = "exercise"                  ;
    String TEST_ID                     = "test_id"                   ;
    String PARAM_ID                    = "param_id"                  ;

// SubtopicTag details
    String SUBTOPIC_TAG                = "SubtopicTag"               ;
    String SUBTOPIC_TAG_TABLE          = "sub_topic_tag"             ;
    String SUBTOPIC_ID                 = "subtopic_id"               ;

// Online test related details
    String ONLINE_TEST                 = "OnlineTest"                ;
    String ONLINE_TEST_TABLE           = "online_test"               ;
    String PASS_PERCENTAGE             = "pass_percentage"           ;
    String FASTRACK_MAX_ATTEMPT        = "fastrack_max_attempt"      ;
    String FULLTRACK_MAX_ATTEMPT       = "fulltrack_max_attempt"     ;

// Course related details 
    String COURSE                      = "Course"                    ;
    String COURSE_TABLE                = "course"                    ;
    String IS_SELF_ASSIGNABLE          = "is_self_assignable"        ;
    String CREATED_DATE                = "created_date"              ;
    String AUTHOR_FINAL                = "is_author_final"           ;
    String PUBLISHED                   = "is_publish"                ;
    String USER_ROLE_COURSE            = "user_role_course"          ;
}
