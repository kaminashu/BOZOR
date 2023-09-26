package com.example.d2android100.data

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.example.d2android100.data.db.AppDatabase
import com.example.d2android100.domain.ShopItem
import com.example.d2android100.domain.ShopItemRepository

class ShopListItemRepositoryImpl(application: Application) : ShopItemRepository {
    private val ld = MutableLiveData<List<ShopItem>>()

    private val mapper = Mapper()
    private val db: AppDatabase = AppDatabase.getInstance(application)

    override  fun addShopItem(shopItem: ShopItem) {
            db.shopItemDao()
                .addShopItem(shopItem)
    }

    override fun editShopItem(shopItem: ShopItem) {
        db.shopItemDao()
                .addShopItem(shopItem)
    }

    override fun deleteShopItem(shopItem: ShopItem) {
        db.shopItemDao().deleteShopItem(shopItem.id)
    }

    override fun getShopItemList(): LiveData<List<ShopItem>> {

        return  MediatorLiveData<List<ShopItem>>().apply {
            addSource(db.shopItemDao().getShopItemList()) {
                value = it
            }
        }
    }

    override fun getShopItemById(id: Int): ShopItem =
        db.shopItemDao().getShopItemById(id)
}