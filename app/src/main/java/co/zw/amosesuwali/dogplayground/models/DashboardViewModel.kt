package co.zw.amosesuwali.dogplayground.models

import android.database.sqlite.SQLiteDatabase.create
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import co.zw.amosesuwali.dogplayground.data.FavBreedIconListAdapter
import co.zw.amosesuwali.dogplayground.database.favBreed.FavBreedDao
import co.zw.amosesuwali.dogplayground.database.favBreed.FavBreedEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class DashboardViewModel(private val favBreedDao: FavBreedDao) : ViewModel() {

    private var _favouriteDogBreeds = MutableLiveData<List<BreedDetailModel>>()
    val favouriteDogBreeds = _favouriteDogBreeds
    val favBreedsListAdapter= FavBreedIconListAdapter()
    val tempUrl="https://images.dog.ceo/breeds/terrier-irish/n02093991_403.jpg"
    init {
//       FavBreedsViewModelFactory(favBreedDao)
//        GlobalScope.launch(Dispatchers.IO) {
//            favBreedDao.insertAll(
//                FavBreedEntity(0,"Pitbull",tempUrl),
//                FavBreedEntity(1,"Rotwiller",tempUrl),
//                FavBreedEntity(2,"Africa",tempUrl), )
//        }

        _favouriteDogBreeds.value=getSavedFavouriteBreeds()
    }

    private fun getSavedFavouriteBreedsn() {
        _favouriteDogBreeds.value =  mutableListOf(
            BreedDetailModel("Pitbull",tempUrl),
            BreedDetailModel("Rotwiller",tempUrl),
            BreedDetailModel("Africa",tempUrl),
            BreedDetailModel("Chihuahua",tempUrl),
        )
    }
//    private fun getSavedFavouriteBreeds(){
//
//        _favouriteDogBreeds.value = favBreedDao.getSavedFavBreeds()
//
//    }
     fun getSavedFavouriteBreeds() : List<BreedDetailModel> = favBreedDao.getSavedFavBreeds()

}
class DashboardViewModelFactory(
    private val favBreedDao: FavBreedDao
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DashboardViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return DashboardViewModel(favBreedDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
