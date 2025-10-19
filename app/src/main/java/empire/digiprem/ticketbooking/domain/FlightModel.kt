package empire.digiprem.ticketbooking.domain

import java.io.Serializable

data class FlightModel (
        val airlineLogo:String="" ,
        val airlineName:String="" ,
        val arriveTime:String="" ,
        val classSeat:String="" ,
        var date:String="" ,
        var from:String="" ,
        var fromShort:String="" ,
        var numberSeat: Int=0 ,
        var price:Double=0.0 ,
        var passenger:String="" ,
        var seats:String="" ,
        var reservedSeats:String="" ,
        var time:String="" ,
        var to:String="" ,
        var toShort:String="" ,
): Serializable


