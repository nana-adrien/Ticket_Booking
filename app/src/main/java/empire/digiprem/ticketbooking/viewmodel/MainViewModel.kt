package empire.digiprem.ticketbooking.viewmodel

import androidx.lifecycle.LiveData
import empire.digiprem.ticketbooking.domain.FlightModel
import empire.digiprem.ticketbooking.domain.LocationModel
import empire.digiprem.ticketbooking.repository.MainRepository

class MainViewModel {
   private val repository= MainRepository()

   fun loadLocalisation(): LiveData<MutableList<LocationModel>> {
      return repository.loadLocalisation()
   }

   fun loadFiltered(from: String, to: String): LiveData<MutableList<FlightModel>> {
      return repository.loadFiltered(from, to)
   }
}

