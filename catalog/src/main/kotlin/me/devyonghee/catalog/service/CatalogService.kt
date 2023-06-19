package me.devyonghee.catalog.service

import me.devyonghee.catalog.controller.CatalogController.CatalogResponse
import me.devyonghee.catalog.domain.CatalogRepository
import org.springframework.stereotype.Service

@Service
class CatalogService(
    private val catalogRepository: CatalogRepository
) {

    fun catalogs(): Collection<CatalogResponse> {
        return catalogRepository.findAll().map { CatalogResponse(it) }
    }
}
