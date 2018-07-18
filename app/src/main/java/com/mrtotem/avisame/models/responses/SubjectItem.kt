package com.mrtotem.avisame.models.responses

/**
 * Created by Octavio on 31/01/2018.
 */
class SubjectItem<T>(key: String, error: SubjectError?, item: T?) {

    var error: SubjectError? = error
    var key: String? = key
    var item: T? = item
}