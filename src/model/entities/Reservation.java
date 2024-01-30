package model.entities;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Reservation {
	
	private Integer roomNumber;
	private Date checkIn;
	private Date checkOut;
	
	
	//COLOQUEI COMO O SDF COMO STATIC PARA QUE NÃO SEJA INSTANCIADO UM NOVO PARA CADA RESERVATION QUE MINHA APLICAÇÃO TIVER.
	private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	public Reservation(Integer roomNumber, Date chekIn, Date checkOut) {
		this.roomNumber = roomNumber;
		this.checkIn = chekIn;
		this.checkOut = checkOut;
	}
	

	public Integer getRoomNumber() {
		return roomNumber;
	}
	

	public void setRoomNumber(Integer roomNumber) {
		this.roomNumber = roomNumber;
	}
	

	public Date getChekIn() {
		return checkIn;
	}


	public Date getCheckOut() {
		return checkOut;
	}

	
	// OS VALORES FORAM DO TIPO LONG, USEI O GETTIME PARA DEIXAR EM MILISSECONDS PARA QUE, POSTERIORMENTE, EU CONSEGUISSE CONVERTER EM DIAS.
	public long duration() {
		long diff = checkOut.getTime() - checkIn.getTime();
		return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);	
	}
	
	public String updateDates(Date checkIn, Date checkOut) {
		Date now = new Date();
		if (checkIn.before(now) || checkOut.before(now)) {
			return "Reservation date for update must be future dates";
		}
		
		if(!checkOut.after(checkIn)){
			return "Check-out date must be after check-in date";
		}
		this.checkIn = checkIn;
		this.checkOut = checkOut;
		return null;
	}
	
	@Override
	public String toString() {
		return "Room "
				+ roomNumber
				+ ", check-in: "
				+ ", check-out: "
				+ sdf.format(checkOut)
				+ ", "
				+ duration()
				+ " nights";
	}
	
	
}
