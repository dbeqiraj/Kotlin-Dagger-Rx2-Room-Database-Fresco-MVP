package com.dibeqiraj.cakeapp.mapper

import com.dibeqiraj.cakeapp.database.AppDatabase
import com.dibeqiraj.cakeapp.database.entity.Cake
import com.dibeqiraj.cakeapp.mvp.model.CakesResponse
import com.dibeqiraj.cakeapp.mvp.model.CakesResponseCakes
import javax.inject.Inject

class CakeMapper @Inject constructor() {

    fun mapCakes(db: AppDatabase, response: CakesResponse?): MutableList<Cake> {
        val cakeList: MutableList<Cake> = ArrayList()
        if (response != null ) {
            val responseCakes: Array<CakesResponseCakes> = response.cakes

            for (cake: CakesResponseCakes  in responseCakes){
                val myCake = Cake(cake.id, cake.title, cake.previewDescription, cake.detailDescription, cake.image)
                db.cakeDao().insert(myCake)
                cakeList.add(myCake)
            }
        }

        return cakeList
    }
}