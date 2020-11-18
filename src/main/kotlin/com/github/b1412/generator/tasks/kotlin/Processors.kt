package com.github.b1412.generator.tasks.kotlin

import com.github.b1412.generator.entity.CodeEntity
import com.github.b1412.generator.entity.CodeProject
import com.github.b1412.generator.task.Task
import com.github.b1412.generator.tasks.kotlin.permissions.*
import java.sql.ResultSet
import java.sql.Statement

fun projectPermissionProcessor(map: MutableMap<String, String>): (Task, CodeProject) -> Map<String, Any> {
    return { task, project ->
        val allPermissionSqlList = mutableListOf<String>()
        val allRolePermissionSqlList = mutableListOf<String>()
        val allRolePermissionRuleSqlList = mutableListOf<String>()

        project.entities.forEach {
            if (task.ignoreEntities.any { name -> name == it.name }) {
                return@forEach
            }
            getConnection(map)
            val stmt: Statement? = null
            val resultSet: ResultSet? = null
            val roleList = getRoles(getResultSet("role", stmt, resultSet))
            val ruleList = getRules(getResultSet("rule", stmt, resultSet))
            val permissionList = getPermissions(it.name, it.code.toLong())
            val rolePermissionList = getRolePermissions(roleList, permissionList)
            val rolePermissionRuleList = getRolePermissionRule(rolePermissionList, ruleList, it.permissions)
            val permissionSqlList = getPermissionSql(permissionList)
            val rolePermissionSqlList = getRolePermissionSql(rolePermissionList)
            val rolePermissionRuleSqlList = getRolePermissionRuleSql(rolePermissionRuleList)

            allPermissionSqlList.addAll(permissionSqlList)
            allRolePermissionSqlList.addAll(rolePermissionSqlList)
            allRolePermissionRuleSqlList.addAll(rolePermissionRuleSqlList)
        }
        mutableMapOf(
                "allPermissionSqlList" to allPermissionSqlList,
                "allRolePermissionSqlList" to allRolePermissionSqlList,
                "allRolePermissionRuleSqlList" to allRolePermissionRuleSqlList
        )

    }
}

fun entityPermissionProcessor(map: MutableMap<String, String>): (Task, CodeEntity) -> Map<String, Any?> {
    return { _, entity ->
        getConnection(map)
        val stmt: Statement? = null
        val resultSet: ResultSet? = null
        val roleList = getRoles(getResultSet("role", stmt, resultSet))
        val ruleList = getRules(getResultSet("rule", stmt, resultSet))
        val permissionList = getPermissions(entity.name, entity.code.toLong())
        val rolePermissionList = getRolePermissions(roleList, permissionList)
        val rolePermissionRuleList = getRolePermissionRule(rolePermissionList, ruleList, entity.permissions)
        val permissionSqlList = getPermissionSql(permissionList)
        val rolePermissionSqlList = getRolePermissionSql(rolePermissionList)
        val rolePermissionRuleSqlList = getRolePermissionRuleSql(rolePermissionRuleList)

        mutableMapOf(
                "permissionSqlList" to permissionSqlList,
                "rolePermissionSqlList" to rolePermissionSqlList,
                "rolePermissionRuleSqlList" to rolePermissionRuleSqlList
        )

    }
}


