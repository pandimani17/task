package com.task.todo.domain.use_case

import com.task.todo.common.Constants
import javax.inject.Inject

class ValidateDescriptionUSeCase @Inject constructor(

) {

    operator fun invoke(description: String): ValidationResult {
        return if (description.isEmpty())
            ValidationResult(successful = false, errorMessage = Constants.descriptionError)
        else
            ValidationResult(successful = true)

    }
}