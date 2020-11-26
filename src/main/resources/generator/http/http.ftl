### Login and generate token
POST {{URL}}/login
Content-Type: application/json

{
    "username": "{{Username}}",
    "password": "{{Password}}"
}

> {%
    client.global.set("auth_token", response.body.access_token);
%}

<#assign getParamsEmbedded = '' />
<#assign getParamsCriteria = '' />

<#assign entityArry = [] />
<#assign entityParamsFlag = false />
<#assign listParamsFlag = false />
<#assign numberParamsFlag = false />
<#assign stringParamsFlag = false />
<#assign dateTimeParamsFlag = false />
<#list entity.fields as fields>
    <#if fields.type.name == "Entity">
        <#if fields.name != "creator" && fields.name != "modifier">
            <#assign entityArry = entityArry+[fields.name] />
        </#if>
        <#if !entityParamsFlag >
            <#if getParamsCriteria?length gt 0>
                <#assign getParamsCriteria = getParamsCriteria + "&" />
            </#if>
            <#assign getParamsCriteria = getParamsCriteria + fields.name + "." + "id=1" />
            <#assign entityParamsFlag = true />
        </#if>
    <#elseif fields.type.name == "List">
        <#assign entityArry = entityArry+[fields.name] />
    <#elseif fields.type.name == "Int" || fields.type.name == "Long" || fields.type.name == "Double">
        <#if !numberParamsFlag >
            <#if getParamsCriteria?length gt 0>
                <#assign getParamsCriteria = getParamsCriteria + "&" />
            </#if>
            <#assign getParamsCriteria = getParamsCriteria + fields.name + "_between=1,10&" + fields.name + "_in=1,2,3&" + fields.name + "_eq=1" />
            <#assign numberParamsFlag = true />
        </#if>
    <#elseif fields.type.name == "String">
        <#if !stringParamsFlag >
            <#if getParamsCriteria?length gt 0>
                <#assign getParamsCriteria = getParamsCriteria + "&" />
            </#if>
            <#assign getParamsCriteria = getParamsCriteria + fields.name + "_like=Test&" + fields.name + "_eq=Test" />
            <#assign stringParamsFlag = true />
        </#if>
    </#if>
</#list>

<#list entityArry as entityItem>
    <#if entityArry?size gt 0 && entityItem_index == 0>
        <#assign getParamsEmbedded = getParamsEmbedded + "embedded=" />
    </#if>
    <#assign getParamsEmbedded = getParamsEmbedded + entityItem />
    <#if entityItem_index lt entityArry?size-1 && entityArry?size gt 1>
        <#assign getParamsEmbedded = getParamsEmbedded + "," />
    </#if>
</#list>

### Get ${entity.name}
GET  {{URL}}/${entity.lowerHyphenName}
Authorization: Bearer {{auth_token}}


### Get ${entity.name}
GET  {{URL}}/${entity.lowerHyphenName}<#if getParamsEmbedded?length gt 0>?${getParamsEmbedded}</#if>
Authorization: Bearer {{auth_token}}


### Get ${entity.name}
GET  {{URL}}/${entity.lowerHyphenName}?${getParamsCriteria}<#if getParamsEmbedded?length gt 0>&${getParamsEmbedded}</#if>
Authorization: Bearer {{auth_token}}


### Add ${entity.name}
POST  {{URL}}/${entity.lowerHyphenName}
Authorization: Bearer {{auth_token}}
Content-Type: application/json

{
<#list entity.fields as fields>
    <#if fields.type.name == "String">
    "${fields.name}" : "Test ${fields.name} of ${entity.name}",
    <#elseif fields.type.name == "Int" || fields.type.name == "Long" >
        <#if fields.name != "id">
    "${fields.name}" : 1,
        </#if>
    <#elseif fields.type.name == "Double">
    "${fields.name}" : 1.1,
    <#elseif fields.type.name == "ZonedDateTime">
        <#if fields.name != "createdAt" && fields.name != "updatedAt" && fields.name != "deletedAt" >
    "${fields.name}" : "${.now?string('yyyy-MM-dd hh:mm:ss')}",
        </#if>
    <#elseif fields.type.name == "Entity">
        <#if fields.name != "creator" && fields.name != "modifier">
    "${fields.name}" : {"id": 1},
        </#if>
    <#elseif fields.type.name == "List">
    "${fields.name}" : [{"id": 1}],
    <#else>
    "${fields.name}" : "Others, you may update this value manually by yourself, field type is ${fields.type.name}",
    </#if>
    <#if fields_index == entity.fields?size-1>
    "id" : 1
    </#if>
</#list>
}

### Update ${entity.name}
PUT  {{URL}}/${entity.lowerHyphenName}/1
Authorization: Bearer {{auth_token}}
Content-Type: application/json

{
<#list entity.fields as fields>
    <#if fields.type.name == "String">
    "${fields.name}" : "Test ${fields.name} of ${entity.name}",
    <#elseif fields.type.name == "Int" || fields.type.name == "Long" >
        <#if fields.name != "id">
    "${fields.name}" : 1,
        </#if>
    <#elseif fields.type.name == "Double">
    "${fields.name}" : 1.1,
    <#elseif fields.type.name == "ZonedDateTime">
        <#if fields.name != "createdAt" && fields.name != "updatedAt" && fields.name != "deletedAt" >
    "${fields.name}" : "${.now?string('yyyy-MM-dd hh:mm:ss')}",
        </#if>
    <#elseif fields.type.name == "Entity">
        <#if fields.name != "creator" && fields.name != "modifier">
    "${fields.name}" : {"id": 1},
        </#if>
    <#elseif fields.type.name == "List">
    "${fields.name}" : [{"id": 1}],
    <#else>
    "${fields.name}" : "Others, you may update this value manually by yourself, field type is ${fields.type.name}",
    </#if>
    <#if fields_index == entity.fields?size-1>
    "id" : 1
    </#if>
</#list>
}


### Delete ${entity.name}
DELETE  {{URL}}/${entity.lowerHyphenName}/1
Authorization: Bearer {{auth_token}}
Content-Type: application/json
