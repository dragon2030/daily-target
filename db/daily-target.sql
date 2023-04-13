CREATE DATABASE IF NOT EXISTS daily_target;
USE daily_target;

CREATE TABLE `daily_record` (
    `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
    `statistics_date` varchar DEFAULT NULL COMMENT '统计日期',
    `plan_target_id` varchar(32) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '目标计划id',
    `plan_target_des` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '目标计划内容（目标项间以“1、XXX。2、XXX。”格式，方便后期io截取统计',
    `plan_target_achievement` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '目标完成情况（目标项间以“1、XXX。2、XXX。”格式，方便后期io截取统计。数字对应计划项。“99、原因”表示额外项和特殊原因）',
    `life_feeling` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '人生感想',
    `diary_record_detail` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '日记',
    `remark` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '备注',
    `day_of_week` varchar(10) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '一周的周几',
    `day_percent_complete` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '日目标完成百分比',
    `week_percent_complete` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '周目标完成百分比',
    `create_time` datetime DEFAULT NULL COMMENT '创建时间',
    `create_by` varchar(32) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建人',
    `update_time` datetime DEFAULT NULL COMMENT '更新时间',
    `update_by` varchar(32) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '更新人',
    `deposit` decimal(8,2) DEFAULT NULL COMMENT '总积蓄金额',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='每日日记及目标完成记录表';

CREATE TABLE `plan_target` (
    `id` varchar(32) COLLATE utf8mb4_general_ci NOT NULL,
    `plan_target_des` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '目标计划内容（目标项间以“1、XXX。2、XXX。”格式，方便后期io截取统计）',
    `long_term_goal` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '远期目标对照',
    `target_thoughts` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '计划感想',
    `remark` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '备注',
    `status` char(1) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '状态(1->启用;0->停用)',
    `change_habit` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '改变习惯',
    `target_core_items` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '计划目标核心项',
    `create_time` datetime DEFAULT NULL COMMENT '创建时间',
    `create_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建人',
    `update_time` datetime DEFAULT NULL COMMENT '更新时间',
    `update_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '更新人',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='计划目标表';
