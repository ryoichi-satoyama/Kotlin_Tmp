package com.example.kotlin_tmp

import com.google.gson.annotations.SerializedName

data class Departments(
    @SerializedName("DepartmentLocations")
    var departmentLocations: List<Any>,
    @SerializedName("ID")
    var id: Int,
    @SerializedName("Name")
    var name: String
) {
    override fun toString(): String {
        return name
    }
}