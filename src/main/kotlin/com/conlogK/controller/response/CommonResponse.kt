package com.conlogK.controller.response

data class CommonResponse(
    val code: String,
    val message: String,
    var validation: HashMap<String, String> = hashMapOf()
) {
}
