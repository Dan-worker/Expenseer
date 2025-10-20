package com.dprog.category.repository

import com.dprog.category.model.Category
import com.dprog.category.model.CategoryType
import com.dprog.database.dao.CategoryDao
import com.dprog.database.entity.CategoryEntity
import java.util.UUID
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class CategoryRepositoryImpl(
    private val dao: CategoryDao,
) : CategoryRepository {
    override fun getAllCategories(): Flow<List<Category>> =
        dao.getAllCategories().map { list -> list.map { it.toDomain() } }

    override fun getCategoriesByType(type: CategoryType): Flow<List<Category>> =
        dao.getCategoriesByType(type.toString()).map { list -> list.map { it.toDomain() } }

    override suspend fun getCategoryById(id: String): Category? = dao.getCategoryById(id)?.toDomain()

    override suspend fun addCategory(category: Category) {
        dao.insertCategory(category.toEntity())
    }

    override suspend fun addDefaultCategories() {
        val defaultCategories =
            listOf(
                Category(UUID.randomUUID().toString(), "Food", "🍔", "#FF6B6B", CategoryType.EXPENSE),
                Category(UUID.randomUUID().toString(), "Transport", "🚗", "#4ECDC4", CategoryType.EXPENSE),
                Category(UUID.randomUUID().toString(), "Shopping", "🛍️", "#FFD93D", CategoryType.EXPENSE),
                Category(UUID.randomUUID().toString(), "Entertainment", "🎬", "#95E1D3", CategoryType.EXPENSE),
                Category(UUID.randomUUID().toString(), "Bills", "📄", "#F38181", CategoryType.EXPENSE),
                Category(UUID.randomUUID().toString(), "Healthcare", "🏥", "#AA96DA", CategoryType.EXPENSE),
                Category(UUID.randomUUID().toString(), "Salary", "💰", "#6BCF7F", CategoryType.INCOME),
                Category(UUID.randomUUID().toString(), "Business", "💼", "#4A90E2", CategoryType.INCOME),
                Category(UUID.randomUUID().toString(), "Investments", "📈", "#F5A623", CategoryType.INCOME),
                Category(UUID.randomUUID().toString(), "Other", "📦", "#9B9B9B", CategoryType.INCOME),
            )
        dao.insertCategories(defaultCategories.map { it.toEntity() })
    }

    private fun CategoryEntity.toDomain() =
        Category(
            id = id,
            name = name,
            icon = icon,
            color = color,
            type = CategoryType.fromString(type),
        )

    private fun Category.toEntity() =
        CategoryEntity(
            id = id,
            name = name,
            icon = icon,
            color = color,
            type = type.toString(),
        )
}
