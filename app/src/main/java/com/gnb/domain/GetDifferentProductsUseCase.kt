package com.gnb.domain

import com.gnb.data.repository.GnbRepository
import javax.inject.Inject

class GetDifferentProductsUseCase @Inject constructor(private val repository: GnbRepository) {

    suspend operator fun invoke(): List<String> {
        return repository.getDifferentsProducts()

    }

}