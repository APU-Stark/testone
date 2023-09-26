package com.sagar.testprojone

data class SaveUserRequest(
    @field:javax.validation.constraints.NotNull(message = "Please provide Name")
    @field:javax.validation.constraints.NotBlank(message = "Please provide Name")
    var name: String = ""
)