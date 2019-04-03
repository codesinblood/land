CREATE TABLE topic ( PRIMARY KEY (`id`)
                    ,`id`            BIGINT     AUTO_INCREMENT
                    ,`name`          VARCHAR(30)
                    ,`description`   VARCHAR(100)
                    ,`display_order` BIGINT
                    ,`course_id`     BIGINT
                    ,KEY k_topic_course(`course_id`)
                    ,CONSTRAINT fk_topic_course
                    FOREIGN KEY (`course_id`) REFERENCES course (`id`)
                    );

CREATE TABLE test( PRIMARY KEY (`id`)
                  ,`id`            BIGINT      AUTO_INCREMENT
                  ,`topic_id`      BIGINT
                  ,`type`          VARCHAR(15)
                  ,`max_attempt`   INT
                  ,`pass_criteria` VARCHAR(20)
                  ,KEY k_test_topic(`topic_id`)
                  ,CONSTRAINT fk_test_topic
                  FOREIGN KEY (`topic_id`) REFERENCES topic (`id`)
                  );

CREATE TABLE sub_topic( PRIMARY KEY (`id`)
                       ,`id`                  BIGINT       AUTO_INCREMENT
                       ,`name`                VARCHAR(100)
                       ,`description`         VARCHAR(150)
                       ,`fastrack_duration`   TIME
                       ,`fulltrack_duration`  TIME
                       ,`topic_id`            BIGINT
                       ,`resource_id`         BIGINT
                       ,`user_id`             BIGINT
                       ,KEY k_sub_topic_topic(`topic_id`)
                       ,KEY k_sub_topic_resource(`resource_id`)
                       ,KEY k_sub_topic_user(`user_id`)
                       ,CONSTRAINT fk_sub_topic_topic
                       FOREIGN KEY (`topic_id`) REFERENCES topic (`id`)
                       ,CONSTRAINT fk_sub_topic_resource
                       FOREIGN KEY (`resource_id`) REFERENCES resource (`id`)
                       ,CONSTRAINT fk_sub_topic_user
                       FOREIGN KEY (`user_id`) REFERENCES user (`id`)
                       );                 

CREATE TABLE test_evaluation_param( PRIMARY KEY (`id`)
                                   ,`id`       BIGINT  AUTO_INCREMENT
                                   ,`test_id`  BIGINT
                                   ,`param_id` BIGINT
                                   ,KEY k_test_evaluation_param_test(`test_id`)
                                   ,KEY k_test_evaluation_param_evaluation_param(`param_id`)
                                   ,CONSTRAINT fk_test_evaluation_param_test
                                    FOREIGN KEY (`test_id`) REFERENCES test (`id`)
                                   ,CONSTRAINT fk_test_evaluation_param_evalaution_param
                                    FOREIGN KEY (`param_id`) REFERENCES evaluation_param (`id`)
                                   );
                                   
CREATE TABLE topic_sub_topic ( PRIMARY KEY (`id`)
                              ,`id` BIGINT AUTO_INCREMENT
                              ,`topic_id` BIGINT
                              ,`sub_topic_id` BIGINT
                              ,KEY k_topic_sub_topic_topic(`topic_id`)
                              ,KEY k_topic_sub_topic_sub_topic(`sub_topic_id`)
                              ,CONSTRAINT fk_topic_sub_topic_topic
                              FOREIGN KEY (`topic_id`) REFERENCES topic (`id`)
                              ,CONSTRAINT fk_topic_sub_topic_sub_topic
                              FOREIGN KEY (`sub_topic_id`) REFERENCES sub_topic (`id`)
                              );
                              
Create Table link( PRIMARY KEY (`id`)
                  ,`id`          BIGINT AUTO_INCREMENT
                  ,`description` VARCHAR(100)
                  ,`link`        VARCHAR(200)
                  ,`topic_id`    BIGINT
                  ,KEY k_link_topic(`topic_id`)
                  ,CONSTRAINT fk_link_topic
                  FOREIGN KEY (`topic_id`) REFERENCES topic (`id`)
                  );
                  
CREATE TABLE exercise ( PRIMARY KEY (`id`)
                       ,`id`                        INT          AUTO_INCREMENT
                       ,`name`                      VARCHAR(50)
                       ,`description`               VARCHAR(100)
                       ,`resource_id`               BIGINT
                       ,`fastrack_duration`        TIME
                       ,`fulltrack_duration`        TIME
                       ,`review_fulltrack_duration` TIME
                       ,`review_fastrack_duration`  TIME
                       ,`topic_id`                  BIGINT
                       ,`recap_fulltrack`           TIME
                       ,`recap_fastrack`           TIME
                       ,KEY k_exercise_resource(`resource_id`)
                       ,KEY k_exercise_sub_topic(`topic_id`)
                       ,CONSTRAINT fk_exercise_resource
                       FOREIGN KEY (`resource_id`) REFERENCES resource (`id`)
                       ,CONSTRAINT fk_exercise_topic
                       FOREIGN KEY (`topic_id`) REFERENCES topic (`id`)
                       );

                       CREATE TABLE sub_topic_tag ( PRIMARY KEY (`id`)
                            ,`id`          BIGINT   AUTO_INCREMENT 
                            ,`name`        VARCHAR(100)
                            ,`subtopic_id` BIGINT  
                            ,KEY k_sub_topic_tag_sub_topic(`subtopic_id`)
                            ,CONSTRAINT fk_sub_topic_tag_sub_topic
                             FOREIGN KEY (`subtopic_id`) REFERENCES sub_topic (`id`)
                            );
