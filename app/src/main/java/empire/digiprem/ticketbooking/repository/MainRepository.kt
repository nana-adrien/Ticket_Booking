package empire.digiprem.ticketbooking.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.Query
import com.google.firebase.database.ValueEventListener
import empire.digiprem.ticketbooking.domain.FlightModel
import empire.digiprem.ticketbooking.domain.LocationModel

class MainRepository {

   private val firebaseDatabase = FirebaseDatabase.getInstance()

   fun loadLocalisation() : LiveData<MutableList<LocationModel>> {
      val listData = MutableLiveData<MutableList<LocationModel>>()
      val ref = firebaseDatabase.getReference("Locations")
      ref.addValueEventListener(object : ValueEventListener {
         override fun onDataChange(snapshot : DataSnapshot) {
            val list = mutableListOf<LocationModel>()
            for (childSnapshot in snapshot.children) {
               val item = childSnapshot.getValue(LocationModel::class.java)
               item?.let { list.add(it) }
               listData.value = list
            }
         }

         override fun onCancelled(error : DatabaseError) {
            TODO("Not yet implemented")
         }
      })
      return listData
   }

   fun loadFiltered(from : String , to : String) : LiveData<MutableList<FlightModel>> {
      val listData = MutableLiveData<MutableList<FlightModel>>()
      val ref = firebaseDatabase.getReference("Flights")
      val query: Query =ref.orderByChild("from").equalTo(from)
      query.addValueEventListener(object : ValueEventListener {
         override fun onDataChange(snapshot : DataSnapshot) {
            val lists = mutableListOf<FlightModel>()
            for (childSnapshot in snapshot.children) {
               val item = childSnapshot.getValue(FlightModel::class.java)
               Log.e("ItemListScreen" , "items: $item")
               if (item!=null){
                  if (item.to == to){
                     item.let { lists.add(it) }
                  }
               }
               listData.value = lists
            }
         }

         override fun onCancelled(error : DatabaseError) {
            TODO("Not yet implemented")
         }
      })
      return listData

   }
}